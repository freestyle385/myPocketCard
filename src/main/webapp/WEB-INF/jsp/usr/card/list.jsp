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
        <input type="checkbox" id="checkedAll"/>
      </div>
      <div id="checkbox-all-label" class="cell">
        <label for="checkedAll">전체 선택</label>
      </div>
    </div>
    <div id="card-list">
      <!-- 체크박스의 value에 구분자 추가 -->
      <!-- addString + card.id = id1, id2... -->
	  <c:set var="addString" value="id"></c:set>
<<<<<<< HEAD
   	  <c:forEach var="card" items="${listRd.data }">
=======
   	  <c:forEach var="card" items="${listRd.data}">
>>>>>>> 3974a76e68ca42a766462a6a09ed58cf0afa026c
   	  	<div id="card" class="row">
	        <div id="card-info" class="cell">
	          <div id="card-num"><span>${card.id }</span></div>
	          <div id="checkbox-one"><input type="checkbox" name="selectedCard" value="${addString }${card.id }"/></div>
	        </div>
	        <div id="card-body" class="cell">
	          <div id="title"><span>Q.</span><a href="/usr/card/detail">${card.title }</a></div>
	          <hr>
<<<<<<< HEAD
	          <div id="hashtag"><span>#.</span>${card.tagStatus }</div>
=======
	          <div id="hashtag"><span>#.</span>${card.tagStatus}</div>
>>>>>>> 3974a76e68ca42a766462a6a09ed58cf0afa026c
	        </div>
      	</div>
   	  </c:forEach>
    </div>
  </div>
  <!-- 리스트 페이징 -->
  <div id="list-paging"></div>
</section>
</body>
</html>