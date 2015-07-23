package com.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.MemberDao;
import com.member.vo.MemberVo;


public class MemberServletSelectId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberVo mvo =  new MemberVo();
	MemberDao mdao =  new MemberDao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		mvo.setM_name(request.getParameter("name"));
		mvo.setM_mobile(request.getParameter("phone"));
		
		mvo = mdao.selectID(mvo);
		System.out.println("email : "+mvo.getM_email());
		if (mvo.getM_email() == null) {
			request.setAttribute("member_email", "이메일없음");
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("member_email", mvo.getM_email());
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request, response);
		}
		
	}

}
