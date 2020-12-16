package com.example.spring;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleConnectionTest {
	
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="devlec";
	private static final String PWD="1234";
	
	
	@Test
	public void oracleConnTest() throws Exception{
		Class.forName(DRIVER);
		
		try (Connection conn=DriverManager.getConnection(URL,USER,PWD)){
			System.out.println(conn);
			System.out.println("��񿬰� ����");

		} catch (Exception e) {
			System.out.println("���� "+e);
		}
		
	}
	
}
