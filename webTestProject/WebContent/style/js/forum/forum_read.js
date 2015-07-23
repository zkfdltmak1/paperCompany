$(function(){
		
		$("#forum_delete_id").click(function(){
			
			if (confirm("정말 삭제하시겠습니까??") == true){
				$("#r_forum_id").attr("action","/paperProject/board/forum.do");
				$("#r_forum_id").submit();
			}
			
		});
		
});