package com.servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDao;
import com.member.vo.MemberVo;


public class MemberServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberVo mvo =  new MemberVo();
	MemberDao mdao =  new MemberDao();
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		//logInOut(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
// 이메일/pw 받기

		mvo.setM_pw(request.getParameter("pwd"));
		mvo.setM_email(request.getParameter("email"));
		mvo = mdao.memberLogin(mvo);
		if (mvo.getM_email() == null || mvo.getM_name() == null) {
			request.setAttribute("login_null", "로그인실패");
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request,response);
		}else{

			HttpSession session =request.getSession();
			
			session.setAttribute("s_member_email", mvo.getM_email());
			session.setAttribute("s_member_name", mvo.getM_name());
			session.setAttribute("s_member_nickname", mvo.getM_nickname());
			System.out.println(session.getAttribute("s_member_email"));
			System.out.println(session.getAttribute("s_member_name"));
			System.out.println(session.getAttribute("s_member_nickname"));
			
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			view.forward(request,response);
		
		}
	}
	
	public void logInOut(HttpServletRequest request, HttpServletResponse response) {
		String command = request.getParameter("");
		
	} 

}
