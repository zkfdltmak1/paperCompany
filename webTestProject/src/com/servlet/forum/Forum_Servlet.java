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
import com.logic.forum.Forum_Logic;

public class Forum_Servlet extends HttpServlet {
	public void  doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String command = (String)req.getParameter("command");
		String masterId = "jslee80130@gmail.com";
		ForumVO fvo = null;
		ForumDAO fDao = null;
		Forum_Logic fLogic = null;
		ArrayList<ForumVO> fList=null;
		ArrayList<ForumVO> allList =null;
		final int ROWSIZE =10;  // 한페이지에 보일 게시물 수
		final int BLOCK = 5; // 아래에 보일 페이지 최대개수 1~5 / 6~10 / 11~15 식으로 5개로 고정
		
		
		//req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		if("forum_delete_command".equals(command)){
			
			fDao = new ForumDAO();
			
			int forum_number = (int)session.getAttribute("forum_number");
			
			
			boolean check= fDao.deleteForum(forum_number);
			
			
			if(check){
				session.removeAttribute("forum_number");
				res.sendRedirect("/webTestProject/papercompany/forum/forum_delete_success.jsp");
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
				res.sendRedirect("/webTestProject/papercompany/forum/forum_write_success.jsp");
			}
			
		}
		
		
		else if("forum_read_command".equals(command)){
			fvo = new ForumVO();
			fDao = new ForumDAO();
			int forum_number = Integer.parseInt(req.getParameter("forum_number"));
			fvo = fDao.titleSearch(forum_number);
			
			
			req.setAttribute("fvo", fvo);
			req.setAttribute("forum_number", forum_number);
			req.setAttribute("masterId", masterId);
			
			
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/forum/forum_read.jsp");
			view.forward(req,res);
			
		}
		
		else if("mainTop_command".equals(command)){
			fDao = new ForumDAO();
			fLogic = new Forum_Logic();
			fList = new ArrayList<ForumVO>();
			allList = new ArrayList<ForumVO>();
			
			String masterID= "jslee80130@gmail.com";
			
			
			session.setAttribute("masterID", masterID);
			
			allList = fDao.forumAllSearch();
			
			
			int pg = 1; //기본 페이지값;
			
			

			int start = (pg*ROWSIZE) - (ROWSIZE-1); // 해당페이지에서 시작번호(step2)
			int end = (pg*ROWSIZE); // 해당페이지에서 끝번호(step2)
			
			int allPage = 0; // 전체 페이지수
			
			int startPage = ((pg-1)/BLOCK*BLOCK)+1; // 시작블럭숫자 (1~5페이지일경우 1, 6~10일경우 6)
			int endPage = ((pg-1)/BLOCK*BLOCK)+BLOCK; // 끝 블럭 숫자 (1~5일 경우 5, 6~10일경우 10)
			
			
			allPage = (int)Math.ceil(allList.size()/(double)ROWSIZE);
			
			
		 	 if(endPage > allPage) {
				endPage = allPage;
			} 
			
		 	int viewRow = (pg-1)*ROWSIZE;
		 	 
		 	fList = fLogic.first_list(start,end);
		 	 
		 	req.setAttribute("ROWSIZE", ROWSIZE);
		 	req.setAttribute("BLOCK",BLOCK );
		 	req.setAttribute("pg",pg );
		 	req.setAttribute("start",start );
		 	req.setAttribute("end",end );
		 	req.setAttribute("allPage",allPage );
		 	req.setAttribute("startPage",startPage );
		 	req.setAttribute("endPage",endPage );
		 	req.setAttribute("fList", fList);
		 	
		 	req.setAttribute("viewRow",viewRow );
		 	
		 	
			
		 	req.setAttribute("fList", fList);
		 	/*session.setAttribute("fList", fList);*/
			RequestDispatcher view = req.getRequestDispatcher("../../papercompany/forum/forum_board.jsp");
			view.forward(req,res);
		}
		else if("page_check".equals(command)){
			fLogic = new Forum_Logic();
			fDao = new ForumDAO();
			fList = new ArrayList<ForumVO>();
			allList = new ArrayList<ForumVO>();
			
			allList = fDao.forumAllSearch();
			
			
			String pgs = req.getParameter("pg"); // pg값을 저장
			int pg = Integer.parseInt(pgs);
			
			
			int start = (pg*ROWSIZE) - (ROWSIZE-1);
			int end = (pg*ROWSIZE); // 해당페이지에서 끝번호(step2)
			
			int allPage = 0; // 전체 페이지수
			
			int startPage = ((pg-1)/BLOCK*BLOCK)+1; // 시작블럭숫자 (1~5페이지일경우 1, 6~10일경우 6)
			int endPage = ((pg-1)/BLOCK*BLOCK)+BLOCK; // 끝 블럭 숫자 (1~5일 경우 5, 6~10일경우 10)
			
			
			allPage = (int)Math.ceil(allList.size()/(double)ROWSIZE);
			
			
		 	 if(endPage > allPage) {
				endPage = allPage;
			}
			
			
		 	int viewRow = (pg-1)*ROWSIZE;
		 	
		 	
		 	 
		 	fList = fLogic.first_list(start,end);
		 	 
		 	req.setAttribute("ROWSIZE", ROWSIZE);
		 	req.setAttribute("BLOCK",BLOCK );
		 	req.setAttribute("pg",pg );
		 	req.setAttribute("end",end );
		 	req.setAttribute("allPage",allPage );
		 	req.setAttribute("startPage",startPage );
		 	req.setAttribute("endPage",endPage );
		 	req.setAttribute("fList", fList);
		 	
		 	req.setAttribute("viewRow",viewRow );
			
		 	RequestDispatcher view = req.getRequestDispatcher("../../papercompany/forum/forum_board.jsp");
			view.forward(req,res);
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
