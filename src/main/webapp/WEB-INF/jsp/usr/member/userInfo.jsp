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
<link rel="stylesheet" type="text/css" href="/resource/css/userInfo.css">

</head>

</body>
<article class="row">
<!-- empty wrap -->
<section class="empty-wrap cell"></section>
  
<!-- loginInfo-wrap -->
<section id="loginInfo-wrap" class="cell">
  <div id="info-msg">
    <span>${loginedMember.userNickname}님의 회원 정보</span>
  </div>
  <table id="info-table">
    <thead>
      <tr>
      	<th>구분</th>
      	<th>정보</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>정보 출처</th>
        <td>${loginedMember.infoOrigin}</td>
      </tr>
      <tr>
        <th>계정</th>
        <td>${loginedMember.userEmail}</td>
      </tr>
      <tr>
        <th>닉네임</th>
        <td>${loginedMember.userNickname}</td>
      </tr>
      <tr>
        <th>가입일</th>
        <td>${loginedMember.regDate}</td>
      </tr>
    </tbody>
  </table>
</section>

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>

</body>
</html>