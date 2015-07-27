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
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
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
	
	
	/* $(document).ready(function(){
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
	
	$(function(){
		//모든 서브 메뉴 숨기기
		
		$("ul.menu>li>.sub").hide();
		$("ul.menu>li").hover(function(){
			//마우스가 올라간 현재 요소의 하위요소중에서".sub"클래스를 갖는 요소를 찾는다
			//찾아낸 요소에게 slideDown 애니메이션을 적용한다.
			$(this).find(".sub").slideDown(300);
		},function(){
			//모든 서브메뉴에 대하여 숨김처리
			$("ul.menu>li>.sub").slideUp(300);
		});
		
		
	}); */
</script>
<style type="text/css">
	
</style>
</head>
<body>
	<%-- <div id="display_size">
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
				<ul class="menu">
					<li>
						<a href="#">새소식</a>
						<ul class="sub">
							<li>
								<form name="forumList">
									<a href="javascript:fbGetBoardList()">공지사항</a>
									<input type="hidden" name="command" value="mainTop_command"/>
								</form>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">예매</a>
						<ul class="sub">
							<li>
								<a href="#">버스 예매</a>
							</li>
							<li>
								<a href="#">기차 예매</a>
							</li>
							<li>
								<a href="#">비행기 예매</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">커뮤니티</a>
						<ul class="sub">
							<li>
								<form name="reviewList">
									<a href="javascript:fnGetReviewList()">후기 게시판</a>
									<input type="hidden" name="command" value="getReviewList">
								</form>
							</li>
							<li>
								<a href="#">Q&A</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
		</header>
	</div> --%>
	<header class="header-user-dropdown">
	<div class="header-limiter">
		<h1><a href="#">Paper<span>Company</span></a></h1>
		<nav>
			<div class="header-user-menu">
				<b>새 소식</b>
				<ul>
					<li><a href="#">공지사항</a></li>
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
				<b>커뮤니티</b>
				<ul>
					<li><a href="#">후기 게시판</a></li>
					<li><a href="#">Q&A</a></li>
				</ul>
			</div>
			<div class="header-user-menu">
				<b>마이페이지</b>
				<ul>
					<li><a href="#"></a></li>
				</ul>
			</div>
			<!-- <a href="#">Roles <span class="header-new-feature">new</span></a> -->
		</nav>
		
		<div class="header-user-menu">			
			<a href="#" class="highlight logout-button">Logout</a>
		</div>
	</div>
</header>
</body>
</html>