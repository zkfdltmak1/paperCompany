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


public class MemberServletSelectPw extends HttpServlet {
	MemberVo mvo =  new MemberVo();
	MemberDao mdao =  new MemberDao();
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		mvo.setM_name(request.getParameter("name"));
		mvo.setM_mobile(request.getParameter("phone"));
		mvo.setM_email(request.getParameter("email"));
		
		mvo = mdao.selectPW(mvo);
		System.out.println("pw : " + mvo.getM_pw());
		if (mvo.getM_pw() == null) {
			
			request.setAttribute("member_pw", "패스워드없음");
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request, response);
			
		}else{
			request.setAttribute("member_pw", mvo.getM_pw());
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request, response);
			
		}
		
	}
		
	}


