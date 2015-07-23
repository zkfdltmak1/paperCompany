package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionMgr {
	private final String _DRIVER 	= "oracle.jdbc.driver.OracleDriver";
	private final String _URL 		= "jdbc:oracle:thin:@192.168.0.92:1521:orcl11";
	private final String _USER 		= "paper";
	private final String _PW 		= "company";
	static DBConnectionMgr dbMgr			= null;
	//DBConnectionMgr객체 인스턴스 얻어오기
	public static DBConnectionMgr getInstance()
	{
		if(dbMgr == null){
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	//물리적으로 떨어져 있는 DB서버에 연결통로 만들기
	public Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//사용한 자원 반납하기(INSERT,UPDATE,DELETE - 동적쿼리)
	//자바에서는 같은 이름의 메소드를 지원한다 -1)오버로딩,  2)오버라이딩
	public void freeConnection(Connection con, PreparedStatement pstmt)
	{
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//사용한 자원 반납하기(INSERT,UPDATE,DELETE - 정적쿼리)
	public void freeConnection(Connection con, Statement stmt)
	{
		try {
			if(stmt !=null) stmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//사용한 자원 반납하기(SELECT - 정적쿼리)
	public void freeConnection(Connection con , Statement stmt, ResultSet rs)
	{
		try {
			if(rs !=null) rs.close();
			if(stmt !=null) stmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//사용한 자원 반납하기(SELECT - 동적쿼리)
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs)
	{
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






