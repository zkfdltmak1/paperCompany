package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.board.vo.ForumVO;
import com.util.DBConnectionMgr;

public class ForumDAO {
	
	DBConnectionMgr dbMgr = new DBConnectionMgr();
	
	
	
	// 공지사항 게시판 첫 화면에 뿌려주는 목록
	public ArrayList<ForumVO> forumSearch(){
		
		ArrayList<ForumVO> fList = new ArrayList<ForumVO>();
		StringBuffer sb = new StringBuffer();
		
		sb.append("select forum_number, forum_title from forum order by TO_NUMBER(forum_number) DESC");
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		ForumVO fvo = null;
		
		try {
			fvo = new ForumVO();
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				fvo = new ForumVO();
				fvo.setForum_no(rs.getInt("forum_number"));
				fvo.setForum_subject(rs.getString("forum_title"));
				fList.add(fvo);
			}
		}catch(SQLException se){
				System.out.println("inmoney = [ "+se+" ]");
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return fList;
	}
	
	
	//공지사항 타이틀 클릭했을때 클릭한 타이틀 정보 받아가는 메소드
	public ForumVO titleSearch(int forum_number){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select forum_number,forum_title, forum_content from forum ");
		sb.append("where forum_number = ?");
		
		PreparedStatement pstmt =null;
		Connection con = null;
		ResultSet rs = null;
		ForumVO fvo = new ForumVO();
		
		try {
			fvo = new ForumVO();
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			
			pstmt.setInt(1, forum_number);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				fvo.setForum_no(rs.getInt("forum_number"));
				fvo.setForum_subject(rs.getString("forum_title"));
				fvo.setForum_content(rs.getString("forum_content"));
			}
		}catch(SQLException se){
			System.out.println("inmoney = [ "+se+" ]");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbMgr.freeConnection(con, pstmt, rs);
		
		return fvo;
	}
	
	
	
	
	//글쓰기(제목,본문내용)
			public boolean writeForum(String forum_title, String forum_content){
				StringBuffer dml = new StringBuffer();
				dml.append("INSERT INTO forum(forum_number,forum_title,forum_content,m_email) ");
				dml.append("VALUES(FORUM_SEQ.nextval, ?, ?, 'kdjjjpig@naver.com') ");
				
				boolean success = false;
				Connection	con = null;
				PreparedStatement pstmt = null;
				
				try{
					con	=dbMgr.getConnection();
					pstmt = con.prepareStatement(dml.toString());
					pstmt.setString(1,forum_title);  //글제목
					pstmt.setString(2,forum_content); //글내용
					int i = pstmt.executeUpdate();
					if(i==1)success = true;
					
				}catch(SQLException se){
					System.out.println("writeforum = [ "+se+" ]");
				}catch (Exception e){
					System.out.println("writeforum = [ "+e+" ]");
				
				}
				return success;
			}
			
			//글삭제(글번호)
			public boolean deleteForum(int forum_number){
				StringBuffer dml = new StringBuffer();
				dml.append("DELETE FROM forum WHERE forum_number = ? ");
			
				
				boolean success = false;
				Connection	con = null;
				PreparedStatement pstmt = null;
				
				try{
					con	= dbMgr.getConnection();
					pstmt = con.prepareStatement(dml.toString());
					pstmt.setInt(1,forum_number);  //글번호
					int i = pstmt.executeUpdate();
					if(i==1)success = true;
					
				}catch(SQLException se){
					System.out.println("deleteforum = [ "+se+" ]");
				}catch (Exception e){
					System.out.println("deleteforum = [ "+e+" ]");
				}
				return success;
			}
			
		/*//글번호를 토대로 본문수정
			public void updateForum(String forum_title, String forum_content, int forum_number){
				StringBuffer dml = new StringBuffer();
				dml.append("UPDATE forum SET forum_title= '?', forum_content = '?' where forum_number = ? ");
			
				
				
				Connection	con = null;
				PreparedStatement pstmt = null;
				
				try{
					con	= dbMgr.getConnection();
					pstmt = con.prepareStatement(dml.toString());
					pstmt.setString(1,forum_title);  //글제목
					pstmt.setString(2,forum_content); //글내용
					pstmt.setInt(3,forum_number);  //글번호
					pstmt.executeQuery();
					
					
				}catch(SQLException se){
					System.out.println("updateforum = [ "+se+" ]");
				}catch (Exception e){
					System.out.println("updateforum = [ "+e+" ]");
				}
			}
		*/
	
}
