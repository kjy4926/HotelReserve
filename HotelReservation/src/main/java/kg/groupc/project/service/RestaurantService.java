package kg.groupc.project.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.RestaurantAddFormDto;
import kg.groupc.project.entity.Restaurant;
import kg.groupc.project.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	/* 수정전
	@Transactional(readOnly = true)
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	*/
	
//	// 수정후
//	public Page<RestaurantAddFormDto> getRestaurantList(RestaurantAddFormDto restaurantAddFormDto, Pageable pageable) {
//		
//		Page<Restaurant> entityPage = restaurantRepository.findAll(pageable);
//		List<Restaurant> resList = restaurantRepository.findAll(pageable).getContent();
//		Page<RestaurantAddFormDto> restaurantList = modelMapper.map(entityPage, new TypeToken<Page<RestaurantAddFormDto>>() {}.getType());
//	}
	
	
	@Transactional(readOnly = true)
	public Restaurant findRestaurant(Long seq) {
		return restaurantRepository.findById(seq).orElse(null);
	}
	
	/* 서치키워드 추가부분
	@Transactional(readOnly = true)
	public List<Restaurant> search(String searchKeyword) {
		List<Restaurant> 
	}
	*/
	
	/*
	public Restaurant create(RestaurantAddFormDto restaurantAddFormDto, MultipartFile file) throws Exception {
		Restaurant restaurant = restaurantAddFormDto.toRestaurant();
		
		String projectPath = System.getProperty("usder.dir") + "\\src\\main\\webapp\\resources\\img\\restaurantImg";
		
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File(projectPath, fileName);
		file.transferTo(saveFile);
		
		restaurant.setImgName(fileName);
		restaurant.setImgUrl("/restaurantImg/" + fileName);
		
		return restaurantRepository.save(restaurant);
	}
	*/
	public Restaurant create(RestaurantAddFormDto restaurantAddFormDto, MultipartFile file) throws Exception {
		Restaurant restaurant = restaurantAddFormDto.toRestaurant();
		
		String img = null;
		MultipartFile uploadFile = restaurantAddFormDto.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReservation2\\src\\main\\webapp\\resources\\img\\restaurantImg\\" + img));
		}
		//restaurantAddFormDto.setImg(img);
		restaurant.setImg(img);
		
		return restaurantRepository.save(restaurant);
	}
	
	public Restaurant edit(Long seq, RestaurantAddFormDto restaurantAddFormDto) {
		Restaurant restaurant = restaurantAddFormDto.toRestaurant();
		Restaurant target = restaurantRepository.findById(seq).orElse(null);
		
		if(target==null) {
			return null;
		}
		
		target.patch(restaurant);
		return restaurantRepository.save(target);
	}
	
	public Restaurant delete(Long seq) {
		Restaurant restaurant = restaurantRepository.findById(seq).orElse(null);
		
		if(restaurant == null) {
			return null;
		}
		
		restaurantRepository.delete(restaurant);
		return restaurant;
	}
}