<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="stylesheet" type="text/css" href="../../style/css/board/board.css" />
</head>
<body>
	<div class="container">
		<table id="table_board" class="list">
			<colgroup>
				<col>
				<col>
			</colgroup>
			<!-- 본문 제목 -->
			<thead>
				<tr>
					<th colspan="2">
						제목(DB에서 가져옴)
					</th>
				</tr>
			</thead>
			<!-- 본문 내용 -->
			<tbody>
				<tr>
					<td colspan="2">
						내용(DB에서 가져옴)
					</td>
				</tr>
			</tbody>
			<!-- 댓글 목록 -->
			<tfoot>
				<!-- for문 돌림 -->
				<tr>
					<td colspan="2">
						작성자(DB에서) / 내용(DB에서)
					</td>
				</tr>
				<!-- for문 돌림 -->
				<form method="post" action="">
				<tr>
					<td>
							<input type="text" name="reviews_reply" id="reviews_reply" size="90">
					</td>
					<td>
						<input type="submit" value="댓글 작성">
					</td>
				</tr>
				</form>
			</tfoot>
		</table>
	</div>
</body>
</html>