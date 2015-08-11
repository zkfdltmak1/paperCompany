package com.servlet.bookingcheck;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.booking.dao.BookingDao;
import com.booking.vo.BookingVO;

public class BookingCheckServlet extends HttpServlet {
	
	public void  doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		BookingDao baDao = new BookingDao();
		ArrayList<BookingVO> baList = new ArrayList<BookingVO>();
		HttpSession session = req.getSession();
		
		
		String member_email=(String)session.getAttribute("s_member_email");
		baList = baDao.bookingAllCheck(member_email);
		
		req.setAttribute("baList", baList);
		RequestDispatcher rd = req.getRequestDispatcher("../../papercompany/bookingCheck/bookingAllList.jsp");
		rd.forward(req, res);
		
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
