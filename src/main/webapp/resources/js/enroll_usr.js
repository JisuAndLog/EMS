$(document).ready(function () {
    // 페이지 로드 시 Ajax 요청 보내기
    getSexTypeOptions();
    getJobSkillOptions();
    getJobRankOptions();
    getDevPtOptions();
    getOfficeTypeOptions();

    function validateDates() {
        var entrDate = new Date(
            document.getElementsByName('entrDate')[0].value
        );
        var dateAlert = document.getElementById('dateAlert');
    }

    // 성별
    function getSexTypeOptions() {
        var codeParam = 'D100';

        $.ajax({
            type: 'GET',
            url: '/code/codeVal',
            data: { codeVal: codeParam },
            dataType: 'json',
            success: function (response) {
                var options = "<tr id='sextr'>";
                options += "<td colspan='2'>성별</td>";
                $.each(response, function (index, option) {
                    options += "<td colspan='1'>";
                    options +=
                        "<input type='radio' name='sex' value='" +
                        option.dtlcd +
                        "' id='sex_" +
                        index +
                        "' />";
                    options +=
                        "<label for='sex_" +
                        index +
                        "'>" +
                        option.dtlcdnm +
                        '</label>';
                    options += '</td>';
                });
                options += '</tr>';
                $('#sextr').replaceWith(options);
            },
            error: function (xhr, status, error) {
                console.log(error);
            },
        });
    }

    function getJobSkillOptions() {
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
});

// 직급
function getJobRankOptions() {
    $.ajax({
        type: 'GET',
        url: '/code/codeVal',
        data: { codeVal: 'D200' },
        dataType: 'json',
        success: function (response) {
            var options = '<tr>';
            options += "<td colspan='2'>직급</td>";
            options += "<td colspan='2'>";
            options += "<select name='jobRank'>";
            options += "<option value='0' selected>--선택--</option>"; // 선택 옵션 추가
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
            $('#jobRankRow').replaceWith(options);
        },
        error: function (xhr, status, error) {
            console.log(error);
        },
    });
}

// 개발분야
function getDevPtOptions() {
    $.ajax({
        type: 'GET',
        url: '/code/codeVal',
        data: { codeVal: 'D400' },
        dataType: 'json',
        success: function (response) {
            var options = '<tr>';
            options += "<td colspan='2'>개발분야*</td>";
            options += "<td colspan='2'>";
            options += "<select name='devPt' id='devPt' required>";
            options += "<option value='0' selected disabled>--선택--</option>"; // 선택 옵션 추가
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
            $('#devPtRow').replaceWith(options);
        },
        error: function (xhr, status, error) {
            console.log(error);
        },
    });
}

// 재직상태
function getOfficeTypeOptions() {
    $.ajax({
        type: 'GET',
        url: '/code/codeVal',
        data: { codeVal: 'D500' },
        dataType: 'json',
        success: function (response) {
            var options = '<tr>';
            options += "<td colspan='2'>재직상태*</td>";
            options += "<td colspan='2'>";
            options += "<select name='inoffi_sts' id='inoffi_sts' required>";
            options += "<option value='0' selected disabled>--선택--</option>"; // 선택 옵션 추가
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
            $('#inoffi_stsRow').replaceWith(options);
        },
        error: function (xhr, status, error) {
            console.log(error);
        },
    });
}
