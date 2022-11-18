// js파일에서 contextPath를 알아내는 함수

function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
  /**
   * 일반회원가입 클릭시
   */
  $("div#memberSignup").click(()=>{
    let check_length = $("input:checkbox[name='agreement']:checked").length;
    if(check_length < 2){
      alert("이용약관에 동의하여주세요");
      return;
    }
    else if(check_length == 2){
      location.href = getContextPath() + "/signup.do";
    }
  });


  /**
   * 교육기관회원가입 클릭시
   */
  $("div#acaMemberSignup").click(()=>{
    let check_length = $("input:checkbox[name='agreement']:checked").length;
    if(check_length < 2){
      alert("이용약관에 동의하여주세요");
      return;
    }
    else if(check_length == 2){
      location.href = getContextPath() + "/acaSignup.do";
    }
  });
  
  
});