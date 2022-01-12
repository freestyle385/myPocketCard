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
<link rel="stylesheet" type="text/css" href="/resource/css/main.css">
</head>

</body>
  <section id="main-wrap">
  <div id="welcome-msg">
    <span>안녕하세요, ${loginedMember.userNickname}님! 오늘도 포켓 카드 노트로 열공하세요!</span>
  </div>
  <div id="first-main">
    <div id="first-sentence">
      <h3>학습할 내용을 문답식으로 카드에 정리해보세요!</h3>
    </div>
    <div id="first-content">
      <div id="content-title">
        <h4>제목을 입력할 수 있어요.</h4>
      </div>
      <hr>
      <div id="content-body">
        <h4>내용을 입력할 수 있어요.</h4>
      </div>
      <div id="content-tag">
        <span>#자바 #스프링 #태그를 추가할 수 있어요</span>
      </div>
    </div>
  </div>
    
  <div id="second-main">
    <div id="second-sentence">
      <h3>해시태그를 추가해 카드를 다양하게 그룹화하세요!</h3>
    </div>
    <div id="second-content">
      <span>학습 주제와 학습 목적에 따라 원하는 카드만 검색할 수 있습니다.</span>
      <div id="recent-tag-box">
        <span>최근 생성한 해시태그</span>
        <div id="recent-tag-list">#해시태그 값 불러오기</div>
      </div>
    </div>
  </div>
  
  <div id="third-main">
    <div id="third-sentence">
      <h3>카드의 문제를 풀어본 뒤, 학습 필요/학습 완료 상태를 설정해보세요!</h3>
    </div>
    <div id="third-content">
      <span>카드의 정답을 숨김 설정 후, 본인만의 답변을 자유롭게 서술해볼 수 있습니다.</span>
      <div id="learning-status-box">
        <span>학습 필요/학습 완료 현황</span>
        <div id="learning-status-count">
          10개 / 2개
        </div>
      </div>
  	</div>
  </div>
</section>
</body>
</html>