<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓 카드 노트</title>
<!-- header.jspf 불러오기 -->
<%@ include file="../common/header.jspf"%>

<!-- css / js -->
<link rel="stylesheet" type="text/css" href="/resource/css/login.css">

</head>

</body>
<article class="row">
<!-- empty wrap -->
<section class="empty-wrap cell"></section>
  
<!-- login-wrap -->
<section id="login-wrap" class="cell">
  <div id="login-msg">
    <span>카카오 계정으로 편하게 로그인하세요!</span>
  </div>
  <c:if test="${userId==null}">
  <div id="kakao-login-btn">
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=477d92a75a81ced855b4f349c5380bf4&redirect_uri=http://localhost:8081/usr/member/doLogin&response_type=code" class="row">
      <img class="cell" src="/resource/img/kakao_login_btn.png">
    </a>
  </div>
  </c:if>
  <c:if test="${userId!=null}">
  <div id="kakao-logout-btn" class="login-box">
    <form name="logout" action="/usr/member/doLogout">
	  <input type="submit" value="로그아웃">
    </form>
  </div>
  </c:if>
</section>  

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>


</body>
</html>