<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록</title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/enroll_usr.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/enroll.css"> 

<style>
	.no_available{
	color:#008000;
	display: none;
	}
	
	.no_unavailable{
	color:#6A82FB; 
	display: none;
	}

	/* 
	button{
    	font-size:30px;
    }
    button:hover{
    	cursor:pointer;
    }
    tr, td{
    	border:1px solid #eeeeee;
    }
    th{
    	width:30%;
    	background-color:#eeeeee;
    }
	span{
		color:red;
		font-size:20px;
	} 
	
	 #year {
	    width: 45%;
	  }
	
	 #month,#day {
	    width: 25%;
	  } */
	
</style>
<script>
	function checkEnglish(input) {
		const specialCharRegex = /[\!\@\#\$\%\^\&\*\(\)\_\+\-\=\[\]\{\}\;\:\"\'\\\|<>\,\.\?\/]/; // 특수문자 정규식
		const notEngCharRegex = /[^a-zA-Z\!\@\#\$\%\^\&\*\(\)\_\+\-\=\[\]\{\}\;\:\"\'\\\|<>\,\.\?\/\s]/; // 영문자, 공백 및 특수문자를 제외한 문자의 정규식

		if (input.value.match(specialCharRegex)) {
			input.value = input.value.replace(specialCharRegex, ''); // 특수문자 제거
			document.getElementById("errorMsgSpecialChar").style.display = "inline";
			document.getElementById("errorMsgNotEng").style.display = "none";
		} else if (input.value.match(notEngCharRegex)) {
			input.value = input.value.replace(notEngCharRegex, ''); // 영문자, 공백 및 특수문자를 제외한 문자 제거
			document.getElementById("errorMsgSpecialChar").style.display = "none";
			document.getElementById("errorMsgNotEng").style.display = "inline";
		} else {
			document.getElementById("errorMsgNotEng").style.display = "none";
			document.getElementById("errorMsgSpecialChar").style.display = "none";
		}
	}


	/* 길이 제한 */
	function checkInputLengthForNo(input) {
		if (input.value.length > 7) {
			alert('7자를 초과할 수 없습니다.');
			input.value = input.value.substring(0, 7);
		}
	}
	
	function checkInputLength(inputElement) {
		if (inputElement.value.length > 50) {
			alert('50글자를 초과할 수 없습니다.');
			input.value = input.value.substring(0, 50);
		}
	}	

	/* 입력 제한 */
	function checkInputLanguage(input) {
		var regex = /^[a-zA-Z가-힣\s]*$/;

		if (!regex.test(input.value)) {
			alert('한국어와 영어만 입력 가능합니다.');
			input.value = input.value.substring(0, input.value.length - 1);
		}
	}

	function checkNumbersOnly(input) {
		const nonNumberRegex = /[^0-9]/g;
		if (input.value.match(nonNumberRegex)) {
			alert("숫자만 입력 가능합니다.");
		}

		input.value = input.value.replace(nonNumberRegex, "");
	}
</script>
</head>
<body>
	<%--<div class="usr_container">
		<%@ include file="../left_side_menu.jsp"%> --%>
	<div class="main">
		<h4>사원 등록</h4>
		<form action="/usr/enroll_usr" method="post">
			<span>*은 필수입력 사항입니다.</span>
			<div class="top">
				<table>
					<tr>
						<th>사번*</th>
						<td><input type="text" id="no" name="no"
							oninput="checkInputLengthForNo(this); checkNumbersOnly(this);" onkeyup= "checkNo(this)" 
							autocomplete=off required /> 사번은 7자리까지 입력 가능합니다.
						<!-- id ajax 중복체크 -->
						<span class="no_available">사용 가능한 사번입니다.</span>
						<span class="no_unavailable">이미 존재하는 사번입니다.</span>								
					</tr>

					<tr>
						<th>성명*</th>
						<td><input type="text" name="nm"
							oninput="checkInputLength(this)"
							onchange="checkInputLanguage(this)" autocomplete=off required />
							성명은 50자까지 입력 가능합니다.
					</tr>
				</table>
			</div>
			<!-- top end -->

			<div class="main-bottom">
				<table>
					<tr>
						<th colspan="2">생년월일</th>
						<td colspan="2"><input type="date" id="birth" name="birth"
							max="9999-12-31" /> <input type="hidden" id="hiddenBirth"
							name="birth" /></td>
					</tr>
					<tr>
						<td colspan="2">입사일*</td>
						<td colspan="2"><input type="date" id="entrDate"
							name="entrDate" max="9999-12-31" required /></td>
					</tr>
					<tr id="sextr">
						<td colspan="2">성별</td>
						<td colspan="1"><input type="radio" name="sex" value="남자"
							checked /> 남자</td>
						<td colspan="1"><input type="radio" name="sex" value="여자" />
							여자</td>
					</tr>
					<tr>
						<th colspan="2">주소</th>
						<td colspan="2"><input type="text" id="address_kakao"
							name="addr" readonly /></td>
					</tr>

					<tr>
						<th colspan="2">휴대폰 번호</th>
						<td colspan="2"><input type="text"
							placeholder="010-1234-5678" name="mblNo"
							oninput="autoHyphen(this)" onblur="checkInputMblNo(this)"
							maxlength="13" /> "-" 없이 숫자만 입력해주십시오.</td>
					</tr>
					<tr>
						<td colspan="2">이메일</td>
						<td colspan="2"><input type="email"
							placeholder="example@email.com" name="emailAddr" id="emailAddr"
							maxlength=100 /></td>
					</tr>
					<tr id="jobSkillRow">
						<!-- <th colspan="2">기술등급*</th>
							<td colspan="2">-->
						<select name="jobSkill" id="jobSkill" required>
						</select>
					</tr>
					<tr id="devPtRow">
						<!--<th colspan="2">개발분야*</th>-->
						<td colspan="2"><select name="devPt" required>
						</select></td>
					</tr>
					<tr id="inoffi_stsRow">
						<th colspan="2">재직상태*</th>
						<td colspan="2"><select name="inoffi_sts" required>
						</select></td>
					</tr>
					<tr id="jobRankRow">
						<!--<th colspan="2">직급</th>-->
						<td colspan="2"><select name="jobRank">
						</select></td>
					</tr>
			
									
					<tr>
						<td colspan="4"><button type="submit">등록</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	<script>
  	const serverValidResult = {};
  	<c:forEach items="${errors}" var="error">
    	serverValidResult['${error.field}'] = '${error.defaultMessage}';
  	</c:forEach>
  		console.log(serverValidResult);
	</script>

	<!-- 중복 체크 -->
	<script>
	function checkNo(){
        var no = $('#no').val();
        if(no){ 
        	$.ajax({
            url:'./noCheck', // 컨트롤러에서 요청 받을 주소
            type:'post', // POST 방식으로 전달
            data:{no:no},
            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받음            	
            	if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) > 사용 가능한 사번 
                    $('.no_available').css("display","inline-block"); 
                    $('.no_unavailable').css("display", "none");
                } else { // cnt가 1일 경우 > 이미 존재하는 사번
                    $('.no_unavailable').css("display","inline-block");
                    $('.no_available').css("display", "none");                   
                    $('#no').val('');
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    } else {
        $('.no_available').css("display", "none"); 
        $('.no_unavailable').css("display", "none");  
    }
};
	</script>

	<!-- 생일 -->
	<script>
		$('#birth').keydown(function(e) {
			e.preventDefault();
		});
		$('#birth').on('paste', function(e) {
			e.preventDefault();
		});		
	
		var yearInput = document.getElementById("year");
		var monthInput = document.getElementById("month");
		var dayInput = document.getElementById("day");
		var hiddenBirth = document.getElementById("hiddenBirth");

		function updateBirth() {
			var year = yearInput.value;
			var month = monthInput.value;
			var day = dayInput.value;
			hiddenBirth.value = year + "-" + month + "-" + day;
		}
		yearInput.addEventListener("input", updateBirth);
		monthInput.addEventListener("input", updateBirth);
		dayInput.addEventListener("input", updateBirth);
	</script>

	<!-- 입사일 -->
	<script>
	$('#entrDate').keydown(function(e) {
		e.preventDefault();
		});
	$('#entrDate').on('paste', function (e) {
		e.preventDefault();
		});	
	</script>

	<!-- 휴대폰 번호 -->
	<script>
	const autoHyphen = (target) => {
		 target.value = target.value
		   .replace(/[^0-9]/g, '')
		   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
		}
	
	function checkInputMblNo(inputElement){
		  var input = inputElement.value;
		  
		  if(input == ""){
		    return true;
		  }
		  
		  var phoneRule = /^010-[0-9]{3,4}-[0-9]{4}$/;
		  
		  if (!phoneRule.test(input)) {
		    alert('잘못된 형식입니다. 010으로 시작하는 번호를 입력해주십시오 (예: 010-1234-5678).');
		    inputElement.value = '';
		    return false;
		  }
		  
		  return true;
		}
	</script>

	<!-- 이메일 -->
	<script>
	function checkInputEmail(input) {
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		return regExp.test(input);
	}
	
	$('#emailAddr').on('blur', function() {
	    var input = $(this).val();

	    if(!checkInputEmail(input)){
	        alert('잘못된 형식입니다. 올바른 형식으로 다시 입력해주십시오 (예: example@email.com).');
	        $(this).val(''); // 입력 필드를 비웁니다.
	        return false;
	    }
	});	
	</script>

	<!-- 주소 -->
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		window.onload = function() {
		document.getElementById("address_kakao").addEventListener("click", function() { 
		// 주소입력칸을 클릭하면 카카오 지도 발생
		new daum.Postcode({
			oncomplete : function(data) { 
			//선택시 입력값 세팅
				document.querySelector("input[name=addr]").value = data.address; // 입력한 주소를 name이 addr인 input 태그에 넣기
				// document.querySelector("input[name=addrdtl]")
					//.focus(); //상세입력 포커싱
					}
				}).open();
			});
		}
	</script>
</body>
</html>