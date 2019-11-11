	$(document).ready(function(){
		// cardGame클릭시
		$("#home").click(function(){
			location.href = "/cardGame/index.html"
		});
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
		if(loginState != null) {
			$("#loginBtn").hide()
			} else {
				$("#gameStart").hide()
				$("#logoutBtn").hide()
				$("#myScoreBtn").hide()
				$("#pwChangeBtn").hide()
				$("#fireBtn").hide()
		}
		// 로그인버튼 클릭
		$("#loginBtn").click(function(){
			location.href = "/cardGame/login.html"
		});
		// 로그아웃버튼 클릭
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
		// 게임스타트버튼 클릭
		$("#gameStart").click(function(){
			location.href = "/cardGame/puzzle.html"
		});
		// 회원탈퇴버튼 클릭
		$("#fireBtn").click(function(){
			location.href = "/cardGame/fire.html"
		});
		// 비밀번호변경 클릭
		$("#pwChangeBtn").click(function(){
			location.href = "/cardGame/pwChange.html"
		});
		// my score list 클릭
		$("#myScoreBtn").click(function(){
			// 배열 값(순위 나타내는 데 쓴다)
			var countNum;
			$("#memberId").text(loginState)
			$("#ht").text("님의 점수 리스트")
			$("#print").empty()
			$("#print").append("<tr><td>순위</td><td>아이디</td><td>시간</td><td>카운트</td><td>날짜</td></tr>")
			$.ajax({
				url : "/cardGame/MyRecord",
				method : "post",
				data : {loginState : loginState},
				success : function(json){
					// json의 크기와 같게한다
					countNum = new Array(json.length);
					console.log("순위크기"+countNum.length);
					for(var i = 0; i<countNum.length; i++) {
						countNum[i] = i+1;
					}
					$(json).each(function(index, item){
						let html = 	"<tr><td>"+countNum[index];
							html += "</td><td>"+item.member.memberId;
							html += "</td><td>"+item.timer;
							html += "</td><td>"+item.count;
							html += "</td><td>"+item.reportDate;
							html += "</td></tr>";
							$("#print").append(html);
					});
				}
			});
		});
		// top10 클릭
		$("#toptenBtn").click(function(){
			// 배열 값(순위 나타내는 데 쓴다)
			var countNum;
			$("#ht").text("TOP10")
			$("#print").empty()
			$("#print").append("<tr><td>순위</td><td>아이디</td><td>시간</td><td>카운트</td><td>날짜</td></tr>")
			$.ajax({
				url : "/cardGame/Top",
				method : "post",
				success : function(json) {
					// json의 크기와 같게한다
					countNum = new Array(json.length);
					console.log("순위크기"+countNum.length);
					for(var i = 0; i<countNum.length; i++) {
						countNum[i] = i+1;
					}
					$(json).each(function(index, item){
						let html = 	"<tr><td>"+countNum[index];
							html += "</td><td>"+item.member.memberId;
							html += "</td><td>"+item.timer;
							html += "</td><td>"+item.count;
							html += "</td><td>"+item.reportDate;
							html += "</td></tr>";
							$("#print").append(html);
					});
					}
				});
				
		});
		// 오늘의 top10 클릭
		$("#todayToptenBtn").click(function(){
			// 배열 값(순위 나타내는 데 쓴다)
			var countNum;
			$("#ht").text("오늘의 TOP10")
			$("#print").empty()
			$("#print").append("<tr><td>순위</td><td>아이디</td><td>시간</td><td>카운트</td><td>날짜</td></tr>")
			$.ajax({
				url : "/cardGame/TodayTop",
				method : "post",
				success : function(json) {
					// json의 크기와 같게한다
					countNum = new Array(json.length);
					console.log("순위크기"+countNum.length);
					for(var i = 0; i<countNum.length; i++) {
						countNum[i] = i+1;
					}
					$(json).each(function(index, item){
						let html = 	"<tr><td>"+countNum[index];
							html += "</td><td>"+item.member.memberId;
							html += "</td><td>"+item.timer;
							html += "</td><td>"+item.count;
							html += "</td><td>"+item.reportDate;
							html += "</td></tr>";
							$("#print").append(html);
					});
					}
				});
		});
		// 이달의 top10 클릭
		$("#monthTopTenBtn").click(function(){
			// 배열 값(순위 나타내는 데 쓴다)
			var countNum;
			$("#ht").text("이달의 TOP10")
			$("#print").empty()
			$("#print").append("<tr><td>순위</td><td>아이디</td><td>시간</td><td>카운트</td><td>날짜</td></tr>")
			$.ajax({
				url : "/cardGame/MonthTop",
				method : "post",
				success : function(json) {
					// json의 크기와 같게한다
					countNum = new Array(json.length);
					console.log("순위크기"+countNum.length);
					for(var i = 0; i<countNum.length; i++) {
						countNum[i] = i+1;
					}
					$(json).each(function(index, item){
						let html = 	"<tr><td>"+countNum[index];
							html += "</td><td>"+item.member.memberId;
							html += "</td><td>"+item.timer;
							html += "</td><td>"+item.count;
							html += "</td><td>"+item.reportDate;
							html += "</td></tr>";
							$("#print").append(html);
					});
					}
				});
		});
	});