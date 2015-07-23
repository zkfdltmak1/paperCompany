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
<link rel="stylesheet" type="text/css" href="../../style/css/board/board.css" />
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function fnReviewWrite(){
		document.form_review_write.action = "/webTestProject/review_board.review";
		document.form_review_write.method= "post";
		document.form_review_write.submit();
	}
</script>
</head>
<body>
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
							<input type="text" name="reviews_title" size="78">
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="8" cols="70" name="reviews_content"></textarea>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="reviews_pw" size="78">
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td id="write" colspan="2">
							<a href="javascript:fnReviewWrite()">글쓰기</a>
						</td>
					</tr>
				</tfoot>
			</table>
			<input type="hidden" name="command" value="review_write">
		</form>
	</div>
</body>
</html>