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
    <c:if test="${loginedMember == null}">
    <span>포켓 카드 노트는 로그인 후 이용 가능합니다.</span>
    </c:if>
    <c:if test="${loginedMember != null}">
    <span>안녕하세요, ${loginedMember.userNickname}님! 오늘도 포켓 카드 노트로 열공하세요!</span>
    </c:if>
  </div>
  <div id="first-main">
    <div id="first-sentence">
      <h3>학습할 내용을 문답식으로 카드에 정리해보세요!</h3>
    </div>
    <div id="first-content">
      <div id="content-title">
        <span>질문을 입력할 수 있어요.</span>
      </div>
      <hr>
      <div id="content-body">
        <span>답변을 입력할 수 있어요.</span>
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
        <h4>최근 생성한 해시태그</h4>
        
        <c:if test="${loginedMember == null}">
    	<span>로그인 후 확인하실 수 있습니다.</span>
    	</c:if>
    	
    	<c:if test="${loginedMember != null}">
    	<!--  해시태그 목록   -->
    	<ul id="recent-tag-list" class="cell row">
    	<c:if test="${recentHashTag != null}">
	    	<!-- jstl fn을 활용해 태그 문자열 split -->
	    	<c:forEach var="tagValue" items="${recentHashTag}">
	    		<li class="tag-item cell">#${tagValue}</li>
	    	</c:forEach>
    	</c:if>
    	</ul>
    	</c:if>
      </div>
    </div>
  </div>
  
  <div id="third-main">
    <div id="third-sentence">
      <h3>카드의 문제를 풀어본 뒤, 학습 필요/학습 완료 상태를 설정해보세요!</h3>
    </div>
    <div id="third-content">
      <span>본인만의 답변을 자유롭게 서술한 후, 정답과 비교해볼 수 있습니다.</span>
      <div id="learning-status-box">
        <h4>카드 학습 현황</h4>
        
        <c:if test="${loginedMember == null}">
    	<span>로그인 후 확인하실 수 있습니다.</span>
    	</c:if>
        
        <c:if test="${loginedMember != null}">
        <div class="learning-status-count">
          전체 카드 수는 ${allLearningCnt} 개입니다
        </div>
        <div class="learning-status-count">
          학습 필요 카드 수는 ${needLearningCnt} 개입니다
        </div>
        <div class="learning-status-count">
          학습 완료 카드 수는 ${complLearningCnt} 개입니다 (${complLearningRate} %)
        </div>
        </c:if>
      </div>
  	</div>
  </div>
</section>
</body>
</html>