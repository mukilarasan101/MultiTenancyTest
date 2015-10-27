//$Id$
package com.example.tenancy;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.HibernateException;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;

public class ConnectionProviderImpl extends C3P0ConnectionProvider {

	private static final long serialVersionUID = -1768592744904010791L;
	private final BasicDataSource basicDataSource = new BasicDataSource();
	private String schema;
	private String host;
	private String database;
	public ConnectionProviderImpl(String host,String database,String schema){
		basicDataSource.setDriverClassName("org.postgresql.Driver");
		basicDataSource.setUrl("jdbc:postgresql://"+host+":5432/"+database+"?currentSchema="+schema+"&searchpath="+schema);
		basicDataSource.setUsername("postgres");
		basicDataSource.setPassword("postgres");
		basicDataSource.setInitialSize(2);
		basicDataSource.setMaxActive(100);
		this.host = host;
		this.database = database;
		this.schema = schema;
	}

	public ConnectionProviderImpl() {
	}

	@Override
	public void closeConnection(Connection con) throws SQLException {
		con.close();
	}

	@Override
	public Connection getConnection() throws SQLException {
		final Connection connection = basicDataSource.getConnection();
		try {
			connection.setCatalog(schema);
			//connection.setSchema(schema);
            connection.createStatement().execute("SET SCHEMA '" + this.schema + "'");
        }
        catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + this.schema + "]", e);
        }

		return connection;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConnectionProviderImpl [schema=");
		builder.append(schema);
		builder.append(", host=");
		builder.append(host);
		builder.append(", database=");
		builder.append(database);
		builder.append("]");
		return builder.toString();
	}

}
