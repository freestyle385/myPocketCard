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
<link rel="stylesheet" type="text/css" href="/resource/css/list.css">

</head>

</body>
<article class="row">

<!-- empty wrap -->
<section class="empty-wrap cell"></section>

<!-- 리스트 -->
<section id="list-wrap" class="cell">
  
  <!-- 검색 폼 -->
  <form action="/usr/card/list" method="GET" id="search-form">     
    <!-- 상태 검색 필터 -->
    <div id="filter-wrap" class="row">
      <div class="filter-box cell">
        <div class="filter-name"><span>해시 태그 필터</span></div>
        <div class="filter-select">
          <select name="tagStatus">
	            <option value="" selected>전체 선택</option>
            <c:forEach var="hashTag" items="${allHashTag}">
	            <option value="${hashTag}">${hashTag}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="filter-box cell-r">
        <div class="filter-name"><span>학습 상태 필터</span></div>
        <div class="filter-select">
          <select name="learningStatus">
            <option value="-1" selected>전체 선택</option>
            <option value="0">학습 필요</option>
            <option value="1">학습 완료</option>
          </select>
        </div>
      </div>
  	</div>
  	<!-- 검색창 -->
   	<div id="searchKeyword-box">
      <input name="searchKeyword" placeholder="질문과 답변의 키워드를 검색해보세요."/>
	  <button type="submit" class="search-btn">
	      <svg aria-hidden="true" data-prefix="fas" data-icon="search" class="search_svg__svg-inline--fa search_svg__fa-search search_svg__fa-w-16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" width="1.3rem" height="1.3rem"><path d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5 7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7 0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128 128 0 70.7-57.2 128-128 128z"></path></svg>
	  </button>
	</div>
  </form>
  
  <!-- 카드 리스트 -->
  <div id="list-main">
    <div id="checkbox-wrap" class="row">
      <div id="checkbox-all" class="cell">
        <input type="checkbox" id="checkAll" class="chk"/>
      </div>
      <div id="checkbox-all-label" class="cell">
        <label for="checkAll">전체 선택</label>
      </div>
    </div>
    <div id="card-list">
   	  <c:forEach var="card" items="${listRd.getData()}">
   	  	<div id="card" class="row" >
	        <div id="card-info" class="cell">
	          <div id="card-num"><span>${card.id }</span></div>
	          <div id="checkbox-one"><input type="checkbox" name="cardId" id="select-chk" class="chk" value="${card.id}"/></div>
	        </div>
	        <div id="card-body" class="cell">
	          <div id="title"><span>Q.</span><a href="/usr/card/detail?cardId=${card.id}">${card.title }</a></div>
	          <hr>
	          <div id="hashtag"><span>#.</span>
	          	<ul>
	          		<c:forEach var="tagValue" items="${fn:split(card.tagStatus, ',')}">
    					<li class="tag-item cell">#${tagValue}</li>
    				</c:forEach>
	          	</ul>
	          </div>
	        </div>
      	</div>
   	  </c:forEach>
    </div>
  </div>
  <!-- 리스트 페이징 -->
  <div id="list-paging"></div>
</section>

<!-- 카드 상태 설정 -->
<section id="status-setter" class="cell-r">
  <div class="side-bar-name"><h4>카드 상태 설정</h4></div>
  <form action="/usr/card/setCardCondition" method="POST" class="setter-form">
    <input type="hidden" value="" name="cardId" id="selected"/>
    <div class="setter-box">
      <div class="setter-name"><span>학습 상태 변경</span></div>
      <div class="setter-select">
        <select name="learningStatus">
          <option disabled="disabled" selected>변경할 상태</option>
          <option value="0">학습 필요</option>
          <option value="1">학습 완료</option>
        </select>
      </div>
    </div>
    
    <div class="btn"><input class="change-btn" type="button" value="저장"></div>
  </form>
</section>
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
        if($("input[id='select-chk']:checked").length == cardListLen){
            $("#checkAll").prop("checked", true);
        }else{
            $("#checkAll").prop("checked", false);
        }
    });
 	
   	// 상태 변경 버튼 클릭 시 setter-form의 hidden input에 체크박스 데이터 삽입 후 제출
 	$(".change-btn").on("click", function (e) {
 		
		if ($("input[id='select-chk']:checked").length == 0){
			alert("카드를 선택하세요.");
			return;
		} else {
			// checkedList에 체크박스 값을 넣어 #selected로 주입 
			var checkedList = new Array();
			$("input:checkbox[id='select-chk']:checked").each(function(){
				checkedList.push(this.value);
			});
			
			$("#selected").val(checkedList.toString());
			
			$(".setter-form").submit();
		}
	});  
});
</script>
</body>
</html>