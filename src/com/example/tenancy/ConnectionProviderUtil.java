//$Id$
package com.example.tenancy;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;


public class ConnectionProviderUtil {

	private static final Logger LOGGER = Logger.getLogger(ConnectionProviderUtil.class.getName());
	private static HashMap<String,ConnectionProvider> tenantIdVsCP = new HashMap<String, ConnectionProvider>();
	public static Properties tenantDetails = new Properties();
	static{
		try{
			tenantDetails.put("mt101", "127.0.0.1__##__mtest__##__user1");
			tenantDetails.put("mt102", "127.0.0.1__##__mtest__##__user2");
			tenantDetails.put("mt201", "127.0.0.1__##__mtest1__##__user1");
			tenantDetails.put("mt202", "127.0.0.1__##__mtest1__##__user2");
		}catch(Exception e){
			LOGGER.log(Level.INFO,"Error while init the tenantDetails");
			e.printStackTrace();
		}
	}
	public static ConnectionProvider getAdminConnectionProvider() {
		return new ConnectionProviderImpl("127.0.0.1","mtest","public");
	}

	public static ConnectionProvider getConnectionProviderForTenancyId(String tenantId) {
		ConnectionProvider cp=null;
		if(tenantIdVsCP.containsKey(tenantId)){
			cp = tenantIdVsCP.get(tenantId);
		}else{
			boolean isContain = tenantDetails.containsKey(tenantId);
			if(isContain){
				String[] host_db_schema = tenantDetails.get(tenantId).toString().split("__##__");
				cp = new ConnectionProviderImpl(host_db_schema[0],host_db_schema[1],host_db_schema[2]);
				tenantIdVsCP.put(tenantId, cp);
			}else{
				throw new RuntimeException("Invalid tenantId");
			}
		}
		return cp;
	}

}
