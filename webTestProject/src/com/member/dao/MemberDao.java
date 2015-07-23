package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.member.vo.MemberVo;
import com.util.DBConnectionMgr;

import jeus.ejb.persistence.database.DBUtil;

public class MemberDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberVo mvo;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
//회원 가입	
	public int memberJoin(MemberVo mvo) {
//		System.out.println("DB:넘어옴");
//		System.out.println(mvo.toString());
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into MEMBER (m_email,m_name,m_nickname,m_mobile,m_pw)");
		sql.append(" values (?,?,?,?,?)");
		int success=0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getM_email());
			pstmt.setString(2, mvo.getM_name());
			pstmt.setString(3, mvo.getM_nickname());
			pstmt.setString(4, mvo.getM_mobile());
			pstmt.setString(5, mvo.getM_pw());
			success = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMgr.freeConnection(con, pstmt);
		}
		return success;
	}

//아이디 검색
	
	public MemberVo selectID(MemberVo mvo) {
//		System.out.println("DB:넘어옴");
//		System.out.println(mvo.toString());
		StringBuffer sql = new StringBuffer();
		
		sql.append("select m_email from MEMBER ");
		sql.append("where m_name = ? ");
		sql.append("and   m_mobile = ? ");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getM_name());
			pstmt.setString(2, mvo.getM_mobile());
			rs = pstmt.executeQuery();
			mvo  =new MemberVo(); 
			while (rs.next()) {
				mvo.setM_email(rs.getString("m_email"));	
			}	
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return mvo;
	}
	
//비밀번호 검색	
	public MemberVo selectPW(MemberVo mvo) {
//		System.out.println("DB:넘어옴");
//		System.out.println(mvo.toString());
		StringBuffer sql = new StringBuffer();
		
		sql.append("select m_pw from MEMBER ");
		sql.append("where m_name = ? ");
		sql.append("and   m_mobile = ? ");
		sql.append("and   m_email = ? ");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getM_name());
			pstmt.setString(2, mvo.getM_mobile());
			pstmt.setString(3, mvo.getM_email());
			rs = pstmt.executeQuery();
			mvo  =new MemberVo(); 
			while (rs.next()) {
				mvo.setM_pw(rs.getString("m_pw"));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return mvo;
	}
	
	public MemberVo memberLogin(MemberVo mvo) {
//		System.out.println("DB:넘어옴");
//		System.out.println(mvo.toString());
		StringBuffer sql = new StringBuffer();
		
		sql.append("select m_email,m_name,m_nickname from MEMBER ");
		sql.append("where m_pw = ? ");
		sql.append("and   m_email = ? ");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getM_pw());
			pstmt.setString(2, mvo.getM_email());
			rs = pstmt.executeQuery();
			mvo  =new MemberVo(); 
			while (rs.next()) {
				mvo.setM_email(rs.getString("m_email"));	
				mvo.setM_name(rs.getString("m_name"));
				mvo.setM_nickname(rs.getString("m_nickname"));				
			}	

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return mvo;
	}
	
}
