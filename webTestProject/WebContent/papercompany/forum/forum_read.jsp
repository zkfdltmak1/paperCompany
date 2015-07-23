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
	 
	session.setAttribute("forum_number", forum_number);
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="stylesheet" type="text/css" href="../../style/css/board/board.css" />
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js">
</script>

<!-- jquery로 처리 -->
<script type="text/javascript" src="../../style/js/forum/forum_read.js">
</script>

<!-- 자바 스크립트로 처리 -->
<script type="text/javascript">
	
	/* function forum_delete(){
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
			document.r_forum.method="post";
			document.r_forum.action="/paperProject/board/forum.do";
			document.r_forum.submit();
		}else{   //취소
		    return;
		}
	} */
	
</script>
</head>
<body>
	<form name="r_forum" id="r_forum_id">			
	<input type="hidden" name="command" value="forum_delete_command"/>
	<div class="container">
		<table id="table_board" class="list">
			<colgroup>
				<col>
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>title</th>
					<th><%= fvo.getForum_subject() %></th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>content</td>
					<td><%= fvo.getForum_content() %></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<a href="#" onclick="history.back(); return false;">이전</a>
					</td>
					<td>
						<a href="#" onclick="forum_delete()" id="forum_delete_id">삭제</a>
					</td>						
				</tr>
			</tfoot>
		</table>
	</div>
	</form>
</body>
</html>