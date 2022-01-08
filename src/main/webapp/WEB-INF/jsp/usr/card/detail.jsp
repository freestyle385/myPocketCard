<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포켓 카드 노트</title>
<!-- header.jspf 불러오기 -->
<%@ include file="../common/header.jspf"%>

<!-- css / js -->
<link rel="stylesheet" type="text/css" href="/resource/css/detail.css">

</head>

</body>
<article class="row">
<!-- empty wrap -->
<section class="empty-wrap cell"></section>
  
<!-- card-wrap -->
<section id="card-wrap" class="cell">
  <div id="tag-wrap" class="row">
    <!--  해시태그 목록   -->
    <ul id="tag-list" class="cell row">
    	<!-- jstl fn을 활용해 태그 문자열 split -->
    	<c:forEach var="tagValue" items="${fn:split(cardRd.getData().getTagStatus(), ',')}">
    		<li class="tag-item cell">#${tagValue}</li>
    	</c:forEach>
    </ul>
  </div>
  
  <div id="move-btn-wrap" class="row">
  	<a href="/usr/card/detail?cardId=${cardRd.getData().getId()-1}" id="prev-btn" class="move-btn cell"><< 이전 글</a>
  	<a href="/usr/card/detail?cardId=${cardRd.getData().getId()+1}" id="next-btn" class="move-btn cell-r">다음 글 >></a>
  </div>
  
  <div id="card" class="row">
    <div id="card-info" class="cell">
      <div id="card-num"><span>${cardRd.getData().getId()}</span></div>
    </div>
    <div id="card-body" class="cell">
      <div id="question"><span>Q.</span>${cardRd.getData().getTitle()}</div>
      <hr>
      <!-- 정답 숨김 시작 -->
      <div id="answer" class=""><span>A.</span>${cardRd.getData().getBody()}</div>
      <hr id="disappearable" class="">
      <!-- 정답 숨김 끝 -->
      <div id="answer-check" class="active">↓정답 확인하기↓</div>
      <div id="answer-hide" class="">↑정답 숨기기↑</div>
      <div id="usr-answer"><span>A.</span><textarea rows="20" autocomplete="off" placeholder="자유롭게 정답을 작성해보세요. 이 정답은 저장되지 않습니다."></textarea></div>
    </div>
   </div>
  
   <div class="btn-list row">
      <div class="del-wrap cell">
        <a class="card-del-btn" onclick="if (confirm('카드를 삭제하시겠습니까?') == false) {return false};" href="/usr/card/doDelete?cardId=${cardRd.getData().getId()}">삭제</a>
      </div>
      <div class="modify-wrap cell-r">
        <a class="modify-btn" href="/usr/card/showModify?cardId=${cardRd.getData().getId()}">수정</a>
      </div>
    </div>
</section>

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>

<script>
$(document).ready(function(){
	// 정답 숨김 관련  
	$("#answer-check").click(function(){
		$("#answer").addClass("active");
      	$("#disappearable").addClass("active");
      	$(this).removeClass("active");
      	$("#answer-hide").addClass("active");
	    
	})
	$("#answer-hide").click(function(){
		$("#answer").removeClass("active");
	    $("#disappearable").removeClass("active");
	    $(this).removeClass("active");
	    $("#answer-check").addClass("active");  
	})
	  
	      
	   
});
</script>
</body>
</html>