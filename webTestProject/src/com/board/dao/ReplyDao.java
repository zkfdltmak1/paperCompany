package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.vo.ReplyVO;
import com.util.DBConnectionMgr;

public class ReplyDao {
	
	private DBConnectionMgr 	dbMgr 	= DBConnectionMgr.getInstance();
	private Connection 			con 	= null;
	private PreparedStatement 	pstmt	= null;
	private ResultSet 			rs 		= null;
	
	// 리플 목록 가져오기
	public List<ReplyVO> getReplyforum(int reply_number){
		List<ReplyVO> voList = new ArrayList<ReplyVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("select (select m_nickname from member ");
		sql.append("where member.m_email = reply_forum.reply_id) as reply_id, ");
		sql.append("REPLY_CONTENT from REPLY_FORUM where REVIEWS_NUMBER = ?");
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, reply_number);
			rs = pstmt.executeQuery();
		
		while(rs.next()){
			ReplyVO rVO = new ReplyVO();
			rVO.setReply_id(rs.getString("reply_id"));
			rVO.setReply_content(rs.getString("reply_content"));
			voList.add(rVO);
		}
	} catch (SQLException se) {
		se.printStackTrace();
	} catch (Exception e){
		e.printStackTrace();
	} finally{
		dbMgr.freeConnection(con, pstmt, rs);
	}
		return voList;
	}
	
	// 글삭제하면서 리플 삭제하기
	public void replyDelete(int reviews_number){
		StringBuffer sql = new StringBuffer();
		sql.append("delete from reply_forum where reviews_number = ?");
		
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, reviews_number);
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	// 댓글 추가 하기
	public void insertReply(ReplyVO rpvo) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into reply_forum(reply_id,");
		sql.append(" reviews_number, reply_content, reply_number) ");
		sql.append("values(?, ?, ?, SEQ_REPLY_NUMBER.nextval)");
		try{
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, rpvo.getReply_id());
			pstmt.setInt(2, rpvo.getReviews_number());
			pstmt.setString(3, rpvo.getReply_content());

			pstmt.executeUpdate();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		finally{
			dbMgr.freeConnection(con, pstmt);
		}
		
	}
	
}
