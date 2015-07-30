<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/booking/airplane/airBooking_detail.css" />
<script type="text/javascript">
	$(function(){
		$(".air_remain_seat").hide();
		
		$(".air_time_table_btn").click(function(){
			$(".air_remain_seat").show();
		});
	});
</script>
</head>
<body>
	<!-- 헤더 영역 -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<!-- 헤더 영역 -->
	
	<!-- 본문 영역 -->
	<!-- 비행기 시간 조회 -->
	<div class="container air_time_table" id="air_time_table">
		<label for="air_time_table">비행 시간</label>
		<div class="row">
			<div class="span12">
				<ul class="thumbnails">
					<!-- for문 돌림 -->
					<li class="span5 clearfix">
						<div class="thumbnail clearfix air_time_table_list">
							<div class="caption" class="pull-left">
								<!-- 남은 좌석이 없으면 비활성화 -->
								<a href="#" class="btn btn-primary icon  pull-right air_time_table_btn"><small>잔여좌석</small></a>
								<h5>
									출발지, 도착지, 날짜, 비행기 등등?
								</h5>
								<small><b>보잉기 : </b> ...?</small>
							</div>
						</div>
					</li>
					<!-- for문 돌림 -->
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 잔여 좌석 -->
	<div class="container air_remain_seat" id="air_remain_seat">
		<label for="air_remain_seat">잔여 좌석</label>
		<div class="row">
			<div class="span10">
				<ul class="thumbnails">
					<!-- for문 돌림 -->
					<li class="span5 clearfix ">
						<div class="thumbnail clearfix air_remain_seat_list">
							<div class="caption" class="pull-left">
								들어갈 내용 
								잔여 좌석 수
								좌석 위치
								좌석 개수 등등?
							</div>
						</div>
					</li>
					<!-- for문 돌림 -->
				</ul>
			</div>
		</div>
	</div>
	<!-- 본문 영역 -->
	
	<!-- 푸터 영역 -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	<!-- 푸터 영역 -->
</body>
</html>