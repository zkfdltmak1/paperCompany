package com.paging.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.ReviewDao;
import com.board.vo.ReviewVO;

public class ReviewBoardPaging {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private ReviewDao rDao = new ReviewDao();
	// 총 개시물 수
	private int total = 0;
	// 한 페이지에 보여줄 게시글 수
	private int numPerPage = 0;
	// 한 페이지에 보여줄 페이지 수(1~5/6~10)
	private int pagePerBlock = 0;
	// 현재 페이지
	private int nowPage = 0;
	// 페이지의 시작 번호
	private int startPageNum = 0;
	// 페이지의 끝 번호
	private int endPageNum = 0;
	// 시작 블럭 숫자
	private int startBlockNum = 0;
	// 끝 블럭 숫자
	private int endBlockNum = 0;
	// 총 페이지 수
	private int totalPage = 0;	
	
	
	
	public void reviewPaging(int nowPage,
				HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException{
		int total = rDao.getReviewTotal();
		this.req = req;
		this.resp = resp;
		this.nowPage = nowPage;
		
		this.numPerPage = 10;
		this.pagePerBlock = 5;
		this.startPageNum = (nowPage*numPerPage)-(numPerPage-1);
		this.endPageNum = (nowPage*numPerPage);
		this.startBlockNum = ((nowPage-1)/pagePerBlock*pagePerBlock)+1;
		this.endBlockNum = ((nowPage-1)/pagePerBlock*pagePerBlock)+pagePerBlock;
		this.totalPage = (int)Math.ceil(total/(double)numPerPage);
				
		if(endBlockNum > totalPage){
			endBlockNum = totalPage;
		}
		
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		reviewList = rDao.getReviewList(startPageNum, endPageNum);
		req.setAttribute("reviewList", reviewList);
		req.setAttribute("total", this.total);
		req.setAttribute("numPerPage", numPerPage);
		req.setAttribute("pagePerBlock", pagePerBlock);
		req.setAttribute("nowPage", this.nowPage);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("startBlockNum", startBlockNum);
		req.setAttribute("endBlockNum", endBlockNum);
		req.setAttribute("totalPage", totalPage);
		
		System.out.println("total : " + total);
		System.out.println("numPerPage : " + numPerPage);
		System.out.println("pagePerBlock : " + pagePerBlock);
		System.out.println("nowPage : " + nowPage);
		System.out.println("startPageNum : " + startPageNum);
		System.out.println("endPageNum : " + endPageNum);
		System.out.println("startBlockNum : " + startBlockNum);
		System.out.println("endBlockNum : " + endBlockNum);
		System.out.println("totalPage : " + totalPage);
		
		RequestDispatcher view = 
				req.getRequestDispatcher("./papercompany/review/reviews_list.jsp?nowPage"+nowPage);
		view.forward(this.req, this.resp);//이동	 
	}
}
