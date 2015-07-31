package com.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConnectionMgr;

public class BusDao {
	 
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	//통합예매코드--리턴값==시퀀스
   	public int booking(String m_email){
		StringBuffer dml = new StringBuffer();
		dml.append("INSERT INTO BOOKING(BOOKING_CODE, BOOKING_DAY, M_EMAIL) ");
		dml.append("VALUES(seq_booking_number.nextval, to_char(sysdate,'yyyy-mm-dd HH:MM'), ?) ");
		Connection	con = null;
		PreparedStatement pstmt = null;
		
		
		try{
			con	=dbMgr.getConnection();
			
			pstmt = con.prepareStatement(dml.toString());
			pstmt.setString(1,m_email);
			pstmt.executeUpdate();
	
		}catch(SQLException se){
			System.out.println("booking = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("booking = [ "+e+" ]");
		}
	
		int seq = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME='SEQ_BOOKING_NUMBER' ");
	    ResultSet rs = null;
		try{
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				seq = rs.getInt("LAST_NUMBER");
			}
					
		}catch(SQLException se){
			System.out.println("booking2 = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("booking2 = [ "+e+" ]");
		}
		return seq-1;
	}
   	

   
   	//버스예매
   	public boolean bookingBus(String b_brice, int d_code, String age, int b_code, String s_code, String t_code,
						String st_city, String en_city, String a_time, String dp_date){
		StringBuffer dml = new StringBuffer();
		dml.append("INSERT INTO BOOKING_DETAILS (VEHICLE_CODE, BOOKING_PRICE, BOOKING_DCODE, BOOKING_AGE, ");
		dml.append("BOOKING_CODE, SEAT_CODE, TIME_CODE, START_CITY, ARRIVAL_CITY, ARRIVAL_TIME, DP_DATE ");
		dml.append("VALUES('bg01', '?', ?, '?', ?, '?', '?', '?', '?', '?','?') ");
		//'버스 = bg01', '가격', DCODE, 성인아동, BOOKINGCODE , '좌석', 'TIME_CODE', '출발지', '도착지', '예상시간','출발날짜'
		
		boolean success = false;
		Connection	con = null;
		PreparedStatement pstmt = null;
		
		try{
			con	=dbMgr.getConnection();
			pstmt = con.prepareStatement(dml.toString());
			pstmt.setString(1,b_brice);//가격
			pstmt.setInt(2,d_code);//예약상세코드
			pstmt.setString(3,age);//성인-아동
			pstmt.setInt(4,b_code);//예약코드
			pstmt.setString(5,s_code);//좌석코드
			pstmt.setString(6,t_code);//시간코드
			pstmt.setString(7,st_city);//출발지
			pstmt.setString(8,en_city);//도착지
			pstmt.setString(9,a_time);//예상시간
			pstmt.setString(10,dp_date);//출발날짜
			
			int i = pstmt.executeUpdate();
			if(i==1)success = true;
			
		}catch(SQLException se){
			System.out.println("bookingBus = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("bookingBus = [ "+e+" ]");
		
		}
		return success;
	}
   	
}