package com.servlet.review;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.ReviewDao;
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
		System.out.println("command : " + command);
		ReviewVO rvo = new ReviewVO();
		ReviewDao rDao = new ReviewDao();
		
		if("review_write".equals(command)){

			
			String reviews_title = req.getParameter("reviews_title");
			String reviews_content = req.getParameter("reviews_content");
			String reviews_pw = req.getParameter("reviews_pw");
			
			rvo.setReviews_title(reviews_title);
			rvo.setReviews_content(reviews_content);
			rvo.setReviews_pw(reviews_pw);
			rDao.insertReviewWrite(rvo);
			
			resp.sendRedirect("./papercompany/review/reviews_list.jsp");
		}
		else if("getReviewList".equals(command)){
		List<ReviewVO> reviewList = null;
			reviewList = rDao.getReviewList();
			req.setAttribute("reviewList", reviewList);
			RequestDispatcher view = req.getRequestDispatcher("./Reviews");
			view.forward(req, resp);//이동	 
		}
	}
	
}
