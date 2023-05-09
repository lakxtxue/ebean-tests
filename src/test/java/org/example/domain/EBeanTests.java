package org.example.domain;

import io.ebean.*;
import io.ebean.config.DatabaseConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class EBeanTests {
	
	private Database database;

	@Before
	public void before(){
		DatabaseConfig config = new DatabaseConfig();
		config.loadFromProperties();
		config.setName("db");
		config.setDefaultServer(true);
		config.setDataTimeZone("GMT+8:00");
		database = DatabaseFactory.create(config);
	}

	@Test
	public void testEbeanSetField(){
//		 Please execute the script in [test.sql] every time you execute the test to reset the data
		DB.script().run("/test.sql");
		AssetsObject assetsObject = new AssetsObject();
		assetsObject.setTenantId(1L);
		assetsObject.setSourceId(1L);
		assetsObject.setFullObjectName("123");
		assetsObject.setObjectName("123");
		assetsObject.setCreator("creator");
		assetsObject.setModifier("modifier");
		assetsObject.setCreatedStamp(new Date());
		assetsObject.setLastUpdatedStamp(new Date());
		assetsObject.save();

		assetsObject = database.find(AssetsObject.class)
				.where()
					.eq("tenantId",1L)
					.eq("sourceId", 1L)
					.eq("fullObjectName","123")
				.findOne();
		//
		assertThat(assetsObject.getCreator()).isNull();

		// The following program exception information will prompt that tenant_id is not allowed to be null

		// The earlier exception information also appeared as follows, but now I can't reproduce it:
		// set tenantId on [org.example.domain.AssetsObjectFieldId] arg[creator] type[org.example.domain.AssetsObjectFieldId] threw error
		// Caused by: java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Long (java.lang.String and java.lang.Long are in module java.base of loader 'bootstrap')

		// Also I tried, it works fine if my entities AssetsObject don't inherit from BaseModel anymore.
		// But all in all it looks like an underlying problem internally, where creator and tenantId are in the wrong place?
		AssetsObjectField assetsObjectField = new AssetsObjectField();
		assetsObjectField.setAssetsObject(assetsObject);
		assetsObjectField.setTenantId(1L);
		assetsObjectField.setFullFieldName("S123");
		assetsObjectField.setFieldName("S123");
		assetsObjectField.setShortName("S123");
		assetsObjectField.setCreator("creator");
		assetsObjectField.setModifier("modifier");
		assetsObjectField.setCreatedStamp(new Date());
		assetsObjectField.setLastUpdatedStamp(new Date());
		assetsObjectField.setFullObjectName(assetsObject.getFullObjectName());
		assetsObjectField.save();
	}

}
