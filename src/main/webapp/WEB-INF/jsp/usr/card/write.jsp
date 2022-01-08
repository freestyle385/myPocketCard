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
<link rel="stylesheet" type="text/css" href="/resource/css/write.css">
<script src="resource/js/common.js"></script>

</head>

</body>
<article class="row">
<!-- empty wrap -->
<section class="empty-wrap cell"></section>
  
<!-- card-wrap -->
<section id="card-wrap" class="cell">
  <div id="tag-wrap" class="row">
    <div id="tag-input" class="cell">
      <input type="text" id="tag" placeholder="추가할 해시 태그"/>
    </div>
    
    <!--  해시태그 목록   -->
    <ul id="tag-list" class="cell row"></ul>
  </div>
  <span id="msg">*입력 후 엔터를 누르면 적용됩니다</span>
  
  <form id="card" class="row" action="../../usr/card/doWrite" method="POST">
    <!-- 게시물 저장 시 함께 넘겨줄 tag값들을 위한 hidden input -->
    <input type="hidden" value="" name="tagStatus" id="rdTag"/>
    <div id="card-info" class="cell"></div>
    <div id="card-body" class="cell">
      <div id="question"><span>Q.</span><textarea name="title" rows="1" autocomplete="off" placeholder="질문을 입력해주세요."></textarea></div>
      <hr>
      <div id="answer"><span>A.</span><textarea name="body" rows="20" autocomplete="off" placeholder="답변을 입력해주세요."></textarea></div>
    </div>
    <div class="btn-list row">
      <div class="btn cell">
        <input class="back-btn" type="button" value="뒤로가기" onclick="history.back(-1);">
      </div>
      <div class="btn cell-r">
        <input class="submit-btn" type="button" value="저장"/>
      </div>
    </div>
   </form>
</section>

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>

<script>
$(document).ready(function () {
	  // 외부. tag 생성을 위한 배열
	  var tag = new Array();
	  var counter = 0;
	
	  // 외부. 입력한 값을 tag로 생성
	  function addTag (value) {
	    tag[counter] = value; // 태그를 Object 안에 추가
	    counter++; // 태그 생성 시 같이 생성되는 del-btn의 id
	  }
	
	  // 내부. tag 배열의 값들을 value 배열로 저장
	  function marginTag () {
	    return Object.values(tag).filter(function (word) {
	      return word !== "";
	    });
	  }
	  
	// 외부. 태그 추가 이벤트
	  $("#tag").on("keyup", function (e) {
	    // 키보드에 입력된 값
		var self = $(this);
	
	    // 엔터를 눌렀을 때 실행
	    if (e.key === "Enter") {
	      // self의 값 가져오기
	      var tagValue = self.val().trim(); 
		
	      // tagValue가 빈 칸이면 태그 추가X
	      if (tagValue == ""){
	    	  alert("올바르지 않은 태그입니다.");
	    	  return;
	      } else {
	        // result는 tag 배열의 값과 새로 입력된 태그가 같은지 검사(===)
	        // 있다면 result에 tagValue 추가X, 없다면 tagValue 추가
	        var result = Object.values(tag).filter(function (word) {
	          return word === tagValue;
	        });
	
	        // result에 따라 addTag 함수 실행 또는 반려
	        if (result.length == 0) { 
	          $("#tag-list").append("<li class='tag-item cell'>"+"#"+tagValue+"<span class='del-btn' idx='"+counter+"'> x</span></li>");
	          addTag(tagValue);
	          self.val("");
	        } else {
	          alert("이미 존재하는 태그입니다.");
	          return;
	        }
	      } 
	    
	      e.preventDefault(); // 스페이스바로 빈 공간이 생기지 않도록 방지
	    }
	  });
	
	  // 외부. 태그 삭제 이벤트 
	  // 인덱스를 찾아내 li 삭제 및 tag 배열에서 값 삭제
	  $(document).on("click", ".del-btn", function (e) {
	    var index = $(this).attr("idx");
	    tag[index] = "";
	   
	    $(this).parent().remove();
	  });
	  
	  // 내부. 게시물 저장 버튼 클릭 시 tag값들을 같이 넘겨줌
	  $(".submit-btn").on("click", function (e) {
	    // marginTag 데이터를 hidden input에 문자열로 적용
	    var value = marginTag().toString();
	    $("#rdTag").val(value);
		
	    $("#card").submit();
	  });
});
</script>
</body>
</html>