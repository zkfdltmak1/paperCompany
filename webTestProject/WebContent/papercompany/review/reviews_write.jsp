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
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/board/board.css" />
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<script type="text/javascript">
	function fnReviewWrite(){
		document.form_review_write.action = "/webTestProject/review_board.review";
		document.form_review_write.method= "post";
		document.form_review_write.submit();
	}
	
	function goList(){
		document.f_goList.action="/webTestProject/review_board.review";
		document.f_goList.method="post";
		document.f_goList.submit();
	}
</script>
</head>
<body>
	<!-- Top  -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<div class="container">
		<form name="form_review_write" id="form_review_write">
			<table id="table_board" class="list">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<thead>
					<tr>
						<th colspan="2">
							글 쓰 기
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>제목</td>
						<td>
							<input type="text" name="reviews_title" size="40">
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="8" cols="40" name="reviews_content"></textarea>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="reviews_pw" size="40">
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td id="write">
							<a href="javascript:fnReviewWrite()">글쓰기</a>
						</td>
				<input type="hidden" name="command" value="review_write">
				</form>
						<td>
							<form name="f_goList">
								<a href="javascript:history.back()">목록으로</a>
								<input type="hidden" name="command" value="getReviewList">
							</form>
						</td>
					</tr>
				</tfoot>
			</table>
	</div>
	<!-- footer -->	
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>