<%@page import="com.board.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ReviewVO rvo = (ReviewVO)request.getAttribute("reviews_readVo");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function fnReviewUpdate(){
		var reviews_title = $("#reviews_title").val();
		var reviews_content = $("#reviews_content").val();
		var reviews_pw = $("#reviews_pw").val();
		
		if(reviews_title.replace(/\s/g,"") == ""){
			alert("제목을 입력하세요");
			$("#reviews_title").val("");
			$("#reviews_title").focus();
			return false;
		}
		if(reviews_content.replace(/\s/g,"") == ""){
			alert("내용을 입력하세요");
			$("#reviews_content").val("");
			$("#reviews_content").focus();
			return false;
		}
		if(reviews_pw.replace(/\s/g,"") == ""){
			alert("비밀번호를 입력하세요");
			$("#reviews_pw").val("");
			$("#reviews_pw").focus();
			return false;
		}
		if(reviews_title != "" && reviews_content != "" && reviews_pw != ""){
			$("#form_review_update").attr({action : "/webTestProject/review_board.review", 
													  mehtod : "post"}).submit();			
		}
	}
</script>
</head>
<body>
	<!-- Top  -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<div class="con">
		<form name="form_review_update" id="form_review_update">
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
							<input type="text" name="reviews_title" id="reviews_title" size="90" value="<%=rvo.getReviews_title()%>">
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="8" cols="78" name="reviews_content" id="reviews_content"><%=rvo.getReviews_content()%></textarea>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="reviews_pw" id="reviews_pw" size="90" value="<%=rvo.getReviews_pw()%>">
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td id="reviews_update" colspan="2">
							<a href="#" onclick="javascript:fnReviewUpdate(); return false;">수정하기</a> | 
							<a href="javascript:history.back()">취소</a>
						</td>
					</tr>
				</tfoot>
			</table>
				<input type="hidden" name="command" value="review_update">
				<input type="hidden" name="reviews_number" value="<%=rvo.getReviews_number()%>">
				<input type="hidden" name="reviews_title" value="<%=rvo.getReviews_title()%>">
				<input type="hidden" name="reviews_content" value="<%=rvo.getReviews_content()%>">
				<input type="hidden" name="reviews_pw" value="<%=rvo.getReviews_pw()%>">
			</form>
	</div>
	<!-- footer -->	
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>