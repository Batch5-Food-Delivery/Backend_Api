package com.hostmdy.food.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String saveImage(MultipartFile image, Long objectId, String uploadPath);
}
