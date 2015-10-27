//$Id$
package com.example.tenancy;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.tenancy.table.LoginDetail;

public class Starter {
	public static void main(String[] args) {
		if(args.length==0 ){
			System.out.println("Plz give the cfg.xml path as argument");
		}
		File f = new File(args[0]);
		if(!f.isFile()){
			System.out.println("Plz give the proper path for cfg.xml");
		}
		Configuration aCONFIG = new Configuration().configure(f);
		SessionFactory aFACTORY = aCONFIG.buildSessionFactory();
		Session session = aFACTORY.withOptions().tenantIdentifier("mt101").openSession();
		LoginDetail lDetailforUser1 = new LoginDetail();
		lDetailforUser1.setBrowser("FF");
		lDetailforUser1.setLoginDate(System.nanoTime());
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(lDetailforUser1);
		transaction.commit();
		session.clear();
		

		session = aFACTORY.withOptions().tenantIdentifier("mt102").openSession();
		LoginDetail lDetailforUser2 = new LoginDetail();
		lDetailforUser2.setBrowser("GC");
		lDetailforUser2.setLoginDate(System.nanoTime());
		transaction = session.getTransaction();
		transaction.begin();
		session.save(lDetailforUser2);
		transaction.commit();
		session.clear();
		
		
		/*
		//If we uncomment the below lines we get following exception::
		 * 
		 * ERROR: ERROR: relation "public.hibernate_sequence" does not exist
  Position: 17
Exception in thread "main" org.hibernate.exception.SQLGrammarException: could not extract ResultSet
	at org.hibernate.exception.internal.SQLStateConversionDelegate.convert(SQLStateConversionDelegate.java:106)
	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:109)
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:95)
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.extract(ResultSetReturnImpl.java:79)
	at org.hibernate.id.enhanced.SequenceStructure$1.getNextValue(SequenceStructure.java:96)
	at org.hibernate.id.enhanced.NoopOptimizer.generate(NoopOptimizer.java:42)
	at org.hibernate.id.enhanced.SequenceStyleGenerator.generate(SequenceStyleGenerator.java:417)
	at org.hibernate.event.internal.AbstractSaveEventListener.saveWithGeneratedId(AbstractSaveEventListener.java:101)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.saveWithGeneratedOrRequestedId(DefaultSaveOrUpdateEventListener.java:192)
	at org.hibernate.event.internal.DefaultSaveEventListener.saveWithGeneratedOrRequestedId(DefaultSaveEventListener.java:38)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.entityIsTransient(DefaultSaveOrUpdateEventListener.java:177)
	at org.hibernate.event.internal.DefaultSaveEventListener.performSaveOrUpdate(DefaultSaveEventListener.java:32)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:73)
	at org.hibernate.internal.SessionImpl.fireSave(SessionImpl.java:678)
	at org.hibernate.internal.SessionImpl.save(SessionImpl.java:670)
	at org.hibernate.internal.SessionImpl.save(SessionImpl.java:665)
	at com.example.tenancy.Starter.main(Starter.java:37)
Caused by: org.postgresql.util.PSQLException: ERROR: relation "public.hibernate_sequence" does not exist
  Position: 17
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2270)
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:1998)
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:255)
	at org.postgresql.jdbc2.AbstractJdbc2Statement.execute(AbstractJdbc2Statement.java:570)
	at org.postgresql.jdbc2.AbstractJdbc2Statement.executeWithFlags(AbstractJdbc2Statement.java:420)
	at org.postgresql.jdbc2.AbstractJdbc2Statement.executeQuery(AbstractJdbc2Statement.java:305)
	at org.apache.tomcat.dbcp.dbcp.DelegatingPreparedStatement.executeQuery(DelegatingPreparedStatement.java:96)
	at org.apache.tomcat.dbcp.dbcp.DelegatingPreparedStatement.executeQuery(DelegatingPreparedStatement.java:96)
	at org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.extract(ResultSetReturnImpl.java:70)
	... 13 more
*/
		/*
		session = aFACTORY.withOptions().tenantIdentifier("mt201").openSession();
		LoginDetail lDetailforUser3 = new LoginDetail();
		lDetailforUser3.setBrowser("IE");
		lDetailforUser3.setLoginDate(System.nanoTime());
		transaction = session.getTransaction();
		transaction.begin();
		session.save(lDetailforUser3);
		transaction.commit();
		session.clear();
		
		session = aFACTORY.withOptions().tenantIdentifier("mt202").openSession();
		LoginDetail lDetailforUser4 = new LoginDetail();
		lDetailforUser4.setBrowser("AS");
		lDetailforUser4.setLoginDate(System.nanoTime());
		transaction = session.getTransaction();
		transaction.begin();
		session.save(lDetailforUser4);
		transaction.commit();
		session.clear();
		*/
	}

}
