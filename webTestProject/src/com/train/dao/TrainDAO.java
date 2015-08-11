package com.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.org.mozilla.javascript.internal.regexp.SubString;

import com.train.vo.CityVO;
import com.train.vo.CodeSearchVO;
import com.train.vo.DadptVO;
import com.train.vo.TableCheckListVO;
import com.train.vo.TimeVO;
import com.train.vo.TrainBookVO;
import com.board.vo.ForumVO;
import com.train.vo.SeatVO;
import com.train.vo.VehicleVO;
import com.util.DBConnectionMgr;

public class TrainDAO {

	
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	
	
	
	/*public String test(){
		StringBuffer dml = new StringBuffer();
		dml.append("select time_time from timetable where time_code ='tt02' ");
		
		
		Connection	con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String s1 =null;
		String s2 =null;
		
		try{
			con	=dbMgr.getConnection();
			pstmt = con.prepareStatement(dml.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next())
				s1 = rs.getString("time_time");
			
		}catch(SQLException se){
			System.out.println("bookingBus = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("bookingBus = [ "+e+" ]");
		
		}
		
		dml.delete(0, dml.capacity());
		dml.append("select times from dadpt where departure = '서울' and arrival = '부산' ");
		
		try{
			con	=dbMgr.getConnection();
			pstmt = con.prepareStatement(dml.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next())
				s2 = rs.getString("times");
			
		}catch(SQLException se){
			System.out.println("bookingBus = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("bookingBus = [ "+e+" ]");
		
		}
		
		
		int i = Integer.parseInt(s1.substring(0, 2))+Integer.parseInt(s2.substring(0, 2));
		int j = Integer.parseInt(s1.substring(3))+Integer.parseInt(s2.substring(3));
		
		if(j >=60){
			j=j-60;
			i=i+1;
			
		}
		if(i>=24){
			i=i-24;
			
		}
		
		
		
		String z = i+":"+j;
		
		
		
		return z;
		
	}*/
	

	
   
	
	
	public ArrayList<CityVO> train_citySearch(){
			
			ArrayList<CityVO> list = new ArrayList<CityVO>();
			StringBuffer sb = new StringBuffer();
			
			sb.append("select city_city, city_code from city ");
			sb.append("where city_code = 're_01' or city_code = 're_08' or city_code = 're_09' ");
			sb.append("or city_code = 're_11' or city_code = 're_12' or city_code = 're_13' ");
			sb.append("or city_code = 're_14' or city_code = 're_15'");
			
			
			
			PreparedStatement pstmt =null;
			Connection con = null;
			ResultSet rs = null;
			CityVO cvo = null;
			
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					cvo = new CityVO();
					cvo.setCity_city((rs.getString("city_city")));
					cvo.setCity_code((rs.getString("city_code")));
					list.add(cvo);
				}
			}catch(SQLException se){
					System.out.println("inmoney = [ "+se+" ]");
					
			}catch (Exception e) {
				e.printStackTrace();
			}
			dbMgr.freeConnection(con, pstmt, rs);
			
			return list;
		}
	
	
	public ArrayList<VehicleVO> train_vehicleSearch(){
		
		ArrayList<VehicleVO> list = new ArrayList<VehicleVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select vehicle_kinds, vehicle_code from vehicle ");
		sb.append("where vehicle_code like 'tg%' ");
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		VehicleVO vvo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				vvo = new VehicleVO();
				vvo.setVehicle_code((rs.getString("vehicle_code")));
				vvo.setVehicle_kinds((rs.getString("vehicle_kinds")));
				list.add(vvo);
			}
		}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return list;
	}
	
	
	public ArrayList<TimeVO> train_dTimeSearch(){
		
		ArrayList<TimeVO> list = new ArrayList<TimeVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select time_time, time_code from timetable");
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		TimeVO dvo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				dvo = new TimeVO();
				dvo.setTime_time((rs.getString("time_time")));
				dvo.setTime_code((rs.getString("time_code")));
				list.add(dvo);
			}
		}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return list;
	}
	
	
	public ArrayList<SeatVO> train_seatStandardSearch(String vehicle_kinds , String start_city, String arrival_city, String time_time, String dp_date){
		
		ArrayList<SeatVO> list = new ArrayList<SeatVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select seat_number,seat_code, seat_seat from seat ");
		sb.append("where seat_number like 'ts%' ");
		sb.append("and seat_code not in(select seat_code from booking_details ");
		sb.append("where vehicle_code = (select vehicle_code from vehicle where vehicle_kinds = ?) ");
		sb.append("and start_city = (select city_code from city where city_city= ? ) ");
		sb.append("and arrival_city = (select city_code from city where city_city= ? ) ");
		sb.append("and time_code = (select time_code from timetable where time_time = ? ) ");
		sb.append("and dp_date = ?) ");
		sb.append("and seat_seat = '일반실' ");
		sb.append("order by seat_number asc ");
		
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		SeatVO svo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, vehicle_kinds);
			pstmt.setString(2, start_city);
			pstmt.setString(3, arrival_city);
			pstmt.setString(4, time_time);
			pstmt.setString(5, dp_date);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				svo = new SeatVO();
				svo.setSeat_seat((rs.getString("seat_seat")));
				svo.setSeat_code((rs.getString("seat_code")));
				svo.setSeat_number((rs.getString("seat_number")));
				list.add(svo);
			}
		}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return list;
	}
	
	
	public ArrayList<SeatVO> train_seatVipSearch(String vehicle_kinds , String start_city, String arrival_city, String time_time, String dp_date){
		
		ArrayList<SeatVO> list = new ArrayList<SeatVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select seat_number,seat_code, seat_seat from seat ");
		sb.append("where seat_number like 'ts%' ");
		sb.append("and seat_code not in(select seat_code from booking_details ");
		sb.append("where vehicle_code = (select vehicle_code from vehicle where vehicle_kinds = ?) ");
		sb.append("and start_city = (select city_code from city where city_city= ? ) ");
		sb.append("and arrival_city = (select city_code from city where city_city= ? ) ");
		sb.append("and time_code = (select time_code from timetable where time_time = ? ) ");
		sb.append("and dp_date = ?) ");
		sb.append("and seat_seat = '특실' ");
		sb.append("order by seat_number asc ");
		
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		SeatVO svo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, vehicle_kinds);
			pstmt.setString(2, start_city);
			pstmt.setString(3, arrival_city);
			pstmt.setString(4, time_time);
			pstmt.setString(5, dp_date);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				svo = new SeatVO();
				svo.setSeat_seat((rs.getString("seat_seat")));
				svo.setSeat_code((rs.getString("seat_code")));
				svo.setSeat_number((rs.getString("seat_number")));
				list.add(svo);
			}
		}catch(SQLException se){
			System.out.println("inmoney = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return list;
	}
	
	
	
	
	
	
	public ArrayList<TimeVO> train_todayDepartTime(String depart_station, String last_station){
		
		ArrayList<TimeVO> list = new ArrayList<TimeVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select time_code,time_time,to_char(to_date(time_time, 'hh24:mi')+1/(24*60)*distance,'hh24:mi') as arrival_time ");
		sb.append("from DADPT, timetable ");
		sb.append("WHERE departure = ? ");
		sb.append("AND arrival = ? ");
		sb.append("and time_code like 'tt%' ");
		sb.append("and time_time > to_char(sysdate,'hh24:mi') ");
		sb.append("order by time_code asc  ");
		
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		TimeVO dvo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, depart_station);
   			pstmt.setString(2, last_station);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				dvo = new TimeVO();
				dvo.setTime_code((rs.getString("time_code")));
				dvo.setTime_time((rs.getString("time_time")));
				dvo.setArrival_time((rs.getString("arrival_time")));
				
				list.add(dvo);
			}
		}catch(SQLException se){
			System.out.println("inmoney = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return list;
	}

	
	
	
	public ArrayList<TimeVO> train_othersDepartTime(String depart_station, String last_station){
		
		ArrayList<TimeVO> list = new ArrayList<TimeVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select time_code,time_time,to_char(to_date(time_time, 'hh24:mi')+1/(24*60)*distance,'hh24:mi') as arrival_time ");
   		sb.append("from DADPT, timetable ");
   		sb.append("WHERE departure = ? ");
   		sb.append("AND arrival = ? ");
   		sb.append("and time_code like 'tt%' ");
		
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		TimeVO dvo = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, depart_station);
   			pstmt.setString(2, last_station);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				dvo = new TimeVO();
				dvo.setTime_code((rs.getString("time_code")));
				dvo.setTime_time((rs.getString("time_time")));
				dvo.setArrival_time((rs.getString("arrival_time")));
				list.add(dvo);
			}
		}catch(SQLException se){
			System.out.println("inmoney = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	//무궁화호 일반실 요금
	public int sharonTrain_charge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price)*0.9 as price from dadpt ");
		sb.append("where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
		}catch(SQLException se){
			System.out.println("inmoney1 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	

	//새마을호 일반실 요금
	public int saemaeulTrain_charge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price) as price from dadpt where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
		}catch(SQLException se){
			System.out.println("inmoney2 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	
	
	//ktx일반실 요금
	public int ktxTrain_charge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price)*1.3 as price from dadpt ");
		sb.append("where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
			
		}catch(SQLException se){
			System.out.println("inmoney3 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	
	
	
	//무궁화호 특실 요금
	public int sharonTrain_vipCharge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price)*0.9*1.3 as price from dadpt ");
		sb.append("where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
		}catch(SQLException se){
			System.out.println("inmoney4 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	
	
	//새마을호 특실 요금
	public int saemaeulTrain_vipCharge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price)*1.3 as price from dadpt ");
		sb.append("where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
		}catch(SQLException se){
			System.out.println("inmoney5 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	
	
	//ktx 특실 요금
	public int ktxTrain_vipCharge(String dp, String av){
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("select to_number(price)*1.3*1.3 as price from dadpt ");
		sb.append("where departure = ? and arrival =?");
		
		int charge = 0;
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, dp);
			pstmt.setString(2, av);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				charge = rs.getInt("price");
			}
			
		}catch(SQLException se){
			System.out.println("inmoney6 = [ "+se+" ]");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		charge = (int) Math.round(charge/100.0)*100;
		return charge;
	}
	
	
	
	
	
	/*//예매 체크
		public TrainBookVO bookingChack(int d_code, int b_code){
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT arrival_time, booking_price, booking_dcode, booking_age, booking_code, dp_date, ");
		    sql.append("(SELECT TIME_TIME FROM TIMETABLE WHERE TIMETABLE.TIME_CODE = BOOKING_DETAILS.time_code)as TIME_CODE, ");
		    sql.append("(SELECT SEAT_NUMBER FROM SEAT WHERE SEAT.SEAT_CODE = BOOKING_DETAILS.seat_code)as SEAT_CODE, ");
		    sql.append("(SELECT SEAT_SEAT FROM SEAT WHERE SEAT.SEAT_CODE = BOOKING_DETAILS.seat_code)as SEAT_TYPE, ");
		    sql.append("(SELECT city_city AS st_city FROM city WHERE city.city_code = BOOKING_DETAILS.start_city)as START_CITY, ");
		    sql.append("(SELECT city_city AS ed_city FROM city WHERE city.city_code = BOOKING_DETAILS.arrival_city)as arrival_city, ");
		    sql.append("(SELECT vehicle_kinds FROM vehicle WHERE vehicle.vehicle_code = BOOKING_DETAILS.vehicle_code)as VEHICLE_CODE ");
		    sql.append("  FROM BOOKING_DETAILS ");
			sql.append(" WHERE booking_dcode = ? ");
			sql.append("   AND Booking_code = ? ");
		    ResultSet rs = null;
		    TrainBookVO bvo = new TrainBookVO(); 
		    Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con	=dbMgr.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1,d_code);
				pstmt.setInt(2,b_code);
				rs = pstmt.executeQuery();
			  while(rs.next()){
				  bvo.setArrival_time(rs.getString("ARRIVAL_TIME"));        
				  bvo.setBooking_price(rs.getString("BOOKING_PRICE"));
				  bvo.setBooking_dcode(rs.getInt("BOOKING_DCODE"));        
				  bvo.setBooking_age(rs.getString("BOOKING_AGE"));       
				  bvo.setBooking_code(rs.getInt("BOOKING_CODE"));       
				  bvo.setSeat_code(rs.getString("SEAT_CODE"));    
				  bvo.setTime_code(rs.getString("TIME_CODE"));      
				  bvo.setStart_city(rs.getString("START_CITY"));      
				  bvo.setVehicle_code(rs.getString("VEHICLE_CODE"));      
				  bvo.setDp_date(rs.getString("DP_DATE"));  
				  bvo.setArrival_city(rs.getString("ARRIVAL_CITY"));
			  }
			
			}catch(SQLException se){
				System.out.println("bookingchk32 = [ "+se+" ]");
			}catch (Exception e){
				System.out.println("bookingchk3 = [ "+e+" ]");
			}
		    
		    dbMgr.freeConnection(con, pstmt, rs);
		    return bvo;
		}*/
	
		
	
	
	
	public int booking(String m_email){
		StringBuffer dml = new StringBuffer();
		dml.append("INSERT INTO BOOKING(BOOKING_CODE, BOOKING_DAY, M_EMAIL) ");
		dml.append("VALUES(seq_booking_number.nextval, to_char(sysdate,'yyyy-mm-dd HH:MM'),?) ");
		
		System.out.println("111");
		int i= 0;
		int book_code = 0;
		Connection	con = null;
		PreparedStatement pstmt = null;
		
		try{
			con	=dbMgr.getConnection();
			pstmt = con.prepareStatement(dml.toString());
			pstmt.setString(1,m_email);
			
			i = pstmt.executeUpdate();
			if(i==1){
				dml.delete(0, dml.length());
				dml.append("select max(booking_code) as booking_code from booking ");
				dml.append("where m_email = ? ");
				
				ResultSet rs = null;
				try {
					
					pstmt = con.prepareStatement(dml.toString());
					pstmt.setString(1, m_email);
					
					rs = pstmt.executeQuery();
					
					rs.next();
					book_code = rs.getInt("booking_code");
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
		}catch(SQLException se){
			System.out.println("bookingTrain_booking = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("bookingTrain_booking = [ "+e+" ]");
		
		}
		return book_code;
	}
	
	
	
	
	
		
		
		//기차 예매
	   	public boolean bookingTrain(CodeSearchVO codeVO,int booking_code,String booking_price,String booking_age,String arrival_time,String train_date){
	   	//출발도시코드,도착도시코드,기차종류코드, 출발시간코드,좌석코드,예약코드,가격,성인아동, 도착시간,출발날짜,
			StringBuffer dml = new StringBuffer();
			dml.append("INSERT INTO BOOKING_DETAILS (VEHICLE_CODE, BOOKING_PRICE, BOOKING_DCODE, BOOKING_AGE, ");
			dml.append("BOOKING_CODE, SEAT_CODE, TIME_CODE, START_CITY, ARRIVAL_CITY, ARRIVAL_TIME, DP_DATE) ");
			dml.append("VALUES(?, ?, seq_booking_dtail_number.nextval, ?, ?, ?, ?, ?, ?, ?,?) ");
			
			boolean success = false;
			Connection	con = null;
			PreparedStatement pstmt = null;
			
			
			try{
				con	=dbMgr.getConnection();
				pstmt = con.prepareStatement(dml.toString());
				pstmt.setString(1,codeVO.getVehicle_code());//기차 코드
				pstmt.setString(2,booking_price);//가격
				pstmt.setString(3,booking_age);//성인-아동
				pstmt.setInt(4,booking_code);//예약코드
				pstmt.setString(5,codeVO.getSeat_code());//좌석코드
				pstmt.setString(6,codeVO.getTime_code());//시간코드
				pstmt.setString(7,codeVO.getStart_city());//출발지
				pstmt.setString(8,codeVO.getArrival_city());//도착지
				pstmt.setString(9,arrival_time);//예상시간
				pstmt.setString(10,train_date);//출발날짜
				
				int i = pstmt.executeUpdate();
				if(i==1)success = true;
				
			}catch(SQLException se){
				System.out.println("bookingTrain = [ "+se+" ]");
			}catch (Exception e){
				System.out.println("bookingTrain = [ "+e+" ]");
			
			}
			dbMgr.freeConnection(con, pstmt);
			
			return success;
		}
	
	   	
	   	
	   	
		public CodeSearchVO searchCode(String start_city,String arrival_city, String vehicle_kinds,String time_time,String seat_number){
			//출발도시코드,도착도시코드,기차종류코드, 출발시간코드,좌석코드
			ArrayList<CodeSearchVO> list = new ArrayList<CodeSearchVO>();
			StringBuffer sb = new StringBuffer();
			
			sb.append("select c1.city_code as start_city,c2.city_code as arrival_city,v.vehicle_code as vehicle_code,t.time_code as time_code,s.seat_code as seat_code ");
			sb.append("from city c1, city c2, vehicle v ,timetable t, seat s ");
			sb.append("where c1.city_city = ? and c2.city_city = ? and v.vehicle_kinds = ? ");
			sb.append("and t.time_time=? and s.seat_number = ? ");
			
			
			PreparedStatement pstmt =null;
			Connection con = null;
			ResultSet rs = null;
			CodeSearchVO cvo = null;
			
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				
			
				
				pstmt.setString(1, start_city);
				pstmt.setString(2, arrival_city);
				pstmt.setString(3, vehicle_kinds);
				pstmt.setString(4, time_time);
				pstmt.setString(5, seat_number);
				
				rs = pstmt.executeQuery();
				
				rs.next();
				
				cvo = new CodeSearchVO();
				cvo.setStart_city(rs.getString("start_city"));
				cvo.setArrival_city(rs.getString("arrival_city"));
				cvo.setVehicle_code((rs.getString("vehicle_code")));
				cvo.setTime_code((rs.getString("time_code")));
				cvo.setSeat_code((rs.getString("seat_code")));
					
					
			}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			dbMgr.freeConnection(con, pstmt, rs);
			return cvo;
		}

	   	
	   	
	   	
	   	
	   	
	   /*	public ArrayList<DadptVO> arrivalTodayTime(String depart_station, String last_station){
	   		ArrayList<DadptVO> list = new ArrayList<DadptVO>();
			StringBuffer sb = new StringBuffer();
			
			
			sb.append("select time_time,to_char(to_date(time_time, 'hh24:mi')+1/(24*60)*distance,'hh24:mi') ");
			sb.append("from DADPT, timetable ");
			sb.append("WHERE departure = ? ");
			sb.append("AND arrival = ? ");
			sb.append("and time_code like 'tt%' ");
			sb.append("and time_time > to_char(sysdate,'hh24:mi') ");
			sb.append("order by time_code asc  ");
			
			
			PreparedStatement pstmt =null;
			Connection con = null;
			ResultSet rs = null;
			DadptVO dvo = null;
			
			try {
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sb.toString());
				pstmt.setString(1, depart_station);
	   			pstmt.setString(2, last_station);
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){	
					dvo = new DadptVO();
					dvo.setArrival_time(rs.getString("time_time"));
					list.add(dvo);
				}
			}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			dbMgr.freeConnection(con, pstmt, rs);
			
			return list;
	   	}
	   	
	   	
	   	
	   	
	   	public ArrayList<DadptVO> arrivalOthersTime(String depart_station, String last_station){
	   		ArrayList<DadptVO> list = new ArrayList<DadptVO>();
	   		StringBuffer sb = new StringBuffer();
	   		
	   		
	   		sb.append("select time_time,to_char(to_date(time_time, 'hh24:mi')+1/(24*60)*distance,'hh24:mi') as arrival_time ");
	   		sb.append("from DADPT, timetable ");
	   		sb.append("WHERE departure = ? ");
	   		sb.append("AND arrival = ? ");
	   		sb.append("and time_code like 'tt%' ");
	   		
	   		
	   		PreparedStatement pstmt =null;
	   		Connection con = null;
	   		ResultSet rs = null;
	   		DadptVO dvo = null;
	   		
	   		try {
	   			con = dbMgr.getConnection();
	   			pstmt = con.prepareStatement(sb.toString());
	   			pstmt.setString(1, depart_station);
	   			pstmt.setString(2, last_station);
	   			
	   			rs = pstmt.executeQuery();
	   			
	   			while(rs.next()){	
	   				dvo = new DadptVO();
	   				dvo.setArrival_time(rs.getString("arrival_time"));
	   				list.add(dvo);
	   			}
	   		}catch(SQLException se){
	   			System.out.println("inmoney = [ "+se+" ]");
	   			
	   		}catch (Exception e) {
	   			e.printStackTrace();
	   		}
	   		dbMgr.freeConnection(con, pstmt, rs);
	   		
	   		return list;
	   	}*/
}
