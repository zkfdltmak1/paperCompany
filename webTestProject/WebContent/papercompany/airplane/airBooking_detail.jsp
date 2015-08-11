<%@page import="java.util.StringTokenizer"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.booking.vo.BookingVO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
	String session_email = (String)session.getAttribute("s_member_email");

	List<String> airBooking_timeList = (List<String>)request.getAttribute("airBooking_timeList");
	String vehicleKinds = (String)request.getAttribute("airBooking_vehicleList");
	List<BookingVO> airBooking_countList = (List<BookingVO>)request.getAttribute("airBooking_countList");
	
	String airBooking_startCity = (String)request.getAttribute("airBooking_startCity");
	String airBooking_arrivalCity = (String)request.getAttribute("airBooking_arrivalCit");
	String airBooking_date = (String)request.getAttribute("airBooking_date");
	String airBooking_adults = (String)request.getAttribute("airBooking_adults");
	String airBooking_kids = (String)request.getAttribute("airBooking_kids");
	int airBooking_price = (int)request.getAttribute("airBooking_price")+ 100000;
	int person_su = (int)request.getAttribute("airBooking_person_su")-1;
	
	
	String[] dateTime = new String[airBooking_timeList.size()];
	for(int i=0; i<airBooking_timeList.size(); i++){
		dateTime[i] = airBooking_date + " " +airBooking_timeList.get(i);
	}
	java.util.Date nowDate = new java.util.Date();
	java.util.Date remainDate = new java.util.Date();
%>
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
	function fnDateTime(date, time, city){
		airBooking_date = date;
		airBooking_time = time;
		airBooking_city = city;
	}
	
	$(function(){
		$(".air_time_table").css({
			"margin-left" : "380px"
		});
		$("#air_insert").click(function(){
			var seat = $("#seat_insert").text();
			var air_insert = confirm("정말로 예매를 하시겠습니까?");
			
			if(air_insert){
				document.airBookingInsert.method="post";
				document.airBookingInsert.action = "/webTestProject/airBooking.air?airBooking_seat="+seat+"&airBooking_vehicle="+'<%=vehicleKinds%>'+"&start_airBooking_date="+airBooking_date+"&start_airBooking_time="+airBooking_time;
				document.airBookingInsert.submit();
			}
			else{
				alert("예매가 취소 되었습니다.");
				location.href="/webTestProject/papercompany/airplane/airBooking.jsp"
			}
		});
		
		$(".air_remain").hide();
		$(".air_buy_decide").hide();	
		
		$(".air_time_table_btn").click(function(){
			$(".air_time_table").css({
				"margin-left" : "180px"
			});
			
			$("#seat_insert").html("");
			$(".air_remain .air_remain_ul li").remove();
			$(".air_remain").hide("fade");
			$(".air_buy_decide").hide("fade");
			$(".air_remain").show("fade");
			$(".air_buy_decide").show("fade");
			
			var makeTag = '';
			var su = 3;
			for(var i = 1; i <= 120; i++){
				
				if(i<10){
					makeTag += "<li class='seat' id='as0"+i+"'>"+"as0"+i+"</li>";
					if(i == su){
						makeTag += "<li style='border: 1px solid #FFFFFF;'>"+"</li>";
						su += 6;
					}
				}else{
					makeTag += "<li class='seat' id='as"+i+"'>"+"as"+i+"</li>";
					if(i == su){
						makeTag += "<li style='border: 1px solid #FFFFFF;'>"+"</li>";
						su += 6;
					}
					if(i == 54){
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
						makeTag += "<li style='border-color: white;'></li>";
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
						makeTag += "<li style='border-color: white; border-top-color: skyblue;'></li>";
					}
				}				
			}
			$(".orderby").html(makeTag);
			
			var person = <%=person_su%>;
			
			$.ajax({
				type : "POST",
				url : "/webTestProject/airBooking.air",
				data : {
					command : "airBooking_remain",
					airBookingDate : airBooking_date,
					airBookingTime : airBooking_time
				},
				datatype : "json",
				success : function(data){
					if(data == 0){
						return;
					}
					else{
						$.each(data, function(key, value) {
							var arr = new Array();
							var airBooking_vehicle_code;
							for(var i=1; i<121; i++){
								if(i < 10){
									arr[i] = $("#as0"+i).text();
								}else{									
									arr[i] = $("#as"+i).text();
								}
								
								if(arr[i] == value.seat_number &&
									airBooking_date == value.dp_date &&
									airBooking_time == value.time_time &&
									airBooking_vehicle_code == value.vehicle_code&&
									airBooking_city == value.city_city){
										
										$("#"+value.seat_number).css({
											"background-color" : "lightgray"
										});
										$("#"+value.seat_number).off("click");
								}
							}
						});
					}
				},
				error : function(e){
					alert("접속실패");
				}
			});
			
			$(".orderby li").click(function(){
				
				var index = $(this).text();
				var listLength = $(".air_remain .air_remain_ul li").length;
				
				if (index == "") {
	               return false;
	            }				
				
				if(listLength <= person && listLength >= 0){
					$(this).toggleClass("reserve");
					$(".air_remain .air_remain_ul").hide();
					
					if($(this).hasClass("reserve")){
						$(".air_remain .air_remain_ul").append("<li><div>"+index+" </div></li>");
						$(".air_remain .air_remain_ul li").addClass("span5 clearfix");
						$(".air_remain .air_remain_ul li div").addClass("thumbnail clearfix caption air_remain_list");
					}
					else{
						$(".air_remain .air_remain_ul li:last").remove();
					}
				}
				else{
					var checkList = $(".air_remain_list").text().split(' ');
					
					for(var i=0; i<listLength; i++){						
						if($(this).text() == checkList[i]){
							$(this).toggleClass("reserve");
							$(".air_remain .air_remain_ul li:last").remove();
						}
					}
				}
				/* alert("당신이 선택한 좌석 : " + $(".air_remain_list").text() + ", size : " + $(".air_remain .air_remain_ul li").length); */
				$("#seat_insert").html($(".air_remain_list").text());
			});
		});
	});
</script>
</head>
<body>
	<%if(session_email == null){%>
		<script type="text/javascript">
			$(function(){
				alert("정상적인 접근이 아닙니다.\n로그인을 하여 주세요.");
				location.href="/webTestProject/index.jsp";
			});
		</script>
	<%}else{ %>
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
					<%for(int i=0; i<airBooking_timeList.size(); i++){%>
					<li class="span5 clearfix">
						<div class="thumbnail clearfix" id="air_time_table_list">
							<div class="caption" class="pull-left">
								<!-- 남은 좌석이 없으면 비활성화 -->
								<%remainDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime[i]);%>
								<%if(remainDate.getTime() < nowDate.getTime()){%>
									<a href="#" onclick="javascript:fnDateTime('<%=airBooking_date %>','<%=airBooking_timeList.get(i)%>', '<%=airBooking_arrivalCity%>')" class="btn btn-primary icon  pull-right air_time_table_btn disabled"><small>마감</small></a>
								<%}else{%>
									<a href="#" onclick="javascript:fnDateTime('<%=airBooking_date %>','<%=airBooking_timeList.get(i)%>', '<%=airBooking_arrivalCity%>')" class="btn btn-primary icon  pull-right air_time_table_btn active"><small>
										<%int count = 120;%>
										<%for(int j=0; j<airBooking_countList.size(); j++){%>
											<%
												BookingVO bvo = new BookingVO();
												bvo = airBooking_countList.get(j);
												
											
												if(bvo.getDp_date().equals(airBooking_date) &&
														bvo.getCity_city().equals(airBooking_arrivalCity) &&
														bvo.getTime_time().equals(airBooking_timeList.get(i))){
													
													count -= 1;%>
											<%}%>
										<%}%>
										<%=count%>
									</small></a>
									<%if(count == 0){%>
										<a href="#" onclick="javascript:fnDateTime('<%=airBooking_date %>','<%=airBooking_timeList.get(i)%>', '<%=airBooking_arrivalCity%>')" class="btn btn-primary icon  pull-right air_time_table_btn disabled"><small>마감</small></a>
									<%}%>
								<%}%>
								<h5>
								<span><%=airBooking_date %></span>
								&nbsp;&nbsp;
								<span class="arrival_time"><%=airBooking_timeList.get(i)%></span> 
								&nbsp;&nbsp;
								<span>출발 : <b><%=airBooking_startCity%></b></span>
								&nbsp;&nbsp;
								<span>도착 : <b><%=airBooking_arrivalCity%></b></span>
								</h5>
								<small class="vehicle" >
									<b>
										<%=vehicleKinds%>
									</b>
								</small>
							</div>
						</div>
					</li>
					<%}%>
					<!-- for문 돌림 -->
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 잔여 좌석 -->
	<div class="container air_remain" id="air_remain" >
		<label for="air_remain"></label>
		<div class="row">
			<div class="span10">
				<ul class="thumbnails air_remain_ul">
				</ul>
				
				<ul class="orderby">
				</ul>
			</div>
		</div>
	</div>
	
	<!-- 구매 결정 -->
    <div class="row air_buy_decide">
        <!-- <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3"> -->
        <div class="row">
            <table class="table table-hover">
                <thead>
                	<tr>
                		<th colspan="4"><h1>예매 확인</h1></th>
                	</tr>
                    <tr>
                        <th>예매 정보</th>
                        <th class="text-center">좌석</th>
                        <th class="text-center">대인</th>
                        <th class="text-center">소인</th>
                        <th class="text-center">가격</th>
                    </tr>
                </thead>
                <tbody>
                	<!-- 예매 목록 -->
                    <tr>
                        <td class="col-md-9"><em><h3><%=airBooking_date%>&nbsp;&nbsp;<%=airBooking_startCity%> 출발&nbsp;&nbsp;<%=airBooking_arrivalCity %> 도착</h3></em></td>
                        <td class="col-md-1" style="text-align: center"  id="seat_insert">  </td>
                        <td class="col-md-1" style="text-align: center"><%=airBooking_adults %>  </td>
                        <td class="col-md-1" style="text-align: center"><%=airBooking_kids %></td>
                        <td class="col-md-1 text-center"><%=airBooking_price %></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td class="text-right"><h4><strong>총액 : </strong></h4></td>
                        <td class="text-center text-danger"><h4><strong><%=airBooking_price * (person_su+1)%></strong></h4></td>
                    </tr>
                </tbody>
            </table>
            <form name="airBookingInsert" >
	            <input type="hidden" name="command" value="airBooking_insert">
	            <input type="hidden" name="airBooking_startCity" value="<%=airBooking_startCity%>">
	            <input type="hidden" name="airBooking_arrivalCity" value="<%=airBooking_arrivalCity %>">
	            <input type="hidden" name="airBooking_date" value="<%=airBooking_date%>">
	            
	            <input type="hidden" name="airBooking_adults" value="<%=airBooking_adults %>">
	            <input type="hidden" name="airBooking_kids" value="<%=airBooking_kids %>">
	            <input type="hidden" name="airBooking_price" value="<%=airBooking_price * (person_su+1) %>">
	            <button type="button" class="btn btn-success btn-lg btn-block" id="air_insert">
	                	결제 하기<span class="glyphicon glyphicon-chevron-right"></span>
	            </button>
            </form>
        </div>
    </div>
    <!-- </div> -->
	<!-- 본문 영역 -->
	
	<!-- 푸터 영역 -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	<!-- 푸터 영역 -->
	<%}%>
</body>
</html>