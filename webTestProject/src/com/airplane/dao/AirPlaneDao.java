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
	
	public String getVehicleKinds(){
		String vehicleKinds = "";
		StringBuffer sql = new StringBuffer();
		sql.append("select vehicle_kinds ");
		sql.append("from vehicle ");
		sql.append("where vehicle_code ");
		sql.append("like 'ag%'");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vehicleKinds = rs.getString("vehicle_kinds");
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return vehicleKinds;
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
		
		sql.append("(select vehicle_kinds from vehicle where vehicle_code like 'ag%')as vehicle_kinds, ");
		
		
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
				bvo.setVehicle_code(rs.getString("vehicle_kinds"));
				
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
	
	
public int booking_code_Insert(String s_member_email) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO BOOKING(BOOKING_CODE, BOOKING_DAY, M_EMAIL) ");
		sql.append("VALUES(seq_booking_number.nextval, to_char(sysdate,'yyyy-mm-dd HH:MM'), ?) ");
		int success = 0;
		int booking_code = 0;
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, s_member_email);
			success = pstmt.executeUpdate();
			
			if (success == 1) {
				StringBuffer sql2 = new StringBuffer();
				sql2.append("select max(BOOKING_CODE)as BOOKING_CODE ");
				sql2.append("from BOOKING where m_email = ? ");
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sql2.toString());
				pstmt.setString(1, s_member_email);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					booking_code = rs.getInt("BOOKING_CODE");	
				}
			}else{
				booking_code = 0;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			close_all();
		}
		
		return booking_code;
		
	}

public int booking_Dcode_Insert(BookingVO bvo) {
	String arrival_airBooking_time = arrival_time(bvo.getArrival_time());
	
	StringBuffer sql = new StringBuffer();
	sql.append ("INSERT INTO BOOKING_DETAILS (VEHICLE_CODE, BOOKING_PRICE, BOOKING_DCODE, BOOKING_AGE,       ");
	sql.append ("		BOOKING_CODE, SEAT_CODE, TIME_CODE, START_CITY, ARRIVAL_CITY, ARRIVAL_TIME, DP_DATE )");
	sql.append ("		VALUES(                                                                              ");
	sql.append ("		            (select VEHICLE_CODE from VEHICLE where VEHICLE_KINDS = ?)      ");
	sql.append ("		            , ?                                                                ");
	sql.append ("		            , (select max(BOOKING_DCODE)+1as  BOOKING_DCODE from BOOKING_DETAILS)    ");
	sql.append ("		            , ?                                                                   ");
	sql.append ("		            , ?                                                                     ");
	sql.append ("		            , (select seat_code from seat where seat_number = ?)               ");
	sql.append ("		            , (select time_code from TIMETABLE where time_time = ?)            ");
	sql.append ("		            , (select city_code from CITY where city_city = ?)                    ");
	sql.append ("		            , (select city_code from CITY where city_city = ?)                   ");
	sql.append ("		            , ? ");
	sql.append ("		            ,?                                                            ");
	sql.append ("		    )	");                                                                                
	int success = 0;                                                                                        
	int st = 0;
	
	String seat = bvo.getSeat_code();
	String[] seat_code = seat.split(" ");
	String person = bvo.getBooking_age();
	
	String[] Booking_age = person.split(" ");
	int[] Booking_age_int_v =  new int[Booking_age.length];
	StringBuilder  peral =  new StringBuilder();
	
	
	for (int i = 0; i < Booking_age_int_v.length; i++) {
		int Booking_age_int =  Integer.parseInt(Booking_age[i]);
		Booking_age_int_v[i]= Booking_age_int;
	}
	for (int j = 0; j < Booking_age_int_v[0]; j++) {
		peral.append("adult ");
	}
	for (int j = 0; j < Booking_age_int_v[1]; j++) {
		peral.append("kids ");
	}
	

	for (st = 0; st < seat_code.length; st++) {
		String[] person_all = peral.toString().split(" ");
			try {
					con = dbMgr.getConnection();
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, "B737-900");
					pstmt.setString(2, bvo.getBooking_price());
					pstmt.setString(3, person_all[st]);
					pstmt.setInt(4, bvo.getBooking_code());
					pstmt.setString(5,	seat_code[st]);
					pstmt.setString(6, bvo.getArrival_time());
					pstmt.setString(7, bvo.getStart_city());
					pstmt.setString(8, bvo.getArrival_city());
					pstmt.setString(9, arrival_airBooking_time);
					pstmt.setString(10, bvo.getDp_date());
					success = pstmt.executeUpdate();
				} catch (SQLException se) {
					se.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}	
		}
	dbMgr.freeConnection(con, pstmt);
	return success;	
}

	
	public String arrival_time(String start_airBooking_date) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT (TO_CHAR (  (SELECT TO_DATE (?, 'hh24:mi') ");
        sql.append("       FROM TIMETABLE                                  ");
        sql.append("      WHERE time_time = ?)                       ");
        sql.append("  + 1 / (24 * 60)*40, 'hh:mi'))as time_time          ");
        sql.append("  FROM TIMETABLE where time_time = ?            ");
        String arrival_airBooking_time = null;
        try {
        	con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, start_airBooking_date);
			pstmt.setString(2, start_airBooking_date);
			pstmt.setString(3, start_airBooking_date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arrival_airBooking_time = rs.getString("time_time");
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close_all();
		}
        
		return arrival_airBooking_time;
	}
	



	public void close_all(){
	try {
		if(con != null)con.close();
		if(pstmt != null)pstmt.close();
		if(rs != null)rs.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}

	
	
}