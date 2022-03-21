package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
  //@NotEmpty  빈값이거나 null
	@NotBlank //빈값이거나 null 빈공백 체크
	private String content;
	@NotNull// null체크
	private Integer imageId;
	
	//toEntity가 필요없다.
}
