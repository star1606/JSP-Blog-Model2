

	$("#img__preview").on("change", function(e){
			var f = e.target.files[0];
			if(!f.type.includes("image")||f.size > 1024*1024*2 ){
			
				alert("이미지만 첨부할 수 있습니다.");
				return;
			}
			// f.size ===1024*1024*2 넘어가면 return때리기 val초기화 시키기 이미지만 첨부할수있습니다 한줄만하고
			// 2메가 못들어오게하기
			// 나는 if에서 1024*1024*2 byte 보다 더 크다를 어떻게 표현? 변수로하나?
			
			
			
			var reader = new FileReader();
			
			reader.onload = function(e){
				console.log(e.target.result);
				$("#img__wrap").attr("src", e.target.result);
			};

			reader.readAsDataURL(f);  // 비동기 실행	
	
	});
	
	
