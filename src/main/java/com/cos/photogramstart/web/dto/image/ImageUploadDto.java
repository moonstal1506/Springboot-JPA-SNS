package com.cos.photogramstart.web.dto.image;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDto {
	
	//멀티파트타입은 @NotBlank 지원안함
	
	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl,User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}
}
