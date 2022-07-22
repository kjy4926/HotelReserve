package kg.groupc.project.service.restaurant;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.restaurant.RestaurantAddFormDto;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.BaseService;

@Service
public class RestaurantService<T, ID extends Serializable> extends BaseService<Restaurant, Long> {
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
		
	// 맛집 전체 리스트 + 페이징
	@Transactional(readOnly = true)
	public Page<Restaurant> findAll(Pageable pageable) {
		return restaurantRepository.findAll(pageable);
	}
	
	// 맛집 상세보기
	@Transactional(readOnly = true)
	public Restaurant findRestaurant(Long seq) {
		return restaurantRepository.findById(seq).orElse(null);
	}
		
	// 서치(상호명) + 페이징
	@Transactional(readOnly = true)
	public Page<Restaurant> search1(String searchKeyword, Pageable pageable) {
		return restaurantRepository.findByNameContaining(searchKeyword, pageable);
	}
	// 서치(주소) + 페이징
	@Transactional(readOnly = true)
	public Page<Restaurant> search2(String searchKeyword, Pageable pageable) {
		return restaurantRepository.findByAddressContaining(searchKeyword, pageable);
	}
	
	// 맛집 등록(관리자)
//	public Restaurant create(RestaurantAddFormDto restaurantAddFormDto, MultipartFile file) throws Exception {
//		Restaurant restaurant = restaurantAddFormDto.toRestaurant();
//		
//		String img = null;
//		MultipartFile uploadFile = restaurantAddFormDto.getUploadFile();
//		if(!uploadFile.isEmpty()) {
//			String originalFileName = uploadFile.getOriginalFilename();
//			String ext = FilenameUtils.getExtension(originalFileName);
//			
//			UUID uuid = UUID.randomUUID();
//			img = uuid+"."+ext;
//			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReserve\\HotelReservation\\src\\main\\webapp\\resources\\img\\restaurantImg\\" + img));
//		}
//		restaurant.setImg(img);
//		
//		return restaurantRepository.save(restaurant);
//	}
	
//	//맛집 수정(관리자)
//	public Restaurant edit(Long seq, RestaurantAddFormDto restaurantAddFormDto) {
//		Restaurant restaurant = restaurantAddFormDto.toRestaurant();
//		Restaurant target = restaurantRepository.findById(seq).orElse(null);
//		
//		if(target==null) {
//			return null;
//		}
//		
//		target.patch(restaurant);
//		return restaurantRepository.save(target);
//	}
	
	//맛집 삭제(관리자)
	public Restaurant delete(Long seq) {
		Restaurant restaurant = restaurantRepository.findById(seq).orElse(null);
		
		if(restaurant == null) {
			return null;
		}
		
		restaurantRepository.delete(restaurant);
		return restaurant;
	}
}