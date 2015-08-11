<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	 String session_email = (String)session.getAttribute("s_member_email");
	 String join_succes = (String)request.getAttribute("join_succes");
	
	if("가입성공".equals(join_succes)){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('회원 가입에 성공 하셨습니다');");
		out.write("</script>\n");
	}
	else if("가입실패".equals(join_succes)){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('회원 가입에 실패 하셨습니다');");
		out.write("</script>\n");
	}
	
	String member_email = (String)request.getAttribute("member_email");
	
	if (member_email != null){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('사용자의 아이디는 :"+ member_email+ "');");
		out.write("</script>\n");
		
	}
	else if("이메일없음".equals(member_email)){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('사용자의 아이디가 없습니다.');");
		out.write("</script>\n");
	}
	
	String member_pw = (String)request.getAttribute("member_pw");
	if (member_pw != null){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('사용자의 패스워드는 :"+ member_pw+ "');");
		out.write("</script>\n");
		
	}
	else if("패스워드없음".equals(member_pw)){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('사용자의 패스워드가 없습니다');");
		out.write("</script>\n");
	}
	
	String login =  (String)request.getAttribute("login_null");
	
	if("로그인실패".equals(login)){
		out.write("<script type='text/javascript'>\n");
		out.write("alert('아이디/패스워드를 확인하여 주세요');");
		out.write("</script>\n");
	}
%>   
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
               maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Index Page</title>
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<script type="text/javascript" src="/webTestProject/style/js/indexModal/indexModal.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/indexModal/indexModal.css"/>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/main/main.css"/>
<script type="text/javascript">
	$(document).ready(function(){
		var session = <%=session_email%>;
		if(session == null){
			$("#loginModal").modal("show");
			$("#email").focus();
		}
		else{
			$("#loginModal").modal("hide");
		}
	});
</script> 
</head>
<body>
	<!-- 헤더 영역 -->
	<jsp:include page="./papercompany/mainBar/mainTop.jsp"></jsp:include>
	<!-- 헤더 영역 -->

	<!-- 본문 영역 -->
	<section>
		<div id="index_section">
			<article><a href="#" ><img id="img1" src="/webTestProject/style/image/bus4.jpg" ></a></article>
			<article><a href="/webTestProject/papercompany/airplane/airBooking.jsp" ><img id="img2" src="/webTestProject/style/image/airplane2.jpg" ></a></article>
			<article><a href="/webTestProject/train/train.do?command=train_first"><img id="img3" src="/webTestProject/style/image/train4.jpg" ></a></article>	
		</div>
	</section>
	<!-- 본문 영역 -->
		
	<!-- 푸터영역 -->
	<jsp:include page="./papercompany/mainBar/mainFooter.jsp"></jsp:include>
	<!-- 푸터영역 -->
	
	<!-- 모달 부분 코드는 제일 아래로 -->
	<!-- loginModal -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" 
				role="dialog" aria-labelledby="mySmallModalLabel" 
				id="loginModal" 	data-backdrop="static"  data-keyboard="false">
		<div class="modal-dialog modal-sm"  id="modal-dialog1">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">로그인</h3>
				</div>
				<form method="post" action="/webTestProject/memberLogin">
					<input type="hidden" value="session_Login">
					<fieldset class="loginForm">
						<div class="form-group">
							<input class="form-control" placeholder="이메일 주소" name="email" id="email" type="email" required="required" autofocus="autofocus"/>
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="비밀번호입력" name="pwd" id="pwd" type="password" value="" required="required" />
						</div>
						<button type="submit" class="btn btn-sm btn-success">로 그 인</button>
						<button type="button" class="btn btn-sm btn-success" onclick="fnJoin()">회원가입</button>
						<br/>
						<a href="javascript:fnSelectId();">아이디</a> / 
						<a href="javascript:fnSelectPW();">비밀번호 찾기</a>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- loginModal -->
	<!-- memberModal -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" 
				role="dialog" aria-labelledby="mySmallModalLabel" 
				id="joinModal" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-sm"  id="modal-dialog2">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">회원가입</h3>
				</div>
				<form method="post" action="/webTestProject/member_join">
					<fieldset class="joinForm">
						<div class="form-group">
							<input class="form-control" placeholder="이메일 주소" name="email" id="email" type="email" required="required" autofocus="autofocus"/>
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="비밀번호 입력" name="pwd1" id="pwd1" type="password" required="required" />
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="비밀번호 입력" name="pwd2" id="pwd2" type="password" required="required" />
						</div>
						<p class="form-group">
							<input class="form-control" placeholder="닉네임 입력" name="nickname" id="nickname" type="text" required="required" />
						</p>
						<p class="form-group">
							<input class="form-control" placeholder="이름 입력" name="name" id="name" type="text" required="required" />
						</p>
						<p class="form-group">
							<input class="form-control" placeholder="전화 번호 입력(-빼고 입력)" name="phone" id="phone" type="text" required="required" />
						</p>
						<button type="submit" class="btn btn-sm btn-success">회원가입</button>
						<button type="button" class="btn btn-sm btn-success" onclick="fnBack()">뒤로</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- selectId -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" 
				role="dialog" aria-labelledby="mySmallModalLabel" 
				id="selectIDModal" 	data-backdrop="static"  data-keyboard="false">
		<div class="modal-dialog modal-sm"  id="modal-dialog1">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">아이디찾기</h3>
				</div>
				<form method="post" action="/webTestProject/memberSelectId">
					<fieldset class="selectIDForm">
						<div class="form-group">
							<input class="form-control" placeholder="이름 입력" name="name" id="name" type="text" required="required" />
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="전화 번호 입력(-빼고 입력)" name="phone" id="phone" type="text" required="required" />
						</div>
						<br/>
						<button type="submit" class="btn btn-sm btn-success">찾기</button>
						<button type="button" class="btn btn-sm btn-success" onclick="fnBack()">뒤로</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!--selectID  -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" 
				role="dialog" aria-labelledby="mySmallModalLabel" 
				id="selectPWModal" 	data-backdrop="static"  data-keyboard="false">
		<div class="modal-dialog modal-sm"  id="modal-dialog1">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">비밀번호 찾기</h3>
				</div>
				<form method="post" action="/webTestProject/memberSelectPW">
					<fieldset class="selectPWForm">
						<div class="form-group">
							<input class="form-control" placeholder="이메일 주소" name="email" id="email" type="email" required="required" autofocus="autofocus"/>
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="이름 입력" name="name" id="name" type="text" required="required" />
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="전화 번호 입력(-빼고 입력)" name="phone" id="phone" type="text" required="required" />
						</div>
						<br/>
						<button type="submit" class="btn btn-sm btn-success">찾기</button>
						<button type="button" class="btn btn-sm btn-success" onclick="fnBack()">뒤로</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body> 
</html> 