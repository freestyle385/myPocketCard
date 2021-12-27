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
<link rel="stylesheet" type="text/css" href="/resource/css/list.css">

</head>

</body>
<article class="row">
<!-- 카드 상태 필터 -->
<section id="status-filter" class="cell">
  <div class="side-bar-name"><h4>카드 상태 검색</h4></div>
  <form action="/usr/card/list" class="side-bar-form">
    <div class="filter">
      <div class="filter-name"><span>해시 태그 필터</span></div>
      <div class="filter-select">
        <select name="tagStatus">
          <option value="-1" selected>전체 선택</option>
          <option value="0">학습 필요</option>
          <option value="1">학습 완료</option>
        </select>
      </div>
    </div>
    <div class="filter">
      <div class="filter-name"><span>학습 상태 필터</span></div>
      <div class="filter-select">
        <select name="learningStatus">
          <option value="-1" selected>전체 선택</option>
          <option value="0">학습 필요</option>
          <option value="1">학습 완료</option>
        </select>
      </div>
    </div>
    <div class="filter">
      <div class="filter-name"><span>정답 상태 필터</span></div>
      <div class="filter-select">
        <select name="answerHideStatus">
          <option value="-1" selected>전체 선택</option>
          <option value="0">정답 표시</option>
          <option value="1">정답 숨김</option>
        </select>
      </div>
    </div>
    <div class="btn"><input type="submit" value="검색"></div>
  </form>
</section>

<!-- 리스트 -->
<section id="list-wrap">
  <!-- 검색창 -->
  <form id="searchKeyword-box">     
    <input name="searchKeyword" type="text" placeholder="제목과 내용의 키워드를 검색해보세요." value="${param.searchKeyword}">
    <button type="submit">
      <svg aria-hidden="true" data-prefix="fas" data-icon="search" class="search_svg__svg-inline--fa search_svg__fa-search search_svg__fa-w-16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="1.3rem" height="1.3rem"><path d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path></svg>
	</button>
  </form>
  <!-- 카드 리스트 -->
  <div id="list-main">
    <div id="checkbox-wrap" class="row">
      <div id="checkbox-all" class="cell">
        <input type="checkbox" id="checkAll" class="chk"/>
      </div>
      <div id="checkbox-all-label" class="cell">
        <label for="checkedAll">전체 선택</label>
      </div>
    </div>
    <div id="card-list">
      <!-- 체크박스의 value에 구분자 추가 -->
      <!-- addString + card.id = id1, id2... -->
	  <c:set var="addString" value="id"></c:set>
   	  <c:forEach var="card" items="${listRd.getData()}">
   	  	<div id="card" class="row">
	        <div id="card-info" class="cell">
	          <div id="card-num"><span>${card.id }</span></div>
	          <div id="checkbox-one"><input type="checkbox" name="selected" class="chk" value="${addString }${card.id }"/></div>
	        </div>
	        <div id="card-body" class="cell">
	          <div id="title"><span>Q.</span><a href="/usr/card/detail">${card.title }</a></div>
	          <hr>
	          <div id="hashtag"><span>#.</span>${card.tagStatus}</div>
	        </div>
      	</div>
   	  </c:forEach>
    </div>
  </div>
  <!-- 리스트 페이징 -->
  <div id="list-paging"></div>
</section>

<!-- setter.jspf 불러오기 -->
<%@ include file="../common/statusSetter.jspf"%>
</article>

<script>
$(document).ready(function(){
	// 컨트롤러에서 넘겨받은 카드 리스트의 길이  
	var cardListLen = ${listRd.getData().size()};
	  
  	// 전체선택 버튼 클릭시 체크박스 전체 선택
    $("#checkAll").click(function(){
        if($("#checkAll").is(":checked")){
            $(".chk").prop("checked", true);
        } else {
            $(".chk").prop("checked", false);
        }
    });
  
   	// 전체 선택 중 한개의 체크박스 선택 해제 시 전체선택 체크박스 해제
    $(".chk").click(function(){
        if($("input[name='selected']:checked").length == cardListLen){
            $("#checkAll").prop("checked", true);
        }else{
            $("#checkAll").prop("checked", false);
        }
    });
 	
   	// 카드 상태 필터/변경 버튼 클릭 시 체크된 카드를 배열로 정리하여 넘김
 	$(".filter-btn, .change-btn").click(function(){
 		// selectedList 배열
 		var selectedList = new Array();
 		var $this = $(this);
 		
 		$("input[name=selected]:checked").each(function() {		                				
 			selectedList.push($(this).val());
 		});
 		
		if(selectedList.length == 0){
			alert("카드를 선택하세요.");
			return;
		}
		
		/* // https://devfootprint.tistory.com/58 참고해서 배열 넘김 기능 구현 요
		// 체크된 value 외의 다른 값이 필요하다면 hidden input으로 넘겨줄 것
		// 필터/변경에 따라 각각 다른 제출 버튼 실행
		if($this.hasClass("filter-btn")){
			$("#filter-form").attr("action", "/usr/card/list");
			$("#change-form").submit();
		} else{
			$("#change-form").attr("action", "/usr/card/list");
			$("#change-form").submit();
		} */
	
	});  
});
</script>
</body>
</html>