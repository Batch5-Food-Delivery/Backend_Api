package com.hostmdy.food.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.food.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Override
	public String saveImage(MultipartFile image, Long objectId, String uploadPath) {
		// TODO Auto-generated method stub
		String fileName = objectId+".jpg";
        Path filePath = Path.of(uploadPath+fileName);
        
        try {
			Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return fileName;
	}

}
