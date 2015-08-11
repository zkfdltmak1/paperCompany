<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.board.vo.ForumVO" %>
<jsp:useBean id="fDao" class="com.board.dao.ForumDAO" ></jsp:useBean>
<jsp:useBean id="fvo" class="com.board.vo.ForumVO" ></jsp:useBean>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
</head>
<body>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>

	<c:choose>
		<c:when test="${empty sessionScope.s_member_email}">
			<h1>정상적인 접근이 아닙니다</h1>
		</c:when>
		<c:otherwise>
	<form method="post" action ="/webTestProject/papercompany/forum/forum_write.jsp" id="forum_board_form_id">
	<div class="con">
		<table id="table_board" class="list">
			<thead>
				<tr>
					<th>번호</th>
					<th id="title_size">제목</th>
					<th>닉네임</th>
					<!-- <th>조회수</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fList" items="${requestScope.fList }">
					<tr>
						<td>${fList.forum_no}</td>
						<td><a href="/webTestProject/board/forum.do?command=forum_read_command&forum_number=${fList.forum_no}">${fList.forum_subject}</a></td>
						<td>운영자</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" id="pageno">
						
						<c:if test="${requestScope.pg > requestScope.BLOCK }">
								[<a href="/webTestProject/board/forum.do?command=page_check&pg=1">◀◀</a>]
								[<a href="/webTestProject/board/forum.do?command=page_check&pg=${requestScope.startPage-1 }">◀</a>]
						</c:if>
							
							<c:forEach var="page" begin="${requestScope.startPage}" end="${requestScope.endPage }">
								<c:choose>
									<c:when test="${page==requestScope.pg }">
											[${page}]
									</c:when>
									<c:otherwise>
											[<a href="/webTestProject/board/forum.do?command=page_check&pg=${page}">${page}</a>]
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
								
						<c:if test="${requestScope.endPage < requestScope.allPage}">
							[<a href="/webTestProject/board/forum.do?command=page_check&pg=${requestScope.endPage+1}">▶</a>]
							[<a href="/webTestProject/board/forum.do?command=page_check&pg=${requestScope.allPage }">▶▶</a>]
						</c:if>
					</td>
		
					<td id="write">
						<c:if test="${sessionScope.s_member_email == sessionScope.masterID }">
		                  <a href="/webTestProject/papercompany/forum/forum_write.jsp">글쓰기</a>
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	</form>
	
		</c:otherwise>
	</c:choose>
 	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>