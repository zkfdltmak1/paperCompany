<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.board.vo.ForumVO" %>
<jsp:useBean id="fDao" class="com.board.dao.ForumDAO" ></jsp:useBean>
<jsp:useBean id="fvo" class="com.board.vo.ForumVO" ></jsp:useBean>

<%
	ArrayList<ForumVO> fList = (ArrayList<ForumVO>)session.getAttribute("fList"); 

	final int ROWSIZE =10;  // 한페이지에 보일 게시물 수
	final int BLOCK = 5; // 아래에 보일 페이지 최대개수 1~5 / 6~10 / 11~15 식으로 5개로 고정
	int pg = 1; //기본 페이지값;

	
	if(request.getParameter("pg")!=null) { //받아온 pg값이 있을때, 다른페이지일때
		pg = Integer.parseInt(request.getParameter("pg")); // pg값을 저장
	}

	int start = (pg*ROWSIZE) - (ROWSIZE-1); // 해당페이지에서 시작번호(step2)
	int end = (pg*ROWSIZE); // 해당페이지에서 끝번호(step2)
	
	int allPage = 0; // 전체 페이지수
	
	int startPage = ((pg-1)/BLOCK*BLOCK)+1; // 시작블럭숫자 (1~5페이지일경우 1, 6~10일경우 6)
	int endPage = ((pg-1)/BLOCK*BLOCK)+BLOCK; // 끝 블럭 숫자 (1~5일 경우 5, 6~10일경우 10)
	
	
	allPage = (int)Math.ceil(fList.size()/(double)ROWSIZE);
	
	
 	 if(endPage > allPage) {
		endPage = allPage;
	} 
     String session_email = (String)session.getAttribute("s_member_email");
 

%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js">
</script>
</head>
<body>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<%if(session_email==null){
	%>
		<script type="text/javascript">
			alert("정상적인 접근이 아닙니다.");
			location.href="/webTestProject/index.jsp";
		</script>
	<%
	}else{
		%>
	<form method="post" action ="/webTestProject/papercompany/forum/forum_write.jsp" id="forum_board_form_id">
	<div class="con">
		<table id="table_board" class="list">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>닉네임</th>
					<!-- <th>조회수</th> -->
				</tr>
			</thead>
			<tbody>
			<% 
			
			if(pg==allPage){
				for(int i=(pg-1)*ROWSIZE; i<fList.size(); i++ ){
			%>
				<tr>
					<td><%= fList.get(i).getForum_no() %></td>
					<td><a href="/webTestProject/papercompany/forum/forum_read.jsp?forum_number=<%= fList.get(i).getForum_no() %>"><%= fList.get(i).getForum_subject() %></a></td>
					<td>운영자</td>
				</tr>
			<%
				}
			}
			else{
				for(int i=(pg-1)*ROWSIZE; i<pg*ROWSIZE; i++ ){
			%>

				<tr>
					<td><%= fList.get(i).getForum_no() %></td>
					<td><a href="/webTestProject/papercompany/forum/forum_read.jsp?forum_number=<%= fList.get(i).getForum_no() %>"><%= fList.get(i).getForum_subject() %></a></td>
					<td>운영자</td>
				</tr>
				
			<%	
				}
			}	
			%>
			
			</tbody>
			<tfoot>
				<tr>
					
					
					<td colspan="2" id="pageno">
						<%
							if(pg>BLOCK) {
						%>
							[<a href="/webTestProject/papercompany/forum/forum_board.jsp?pg=1">◀◀</a>]
							[<a href="/webTestProject/papercompany/forum/forum_board.jsp?pg=<%=startPage-1%>">◀</a>]
						<%
							}
						%>
						
						<%
							for(int i=startPage; i<= endPage; i++){
								if(i==pg){
						%>
									[<%=i %>]
						<%
								}else{
									
						%>
									
									[<a href="/webTestProject/papercompany/forum/forum_board.jsp?pg=<%=i %>"><%=i %></a>]
						<%
								}
							}
						%>
						
						<%
							if(endPage<allPage){
						%>
							[<a href="/webTestProject/papercompany/forum/forum_board.jsp?pg=<%=endPage+1%>">▶</a>]
							[<a href="/webTestProject/papercompany/forum/forum_board.jsp?pg=<%=allPage%>">▶▶</a>]
						<%
							}
						%>
						</td>
		
					<td id="write">
						<%-- <%
						String ID = (String)session.getAttribute("id"); 
						String masterID = "abc";
						
						if (masterID.equals(ID)){ 
						%>  --%>
						<%
		                  String masterId = "jslee80130@gmail.com";
		                  if(masterId.equals(session_email)){ %>
		                  <a href="forum_write.jsp">글쓰기</a>
		                  <%
		                     }
		                  %>
						<%-- <% 
						} 
						%>  --%>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	</form>
	<%} %>
 	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>