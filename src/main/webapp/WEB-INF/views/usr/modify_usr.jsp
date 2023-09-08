<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 수정 화면</title>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!--  <link rel="stylesheet" type="text/css" href="/resources/css/enroll.css"> -->

<script>
getSexTypeOptions();
getJobSkillOptions();
getJobRankOptions();
getDevPtOptions();
getOfficeTypeOptions();

function getSexTypeOptions() {
	var selectedValue = '${usr.getSex()}';
    $.ajax({
        type: "GET",
        url: "/code/codeVal",
        data: { codeVal: "D100"},
        dataType: "json",
        success: function(response) {
        var options = "<tr id='sextr'>";
        options += "<td colspan='2'>성별</td>";
        $.each(response, function(index, option) {
            options += "<td colspan='1'>";
            options += "<input type='radio' name='sex' value='" + option.dtlcd + "' id='sex_" + index + "'";

            if (option.dtlcdnm === selectedValue) {
                options += " checked";
            }

            options += " />";
            options += "<label for='sex_" + index + "'>" + option.dtlcdnm + "</label>";
            options += "</td>";
        });
        options += "</tr>";
        $("#sextr").replaceWith(options);
        },
	            error: function(xhr, status, error) {
	            console.log(error);
        }
      });
    }
    
function getJobSkillOptions() {
	var selectedValue = '${usr.getJobSkill()}';
	  $.ajax({
	    type: "GET",
	    url: "/code/codeVal",
	    data: { codeVal: "D300" },
	    dataType: "json",
	    success: function(response) {
	      var options = "<tr>";
	      options += "<th colspan='2'>기술등급*</th>";
	      options += "<td colspan='2'>";
	      options += "<select name='jobSkill'>";
	      $.each(response, function(index, option) {
              options += "<option value='" + option.dtlcd + "'";
              if (option.dtlcdnm === selectedValue) {
                  options += " selected";
              }
              options += ">" + option.dtlcdnm + "</option>";
          });
	      options += "</select>";
	      options += "</td>";
	      options += "</tr>";
	      $("#jobSkillRow").replaceWith(options);
	    },
	    error: function(xhr, status, error) {
	      console.log(error);
	    }
	  });
}

function getJobRankOptions() {
	var selectedValue = '${usr.getJobRank()}';
	  $.ajax({
	    type: "GET",
	    url: "/code/codeVal",
	    data: { codeVal: "D200" },
	    dataType: "json",
	    success: function(response) {
	      var options = "<tr>";
	      options += "<th colspan='2'>직급*</th>";
	      options += "<td colspan='2'>";
	      options += "<select name='jobRank'>";
	      $.each(response, function(index, option) {
            options += "<option value='" + option.dtlcd + "'";
            if (option.dtlcdnm === selectedValue) {
                options += " selected";
            }
            options += ">" + option.dtlcdnm + "</option>";
        });
	      options += "</select>";
	      options += "</td>";
	      options += "</tr>";
	      $("#jobRankRow").replaceWith(options);
	    },
	    error: function(xhr, status, error) {
	      console.log(error);
	    }
	  });
}

function getDevPtOptions(){
	$.ajax({
        type: 'GET',
        url: '/code/codeVal',
        data: { codeVal: 'D300' },
        dataType: 'json',
        success: function (response) {
            console.log('success');
            var options = '<tr>';
            options += "<th colspan='2'>기술등급*</th>";
            options += "<td colspan='2'>";

            options += "<select name='jobSkill' id='jobSkill' required>";
            options +=
                "<option value='0' selected disabled>--선택--</option>"; // 선택 옵션 추가
            $.each(response, function (index, option) {
                options +=
                    "<option value='" +
                    option.dtlcd +
                    "'>" +
                    option.dtlcdnm +
                    '</option>';
            });

            options += '</select>';
            options += '</td>';
            options += '</tr>';

            $('#jobSkill').replaceWith(options);
        },
        error: function (xhr, status, error) {
            console.log(error);
        },
    });
}


</head>
<body>

</body>
</html>