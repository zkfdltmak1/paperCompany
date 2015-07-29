<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    String session_email = (String)session.getAttribute("s_member_email");
	    String session_name = (String)session.getAttribute("s_member_name");
	    String session_nickname = (String)session.getAttribute("s_member_nickname");
    %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
               maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="stylesheet" href="/webTestProject/style/css/main/mainHeader.css">
<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
<script type="text/javascript">
	function fnGetReviewList(){
		document.reviewList.method="post";
		document.reviewList.action="/webTestProject/review_board.review";//서블릿을 다녀옴.
		document.reviewList.submit();
	}
	function fbGetBoardList(){
		document.forumList.method="post";
		document.forumList.action="/webTestProject/board/forum.do";//서블릿을 다녀옴.
		document.forumList.submit();
	}
	
	function LogOut(){
		alert("로그아웃");
		document.logout.method="post";
		document.logout.action="/webTestProject/papercompany/mainBar/LogOut";//서블릿을 다녀옴.
		document.logout.submit();
		
	}
</script>
</head>
<body>
	<header class="header-user-dropdown">
		<div class="header-limiter">
			<h1><a href="/webTestProject/index.jsp">Paper<span>Company</span></a></h1>
			<nav>
				<!-- 이상하게 순서가 거꾸로 됨 -->
				<div class="header-user-menu">
					<b>회원정보</b>
					<ul>
						<li><a href="#">예매확인</a></li>
						<li><a href="#">마이페이지</a></li>
						<li><a href="#">회원정보수정</a></li>
					</ul>
				</div>
				<div class="header-user-menu">
					<b>커뮤니티</b>
					<ul>
						<li>
							<form name="reviewList">
								<a href="javascript:fnGetReviewList()">후기 게시판</a>
								<input type="hidden" name="command" value="getReviewList">
							</form>
						</li>
						<li><a href="#">Q&A</a></li>
					</ul>
				</div>
				<div class="header-user-menu">
					<b>예매</b>
					<ul>
						<li><a href="#">버스 예매</a></li>
						<li><a href="#">기차 예매</a></li>
						<li><a href="#">비행기 예매</a></li>
					</ul>
				</div>
				<div class="header-user-menu">
					<b>새 소식</b>
					<ul>
						<li>
							<form name="forumList">
								<a href="javascript:fbGetBoardList()">공지사항</a>
								<input type="hidden" name="command" value="mainTop_command"/>
							</form>
						</li>
					</ul>
				</div>
			</nav>
			<div>	
				<%if(session_email != null){%>
					<form name="logout">
						<input type="hidden" name="session_logout" value="<%=session_email%>">
						<a class="logout-button" href="/webTestProject/index.jsp" id="b_logout" onclick="LogOut(); return false;">Logout</a>
					</form>
				<%}%>
			</div>
		</div>
	</header>
</body>
</html>