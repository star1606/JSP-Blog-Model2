package com.cos.blog.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
	int status; // 200 성공
	T data; //int가 될수도 있고 오브젝트가 될 수 도있고

}
