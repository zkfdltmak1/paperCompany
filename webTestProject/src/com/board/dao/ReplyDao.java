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
	
	private DBConnectionMgr 	dbMgr 	= null;
	private Connection 			con 	= null;
	private PreparedStatement 	pstmt	= null;
	private ResultSet 			rs 		= null;

	public List<ReplyVO> getReplyforum(int reply_number){
		List<ReplyVO> voList = new ArrayList<ReplyVO>();
		dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select REPLY_NUMBER, REPLY_ID, REPLY_CONTENT, from REPLY_FORUM where REVIEWS_NUMBER = ?");
	try{
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setInt(1, reply_number);
		rs = pstmt.executeQuery();
		con = dbMgr.getConnection();
		ReplyVO rVO = null;
		while(rs.next()){
			rVO = new ReplyVO();
			rVO.setReply_number(rs.getInt("reply_number"));
			rVO.setReply_id(rs.getString("reply_id"));
			rVO.setReply_content(rs.getString("reply_content"));
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
}
