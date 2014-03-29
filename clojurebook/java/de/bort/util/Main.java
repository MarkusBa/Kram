package de.bort.util;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.bort.entities.Nodes;
import de.bort.entities.Prefixes;
import de.bort.entities.Quads;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void deleteThese(Session session, String entity, List<Integer> ids){
      session.createQuery(" update "  + entity + " p set p.deleted = true where p.id in :ids ").setParameterList("ids",ids).executeUpdate();
              session.createQuery(" update "  + entity + " p set p.deleted = false where p.id not in :ids ").setParameterList("ids",ids).executeUpdate();
    }

    public static void main(String...strings ) {
    String dbUrl = "jdbc:mysql://localhost/crudbook";
    String dbClass = "com.mysql.jdbc.Driver";
    String query = "Select distinct(table_name) from INFORMATION_SCHEMA.TABLES";
    String username = "root";
    String password = "1234";
    try {

//        Class.forName(dbClass);
//        Connection connection = DriverManager.getConnection(dbUrl,
//            username, password);
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//        while (resultSet.next()) {
//        String tableName = resultSet.getString(1);
//        System.out.println("Table name : " + tableName);
//        }
//        connection.close();
    	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		Nodes node = new Nodes();
//		node.setDatatype("test");
//		node.setHash(new BigInteger("2"));
//		node.setLang("bla");
//		node.setLex("hi");
//		node.setType(1);
		
//		session.save(node);
		
//		Prefixes prefix = new Prefixes();
//		prefix.setPrefix("dc");
//		prefix.setUri("http://purl.org/dc/elements/1.1/");
//		
//		session.save(prefix);
		
		Quads quad = new Quads();
		quad.setS(1);
		quad.setP(1);
		quad.setO(1);
		
		session.save(quad);
		
		session.getTransaction().commit();
		
		session.close();
    	
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    } catch (SQLException e) {
//        e.printStackTrace();
    } finally {}
    }
}
