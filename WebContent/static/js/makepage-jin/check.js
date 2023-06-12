/* 체크박스 활성화 */

const $checkboxs = $(".checkbox-wrap input[type='checkbox']");
const $checkAll = $("input[name='checkAll']");
const $checks = $("input[name='check']");
const $optionsAll = $(".options-title input[name='check']");
const $options = $(".options-list input[name='check']");

// 전체 선택 체크박스 변경 시 실행
$checkAll.on("change", function(){
    let isChecked = $(this).prop("checked");
    isChecked ? checkedAll() : notCheckedAll();
    $checks.prop("checked", isChecked);
    $checks.trigger("change");
});

// 개별 체크박스 변경 시 실행
$checks.on("change", function(){
    let isChecked = $(this).prop("checked");
    let $img = $(this).next().find("img");
    isChecked ? checked($img) : notChecked($img);
});

// 옵션 전체 선택 체크박스 변경 시 실행
$optionsAll.on("change", function(){
    $options.prop("checked", $(".options-title input[type='checkbox']").prop("checked"));
    $(".options-list .checkbox-wrap img").attr("src", "/static/image/" + ($(this).prop("checked") ? "checked.png" : "check.png"));
});

// 개별 옵션 체크박스 변경 시 실행
$options.on("change", function(){
    $(".options-title input[type='checkbox']").prop("checked", $options.filter(":checked").length);
    $(".options-title .checkbox-wrap img").attr("src", "/static/image/" + ($(".options-title input[type='checkbox']").prop("checked") ? "checked.png" : "check.png"));
});

// 모든 체크박스 변경 시 실행
$checkboxs.on("change", function(){
    if($checkboxs.length == $checkboxs.filter(":checked").length){
        $checkAll.prop("checked", true);
        checkedAll();
        return;
    }
    $checkAll.prop("checked", false);
        notCheckedAll();
});

// 전체 선택 체크박스가 선택된 상태 스타일 적용
function checkedAll(){
    $("#check-all-wrap span.checkbox").css("border-color", "rgb(235 124 120)");
    $("#check-all-wrap span.checkbox").css("background-color", "rgb(235 124 120)");
}

// 전체 선택 체크박스가 선택되지 않은 상태 스타일 적용
function notCheckedAll(){
    $("#check-all-wrap span.checkbox").css("border-color", "");
    $("#check-all-wrap span.checkbox").css("background-color", "");
}

// 개별 체크박스가 선택된 상태 스타일 적용
function checked($img){
    $img.attr("src", "/static/image/checked.png");
    checkAvaliable();
}

// 개별 체크박스가 선택되지 않은 상태 스타일 적용
function notChecked($img){
    $img.attr("src", "/static/image/check.png");
    checkAvaliable();
}
//체크박스 상태 확인
function checkAvaliable(){
    if($("#enable").prop("checked")){
        $("li.options").removeClass("disable");
        return;
    }
    $("li.options").addClass("disable");
    $(".options input[type='checkbox']").prop("checked", false);
    $(".options input[type='checkbox']").next().find("img").attr("src", "/static/image/check.png");
}

