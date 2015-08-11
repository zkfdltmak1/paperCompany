<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.board.vo.ReviewVO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ko">
<%
	String session_email = (String)session.getAttribute("s_member_email");

	int size = 0;	
	List<ReviewVO> reviewList = 
			(List<ReviewVO>)request.getAttribute("reviewList");
	if(reviewList !=null){         //NullPointerException
		size = reviewList.size();
	}
	
	int total = Integer.parseInt(String.valueOf(request.getAttribute("total")));
	int numPerPage = Integer.parseInt(String.valueOf(request.getAttribute("numPerPage")));
	int pagePerBlock = Integer.parseInt(String.valueOf(request.getAttribute("pagePerBlock")));
	int nowPage = Integer.parseInt(String.valueOf(request.getAttribute("nowPage")));
	int startPageNum = Integer.parseInt(String.valueOf(request.getAttribute("startPageNum")));
	int endPageNum = Integer.parseInt(String.valueOf(request.getAttribute("endPageNum")));
	int startBlockNum = Integer.parseInt(String.valueOf(request.getAttribute("startBlockNum")));
	int endBlockNum = Integer.parseInt(String.valueOf(request.getAttribute("endBlockNum")));
	int totalPage = Integer.parseInt(String.valueOf(request.getAttribute("totalPage")));
%>
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
					<col width="150">
					<col width="60%">
					<col width="250">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>닉네임</th>
					</tr>
				</thead>
				<tbody>
<%
		if(size ==0){
%>
					<tr>
						<td colspan="3"> 등록된 글이 없습니다.</td>
					</tr> 
<%
		} else {
			for(int i=0;i<size;i++){
				if(i==size)break;
				ReviewVO rVO= reviewList.get(i);
%>				
					<tr>
						<td><%=rVO.getReviews_number()%></td>
							<td>
									<a href="/webTestProject/review_board.review?command=getReviewRead&reviews_number=<%= rVO.getReviews_number() %>&nowPage=<%=nowPage%>" ><%=rVO.getReviews_title()%></a>
							</td>								
						<td><%=rVO.getM_nickname() %></td>
					</tr>
<%
			}
		}		
%>	
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" id="pageno">
							<!-- 현재 페이지번호가 pagePerBlock 보다 클때
							        (현재 값으로 현재 페이지번호가 6일때 부터)-->
							<%if(nowPage > 1){%>
								<%if(nowPage <= 1){%>
									<a href="/webTestProject/review_board.review?command=getReviewList&nowPage=<%=1%>">이전</a>
								<%}else{%>
									<a href="/webTestProject/review_board.review?command=getReviewList&nowPage=<%=nowPage-1%>">이전</a>
								<%}%>
							<%}else{%>
								이전
							<%}%>
							&nbsp;&nbsp;
							<%for(int i=startBlockNum; i<=endBlockNum; i++){%>
								<%if(i == nowPage){%>
									<b><%=i%></b>
								<%}else{%>
									<a href="/webTestProject/review_board.review?command=getReviewList&nowPage=<%=i%>"><%=i%></a>
								<%}%>
							<%}%>
							&nbsp;&nbsp;
							<!-- 끝 블럭숫자가 총 페이지 수보다 작을때 -->
							<%if(nowPage < totalPage){%>
								<a href="/webTestProject/review_board.review?command=getReviewList&nowPage=<%=nowPage+1%>">다음</a>
							<%}else{%>
								다음
							<%}%>
						</td>
						<td id="write">
							<a href="/webTestProject/papercompany/review/reviews_write.jsp?nowPage=<%=nowPage%>">글쓰기</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<%}%>
		<!-- footer -->
 		<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	</body>
</html>