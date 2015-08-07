package com.airplane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.booking.vo.BookingVO;
import com.util.DBConnectionMgr;

public class AirPlaneDao {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<String> getTimeTable(){
		List<String> timeList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select time_time ");
		sql.append("from timetable ");
		sql.append("where time_code ");
		sql.append("like 'at%'");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String time = rs.getString("time_time");
				timeList.add(time);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return timeList;
	}
	
	public List<String> getVehicleKinds(){
		List<String> airList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select vehicle_kinds ");
		sql.append("from vehicle ");
		sql.append("where vehicle_code ");
		sql.append("like 'ag%'");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String time = rs.getString("vehicle_kinds");
				airList.add(time);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return airList;
	}

	public int getPrice(String startCity, String arrivalCit) {
		StringBuffer sql = new StringBuffer();
		sql.append("select price from DADPT ");
		sql.append("where departure like ? ");
		sql.append("and arrival like ? ");
		int Price = 0;
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, startCity);
			pstmt.setString(2, arrivalCit);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String rs_price = rs.getString("price");
				Price = Integer.parseInt(rs_price);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return Price;
	}
	
	// booking_details 테이블에서 좌석코드 가져오는 쿼리
	public List<BookingVO> getAirBookingRemain(
			String airBookingDate, String airBookingTime){
		List<BookingVO> remain = new ArrayList<BookingVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select dp_date, ");
		sql.append("(select time_time from timetable ");
		sql.append("where timetable.time_code = booking_details.time_code) as time_time, ");
		sql.append("(select seat_number from seat ");
		sql.append("where seat.seat_code = booking_details.seat_code) as seat_number, ");
		sql.append("(select seat_seat from seat where ");
		sql.append("seat.seat_code = booking_details.seat_code) as seat_seat, ");
		sql.append("(select city_city from city where ");
		sql.append("city.city_code = booking_details.arrival_city) as city_city ");
		sql.append("from booking_details ");
		sql.append(" where dp_date = ? ");
		sql.append("and time_code = (select time_code from timetable where time_time = ?) ");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, airBookingDate);
			pstmt.setString(2, airBookingTime);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BookingVO bvo = new BookingVO();
				bvo.setDp_date(rs.getString("dp_date"));
				bvo.setTime_time(rs.getString("time_time"));
				bvo.setSeat_number(rs.getString("seat_number"));
				bvo.setSeat_seat(rs.getString("seat_seat"));
				bvo.setCity_city(rs.getString("city_city"));
				
				remain.add(bvo);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return remain;
	}

	public List<BookingVO> getCountList() {
		List<BookingVO> countList = new ArrayList<BookingVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select dp_date, ");
		sql.append("(select city_city ");
		sql.append("from city where city.city_code ");
		sql.append("= booking_details.arrival_city) as city_city, ");
		sql.append("(select time_time from timetable ");
		sql.append("where timetable.time_code = ");
		sql.append("booking_details.time_code) as time_time ");
		sql.append("from booking_details ");
		sql.append("where vehicle_code in ('ag01', 'ag02')");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BookingVO bvo = new BookingVO();
				bvo.setDp_date(rs.getString("dp_date"));
				bvo.setCity_city(rs.getString("city_city"));
				bvo.setTime_time(rs.getString("time_time"));
				
				countList.add(bvo);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return countList;
	}
}