$(function(){
		 $("#forum_title_id").focus();
		 
		 
		 $("#write > a").click(function(){
		 	
			if($("#forum_title_id").val().replace(/\s/g,"")==""){
				alert("제목을 입력하세요!");
				$("#forum_title_id").focus();
				return false;
			} 
			else if($("#forum_content_id").val().replace(/\s/g,"")==""){
				alert("내용을 입력하세요!");
				$("#forum_content_id").focus();
				return false;
			}
		 	else if($("#forum_title_id").val()!="" && $("#forum_content_id").val()!=""){ 
		 		$("#w_forum_id").attr("method","post");
				$("#w_forum_id").attr("action","/webTestProject/board/forum.do");
				$("#w_forum_id").submit();
			} 
		 });
		 
	}); 