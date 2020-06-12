package com.cos.blog.dto;

import java.util.List;

import com.cos.blog.model.Board;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 데이터 트랜스퍼 오브젝트
// 요청올 때 한방에 보내기 위해 Board와 username을 합친 객체를 만들엇다


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailResponseDto {
	private BoardResponseDto boardDto;
	private List<ReplyResponseDto> replyDtos;
}
