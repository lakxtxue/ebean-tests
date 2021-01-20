package org.example.domain;

import io.ebean.*;
import io.ebean.annotation.TxIsolation;
import io.ebean.config.DatabaseConfig;

import org.example.domain.query.QConfig;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class EBeanTests {
	
	private Database database;

	@Before
	public void before(){
		DatabaseConfig config = new DatabaseConfig();
		config.loadFromProperties();
		config.setName("db");
		config.setDefaultServer(true);
		config.setDataTimeZone("GMT+8:00");
//		config.setDisableL2Cache(true);
		database = DatabaseFactory.create(config);
	}

	@Test
	public void testLazyLoad(){
		DB.script().run("/test.sql");
		// Please execute the script in [test.sql] every time you execute the test to reset the data
		Config c1 = new QConfig().setUseCache(true).setId("config_1").findOne();
		List<ConfCp> confCpList = c1.getConfCpList();
		if(confCpList != null && !confCpList.isEmpty()){
			List<ConfCpCon> conList = confCpList.get(0).getConList();
			if (!conList.isEmpty()) {
				conList.get(0);
			}
		}
		c1.setConfigName("c1");

		Config c2 = new QConfig().setUseCache(true).setId("config_1").findOne();
		ConfCp confCp = c2.getConfCpList().get(0);

		ConfCpCon con = new ConfCpCon();
		con.setId("newCon");
		con.setEnabled("N");
		confCp.getConList().clear();
		confCp.getConList().add(con);

		c2.getConfCpList().clear();
		c2.getConfCpList().add(confCp);


		c2.save();

		c1.save();



		Config config = new QConfig().setUseCache(true).setId("config_1").findOne();
		List<ConfCp> confCpList1 = config.getConfCpList();
		if(confCpList1 != null && !confCpList1.isEmpty()){
			List<ConfCpCon> conList = confCpList1.get(0).getConList();
			if (conList != null) {
				for (ConfCpCon confCpCon : conList) {
					// An exception will be thrown here
					// EntityNotFoundException: Bean not found during lazy load or refresh
					System.out.println(confCpCon.getId()+":"+confCpCon.getEnabled());
				}
			}
		}

	}

	@Test
	public void textMysqlLazyLoad() throws Exception {
		DB.script().run("/test.sql");
		Database database = DB.getDefault();

		// 1 start a transaction for update
		Transaction transactionA = database.beginTransaction(TxIsolation.REPEATABLE_READ);
		Config con = new QConfig().setUseCache(true).usingTransaction(transactionA)
				.setId("config_1").findOne();
		ConfCp confCp = con.getConfCpList().get(0);
		confCp.getConList().get(0).getEnabled();

		// 2 delete data
		ConfCpCon confCpCon = new ConfCpCon();
		confCpCon.setId("newCon");
		confCpCon.setEnabled("N");
		confCpCon.setConfCp(confCp);
		confCp.getConList().clear();
		confCp.getConList().add(confCpCon);

		Thread t1 = new Thread(()->{
			// 4 查询 config_1
			Transaction transactionB = database.beginTransaction(TxIsolation.REPEATABLE_READ);
			Config con1 = new QConfig().setUseCache(true).usingTransaction(transactionB)
					.setId("config_1").findOne();
			try {
				Thread thread1 = Thread.currentThread();
				synchronized (thread1) {
					thread1.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 6 Lazy load
			con1.getConfCpList().size();
			con1.getConfCpList().get(0).getConList().size();
		});

		// 3 start another transaction for query by starting a thread
		t1.start();
		Thread.sleep(2000);// sleep the current thread to let the [t1] execute first

		// 5 commit the transaction and clear the cache
		con.save(transactionA);
		transactionA.commit();
		Thread.sleep(2000);
		synchronized (t1) {
			t1.notify();
		}
		Thread.sleep(2000);


		con = new QConfig().setUseCache(true).setId("config_1").findOne();
		ConfCpCon confCpCon1 = con.getConfCpList().get(0).getConList().get(0);
		// 7
		// An exception will be thrown here
		// EntityNotFoundException: Bean not found during lazy load or refresh
		confCpCon1.getEnabled();
	}
}
