<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%
   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
   String today = formatter.format(new java.util.Date());
   Date date = new java.util.Date();
   date.setMonth(date.getMonth()+1);
   String endday = formatter.format(date);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
</script>
<style>
	body{
		float: 0 auto;
	}
	.container{
		padding-top: 240px;
		padding-bottom: 240px;
	}
</style>
</head>
<body>
	<!-- 헤더 영역 -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<!-- 헤더 영역 -->
	
	<!-- 본문 영역 -->
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="form-horizontal" role="form">
					<fieldset>
						<!-- Form Name -->
						<legend><h3>비행기 예매</h3></legend>
						
						<!-- 출발지, 도착지 -->
						<div class="form-group">
							<label class="col-sm-3 control-label">장소</label>
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_startCity" name="airBooking_startCity">
									<option>출발지</option>
									<!-- DB에서? -->
								</select>
							</div>
						 	
							<!-- <label class="col-sm-2 control-label" for="textinput"></label> -->
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_arrivalCity" name="airBooking_arrivalCity">
									<option>출발지</option>
									<!-- DB에서? -->
								</select>
							</div>
						</div>
						
						<!-- 날짜 -->
						<div class="form-group">
							<label class="col-sm-3 control-label" for="textinput">날짜</label>
							<div class="col-sm-8">
								<input class="form-control" type="date" placeholder="<%=today%>" min="<%=today%>" max="<%=endday%>" id="airBooking_date" name="airBooking_date"/>
							</div>
						</div>
						
						<!-- 인원수 -->
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel1">인원</label>
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_adults" name="airBooking_adults">
									<option>대인</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_kids" name="airBooking_kids">
									<option>소인</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
						</div>
						
						<!-- 버튼 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-7">
								<div class="pull-right">
									<button type="submit" class="btn btn-primary">예매</button>
									<button type="button" class="btn btn-default" onclick="">취소</button>
								</div>
							</div>
						</div>
						
					</fieldset>
				</form>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->
	</div>
	<!-- 본문 영역 -->
	
	<!-- 푸터 영역 -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	<!-- 푸터 영역 -->
</body>
</html>