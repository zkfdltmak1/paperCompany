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
<script type="text/javascript">
	$(function(){
		$(".btn").
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="form-horizontal" role="form">
					<fieldset>
						<!-- Form Name -->
						<legend>비행기 예매</legend>
						
						<!-- 출발지, 도착지 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="textinput">장소</label>
							<div class="col-sm-5">
								<input type="text" placeholder="출발지" class="form-control">
							</div>
						 
							<!-- <label class="col-sm-2 control-label" for="textinput"></label> -->
							<div class="col-sm-5">
								<input type="text" placeholder="도착지" class="form-control">
							</div>
						</div>
						
						<!-- 날짜 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="textinput">날짜</label>
							<div class="col-sm-10">
								<input class="form-control" type="date" placeholder="<%=today%>" min="<%=today%>" max="<%=endday%>"/>
							</div>
						</div>
						
						<!-- 인원수 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="textinput">인원수</label>
							<div class="input-group">
								<span class="input-group-btn">
									<button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]">
										<span class="glyphicon glyphicon-minus"></span>
									</button>
								</span>
								<input type="text" name="quant[1]" class="form-control input-number" value="1" min="1" max="10">
								<span class="input-group-btn">
									<button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
								</span>
							</div>
						</div>
						
						<!-- 버튼 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="pull-right">
									<button type="submit" class="btn btn-default">Cancel</button>
									<button type="submit" class="btn btn-primary">Save</button>
								</div>
							</div>
						</div>
						
					</fieldset>
				</form>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->
	</div>
</body>
</html>