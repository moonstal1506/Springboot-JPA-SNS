package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly =true)//영속성 컨텍스트 변경감지를 해서, 더티체킹, flush(반영)안함
	public Page<Image> 이미지스토리(int principalId,Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId,pageable);
		return images;
	}
	
	@Value("${file.path}")
	private String uploadFolder;

	@Transactional//일의 최소단위
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails ) {
		UUID uuid= UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();//1.jpg
		System.out.println("이미지파일이름: "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		//통신,I/O 예외발생가능
		try {
			Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
		imageRepository.save(image);
		//System.out.println(imageEntity);
	}
}
