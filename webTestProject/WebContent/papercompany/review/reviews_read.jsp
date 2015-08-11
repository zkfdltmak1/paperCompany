<%@page import="com.board.vo.ReplyVO"%>
<%@ page import="com.board.vo.ReviewVO, java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ReviewVO rvo = (ReviewVO)request.getAttribute("review_read");
	List<ReplyVO> rpList = (List<ReplyVO>)request.getAttribute("rpList");
	String session_id = (String)session.getAttribute("s_member_email");
	int nowPage = Integer.parseInt(request.getParameter("nowPage"));
	
	String session_email = (String)session.getAttribute("s_member_email");
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
	function fnReadUpdateProc(){
		/* document.f_readUpdate.method = "post";
		document.f_readUpdate.action = "/webTestProject/review_board.review";
		document.f_readUpdate.submit(); */
		$("#f_readUpdate").attr({
			method : "post",
			action : "/webTestProject/review_board.review"
		}).submit();
	}
	
	function fnReadDelete(){
		/* document.f_readDelete.method = "post";
		document.f_readDelete.action = "/webTestProject/review_board.review";
		document.f_readDelete.submit(); */
		var result = $(confirm("정말 삭제하겠습니까?"));
		alert(result);
		if(result){
			$("#f_readDelete").attr({
				method : "post",
				action : "/webTestProject/review_board.review"
			}).submit();			
		}
		else{
			location.href="/webTestProject/review_board.review?command=getReviewList";
		}
	}
	
	function insertReply(){
		$("#insertReply").attr({
			method : "post",
			action : "/webTestProject/review_board.review"
		}).submit();
	}
	
</script>
</head>
<body>
	<!-- Top  -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	
	<%if(session_email.equals(null) || session_email == null ||
			session_email == ""){%>
			<script type="text/javascript">
			$(function(){
				alert("정상적인 접근이 아닙니다.\n로그인을 하여 주세요.");
				location.href="/webTestProject/index.jsp";
			});
		</script>
	<%}else{%>
	<div class="con">
		<table id="table_board" class="list">
			<colgroup>
				<col>
				<col>
			</colgroup>
			<!-- 본문 제목 -->
			<thead>
				<tr>
					<th colspan="2">
						<%=rvo.getReviews_title() %>
					</th>
				</tr>
			</thead>
			<!-- 본문 내용 -->
			<tbody>
				<tr id="reviews_read_tbody" class="read">
					<td colspan="2" style="word-break:break-all;">
						<%=rvo.getReviews_content() %>
					</td>
				</tr>
				<!-- for문 돌림 -->
				<%if(rpList == null){%>
				<tr>
					<td colspan="2">댓글을 등록해 주세요</td>
				</tr>
				<%} else{%>
				<%for(int i=0; i<rpList.size(); i++){%>
				<tr id="reply_list">
					<td id="reply_list_id">
						<%= rpList.get(i).getReply_content() %>
					</td>
					<td id="reply_list_content" style="word-break:break-all;">
						<%= rpList.get(i).getReply_id() %>
					</td>
				</tr>
				<%}
				}%>
				<!-- for문 돌림 -->
				<form id="insertReply">
					<tr class="reply">
						<td>
							<div >
								<input type="text" name="reviews_reply" id="reviews_reply" size="90">
							</div>
						</td>
						<td>
							<div>
								<input type="button" value="댓글 작성" onclick="insertReply()">
								<input type="hidden" name="command" value="insertReply">
								<input type="hidden" name="reviews_number" value="<%=rvo.getReviews_number()%>">
								<input type="hidden" name="session_id" value="<%=session_id%>">
								<input type="hidden" name="nowPage" value="<%=nowPage%>">
							</div>
						</td>
					</tr>
				</form>
			</tbody>
			<tfoot class="r_read">
				<td colspan="2">
					<form name="f_readUpdate" id="f_readUpdate">
							<a href="javascript:fnReadUpdateProc()">수정</a> | 
							<input type="hidden" name="command" value="readUpadateProc">
							<input type="hidden" name="reviews_number" value="<%=rvo.getReviews_number()%>">
							<input type="hidden" name="reviews_title" value="<%=rvo.getReviews_title()%>">
							<input type="hidden" name="reviews_content" value="<%=rvo.getReviews_content()%>">
							<input type="hidden" name="reviews_pw" value="<%=rvo.getReviews_pw()%>">
							<input type="hidden" name="nowPage" value="<%=nowPage%>">
					</form>
					<%if(session_id.equals(rvo.getM_email())){%>
					<form name="f_readDelet" id="f_readDelete">
							<a href="javascript:fnReadDelete()">삭제</a> | 
							<input type="hidden" name="command" value="read_delete">
							<input type="hidden" name="reviews_number" value="<%= rvo.getReviews_number() %>">
							<input type="hidden" name="reviews_pw" value="<%= rvo.getReviews_pw() %>">
							<input type="hidden" name="reviews_reply_size" value="<%= rpList.size() %>">
							<input type="hidden" name="nowPage" value="<%=nowPage%>">
					</form>
					<%}%>
					<a href="/webTestProject/review_board.review?command=getReviewList&nowPage=<%=nowPage%>">목록으로</a>
				</td>
			</tfoot>
		</table>
	</div>
	<%}%>
	
	<!-- footer -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>