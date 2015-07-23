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


public class MemberServletJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberVo mvo =  new MemberVo();
	MemberDao mdao =  new MemberDao();
	int success = 0;
    public MemberServletJoin() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		mvo.setM_email(request.getParameter("email"));
		mvo.setM_pw(request.getParameter("pwd1"));
		mvo.setM_name(request.getParameter("name"));
		mvo.setM_nickname(request.getParameter("nickname"));
		mvo.setM_mobile(request.getParameter("phone"));
		success = mdao.memberJoin(mvo);
		if (success == 1) {
			
			request.setAttribute("join_succes", "가입성공");
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("join_succes", "가입실패");
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}
		
		
	}

}
