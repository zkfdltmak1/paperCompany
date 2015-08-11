package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.booking.vo.BookingVO;
import com.util.DBConnectionMgr;

public class BookingDao {

	DBConnectionMgr dbMgr = new DBConnectionMgr();
	
	
	
	
	public ArrayList<BookingVO> bookingAllCheck(String email){
		ArrayList<BookingVO> list = new ArrayList<BookingVO>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select arrival_time, booking_price, booking_dcode, booking_age, booking_code,dp_date");
	    sql.append(",(select seat_seat from seat where booking_details.seat_code = seat.seat_code) seat_code");
	    sql.append(",(select time_time from timetable where booking_details.time_code = timetable.time_code) time_code");
	    sql.append(",(select city_city from city where booking_details.start_city = city.city_code) start_city");
	    sql.append(",(select vehicle_kinds from vehicle where booking_details.vehicle_code = vehicle.vehicle_code) vehicle_code");
	    sql.append(",(select city_city from city where booking_details.arrival_city = city.city_code) arrival_city ");
	    sql.append("from booking_details ");
	    sql.append("where booking_code in (select booking_code from booking where m_email = ?) ");
		sql.append("order by booking_dcode desc");
	    ResultSet rs = null;
	    BookingVO bacvo = null;
	    Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con	=dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
		  while(rs.next()){
			  bacvo = new BookingVO(); 
			  bacvo.setArrival_time(rs.getString("arrival_time"));        
			  bacvo.setBooking_price(rs.getString("booking_price"));
			  bacvo.setBooking_dcode(rs.getInt("booking_dcode"));        
			  bacvo.setBooking_age(rs.getString("booking_age"));       
			  bacvo.setBooking_code(rs.getInt("booking_code"));       
			  bacvo.setSeat_code(rs.getString("seat_code"));    
			  bacvo.setTime_code(rs.getString("time_code"));      
			  bacvo.setStart_city(rs.getString("start_city"));      
			  bacvo.setVehicle_code(rs.getString("vehicle_code"));      
			  bacvo.setDp_date(rs.getString("dp_date"));  
			  bacvo.setArrival_city(rs.getString("arrival_city"));
			  list.add(bacvo);
		  }
		
		}catch(SQLException se){
			System.out.println("bookingchk32 = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("bookingchk3 = [ "+e+" ]");
		}
		
		dbMgr.freeConnection(con, pstmt, rs);
		
	    return list;
	}
}
