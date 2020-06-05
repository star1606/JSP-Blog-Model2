<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	var t1= this;
	console.log(t1);   //window로 나옴


	// 오브젝트 window.car 내부 function move() this
	// 메소드 내부의 this는 그 메소드를 감싸고 있는 오브젝트
	var car ={
		name: '소나타',
		move: function(){
			
			console.log('car 오브젝트의 function');
			console.log('달린다');
	
			},
		send: function(){
				console.log(this);
				car.move();
			}

			};
		console.log('-------------');
		console.log(car.name);
		car.move();




		

</script>
	
	
</body>
</html>