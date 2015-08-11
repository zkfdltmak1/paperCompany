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
	public ReviewVO getReviewRead(int reviews_number){
		ReviewVO reviewVO = new ReviewVO();
		StringBuilder sql = new StringBuilder();
		sql.append("select REVIEWS_NUMBER, REVIEWS_TITLE, ");
		sql.append("REVIEWS_PW, REVIEWS_CONTENT, M_EMAIL ");
		sql.append("from REVIEWS_FORUM ");
		sql.append("where REVIEWS_NUMBER = ?");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, reviews_number);
			rs = pstmt.executeQuery();
			
		while(rs.next()){
			reviewVO.setReviews_number(rs.getInt("reviews_number"));
			reviewVO.setReviews_title(rs.getString("reviews_title")); 		
			reviewVO.setReviews_content(rs.getString("reviews_content"));
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
	public void insertReviewWrite(ReviewVO rvo, String session_id){
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
			pstmt.setString(4, session_id);
			pstmt.executeUpdate();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt);
		}
		
	}
	
	// 후기 게시판 전체 글 갯수 구하기
	public int getReviewTotal(){
		int total = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as count from reviews_forum");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				total = rs.getInt("count");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return total;
	}
	
	//후기 게시판 전체 리스트 불러오기
	public List<ReviewVO> getReviewList(int startPageNum, int endPageNum){
		List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		StringBuffer tml= new StringBuffer();
		tml.append("select * from "); 
		tml.append("(select /*+ INDEX_DESC(review reviews_number) */ ");
		tml.append("rownum rnum, reviews_number, reviews_title, ");
		tml.append("(select m_nickname from member where ");
		tml.append("member.m_email = review.m_email) as m_nickname ");
		tml.append("from reviews_forum review) ");
		tml.append("where rnum between ? and ?");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(tml.toString());
			pstmt.setInt(1, startPageNum);
			pstmt.setInt(2, endPageNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReviewVO rvo = new ReviewVO();
				rvo.setReviews_number(rs.getInt("reviews_number"));
				rvo.setReviews_title(rs.getString("reviews_title"));
				rvo.setM_nickname(rs.getString("m_nickname"));
				reviewList.add(rvo);
			}
		}catch(SQLException se){
			System.out.println("se = [ "+se+" ]");
		}catch (Exception e){
			System.out.println("e = [ "+e+" ]");
		}finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return reviewList;
	}
	
	// 후기 게시판 수정
	public int reviewUpdate(ReviewVO rvo){
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("update reviews_forum set ");
		sql.append("reviews_title = ?, reviews_content = ?, ");
		sql.append("reviews_pw = ? where reviews_number = ?");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, rvo.getReviews_title());
			pstmt.setString(2, rvo.getReviews_content());
			pstmt.setString(3, rvo.getReviews_pw());
			pstmt.setInt(4, rvo.getReviews_number());
			result = pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	// 후기 게시판 삭제
	public int reviewDelete(int reviews_number, String reviews_pw){
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from reviews_forum where reviews_number = ? and reviews_pw = ?");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, reviews_number);
			pstmt.setString(2, reviews_pw);
			result = pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
}
