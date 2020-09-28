package org.example.domain;

import io.ebean.*;
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
}
