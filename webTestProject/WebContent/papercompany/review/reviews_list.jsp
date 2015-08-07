<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.board.vo.ReviewVO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ko">
<%
	int size = 0;	
	List<ReviewVO> reviewList = 
			(List<ReviewVO>)request.getAttribute("reviewList");
	if(reviewList !=null){         //NullPointerException
		size = reviewList.size();
	}
	
	int total = 0;
	
//	out.print("size:"+size);
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />

</head>
<body>
	<!-- Top  -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	
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
									<a href="/webTestProject/review_board.review?command=getReviewRead&reviews_number=<%= rVO.getReviews_number() %>" ><%=rVO.getReviews_title()%></a>
							</td>								
						<td><%=rVO.getM_email() %></td>
					</tr>
<%
			}
		}		
%>	
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2" id="pageno">
							<a href="#">이전</a>
							<a href="#">1</a>
							<a href="#">다음</a>
						</td>
						<td id="write">
							<a href="/webTestProject/papercompany/review/reviews_write.jsp">글쓰기</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- footer -->
 		<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	</body>
</html>