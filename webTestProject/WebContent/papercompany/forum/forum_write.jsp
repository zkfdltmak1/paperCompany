<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Forum_write</title>
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

<!-- jquery로 처리 -->
<script type="text/javascript" src="../../style/js/forum/forum_write.js">
</script>

</head>
<body>
	<c:choose>
		<c:when test="${empty sessionScope.s_member_email}">
			<h1>정상적인 접근이 아닙니다</h1>
		</c:when>
		<c:otherwise>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<form name="w_forum" id="w_forum_id" enctype="multipart/form-data">
		<input type="hidden" name="command" value="forum_write_command" >
		<div class="con">
			<table id="table_board" class="list">
				<thead>
					<tr>
						<th colspan="3">
							글 쓰 기
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>제목</td>
						<td>
							<input type="text" name="forum_title" size="80" id="forum_title_id"/>
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td>
							<input type="file" name="forum_file" id="forum_file_id"/>
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="8" cols="80" name="forum_content" id="forum_content_id" ></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td id="write" >						
							<a href="#" onclick="forum_write()" >글쓰기</a>	
						</td>
						<td> 
							<a href="#" onclick="history.back(); return false;">목록으로</a> 
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