package com.servlet.forum;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.ForumDAO;
import com.board.vo.ForumVO;

public class Forum_Servlet extends HttpServlet {
	public void  doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String command = (String)req.getParameter("command");
		ForumDAO fDao = null;
		//req.setCharacterEncoding("UTF-8");
		
		
		if("forum_delete_command".equals(command)){
			
			fDao = new ForumDAO();
			HttpSession session = req.getSession();
			
			int forum_number = (int)session.getAttribute("forum_number");
			
			
			System.out.println(forum_number);
			boolean check= fDao.deleteForum(forum_number);
			
			
			if(check){
				session.removeAttribute("forum_number");
				res.sendRedirect("../papercompany/forum/forum_delete_success.jsp");
			}
			else{
				session.removeAttribute("forum_number");
			}

		}
		
		
		else if("forum_write_command".equals(command)){
			
			fDao = new ForumDAO();
			
			String forum_title = (String)req.getParameter("forum_title");
			String forum_content = (String)req.getParameter("forum_content");
			boolean check = fDao.writeForum(forum_title,forum_content);
			
			if(check){
				res.sendRedirect("../papercompany/forum/forum_write_success.jsp");
			}
			
		}
		
		else if("mainTop_command".equals(command)){
			fDao = new ForumDAO();
			ArrayList<ForumVO> fList = new ArrayList<ForumVO>();
			fList = fDao.forumSearch();
			
			System.out.println(fList);
			req.setAttribute("fList", fList);
			RequestDispatcher view = req.getRequestDispatcher("../papercompany/forum/forum_board.jsp");
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
