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
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../../style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../style/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../style/css/main/mainTop.css"/>
<script type="text/javascript">
	function fnGetReview(){
		document.f_getReview.action="/webTestProject/review_board.review";
		document.f_getReview.method="post";
		document.f_getReview.submit();
	}
	
	function LogOut(){
		alert("로그아웃");

		document.logout.method="post";
		document.logout.action="/webTestProject/papercompany/mainBar/LogOut";//서블릿을 다녀옴.
		document.logout.submit();
		
	}
	
	
	$(document).ready(function(){
	    $(".dropdown-menu>a").click(function(){
	        var submenu = $(this).next("ul");
	
	        // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
	        if( submenu.is(":visible") ){
	            submenu.slideUp();
	        }else{
	            submenu.slideDown();
	        }
	    }).mouseover(function(){
	        $(this).next("ul").slideDown();
	    });
	    
	    
	    
	});
</script>
</head>
<body>
	<div id="display_size">
		<header>
			<%if(session_email != null){ %>
			<div id="div_01">
				<%=session_name %>님 반갑습니다. 
			</div>
			<form name="logout">
			<input type="hidden" name="session_logout" value="<%=session_email%>">
			<a href="/webTestProject/index.jsp" id="b_logout" onclick="LogOut(); return false;" >로그아웃</a>
			</form>
			<%} %>
			<nav>
				<ul>
					<li><a href="../forum/forum_board.jsp">공지사항</a></li>
					<li class="menu">
		            	<div class="bs-example">
							<div class="dropdown">
								<a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">기차</a></li>
									<li><a href="#">비행기</a></li>
									<li><a href="#">버스</a></li>
								</ul>
							</div>
						</div>
					</li>
	        		<li>
	        			<form name="f_getReview">
	        				<a href="javascript:fnGetReview()">후기 게시판</a>
	        				<input type="hidden" name="command" value="getReviewList"> 
	        			</form>
	        		</li>
				</ul> 
			</nav>
		</header>
	</div>
</body>
</html>