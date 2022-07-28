package kg.groupc.project.service.restaurant;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.dto.restaurant.MenuAddFormDto;
import kg.groupc.project.entity.restaurant.Menu;
import kg.groupc.project.entity.restaurant.Restaurant;
import kg.groupc.project.repository.restaurant.MenuRepository;
import kg.groupc.project.repository.restaurant.RestaurantRepository;
import kg.groupc.project.service.BaseService;

@Service
public class MenuService <T, ID extends Serializable> extends BaseService<Menu, Long> {
			
	@Autowired
	private MenuRepository<Menu, Long> menuRepository;
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	// 메뉴 등록
	public Menu create(MenuAddFormDto menuAddFormDto, MultipartFile file, String path) throws Exception {
		//Restaurant restaurant_id = restaurantRepository.findById(restaurant).get();
		System.out.println("uloki"+menuAddFormDto.getRestaurant());
		Restaurant restaurant = restaurantRepository.findBySeq(menuAddFormDto.getRestaurant());
		//Restaurant restaurant = restaurantRepository.findBySeq(menuAddFormDto.getRestaurant().getSeq());
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant);
		String img = null;
		MultipartFile uploadFile = menuAddFormDto.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File(path + img));
		}
		menu.setImg(img);
		
		return menuRepository.save(menu);
	}
	
	// 메뉴 리스트
	@Transactional(readOnly = true)
	public List<Menu> menuList(Long restaurant) {
		return menuRepository.findByRestaurant(restaurant);
	}
	
	// 메뉴 상세보기
	@Transactional(readOnly = true)
	public Menu findMenu(Long seq) {
		return menuRepository.findById(seq).orElse(null);
	}
	
	// 메뉴 삭제
	public Menu delete(Long seq) {
		Menu menu = menuRepository.findById(seq).orElse(null);
		
		if(menu == null) {
			return null;
		}
		
		menuRepository.delete(menu);
		return menu;
	}
	
	// 메뉴 수정 : 7.27 저녁
	public Menu edit(Long seq, MenuAddFormDto menuAddFormDto) {
		Restaurant restaurant = restaurantRepository.findBySeq(menuAddFormDto.getRestaurant());
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant);
		Menu target = menuRepository.findById(seq).orElse(null);
		if(target==null) {
			return null;
		}
		
		target.patch(menu);
		return menuRepository.save(target);	
	}
		
	/*
	// 메뉴 리스트 - 수정 전
	@Transactional(readOnly = true)
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}
	*/
}
