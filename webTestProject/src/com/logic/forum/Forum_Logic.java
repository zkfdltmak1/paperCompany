package com.logic.forum;

import java.util.ArrayList;

import com.board.dao.ForumDAO;
import com.board.vo.ForumVO;

public class Forum_Logic {

	
	
	
	public ArrayList<ForumVO> first_list(int start , int end){
		ForumDAO fDao = new ForumDAO();
		ArrayList<ForumVO> fList = new ArrayList<ForumVO>();
		fList = fDao.forumSearch(start, end);
		
		return fList;
	}
}
