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
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js">
</script>

<!-- jquery로 처리 -->
<script type="text/javascript" src="../../style/js/forum/forum_write.js">
</script>

<!-- 자바 스크립트로 처리 -->
<script type="text/javascript">

/* 	 function forum_write(){
		
		var forum = document.w_forum; 
		
		if(forum.forum_title.value=="" || forum.forum_content.value==""){
			 alert("내용을 입력하세요"); 
			 location.reload(); 
		}
		 else{ 
		document.w_forum.method="post";
		document.w_forum.action="/paperProject/board/forum.do";
		document.w_forum.submit();
		 } 
		
	}  */
	
</script>
</head>
<body>
	<form name="w_forum" id="w_forum_id">
		<input type="hidden" name="command" value="forum_write_command" >
		<div class="container">
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
							<input type="text" name="forum_title" size="78" id="forum_title_id"/>
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="8" cols="70" name="forum_content" id="forum_content_id" ></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td id="write" >
							<a href="#" onclick="forum_write()">글쓰기</a>

						</td>
						<td> 
							<a href="#" onclick="history.back(); return false;">목록으로</a> 
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</form>
</body>
</html>