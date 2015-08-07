package com.servlet.airBooking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airplane.dao.AirPlaneDao;
import com.booking.vo.BookingVO;
import com.google.gson.Gson;

public class AirplaneServlet extends HttpServlet {

	AirPlaneDao airDao = new AirPlaneDao();
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp)
					throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp)
					throws ServletException, IOException {
		resp.setContentType("text/plain;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		doService(req, resp);
	}

	private void doService(HttpServletRequest req,HttpServletResponse resp)
				throws ServletException, IOException {
		String command = req.getParameter("command");
		
		resp.setContentType("text/plain;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		
		if("airBooking_detail".equals(command)){
			
			//airBooking.jsp -> airBooking_loading.jsp -> airBooking_detail.jsp
			String  airBooking_startCity = req.getParameter("airBooking_startCity");
			String  airBooking_arrivalCit = req.getParameter("airBooking_arrivalCity");
			String  airBooking_date = req.getParameter("airBooking_date");
			String  airBooking_adults = req.getParameter("airBooking_adults");
			String  airBooking_kids = req.getParameter("airBooking_kids");
			
			// 시간 리스트
			List<String> airBooking_timeList = airDao.getTimeTable();
			// 비행기 종류
			List<String> airBooking_vehicleList = airDao.getVehicleKinds();
			// 가격
			int airBooking_price = airDao.getPrice(airBooking_startCity, airBooking_arrivalCit);
			// 대인과 소인 명 수
			int airBooking_person_su = Integer.parseInt(airBooking_adults) + Integer.parseInt(airBooking_kids);
			// 남은 좌석수
			List<BookingVO> countList = airDao.getCountList();
			
			req.setAttribute("airBooking_countList", countList);
			req.setAttribute("airBooking_timeList", airBooking_timeList);
			req.setAttribute("airBooking_vehicleList", airBooking_vehicleList);
			
			req.setAttribute("airBooking_price", airBooking_price);
			req.setAttribute("airBooking_startCity", airBooking_startCity);
			req.setAttribute("airBooking_arrivalCit", airBooking_arrivalCit);
			req.setAttribute("airBooking_date", airBooking_date);
			req.setAttribute("airBooking_adults", airBooking_adults);
			req.setAttribute("airBooking_kids", airBooking_kids);
			req.setAttribute("airBooking_person_su", airBooking_person_su);
			
			RequestDispatcher view = 
					req.getRequestDispatcher("./papercompany/airplane/airBooking_detail.jsp");
			view.forward(req, resp);
		}
		else if("airBooking_loading".equals(command)){
			String airBooking_startCity = req.getParameter("airBooking_startCity");
			String airBooking_arrivalCity = req.getParameter("airBooking_arrivalCity");
			String airBooking_date = req.getParameter("airBooking_date");
			String airBooking_adults = req.getParameter("airBooking_adults");
			String airBooking_kids = req.getParameter("airBooking_kids");
			
			req.setAttribute("airBooking_startCity", airBooking_startCity);
			req.setAttribute("airBooking_arrivalCity", airBooking_arrivalCity);
			req.setAttribute("airBooking_date", airBooking_date);
			req.setAttribute("airBooking_adults", airBooking_adults);
			req.setAttribute("airBooking_kids", airBooking_kids);
			
			RequestDispatcher view = 
					req.getRequestDispatcher("./papercompany/airplane/airBooking_loading.jsp");
			view.forward(req, resp);
		}
		else if("airBooking_remain".equals(command)){
			String airBookingDate = req.getParameter("airBookingDate");
			String airBookingTime = req.getParameter("airBookingTime");
			
			PrintWriter pw = resp.getWriter();
			String json;
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			List<BookingVO> remain = airDao.getAirBookingRemain(airBookingDate, airBookingTime);
			
			if(remain.size() == 0){
				String size = "0";
				json = size;
				System.out.println("remain == 0 : " + json);
				pw.print(json.toString());
			}else if(remain.size() > 0){
				json = new Gson().toJson(remain);
				System.out.println("remain != 0 : " + json);
				pw.print(json.toString());
			}
			pw.close();
		}
		
	}
}
