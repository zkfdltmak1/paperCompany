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
import com.paging.review.ReviewBoardPaging;

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
		ReplyVO rpvo = new ReplyVO();
		ReviewDao rDao = new ReviewDao();
		ReplyDao rpDao = new ReplyDao();
		
		// 후기 글 추가
		if("review_write".equals(command)){
			String reviews_title = req.getParameter("reviews_title");
			String reviews_content = req.getParameter("reviews_content");
			String reviews_pw = req.getParameter("reviews_pw");
			String session_id = req.getParameter("session_id");
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			System.out.println("reivew_write nowPage : " + nowPage);
			System.out.println("review reviews_content : " + reviews_content);
			
			rvo.setReviews_title(reviews_title);
			rvo.setReviews_content(reviews_content);
			rvo.setReviews_pw(reviews_pw);
			rDao.insertReviewWrite(rvo, session_id);
			
			resp.sendRedirect("/webTestProject/review_board.review?command=getReviewList&nowPage="+nowPage);
		}
		// 후기 글 목록 가져오기
		else if("getReviewList".equals(command)){
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			ReviewBoardPaging paging = new ReviewBoardPaging();
			paging.reviewPaging(nowPage, req, resp);
		}
		// 후기 글 읽기
		else if("getReviewRead".equals(command)){
			List<ReplyVO> rpList = new ArrayList<ReplyVO>();
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			rvo = rDao.getReviewRead(reviews_number);
			rpList = rpDao.getReplyforum(reviews_number);
			
			req.setAttribute("review_read", rvo);
			req.setAttribute("rpList", rpList);			
			
			RequestDispatcher view = 
					req.getRequestDispatcher("./papercompany/review/reviews_read.jsp?nowPage="+nowPage);
			view.forward(req, resp);
		}
		// 후기 글 수정 페이지로
		else if("readUpadateProc".equals(command)){
			String reviews_number = req.getParameter("reviews_number");
			String reviews_title = req.getParameter("reviews_title");
			String reviews_content = req.getParameter("reviews_content");
			String reviews_pw = req.getParameter("reviews_pw");
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			rvo.setReviews_number(Integer.parseInt(reviews_number));
			rvo.setReviews_title(reviews_title);
			rvo.setReviews_content(reviews_content);
			rvo.setReviews_pw(reviews_pw);			
			req.setAttribute("reviews_readVo", rvo);
			
			System.out.println("content : " + rvo.getReviews_content());
			
			RequestDispatcher view = req.getRequestDispatcher("./papercompany/review/reviews_update.jsp?nowPage"+nowPage);
			view.forward(req, resp);
		}
		// 후기 글 수정 하기
		else if("review_update".equals(command)){
			String reviews_number = req.getParameter("reviews_number");
			String reviews_title = req.getParameter("reviews_title");
			String reviews_content = req.getParameter("reviews_content");
			String reviews_pw = req.getParameter("reviews_pw");
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			rvo.setReviews_number(Integer.parseInt(reviews_number));
			rvo.setReviews_title(reviews_title);
			rvo.setReviews_content(reviews_content);
			rvo.setReviews_pw(reviews_pw);
			
			int result = rDao.reviewUpdate(rvo);
			
			if(result == 1){
				resp.sendRedirect("/webTestProject/review_board.review?command=getReviewRead&reviews_number="+rvo.getReviews_number()+"&nowPage="+nowPage);
				/*RequestDispatcher view = req.getRequestDispatcher("/webTestProject/review_board.review?command=getReviewRead&reviews_number="+rvo.getReviews_number());
				view.forward(req, resp);*/
			}
			else{
				resp.sendRedirect("./papercompany/review/reviews_update_fail.jsp?nowPage="+nowPage);
				/*RequestDispatcher view = req.getRequestDispatcher("./papercompany/review/reviews_update_fail.jsp");
				view.forward(req, resp);*/
			}
		}
		// 후기 글 삭제하기 
		else if("read_delete".equals(command)){
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));
			String reviews_pw = req.getParameter("reviews_pw");
			int reviews_reply_size = Integer.parseInt(req.getParameter("reviews_reply_size"));
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			if(reviews_reply_size > 0){
				rpDao.replyDelete(reviews_number);				
			}
			
			System.out.println("reviews_number : " + reviews_number);
			int result = rDao.reviewDelete(reviews_number, reviews_pw);
			
			if(result == 1){
				resp.sendRedirect("/webTestProject/review_board.review?command=getReviewList&nowPage="+nowPage);
				/*RequestDispatcher view = req.getRequestDispatcher("/webTestProject/review_board.review?command=getReviewList");
				view.forward(req, resp);*/
			}
			else{
				resp.sendRedirect("./papercompany/review/reviews_delete_fail.jsp?nowPage="+nowPage);
				/*RequestDispatcher view = req.getRequestDispatcher("./papercompany/review/reviews_delete_fail.jsp");
				view.forward(req, resp);*/
			}
		}
		//답글 추가 하기 서블릿
		else if("insertReply".equals(command)){
			String reply_content = req.getParameter("reviews_reply");
			int reviews_number = Integer.parseInt(req.getParameter("reviews_number"));		
			String reply_id = req.getParameter("session_id");
			int nowPage = Integer.parseInt(req.getParameter("nowPage"));
			
			rpvo.setReply_id(reply_id);
			rpvo.setReviews_number(reviews_number);
			rpvo.setReply_content(reply_content);

			rpDao.insertReply(rpvo);
			
			resp.sendRedirect("/webTestProject/review_board.review?command=getReviewRead&reviews_number="+reviews_number+"&nowPage="+nowPage);
		}
	}
	
}