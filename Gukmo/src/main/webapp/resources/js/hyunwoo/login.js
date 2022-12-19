// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}





window.onload = function () {
    google.accounts.id.initialize({
      client_id: "1009243602481-q3hk5769gab0ucfqbsf3r1abj4cg8av5.apps.googleusercontent.com",
      callback: handleCredentialResponse
    });
//    google.accounts.id.prompt();
   // One Tap 기능을 사용하지 않기 때문에 주석처리하였다.
  };

$(document).ready(function(){
	// 카카오 로그인 초기화
	Kakao.init("7643955cb5fbab2b0481e4b321cff247");
	
	//아이디에서 엔터입력이벤트
	$("input#userid").keydown(function(e){	//아이디에서 값 입력시 이벤트
	  if(e.keyCode == 13){	//엔터를 했을 경우
		$("button#btn_login").trigger("click");  
	  }
	});
	
	//패스워드에서 엔터입력이벤트
	$("input#passwd").keydown(function(e){
	  if(e.keyCode == 13){	//엔터를 했을 경우
	    $("button#btn_login").trigger("click");  
	  }
	});
	
	//회원가입버튼클릭시
	$("span#btn_signup").click(()=>{
		location.href = getContextPath()+'/TOS.do';
	});
	
	
	//로그인버튼 클릭시 이벤트
	$("button#btn_login").click(function(){
	  const userid = $("input#userid").val().trim();
	  const passwd = $("input#passwd").val().trim();
	  if(userid == ""){	//아이디 값이 비어있다면
		  $("span#passwd_error").css("display","none");
		  $("span#userid_error").text("아이디를 입력해주세요");
	      $("span#userid_error").css("display","block");
	      return;
	  }
	  else if(passwd == ""){	//비밀번호 값이 비어있다면
		  $("span#userid_error").css("display","none");
		  $("span#passwd_error").text("비밀번호를 입력해주세요");
		  $("span#passwd_error").css("display","block");
		  return;
	  }
	  else{	//아이디도 입력하고 비밀번호도 입력했다면
		  $("span#userid_error").text("");	//아이디 에러 지우기
		  $("span#passwd_error").text("");	//비밀번호 에러 지우기
		  $("span#userid_error").css("display","none");
		  $("span#passwd_error").css("display","none");
		  $.ajax({	//아이디존재여부 검사
			url:getContextPath()+"/member/idExistCheck.do",
			type:"POST",
			data:{"userid":userid},
		    dataType:"JSON",
		    success:function(json){	
		    	if(!json.idExist){	//존재하지 않는 아이디라면
		    		$("span#userid_error").text("존재하지 않는 아이디입니다.");
		    		$("span#userid_error").css("display","block");
		    		return;
		    	}
		    	else{	//존재하는 아이디라면
		    		$.ajax({	
		    			url:getContextPath()+"/userExist.do",
		    			type:"POST",
		    			data:{"userid":userid
		    				 ,"passwd":passwd},
		    		    dataType:"JSON",
		    		    success:function(json){	
		    		    	if(json.userExist){ //로그인이 성공이라면
	    		    			user_status(userid);	//유저의 상태체크
		    		    		return;
		    		    	}
		    		    	else{	//로그인이 실패했다면
		    		    		$("span#userid_error").text("");
		    		    		$("span#userid_error").css("display","none");
		    		    		$("span#passwd_error").text("아이디 또는 비밀번호를 올바르게 입력해주세요.");
		    		    		$("span#passwd_error").css("display","block");
		    		    		return;
		    		    	}
		    		    },
		    			error: function(request, status, error){
		    			    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		    			}
		    		});//end of ajax
		    	}//end of else--
			
			},
			error: function(request, status, error){
			    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});//end of ajax
	  }
	});//end of Event--
	
	
	// 카카오로그인 버튼 클릭시 이벤트
	$("div#kakao_login").click(()=>{
		kakaoLogin(); 	
	});//end of Event--
	
	
	// 네이버로그인 버튼 클릭시 이벤트
	$("div#naver_login").click(()=>{
		const url = viewNaverLoginFrm();
		location.href = url;
	});//end of Event--
	
	
  
});//end of $(document).ready(function(){})

//Function Declaration


/**
 * user의 상태 체크
 * @param 사용자가 입력한 userid
 * @returns
 */
function user_status(userid){
	$.ajax({	
		url:getContextPath()+"/statusCheck.do",
		type:"POST",
		data:{"userid":userid},
	    dataType:"JSON",
	    success:function(json){	
	    	switch (json.status) {
				case '활동':
					login(userid);
					break;
					
				case '정지':
					if(json.penaltyReason.SIMPLE_PENALTY_REASON != '기타사유'){
						alert("정지된 회원입니다. \n" +
							  "정지사유 : "+json.penaltyReason.SIMPLE_PENALTY_REASON + "\n" +
							  "정지기간 : " + json.penaltyReason.START_DATE + " ~ " + json.penaltyReason.END_DATE + "(" + json.penaltyReason.PERIOD + "일)");
					}else{
						alert("정지된 회원입니다. \n" +
							  "정지사유 : " + json.detailReason + "\n" +
							  "정지기간 : " + json.penaltyReason.START_DATE + " ~ " + json.penaltyReason.END_DATE + "(" + json.penaltyReason.PERIOD + "일)");
					}
					break;
				case '휴면':
					let result = confirm("로그인한지 1년이상 지나서 휴면회원으로 전환되었습니다. 휴면을 푸시겠습니까?");
					if(result){	//휴면을 풀겠다고 했다면
						$.ajax({	
							url:getContextPath()+"/회원상태를 휴면에서 활동으로 업데이트시켜주는 빽단.do",
							type:"POST",
							data:{"userid":userid},
						    dataType:"JSON",
						    success:function(json){
						    	if(json.result){
						    		alert("휴면이 풀렸습니다!");
						    		login(userid);
						    	} else{
						    		alert("휴면을 푸는 도중 문제가 발생하였습니다. 다시 시도하여주세요.");
						    	}
						    },
						    error:function(request, status, error){
							    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
							}
						});//end of $.ajax({})--
					}//end of if--
					break;
					
				case '대기':
					alert("승인대기중인 회원입니다. 관리자가 승인할 때 까지 기다려주세요!\n" +
						  "승인에는 1~2일정도 소요될 수 있습니다.");
					break;
					
				case '승인거부':
					alert("승인이 거절되어 일반회원으로 전환되었습니다. \n" +
						  "거부사유를  상세히 읽고 다시 교육기관회원으로 전환해주세요\n" +
						  "승인 거부사유  : "+json.refuseReason);
					login(userid);
					break;
				
				case '비밀번호 변경 권장':
					alert("비밀번호 변경한지 3개월 이상 지났습니다 비밀번호 변경을 권장합니다.");
					login(userid);
					break;
			}//end of switch-case---
	    },
	    error:function(request, status, error){
		    alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});//end of $.ajax({})--
	    	
}//end of method----



/**
 * 로그인완료처리하기
 */
function login(userid){
	$("input#userid").val(userid);
	const frm = document.login_form;
	frm.action = getContextPath()+"/login.do";
	frm.method = "POST";
	frm.submit();
}//end of method--



/**
 * 카카오로그인 객체 보내기
 * @param response
 * @returns
 */
function kakaoLoginPro(response){
  const userInfo = {userid:response.id,email:response.kakao_account.email,profile_image:response.properties.profile_image,nickname:response.properties.nickname,email_acept:'0'
		  		 ,username:response.properties.nickname,flag:'kakao'}
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/kakaoLoginPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			user_status(data.userid);
		}else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		}else{
			alert("로그인에 실패했습니다");
		}
		
	},
	error: function(xhr, status, error){
		alert("로그인에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---




/**
 * 카카오로그인창 띄우기
 */
function kakaoLogin(){
	Kakao.Auth.login({
		success: function (response) {
		Kakao.API.request({
			url: '/v2/user/me',
			success: function (response) {
				console.log(response);
				kakaoLoginPro(response);
			},
			fail: function (error) {
				console.log(error)
			},
		})
	},
		fail: function (error) {
			console.log(error)
		},
	});
}//end of method--



/**
 * 소셜로그인 회원가입시키기
 */
function userSnsRegisterPro(userInfo){
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/userSnsRegisterPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			user_status(data.userid);
		}else{
			alert("로그인에 실패했습니다");
		}
		
	},
	error: function(xhr, status, error){
		alert("로그인에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---



/**
 * 네이버 로그인 폼 띄우기
 * @return 네이버로그인 폼 URL 을 반환한다.
 */
function viewNaverLoginFrm(){
	let url=getContextPath()+"/login.do";
	$.ajax({
		type : 'get',
		url : getContextPath()+'/naverLogin.do',
		dataType : 'json',
		async:false,
		success : function(json){
			url = json.naverUrl;
		},
		error: function(status, error){
			alert("네이버로그인이 현재 불안정한 상태입니다. 다시 시도해주세요"+error);
		}
	  });//end of ajax
	return url;
}//end of method--




//구글로그인 핸들러
function handleCredentialResponse(response) {
	const responsePayload = parseJwt(response.credential);
//	  console.log("ID: " + responsePayload.sub);
//    console.log('Full Name: ' + responsePayload.name);
//    console.log('Given Name: ' + responsePayload.given_name);
//    console.log('Family Name: ' + responsePayload.family_name);
//    console.log("Image URL: " + responsePayload.picture);
//    console.log("Email: " + responsePayload.email);
    
    const userInfo = {userid:responsePayload.sub,
    				  email:responsePayload.email,
    				  profile_image:responsePayload.picture,
    				  nickname:responsePayload.given_name,
    				  email_acept:'0',
    				  username:responsePayload.name,
    				  flag:'google'}
    $.ajax({
		type : 'post',
		url : getContextPath()+'/googleLoginPro.do',
		data : userInfo,
		dataType : 'json',
		async:false,
		success : function(data){
		  console.log(data)
		  if(data.JavaData == "YES"){
			 user_status(data.userid);
		  }else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			 userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		  }else{
		    alert("로그인에 실패했습니다");
		  }
		},
		error: function(xhr, status, error){
			alert("로그인에 실패했습니다."+error);
		}
	});//end of ajax
}//end of method--



/**
 * 구글로그인 유저정보JWT 파싱하기
 * @param token
 */
function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};








// 페이스북 로그인

//기존 로그인 상태를 가져오기 위해 Facebook에 대한 호출
function statusChangeCallback(res){
	statusChangeCallback(response);
}

function fnFbCustomLogin(){
	FB.login(function(response) {
		if (response.status === 'connected') {
			console.log(response);
			FB.api('/me', 'get', {fields: 'name,email,picture'}, function(response) {
				let fb_data = jQuery.parseJSON(JSON.stringify(response));
				
				//가져온 데이터 콘솔출력
//				console.log(fb_data);
				
				const userInfo = {userid:fb_data.id
								 ,email:fb_data.email
								 ,profile_image:fb_data.picture.data.url
								 ,nickname:fb_data.name
								 ,email_acept:'0'
			  		 			 ,username:fb_data.name
			  		 			 ,flag:'facebook'};
				//페이스북 로그인 처리 메소드 호출
				facebookLoginPro(userInfo);
			})
		} else if (response.status === 'not_authorized') {
			// 사람은 Facebook에 로그인했지만 앱에는 로그인하지 않았습니다.
			alert('앱에 로그인해야 이용가능한 기능입니다.');
		} else {
			// 그 사람은 Facebook에 로그인하지 않았으므로이 앱에 로그인했는지 여부는 확실하지 않습니다.
			alert('페이스북에 로그인해야 이용가능한 기능입니다.');
		}
	}, {scope: 'public_profile,email'});
}

window.fbAsyncInit = function() {
	FB.init({
		appId      : '5826169730780578', // 내 앱 ID를 입력한다.
		cookie     : true,
		xfbml      : true,
		version    : 'v10.0'
	});
	FB.AppEvents.logPageView();   
};



/**
 * 페이스북로그인 유저정보 보내기
 * @param userInfo
 * @returns
 */
function facebookLoginPro(userInfo){
  $.ajax({
	type : 'POST',
	url : getContextPath()+'/facebookLoginPro.do',
	data : userInfo,
	dataType : 'json',
	success : function(data){
		console.log(data)
		if(data.JavaData == "YES"){
			user_status(data.userid);	//로그인처리
		}else if(data.JavaData == "register"){// 회원가입을 해야하는경우
			userSnsRegisterPro(userInfo);	//소셜로그인 회원가입 메소드
		}else{
			alert("로그인에 실패했습니다");
		}
		
	},
	error: function(xhr, status, error){
		alert("로그인에 실패했습니다."+error);
	}
  });//end of ajax
}//end of method---
