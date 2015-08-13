package com.servlet.bus;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.logic.bus.Bus_Logic;
import com.bus.dao.BusDAO;
import com.bus.vo.ChargeListVO;
import com.bus.vo.CityVO;
import com.bus.vo.SeatVO;
import com.bus.vo.TableCheckListVO;
import com.bus.vo.TimeVO;
import com.bus.vo.VehicleVO;


public class Bus_Servlet extends HttpServlet {
	
	
	public void  doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String command = req.getParameter("command");
		ArrayList<CityVO> 		cList = null;
		ArrayList<VehicleVO> 	vList = null;
		ArrayList<TimeVO> dList = null; 
		ArrayList<SeatVO> 		sList = null;
		BusDAO 				bDao  = null;
		ArrayList<TableCheckListVO> bList = null;
		ChargeListVO clVO = null;
		Bus_Logic bLogic = new Bus_Logic();
		HttpSession session = req.getSession();
		
		if("bus_search_command".equals(command)){
			dList = new ArrayList<TimeVO>();
			
			clVO = new ChargeListVO();
			
			String bus_type = req.getParameter("bus_type");
			String depart_station = req.getParameter("depart_station");
			String last_station = req.getParameter("last_station");
			String bus_date = req.getParameter("bus_date");
			String adult_people =req.getParameter("adult_people");
			String children_people =req.getParameter("children_people");
			
			session.setAttribute("bus_type", bus_type);
			session.setAttribute("depart_station", depart_station);
			session.setAttribute("last_station", last_station);
			session.setAttribute("bus_date", bus_date);
			session.setAttribute("adult_people", adult_people);
			session.setAttribute("children_people", children_people);
			 
			clVO =bLogic.charge_search(bus_type,depart_station,last_station);
			dList = bLogic.depart_check(bus_date,depart_station,last_station);
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			req.setAttribute("bus_type", bus_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("bus_date", bus_date);
			req.setAttribute("adult_people", adult_people);	
			req.setAttribute("children_people", children_people);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_list", dList);
			req.setAttribute("charge_vip", clVO.getVipCharge());
			req.setAttribute("charge_vipChildren", clVO.getVipChildrenCharge());
			req.setAttribute("charge_Standard", clVO.getStandardCharge());
			req.setAttribute("charge_StandardChildren", clVO.getStandardChildrenCharge());
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/bus/bus_select.jsp");
			view.forward(req, res);
		}
		
		else if("bus_vip".equals(command)){
			bList = new ArrayList<TableCheckListVO>();
			String time_time = req.getParameter("bus_date");
			String arrival_time = req.getParameter("arrival_time");
			
			String bus_type =(String)session.getAttribute("bus_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String bus_date =(String)session.getAttribute("bus_date");
			String adult_people =(String)session.getAttribute("adult_people");
			String children_people =(String)session.getAttribute("children_people");
			
			int vipChargeInt = bLogic.vipCharge(bus_type, depart_station, last_station);
			int childrenVipChargeInt = bLogic.cildrenVipCharge(bus_type, depart_station, last_station);
			
			String resultVipCharge = bLogic.result_vipCharge(adult_people, children_people,vipChargeInt,childrenVipChargeInt);
			String vipCharge = vipChargeInt+"";
			String childrenVipCharge = childrenVipChargeInt+"";
			
			dList = bLogic.depart_check(bus_date,depart_station,last_station);
			
			sList = bLogic.getVipSeatList(bus_type,depart_station, last_station,time_time,bus_date);
				
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			bList=bLogic.tableList(sList,vipCharge,childrenVipCharge,result_people,adult_people,children_people,arrival_time,time_time);
			
			session.setAttribute("bList", bList);
			
			req.setAttribute("bus_type", bus_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("bus_date", bus_date);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_date", time_time);
			req.setAttribute("arrival_time", arrival_time);
			req.setAttribute("sList",sList);
			req.setAttribute("bList",bList);
			req.setAttribute("resultCharge", resultVipCharge);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/bus/bus_book_check.jsp");
			view.forward(req, res);
			
		}
		
		else if("bus_standard".equals(command)){
			String time_time = req.getParameter("bus_date");
			String arrival_time = req.getParameter("arrival_time");
			
			String bus_type =(String)session.getAttribute("bus_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String bus_date =(String)session.getAttribute("bus_date");
			String adult_people =(String)session.getAttribute("adult_people");
			String children_people =(String)session.getAttribute("children_people");
			
			int standardChargeInt = bLogic.standardCharge(bus_type, depart_station, last_station);
			int childrenStandardChargeInt = bLogic.cildrenStandardCharge(bus_type, depart_station, last_station);
			
			String resultStandardCharge = bLogic.result_standardCharge(adult_people, children_people,standardChargeInt,childrenStandardChargeInt);
			
			
			String standardCharge = standardChargeInt+"";
			String childrenStandardCharge = childrenStandardChargeInt+"";
			
			sList = bLogic.getStandardSeatList(bus_type,depart_station, last_station,time_time,bus_date);
				
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			bList=bLogic.tableList(sList,standardCharge,childrenStandardCharge,result_people,adult_people,children_people,arrival_time,time_time);
			
			session.setAttribute("bList", bList);
			
			req.setAttribute("bus_type", bus_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("bus_date", bus_date);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_date", time_time);
			req.setAttribute("arrival_time", arrival_time);
			req.setAttribute("sList",sList);
			req.setAttribute("bList",bList);
			req.setAttribute("resultCharge", resultStandardCharge);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/bus/bus_book_check.jsp");
			view.forward(req, res);
			
		}
		else if("bus_first".equals(command)){
			String today= bLogic.todayDate();
			String endday = bLogic.enddayDate();
			
			cList = bLogic.getCityList();
			vList = bLogic.getVehicleList();
			
			session.setAttribute("today", today);
			session.setAttribute("endday", endday);
			session.setAttribute("cList", cList);
			session.setAttribute("vList", vList);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/bus/bus_select.jsp");
			view.forward(req, res);
		}
		
		else if("payCheck_command".equals(command)){
			bList = (ArrayList<TableCheckListVO>)session.getAttribute("bList");
			
			String email = (String)session.getAttribute("s_member_email");
			String bus_type =(String)session.getAttribute("bus_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String bus_date =(String)session.getAttribute("bus_date");
			
			bLogic.bookingResult(bList,email,bus_type,depart_station,last_station,bus_date);
			
			RequestDispatcher view = req.getRequestDispatcher("../book/bookingAllCheck.do");
			view.forward(req, res);			
			}			
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doService(req,res);		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doService(req,res);
	}
}