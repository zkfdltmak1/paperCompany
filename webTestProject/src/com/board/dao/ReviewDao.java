package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.vo.ReviewVO;
import com.util.DBConnectionMgr;

public class ReviewDao {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuffer sql;
	private DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	// 후기게시판 글 읽기 가져오기
	/*public List<ReviewVO> getReviewforum(int reviews_number){*/ 
	public ReviewVO getReviewforum(int reviews_number){
			//얜 여러개
			/*List<ReviewVO> VoList = new ArrayList<ReviewVO>();*/
			//얜 하나
			ReviewVO reviewVO = new ReviewVO();
			dbMgr = DBConnectionMgr.getInstance();
			StringBuilder sql = new StringBuilder();
			sql.append("select REVIEWS_TITLE, REVIEWS_CONTENT, ");
			sql.append("M_EMAIL from REVIEWS_FORUM where ");
			sql.append("REVIEWS_NUMBER = ?");
			
		try{
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, reviews_number);
			rs = pstmt.executeQuery();
			con = dbMgr.getConnection();
			
		while(rs.next()){
			reviewVO.setReviews_number(rs.getInt("reviews_number")); 		
			reviewVO.setReviews_content(rs.getString("reviews_content")); 	
			reviewVO.setReviews_title(rs.getString("reviews_title")); 		
			reviewVO.setReviews_pw(rs.getString("reviews_pw")); 				
			reviewVO.setM_email(rs.getString("m_email")); 	
		}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return reviewVO;
	}
	
	// 후기 게시판 글쓰기
	public void insertReviewWrite(ReviewVO rvo){
		sql = new StringBuffer();
		sql.append("insert into reviews_forum(reviews_number,");
		sql.append(" reviews_title, reviews_content, reviews_pw, m_email) ");
		sql.append("values(seq_reviews_number.nextval, ?, ?, ?, ?)");
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, rvo.getReviews_title());
			pstmt.setString(2, rvo.getReviews_content());
			pstmt.setString(3, rvo.getReviews_pw());
			pstmt.setString(4, "zkfdltmak1@naver.com");
			pstmt.executeUpdate();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt);
		}
		
	}
	
	//후기 게시판 전체 리스트 불러오기
	public List<ReviewVO> getReviewList(){
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		StringBuffer tml= new StringBuffer();
		tml.append("select reviews_number, reviews_title, reviews_content, reviews_pw, m_email from Reviews_forum");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(tml.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReviewVO rvo = new ReviewVO();
				rvo.setReviews_number(rs.getInt("reviews_number"));
				rvo.setReviews_title(rs.getString("reviews_title"));
				rvo.setReviews_content(rs.getString("reviews_content"));
				rvo.setReviews_pw(rs.getString("reviews_pw"));
				rvo.setM_email(rs.getString("m_email"));
				reviewList.add(rvo);
			}
			System.out.println("reviewList size : " + reviewList.size());
		}catch(SQLException se){
			System.out.println("se = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("e = [ "+e+" ]");
		}finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return reviewList;
	}
	
}
