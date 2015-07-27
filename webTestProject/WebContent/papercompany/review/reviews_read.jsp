<%@page import="com.board.vo.ReplyVO"%>
<%@ page import="com.board.vo.ReviewVO, java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ReviewVO rvo = (ReviewVO)request.getAttribute("review_read");
	List<ReplyVO> rpList = (List<ReplyVO>)request.getAttribute("rpList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
<script type="text/javascript">
	function fnReadUpdate(){
		document.f_readUpdate.method = "post";
		document.f_readUpdate.action = "/webTestProject/review_board.review";
		document.f_readUpdate.submit();
	}
	
	function fnReadDelete(){
		document.f_readDelete.method = "post";
		document.f_readDelete.action = "/webTestProject/review_board.review";
		document.f_readDelete.submit();
	}
</script>
</head>
<body>
	<!-- Top  -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	
	<div class="container">
		<table id="table_board" class="list">
			<colgroup>
				<col>
				<col>
				<col>
			</colgroup>
			<!-- 본문 제목 -->
			<thead>
				<tr>
					<th colspan="3">
						<%=rvo.getReviews_title() %>
					</th>
				</tr>
			</thead>
			<!-- 본문 내용 -->
			<tbody>
				<tr id="reviews_read_tbody" class="read">
					<td colspan="3">
						<%=rvo.getReviews_content() %>
					</td>
				</tr>
				<!-- for문 돌림 -->
				<%if(rpList == null){%>
				<tr>
					<td colspan="3">댓글을 등록해 주세요</td>
				</tr>
				<%} else{%>
				<%for(int i=0; i<rpList.size(); i++){%>
				<tr id="reply_list">
					<td id="reply_list_id">
						<%= rpList.get(i).getReply_id() %>
					</td>
					<td colspan="2" id="reply_list_content">
						<%= rpList.get(i).getReply_content() %>
					</td>
				</tr>
				<%}
				}%>
				<!-- for문 돌림 -->
				<form method="post" action="insertReply">
					<tr class="reply">
						<td colspan="2">
								<input type="text" name="reviews_reply" id="reviews_reply" size="40">
						</td>
						<td>
							<input type="submit" value="댓글 작성">
						</td>
					</tr>
				</form>
			</tbody>
			<tfoot class="r_read">
				<td colspan="3">
					<form name="f_readUpdate">
							<a href="javascript:fnReadUpdate()">수정</a> | 
							<input type="hidden" name="command" value="readUpdate">
							<input type="hidden" name="reviews_number" value="<%= rvo.getReviews_number() %>">
							<input type="hidden" name="reviews_content" value="<%= rvo.getReviews_content() %>">
					</form>
					<form name="f_readDelet">
							<a href="javascript:fnReadDelete()">삭제</a> | 
							<input type="hidden" name="command" value="readDelete">
							<input type="hidden" name="reviews_number" value="<%= rvo.getReviews_number() %>">
							<input type="hidden" name="reviews_pw" value="<%= rvo.getReviews_pw() %>">
					</form>
					<a href="javascript:history.back()">목록으로</a>
				</td>
			</tfoot>
		</table>
	</div>
	
	<!-- footer -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>