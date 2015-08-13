$(function(){
			
	
	
	
			/*$("#category1").load("/webTestProject/papercompany/bus/bus_select_data.jsp #category1-1",function(){
				$(this).show();
			});
			
			$("#category2").load("/webTestProject/papercompany/bus/bus_select_data.jsp #category1-2",function(){
				$(this).show();
			});
			$("#category3").load("/webTestProject/papercompany/bus/bus_select_data.jsp #category1-3",function(){
				$(this).show();
			});

			
			
			 $(document).on("change", "#category1 > select", function(){
				var target = $(this).find("option:selected").attr("data-target");
				var selector = "/webTestProject/papercompany/bus/bus_select_data.jsp "+ target;
				
				$("#category2").load(selector, function(){
					$(this).show();
				});
			});
			
			$(document).on("change", "#category2 > select", function(){
				var target = $(this).find("option:selected").attr("data-target");
				var selector = "/webTestProject/papercompany/bus/bus_select_data.jsp "+ target;
				
				$("#category3").load(selector, function(){
					$(this).show();
				});
			});*/
			
			
			
			
			
			
			
			$("#bus_mainForm").submit(function(){
				
				var user_id = $("input[name='user_id']").val();
				
				
				
				if($("select[name='bus_type'] > option:selected").index() < 1){
					alert("버스 종류를 선택해 주세요.");
					$("select[name='bus_type']").focus();
					return false;
				}
				
				if($("select[name='depart_station'] > option:selected").index() < 1){
					alert("출발지를 선택해 주세요.");
					$("select[name='depart_station']").focus();
					return false;
				}
				
				if($("select[name='last_station'] > option:selected").index() < 1){
					alert("도착지를 선택해 주세요.");
					$("select[name='last_station']").focus();
					return false;
				}

				
				if($("select[name='adult_people'] > option:selected").index() < 1){
					
					if($("select[name='children_people'] > option:selected").index() < 1){
						alert("인원을 선택해 주세요.");
						$("select[name='adult_people']").focus();
						return false;
					}
					
					return false;
					
				}
				
				if($("input[name='bus_date'").val().replace(/\s/g,"")==""){
					alert("날짜를 입력하세요.");
					$("input[name='bus_date']").focus();
					return false;
				}
				
				
				
				if(($("select[name='depart_station'] > option:selected").val())==(($("select[name='last_station'] > option:selected")).val())){
					alert("출발지와 도착지가 같습니다.");
					$("select[name='depart_station']").focus();
					return false;
				}
				
				
				
				
				$("#bus_main_id").attr({
			          "method":"post" ,
			          "action":"/webTestProject/bus/bus.do"
				}).submit();
				
				
				
			});
			
			$(".search_charge_class").click(function(){
				window.open("/webTestProject/papercompany/bus/bus_charge_list.jsp", "charge", "width=600 height=450 left=320 top=270");
			});
			
			
			
		});

	