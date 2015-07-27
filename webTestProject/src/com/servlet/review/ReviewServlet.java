package com.servlet.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.ReplyDao;
import com.board.dao.ReviewDao;
import com.board.vo.ReplyVO;
import com.board.vo.ReviewVO;

public class ReviewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);		
	}

	private void doService(HttpServletRequest req, 
			HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String command = req.getParameter("command");
		ReviewVO rvo = new ReviewVO();
		ReviewDao rDao = new ReviewDao();
		ReplyDao rpDao = new ReplyDao();
		
		if("review_write".equals(command)){

			
			String reviews_title = req.getParameter("reviews_title");
			String reviews_content = req.getParameter("reviews_content");
			String reviews_pw = req.getParameter("reviews_pw");
			
			rvo.setReviews_title(reviews_title);
			rvo.setReviews_content(reviews_content);
			rvo.setReviews_pw(reviews_pw);
			rDao.insertReviewWrite(rvo);
			
			resp.sendRedirect("/webTestProject/review_board.review");
		}
		else if("getReviewList".equals(command)){
			List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
			reviewList = rDao.getReviewList();
			req.setAttribute("reviewList", reviewList);
			RequestDispatcher view = 
					req.getRequestDispatcher("./papercompany/review/reviews_list.jsp");
			view.forward(req, resp);//이동	 
		}
		else if("getReviewRead".equals(command)){
			List<ReplyVO> rpList = new ArrayList<ReplyVO>();
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));
			
			rvo = rDao.getReviewRead(reviews_number);
			rpList = rpDao.getReplyforum(reviews_number);
			
			req.setAttribute("review_read", rvo);
			req.setAttribute("rpList", rpList);			
			
			RequestDispatcher view = 
					req.getRequestDispatcher("./papercompany/review/reviews_read.jsp");
			view.forward(req, resp);
		}
		else if("readUpadate".equals(command)){
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));
			String reviews_content = req.getParameter("reviews_content");
			
			int result = rDao.reviewUpdate(reviews_number, reviews_content);
			if(result == 1){
				RequestDispatcher view = req.getRequestDispatcher("");
				view.forward(req, resp);
			}
			else{
				RequestDispatcher view = req.getRequestDispatcher("");
				view.forward(req, resp);				
			}
		}
		else if("readDelete".equals(command)){
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));
			String reviews_pw = req.getParameter("reviews_pw");
			
			int result = rDao.reviewDelete(reviews_number, reviews_pw);
			if(result == 1){
				RequestDispatcher view = req.getRequestDispatcher("");
				view.forward(req, resp);
			}
			else{
				RequestDispatcher view = req.getRequestDispatcher("");
				view.forward(req, resp);				
			}
		}
	}
	
}