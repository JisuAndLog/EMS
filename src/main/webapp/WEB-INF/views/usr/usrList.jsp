<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 조회</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
  $(document).ready(function(){
//		getSexTypeOptions();
		getSkillTypeOptions();
		getOfficeTypeOptions();
		getRoleTypeOptions();
		getMenuNameByMenuno();
    var actionForm = $("#actionForm");
    $(".paginate_button a").on("click", function(e) {
      e.preventDefault();
      console.log('click');
      actionForm.find("input[name='pageNum']").val($(this).attr("href"));     
      actionForm.submit();
    });
    
    function getSkillTypeOptions() {
  	  var selectedValue = '${jobSkill}';
  	  $.ajax({
  	    type: "GET",
  	    url: "/code/codeVal",
  	    data: { codeVal: "D300" },
  	    dataType: "json",
  	    success: function(response) {
  	      var options = "<button disabled>기술등급</button>";
  	      options += "<select name='jobSkill' id='search_usr'>";
  	      options += "<option value='0'>선택없음</option>"; // 기본 옵션 추가

  	      $.each(response, function(index, option) {
                options += "<option value='" + option.dtlcd + "'";
                if (option.dtlcd === selectedValue) {
                    options += " selected";
                }
                options += ">" + option.dtlcdnm + "</option>";
            });
  	      options += "</select>";
  	      $(".skillSelect").html(options);
  	    },
  	    error: function(xhr, status, error) {
  	      console.log(error);
  	    }
  	  });
  	}
    
    function getOfficeTypeOptions() {
    	  var selectedValue = '${inoffi_sts}';
    	  $.ajax({
    	    type: "GET",
    	    url: "/code/codeVal",
    	    data: { codeVal: "D500" },
    	    dataType: "json",
    	    success: function(response) {
    	      var options = "<button disabled>재직상태</button>";
    	      options += "<select name='inoffi' id='search_usr'>";
    	      options += "<option value='0'>선택없음</option>"; // 기본 옵션 추가   	      

    	      $.each(response, function(index, option) {
                  options += "<option value='" + option.dtlcd + "'";
                  if (option.dtlcd === selectedValue) {
                      options += " selected";
                  }
                  options += ">" + option.dtlcdnm + "</option>";
              });
    	      options += "</select>";
    	      $(".inoffiSelect").html(options);
    	    },
    	    error: function(xhr, status, error) {
    	      console.log(error);
    	    }
    	  });
    	}
    
    /* function getSexTypeOptions() {
  	  var selectedValue = '${sex}';
  	  $.ajax({
  	    type: "GET",
  	    url: "/code/codeVal",
  	    data: { codeVal: "D100" },
  	    dataType: "json",
  	    success: function(response) {
  	      var options = "<button disabled>성별</button>";
  	      options += "<select name='sex' id='search_usr'>";
  	      options += "<option  value='0'  selected >선택없음</option>"; // 기본 옵션 추가
  	      $.each(response, function(index, option) {
                options += "<option value='" + option.dtlcd + "'";
                if (option.dtlcd === selectedValue) {
                    options += " selected";
                }
                options += ">" + option.dtlcdnm + "</option>";
            });
  	      options += "</select>";
  	      $(".sexSelect").html(options);
  	    },
  	    error: function(xhr, status, error) {
  	      console.log(error);
  	    }
  	  });
  	} */
    
    function getRoleTypeOptions() {
  	  $.ajax({
  	    type: "GET",
  	    url: "/code/codeVal",
  	    data: { codeVal: 'D700' },
  	    dataType: "json",
  	    success: function(response) {
  	      var options = "<tr>";
  	      options += "<th>프로젝트역할</th>";
  	      options += "<td>";
  	      options += "<select name='pjtRole'>";
  	      $.each(response, function(index, option) {
  	        options += "<option value='" + option.dtlcd + "'>" + option.dtlcdnm + "</option>";
  	      });
  	      options += "</select>";
  	      options += "</td>";
  	      options += "</tr>";
  	      $("#projectRoletr").replaceWith(options);
  	    },
  	    error: function(xhr, status, error) {
  	      console.log(error);
  	    }
  	  });
  	}
    
    /* 체크된 체크박스 요소들이 이름 수집 > 배열로 반환 */
    function getSelectedItems() {
  	  // 체크박스를 선택하여 선택한 항목 수집
  	  let selectedItems = [];
  	  $("input[type='checkbox']:checked").each(function () {
  	    selectedItems.push($(this).attr("name"));
  	  });
  	  return selectedItems;
  	}
    
    /* 입력값에 숫자만 허용. 숫자 이외의 문자 포함된 경우 > 경고창 */
    function checkNumbersOnly(input) {
	    const nonNumberRegex = /[^0-9]/g;
	    if (input.value.match(nonNumberRegex)) {
	        alert("숫자만 입력 가능합니다.");
	    }

	    input.value = input.value.replace(nonNumberRegex, ""); /* 숫자 이외 문자 > 빈 문자열로 대체 */
	    
	}
	
    /* 특정 멤버 선택 > 선택한 멤버 정보를 새 창에서 열어서 확인 */
	$("#assign-selected").click(function (event) {
		 let selectedItems = getSelectedItems();

		  // 선택된 항목이 없는 경우 경고창 표시
		  if (selectedItems.length === 0) {
		    alert("선택된 인원이 없습니다.");
		    return false;
		  }
	  // 선택한 항목 ID를 URL에 전달합니다.
	  let url = "/usr/w_enroll_usr?selectedItems=" + selectedItems.join(',');
	  
	  // 새 창을 엽니다.
	  window.open(url, "_blank", "width=1000, height=800, top=0, left=250");
	  });

    	/* 시작 날짜, 종료 날짜 입력란에서 한글 입력 방지 */
    	$("#from-date, #to-date").on("input", function () {
		    let inputValue = $(this).val();
		    let noKoreanValue = inputValue.replace(/[가-힣ㄱ-ㅎㅏ-ㅣ]/g, "");
		    $(this).val(noKoreanValue);
		  });	       	
	   
		/* 현재 페이지 초기화 > 검색 조건 리셋 */
		$("#resetSearch").on("click", function() {
			location.href="/usr/usrList";
		 });
		
		
	  
	  /*
	   function getMenuNameByMenuno() {
		   var menuno = 1;
		    $.ajax({
		      type: "GET",
		      url: "/getMenuName",
		      data: { menuno: menuno },
		      dataType: "text",
		      success: function(data) {
		    	  // 새로운 H1 태그를 생성하고 가져온 메뉴 이름을 텍스트로 설정
		          const newH1 = $('<h1>').text(data);

		          // 기존의 H1 태그를 새로 만든 태그로 교체
		          $("#menu-title").replaceWith(newH1);
		      },
		      error: function(xhr, status, error) {
		        console.log("Error: " + error);
		      }
		    });
		  } 
	  */
	   
  }); /* ready function end */
  </script>
</head>


<body>
	<div class="usr_container">
	<%-- <%@ include file= "../left_side_menu.jsp"%> 메뉴 상단 바 --%>
	<div class="main">
		<div class="greeting-container">
		<%-- <h1 id="menu-title"></h1>
	    	<%@ include file="../common/greeting.jsp" %> --%>
	    </div>
	    <hr/>		
		<h3>사원 조회</h3>
		<div class="search">
			<form id='searchForm' action="usrList" method="post">
				<button disabled>성명</button>
				<input type="text" name="nm" id="search_usr" oninput="checkInputLength(this)" onchange="checkInputLanguage(this)" autocomplete="off" />				
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />

				<button disabled>기술등급</button>
				<select class="skillSelect" name="jobSkill" id="search_usr">					
				</select>

				<button disabled>재직상태</button>
				<select class="inoffiSelect" name="inoffi_sts" id="search_usr">					
				</select>

				<button disabled>입사일</button>
				<input type="text" id="from-date" name="from" maxlength="8" oninput="checkNumbersOnly(this);" value="${from}" autocomplete="off" required> 
				<span> ~ </span> 
				<input type="text" id="to-date" name="to" maxlength="8" oninput="checkNumbersOnly(this);" value="${to}" autocomplete="off" required>
				<script type="text/javascript" src="/resources/js/date_check_function.js"></script>
				
				
				<button class="btn btn-primary">검색</button>
				&nbsp;				
				<button type="button" onclick="location.href='/usr/enroll_usr' ">사원 등록</button>
				<!--<button class="btn btn-default" id="resetSearch" type="reset">초기화</button>-->
			</form>
		</div>
		<br>

		<form action="/usr/usrList" method="GET">

			<div id="usr-table" class="table">
				<table>
					<tr>
						<th onclick="toggleCheckboxes()">선택</th>
						<th>입사일</th>
						<th>사번</th>
						<th>성명</th>
						<th>생년월일</th>
						<th>기술등급</th>
						<th>재직상태</th>
					</tr>

					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="9">검색결과를 찾을 수 없습니다.</td>
							</tr>
						</c:when>

						<c:otherwise>
							<c:forEach var="usr" items="${list}">
								<tr>
									<td><input type="checkbox" />
									
									<td><fmt:parseDate value="${usr.entrDate}" 
											pattern="yyyy-MM-dd" var="parsedEntrDate" />
											<fmt:formatDate value="${parsedEntrDate}" pattern="yyyy-MM-dd" /></td>
											
									<td>${usr.no}</td>
									<td>${usr.nm}</td>
									<td><fmt:parseDate value="${usr.birth}" pattern="yyyy-MM-dd"
											var="parsedBirth" /> <fmt:formatDate value="${parsedBirth}"
											pattern="yyyy-MM-dd" /></td>
									<td>${usr.jobSkill}</td>
									<td>${usr.inoffi_sts}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
						
					</c:choose>
				</table>

				<!-- 페이징 -->
				<div class="text-center">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage - 1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li
								class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''}">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</div>

				<!-- 페이징 form -->
				<form id="actionForm" action="/usr/usrList" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" /> 
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}" /> 
					<input type="hidden" name="jobSkill" value="${jobSkill}" /> 
					<input type="hidden" name="inoffi_sts" value="${inoffi_sts}" /> 
					<input type="hidden" name="from" value="${from}" /> 
					<input type="hidden" name="to" value="${to}" />
				</form>
				
	</div>		
		<%-- <c:if test="${authno == 1}"> --%>
			<div class="multi-function">
			  	 <button id="assign-selected">투입</button>&nbsp;
			     <span>선택항목 여러 개 선택 시</span>
			</div>
		<%-- </c:if> --%>		
	</div><!-- main end -->

				<script>
					function toggleCheckboxes() {
						var checkboxes = document
								.querySelectorAll('input[type="checkbox"]');
						var isChecked = checkboxes[0].checked;

						for (var i = 0; i < checkboxes.length; i++) {
							if (!isChecked) {
								checkboxes[i].checked = true;
							} else {
								checkboxes[i].checked = false;
							}
						}
					}
				</script>
			</div>		
	
</body>
<script>
	/* 입력 길이 제한 */
	function checkInputLength(inputElement) {
		if (inputElement.value.length > 10) {
			alert("입력은 10자 이내로 제한됩니다.");
			inputElement.value = inputElement.value.slice(0, 10);
		}
	}

	/* 언어 입력 제한 */
	function checkInputLanguage(input) {
		var regex = /^[a-zA-Z가-힣\s]*$/;

		if (!regex.test(input.value)) {
			alert('한국어와 영어만 입력 가능합니다.');
			input.value = input.value.substring(0, input.value.length - 1);
		}
	}

	/* 숫자 입력 */
	function checkNumbersOnly(input) {
		const nonNumberRegex = /[^0-9]/g;
		if (input.value.match(nonNumberRegex)) {
			alert("숫자만 입력 가능합니다.");
		}

		input.value = input.value.replace(nonNumberRegex, "");
	}

	/* 날짜 validation */
	$(document).ready(function() {
		const today = new Date();
		const shortYear = String(today.getFullYear()); // 연도의 마지막 두 자리만 필요합니다
		const mm = String(today.getMonth() + 1).padStart(2, '0');
		const dd = String(today.getDate()).padStart(2, '0');
		const currentDate = shortYear + mm + dd;
		if ('${from}' == null || '${from}' == '') {

			$("#to-date").attr("value", currentDate);
			$("#from-date").attr("value", currentDate);
		}
		$("#searchForm").on("submit", function(event) {
			if (!validateDates() || !validateLength()) {
				event.preventDefault();
			}
		});
	});

	function validateDates() {
		var fromDate = document.getElementById("from-date").value;
		var toDate = document.getElementById("to-date").value;
		if (toDate < fromDate) {
			alert("시작일이 종료일보다 이른 날짜일 수 없습니다. \n시작일 : " + fromDate + " ~ 종료일 : "
					+ toDate);
			return false;
		}

		return true;
	}

	function validateLength() {
		var fromDate = document.getElementById("from-date").value;
		var toDate = document.getElementById("to-date").value;

		if (fromDate.length < 8 || toDate.length < 8) {
			alert("YYYYMMDD 의 형태로 입력해 주세요.(ex 20230816)");
			return false;
		}
		return true;
	}
</script>
</html>