

//juso.go.kr 라이브러리 함수(끝)-----------------------

// 중복체크 함수
function validate() {
	if (!isCheckedUsername) {
		alert('username 중복체크를 해주세요');
	}
	return isCheckedUsername;
	alert()
}

// 데이터베이스에 ajax 요청을 해서 중복 유저네임이 있는지 확인
// 있으면 1을 리턴, 없으면 0을 리턴, 오류가 나면 -1을 리턴
function usernameCheck() {
	// 성공(ajax)
	var tfUsername = $('#username').val();
	// alert(tfUsername);
	console.log(tfUsername);
	$.ajax({
		type : 'get',
		url : `/blog/user?cmd=usernameCheck&username=${tfUsername}`
	// 람다식에서의 JS
	}).done(function(result) { // 람다식을 쓰면 타입을 몰라도 쓸수 있다.
		console.log(result); // this 문제때문에 람다식씀 람다식쓰면
		if (result == 1) {
			alert('아이디가 중복되었습니다.');
		} else if (result == 0) { // === 3개는 타입까지 비교 == 값만비교
			alert('사용하실 수 있는 아이디입니다.');
			isCheckedUsername = true;

		} else {
			console.log('develop : 서버오류');

		}

	}).fail(function(error) {
		console.log(error); // 내가 생각하는 this를 쓸 수 있다.
		// 원하는 곳의 this로갈수있다
	}); // JAVA에서의 람다식
	// 타입을 몰라도 된다.
	// 성공하면 done으로 가고 실패하면 fail로 감
	// play(()->){ });
	isCheckedUsername = true; // 나두면 바로 때려짐
}