<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.board.vo.ForumVO" %>
<jsp:useBean id="fDao" class="com.board.dao.ForumDAO" ></jsp:useBean>
<jsp:useBean id="fvo" class="com.board.vo.ForumVO" ></jsp:useBean>
 
<%! 
	int forum_number =0;
%>

<%
	forum_number = Integer.parseInt(request.getParameter("forum_number"));
	
	fvo = fDao.titleSearch(forum_number);
	
	request.setAttribute("forum_number", forum_number);
		
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/css/board/board.css" />
</head>
<body>
	<div class="container">
		<table id="table_board" class="list">
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">
						글 쓰 기
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="reviews_title" size="78">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="8" cols="70" name="reviews_content"><%=fvo.getForum_content()%></textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td id="write" colspan="2">
						<a href="#">글쓰기</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>