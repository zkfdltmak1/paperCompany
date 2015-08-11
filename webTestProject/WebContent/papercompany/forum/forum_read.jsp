<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.board.vo.ForumVO" %>
<jsp:useBean id="fDao" class="com.board.dao.ForumDAO" ></jsp:useBean>
<jsp:useBean id="fvo" class="com.board.vo.ForumVO" ></jsp:useBean>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="forum_number" value="${forum_number}" scope="session"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />

<!-- jquery로 처리 -->
<script type="text/javascript" src="/webTestProject/style/js/forum/forum_read.js">
</script>

</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.s_member_email}">
			<h1>정상적인 접근이 아닙니다</h1>
		</c:when>
		<c:otherwise>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<form name="r_forum" id="r_forum_id">			
	<input type="hidden" name="command" value="forum_delete_command"/>
	<div class="con">
		<table id="table_board" class="list">
			<colgroup>
				<col width="150px">
				<col width="650px">
			</colgroup>
			<thead>
				<tr>
					<th>title</th>
					<th>${ requestScope.fvo.forum_subject}</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>content</td>
					<td style="word-break:break-all;">${requestScope.fvo.forum_content}</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
						<a href="#" onclick="history.back(); return false;">이전</a>
					</td>
					<td>
						<c:if test="${requestScope.masterId == sessionScope.s_member_email }">
							<a href="forum_read_modify.jsp?forum_number=${requestScope.forum_number }">수정</a>&nbsp;&nbsp;
							<a href="#" onclick="forum_delete()" id="forum_delete_id">삭제</a>
						</c:if>
					</td>						
				</tr>
			</tfoot>
		</table>
	</div>
	</form>
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	</c:otherwise>
	</c:choose>
</body>
</html>