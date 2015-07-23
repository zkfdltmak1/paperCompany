package com.servlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MemberServletLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String session_logout = request.getParameter("session_logout");
//		System.out.println("session_logout" + session_logout);
		HttpSession session =request.getSession();
//		System.out.println("session: " + session.getAttribute("s_member_email"));
		if (session.getAttribute("s_member_email").equals(session_logout)) {
			session.invalidate();
			response.sendRedirect("../../index.jsp");
		}else{
			response.sendRedirect("../../index.jsp");
		}
		
		
	}

}
