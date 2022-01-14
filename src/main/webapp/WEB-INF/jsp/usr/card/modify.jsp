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
<link rel="stylesheet" type="text/css" href="/resource/css/modify.css">

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
    <ul id="tag-list" class="cell row">
      <!-- jstl fn을 활용해 태그 문자열 split -->
      <c:if test="${cardRd.getData().getTagStatus() != ''}">
	      <c:set var="tagStatusList" value="${fn:split(cardRd.getData().getTagStatus(), ',')}"/>
	      <c:forEach var="tagValue" items="${tagStatusList}" varStatus="status">
	      	<li class="tag-item cell"><span>#</span>${tagValue}<span class='del-btn' idx="${status.index}"> x</span></li>
	      </c:forEach>
      </c:if>
    </ul>
  </div>
  <span id="msg">*입력 후 엔터를 누르면 적용됩니다</span>
  
  <form id="card" class="row" action="../../usr/card/doModify?cardId=${cardRd.getData().getId()}" method="POST">
    <!-- 게시물 저장 시 함께 넘겨줄 tag값들을 위한 hidden input -->
    <input type="hidden" value="" name="tagStatus" id="rdTag"/>
    
    <div id="card-info" class="row cell">
      <div id="card-num" class="cell"><span>No.</span>${cardRd.getData().getId()}</div>
      <div id="setter-box" class="row cell-r">
        <div id="setter-name" class="cell"><span>학습 상태</span></div>
        <div id="setter-select" class="cell">
          <select name="learningStatus">
            <option value="0" ${cardRd.data.learningStatus == 0 ? 'selected' : ''}>학습 필요</option>
            <option value="1" ${cardRd.data.learningStatus == 1 ? 'selected' : ''}>학습 완료</option>
          </select>
        </div>
      </div>
    </div>
    
    <div id="card-body" class="cell">
      <div id="question"><span>Q.</span><textarea name="title" rows="1" autocomplete="off" placeholder="질문을 입력해주세요.">${cardRd.getData().getTitle()}</textarea></div>
      <div id="byte-box"><span id="nowByte">0</span> / 100 byte</div>
      <hr>
      <div id="answer"><span>A.</span><textarea name="body" rows="20" autocomplete="off" placeholder="답변을 입력해주세요.">${cardRd.getData().getBody()}</textarea></div>
    </div>
   </form>
   
   <div class="btn-list row">
      <div class="btn cell">
        <input class="back-btn" type="button" value="뒤로가기" onclick="history.back(-1);">
      </div>
      <div class="btn cell-r">
        <input class="submit-btn" type="button" value="저장"/>
      </div>
    </div>
</section>

<!-- empty wrap -->
<section class="empty-wrap cell"></section>
</article>

<script>
$(document).ready(function(){
	  // 외부. 수정 이전에 생성된 태그 문자열을 split
	  const oldTag = "${cardRd.getData().getTagStatus()}";
	  var oldTagList = new Array();
	  
	  if(oldTag != ''){
		  oldTagList = oldTag.split(',');
	  }
	  
	  // 외부. tag 생성을 위한 배열
	  var tag = new Array();
	  var counter = 0;
	  
	  // 외부. oldTagList를 tag 배열에 반영하고 그 다음부터 인덱스 계산
	  tag = tag.concat(oldTagList);
	  counter += oldTagList.length;
	
	  // 외부. 입력한 값을 tag로 생성
	  function addTag (value) {
	    tag[counter] = value; // 태그를 Object 안에 추가
	    counter++; // 태그 생성 시 같이 생성되는 del-btn의 id
	    console.log(tag);
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

fn_checkByte();

$("#question > textarea").on("keyup", fn_checkByte);

function fn_checkByte(){
    const maxByte = 100; //최대 100바이트
    const text_val = $("#question > textarea").val(); //입력한 문자
    console.log(text_val);
    const text_len = text_val.length; //입력한 문자수
    
    let totalByte=0;
    for(let i=0; i<text_len; i++){
    	const each_char = text_val.charAt(i);
        const uni_char = escape(each_char) //유니코드 형식으로 변환
        if(uni_char.length>4){
        	// 한글 : 2Byte
            totalByte += 2;
        }else{
        	// 영문,숫자,특수문자 : 1Byte
            totalByte += 1;
        }
    }
    
    if(totalByte>maxByte){
    	alert('최대 100Byte까지만 입력가능합니다.');
        	document.getElementById("nowByte").innerText = totalByte;
            document.getElementById("nowByte").style.color = "red";
        }else{
        	document.getElementById("nowByte").innerText = totalByte;
            document.getElementById("nowByte").style.color = "green";
        }
    }
</script>
</body>
</html>