	let model1 = ["p0.png","p0.png",
			    "p1.png","p1.png",
			    "p2.png","p2.png",
			    "p3.png","p3.png"
			];
	
	let model2 = ["p0.png","p0.png",
			    "p1.png","p1.png",
			    "p2.png","p2.png",
			    "p3.png","p3.png",
			    "p4.png","p4.png",
			    "p5.png","p5.png"
			];
	let model3 = ["p0.png", "p0.png",
				"p1.png", "p1.png",
				"p2.png", "p2.png",
				"p3.png", "p3.png",
				"p4.png", "p4.png",
				"p5.png", "p5.png",
				"p6.png", "p6.png",
				"p7.png", "p7.png",
				"p8.png", "p8.png",
				"p9.png", "p9.png",
				"p10.png", "p10.png",
				"p11.png", "p11.png",
				"p12.png", "p12.png",
				"p13.png", "p13.png",
				"p14.png", "p14.png",
				"p15.png", "p15.png",
				"p16.png", "p16.png",
				"p17.png", "p17.png",
				];
	$(document).ready(function(){
	//-------------------------------------------
	// 힌트버튼 선택된거는 보여지고 사라지면 안되니까 succesPic이라는 클래스로 바꿔줌
	let hintCount = 1;
	$("#hint").click(function(){
		// (힌트는 총 두번 hintCount가 1 or 2 가능)
		console.log("hintCount", hintCount);
		if(hintCount == 1|| hintCount == 2) {
			if($(".pic").not(".successPic")){
				console.log("couu");
				$(".pic").animate({opacity:1});
				$(".pic").animate({opacity:0.01}, 3000);
			}
		} 
		hintCount += 1;
		if(hintCount == 3){
			$("#hint").hide();
		}
	});
	//-------------------------
		// cardGame클릭시(index화면으로 가기)
		$("#home").click(function(){
			location.href = "/cardGame/index.html"
		});
		// session 검사(sessionInfo 값을 동기요청)
		let loginState = null;
		$.ajax({
			url : "/cardGame/GetSessionInfo",
			method: "post",
			async : false, //동기요청 : 값을 받을때까지 기다리고 success가 실행된다
			success: function(json){
				loginState = json;
				console.log("loginState="+loginState);
			}
		});
		
		if(loginState === null) {
			location.href = "/cardGame/login.html"
			return false;
		}
		
		// 상단에 로그인 아이디 표시
		$("#loginState").text(loginState);
		let timerNum = 0;
		timer = setInterval(function(){
			timerNum++;
			$("#timer").text(" 경과시간"+timerNum);
		}, 1000);
		
		// 로그 아웃
		$("#logoutBtn").click(function(){
			$.ajax({
				url : "/cardGame/Logout",
				method : "post",
				success : function(){
					console.log("로그아웃");
					location.href = "/cardGame/puzzle.html"
				}
			});
		});
		//---------------------------------------------------------------
		let total = 0; // 전체 몇번째 클릭인지
		let stage = 1;
		// stage1이면 모델1, stage2이면 모델2 ,,,,,,,,,
		let model = model1;
		console.log("model길이 : ",model.length);
		// 모델 셔플
		for(let i=0; i<100000; i++) {
			let ran = Math.floor(Math.random()*model.length); // 갯수수정
			let temp = model[0];
			model[0] = model[ran];
			model[ran] = temp;
		}
		
		// 모델 출력
		$("#board").empty();
		$(model).each(function(index, item){
			$("#board").append("<td><input type='image' class= 'pic succesPic', src='/cardGame/images/"+item+"'></td>")
		});
		
		// 이미지 안보이게하기
		$(".pic").each(function(){
			$(this).animate({opacity:0.01}, 3000); // 드래그 버그 -> 우클릭방지해야 함
		});

		// 게임 변수 설정
		
		let state = 0; // 0 or 1 or 2 
		let success = 0; // 몇개의 그림을 맞추었는지 max=8
		let onePic = null;
		let twoPic = null;
		
		 $(document).on("click",".pic",function() { //동적으로 생성된 태그에 이벤트작동할수있도록 설정
			console.log("click");
			console.log("stage :", stage);
			console.log("total", total);
			total++;
			state++;
			$(this).attr("disabled", true);
			if(state == 1) {
				$(this).animate({opacity:1},1);
				console.log("state 1");
				onePic = $(this);
			}else if(state == 2) {
				console.log("state 2");
				$(this).animate({opacity:1},1);
				twoPic = $(this);
				
				if($(onePic).attr("src") == $(twoPic).attr("src")) {
					success++
					console.log("success: ", success);
					$(onePic).removeClass("pic");
					$(twoPic).removeClass("pic");
					
	                console.log($(onePic).attr("src"));
	                console.log($(onePic).attr("class"));
					if(success == model.length/2) {
						console.log("스테이지 시간("+timerNum+"), 횟수("+total+")");
						// 새로운 스테이지
						stage += 1;
						success = 0
						if(stage < 4) {
							console.log("stage : ", stage);
							if(stage == 2){
								model = model2;
							} else if(stage == 3) {
								model = model3
							}
							// 모델 셔플
							for(let i=0; i<100000; i++) {
								let ran = Math.floor(Math.random()*model.length); // 갯수수정
								let temp = model[0];
								model[0] = model[ran];
								model[ran] = temp;
							}
							// 모델 출력
							 $("#board").empty();
							 $(model).each(function(index, item){
		                      if(index%6==0){
		                         $("#board").append("<tr>");
		                           }
		                      $("#board").append("<td oncontextmenu='return false' ondragstart='return false'><input class='pic' type='image' src='/cardGame/images/"+item+"'></td>");
		                      if(index%6==0){
		                          $("#board").append("</tr>");
		                           }
		                        });
		                      $(".pic").each(function(){
		                        $(this).animate({opacity:0.01}, 3000); // 이슈 : 드래그 버그 -> 우클릭 방지로 방지
		                        });
						} else{
							clearInterval(timer);
							console.log("게임종료");
							$.ajax({
								url:"/cardGame/AddReport",
								method: "post",
								data: {"timer" : timerNum, "count" : total}
							});
						}
					}
				} else { // 그림 두개가 틀릴 시
					$(onePic).animate({"opacity":0.01},1);
					$(twoPic).animate({"opacity":0.01},1);
					$(onePic).attr("disabled",false);
					$(twoPic).attr("disabled",false);
				}
				state = 0;
			}
		});
	});