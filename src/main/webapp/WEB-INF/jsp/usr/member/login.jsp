<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- OAuth2.0 클라이언트ID -->
<meta name ="google-signin-client_id" content="27159324347-72k26vcohvo86prrvroetf0ip16laiqk.apps.googleusercontent.com">

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
    <span>Google 또는 Github로 편하게 로그인하세요!</span>
  </div>
  <ul id="login-menu">
    <li id="google-login-btn" class="login-box">
      <a href="javascript:void(0)" class="row">
        <img class="cell" src="/resource/img/google_logo.png">
        <span>Google 계정으로 로그인하기</span>
      </a>
    </li>
    <li id="github-login-btn" class="login-box">
      <a href="javascript:void(0)" class="row">
        <img class="cell" src="/resource/img/github_logo.png">
        <span>Github 계정으로 로그인하기</span>
      </a>
    </li>
  </ul>
</section>  

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>


</body>
</html>