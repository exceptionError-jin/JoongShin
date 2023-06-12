/* 주니어 로그인 유효성 검사 */

const $id = $("input#id");
const $password = $("input#password");

// 로그인 버튼 클릭 시 실행되는 함수
function send(){
 	// 이메일 입력 여부 확인
    if(!$id.val()){
        showWarnModal("이메일를 입력해주세요!");
        $id.next().fadeIn(500);
        return;
    }
    // 비밀번호 입력 여부 확인
    if(!$password.val()){
        showWarnModal("비밀번호를 입력해주세요!");
        $password.next().fadeIn(500);
        return;
    }
    
    // 로그인 폼 제출
    document.login.submit();
}

// 이메일 입력란에서 포커스가 벗어났을 때 실행
$id.on("blur", function(){
    $id.next().hide();
    if($id.val()){
        $id.next().fadeIn(500);
        showHelp($id, "pass.png");
    }
});

// 비밀번호 입력란에서 포커스가 벗어났을 때 실행
$password.on("blur", function(){
    $password.next().hide();
    if($password.val()){
        $password.next().fadeIn(500);
        showHelp($password, "pass.png");
    }
});

// 도움말 표시 함수
function showHelp($input, fileName){
    $input.next().attr("src", "/static/image/" + fileName);

    if(fileName == "pass.png") {
        $input.css("border", "1px solid rgba(0, 0, 0, 0.1)");
        $input.css("background", "rgb(255, 255, 255)");
        $input.next().attr("width", "18px");
    }else {
        $input.css("border", "1px solid rgb(255, 64, 62)");
        $input.css("background", "rgb(255, 246, 246)");
    }
}