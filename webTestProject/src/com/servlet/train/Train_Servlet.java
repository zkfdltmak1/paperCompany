package com.servlet.train;

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

import com.logic.train.Train_Logic;
import com.train.dao.TrainDAO;
import com.train.vo.ChargeListVO;
import com.train.vo.CityVO;
import com.train.vo.SeatVO;
import com.train.vo.TableCheckListVO;
import com.train.vo.TimeVO;
import com.train.vo.VehicleVO;


public class Train_Servlet extends HttpServlet {
	
	
	public void  doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String command = req.getParameter("command");
		ArrayList<CityVO> 		cList = null;
		ArrayList<VehicleVO> 	vList = null;
		ArrayList<TimeVO> dList = null; 
		ArrayList<SeatVO> 		sList = null;
		TrainDAO 				tDao  = null;
		ArrayList<TableCheckListVO> tList = null;
		ChargeListVO clVO = null;
		Train_Logic tLogic = new Train_Logic();
		HttpSession session = req.getSession();
		
		if("train_search_command".equals(command)){
			dList = new ArrayList<TimeVO>();
			
			clVO = new ChargeListVO();
			
			String train_type = req.getParameter("train_type");
			String depart_station = req.getParameter("depart_station");
			String last_station = req.getParameter("last_station");
			String train_date = req.getParameter("train_date");
			String adult_people =req.getParameter("adult_people");
			String children_people =req.getParameter("children_people");
			
			session.setAttribute("train_type", train_type);
			session.setAttribute("depart_station", depart_station);
			session.setAttribute("last_station", last_station);
			session.setAttribute("train_date", train_date);
			session.setAttribute("adult_people", adult_people);
			session.setAttribute("children_people", children_people);
			 
			clVO =tLogic.charge_search(train_type,depart_station,last_station);
			dList = tLogic.depart_check(train_date,depart_station,last_station);
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			req.setAttribute("train_type", train_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("train_date", train_date);
			req.setAttribute("adult_people", adult_people);	
			req.setAttribute("children_people", children_people);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_list", dList);
			req.setAttribute("charge_vip", clVO.getVipCharge());
			req.setAttribute("charge_vipChildren", clVO.getVipChildrenCharge());
			req.setAttribute("charge_Standard", clVO.getStandardCharge());
			req.setAttribute("charge_StandardChildren", clVO.getStandardChildrenCharge());
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/train/train_select.jsp");
			view.forward(req, res);
		}
		
		else if("train_vip".equals(command)){
			tList = new ArrayList<TableCheckListVO>();
			String time_time = req.getParameter("train_date");
			String arrival_time = req.getParameter("arrival_time");
			
			String train_type =(String)session.getAttribute("train_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String train_date =(String)session.getAttribute("train_date");
			String adult_people =(String)session.getAttribute("adult_people");
			String children_people =(String)session.getAttribute("children_people");
			
			int vipChargeInt = tLogic.vipCharge(train_type, depart_station, last_station);
			int childrenVipChargeInt = tLogic.cildrenVipCharge(train_type, depart_station, last_station);
			
			String resultVipCharge = tLogic.result_vipCharge(adult_people, children_people,vipChargeInt,childrenVipChargeInt);
			String vipCharge = vipChargeInt+"";
			String childrenVipCharge = childrenVipChargeInt+"";
			
			dList = tLogic.depart_check(train_date,depart_station,last_station);
			
			sList = tLogic.getVipSeatList(train_type,depart_station, last_station,time_time,train_date);
				
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			tList=tLogic.tableList(sList,vipCharge,childrenVipCharge,result_people,adult_people,children_people,arrival_time,time_time);
			
			session.setAttribute("tList", tList);
			
			req.setAttribute("train_type", train_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("train_date", train_date);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_date", time_time);
			req.setAttribute("arrival_time", arrival_time);
			req.setAttribute("sList",sList);
			req.setAttribute("tList",tList);
			req.setAttribute("resultCharge", resultVipCharge);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/train/train_book_check.jsp");
			view.forward(req, res);
			
		}
		
		else if("train_standard".equals(command)){
			String time_time = req.getParameter("train_date");
			String arrival_time = req.getParameter("arrival_time");
			
			String train_type =(String)session.getAttribute("train_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String train_date =(String)session.getAttribute("train_date");
			String adult_people =(String)session.getAttribute("adult_people");
			String children_people =(String)session.getAttribute("children_people");
			
			int standardChargeInt = tLogic.standardCharge(train_type, depart_station, last_station);
			int childrenStandardChargeInt = tLogic.cildrenStandardCharge(train_type, depart_station, last_station);
			
			String resultStandardCharge = tLogic.result_standardCharge(adult_people, children_people,standardChargeInt,childrenStandardChargeInt);
			
			
			String standardCharge = standardChargeInt+"";
			String childrenStandardCharge = childrenStandardChargeInt+"";
			
			sList = tLogic.getStandardSeatList(train_type,depart_station, last_station,time_time,train_date);
				
			int result_people = Integer.parseInt(adult_people)+Integer.parseInt(children_people);
			
			tList=tLogic.tableList(sList,standardCharge,childrenStandardCharge,result_people,adult_people,children_people,arrival_time,time_time);
			
			session.setAttribute("tList", tList);
			
			req.setAttribute("train_type", train_type);
			req.setAttribute("depart_station", depart_station);
			req.setAttribute("last_station", last_station);
			req.setAttribute("train_date", train_date);
			req.setAttribute("result_people", result_people);
			req.setAttribute("depart_date", time_time);
			req.setAttribute("arrival_time", arrival_time);
			req.setAttribute("sList",sList);
			req.setAttribute("tList",tList);
			req.setAttribute("resultCharge", resultStandardCharge);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/train/train_book_check.jsp");
			view.forward(req, res);
			
		}
		else if("train_first".equals(command)){
			String today= tLogic.todayDate();
			String endday = tLogic.enddayDate();
			
			cList = tLogic.getCityList();
			vList = tLogic.getVehicleList();
			
			session.setAttribute("today", today);
			session.setAttribute("endday", endday);
			session.setAttribute("cList", cList);
			session.setAttribute("vList", vList);
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/train/train_select.jsp");
			view.forward(req, res);
		}
		
		else if("payCheck_command".equals(command)){
			tList = (ArrayList<TableCheckListVO>)session.getAttribute("tList");
			
			String email = (String)session.getAttribute("s_member_email");
			String train_type =(String)session.getAttribute("train_type");
			String depart_station =(String)session.getAttribute("depart_station");
			String last_station =(String)session.getAttribute("last_station");
			String train_date =(String)session.getAttribute("train_date");
			
			tLogic.bookingResult(tList,email,train_type,depart_station,last_station,train_date);
			
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
