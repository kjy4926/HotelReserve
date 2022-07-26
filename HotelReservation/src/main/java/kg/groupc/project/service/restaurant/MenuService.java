package kg.groupc.project.service.restaurant;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
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
import lombok.RequiredArgsConstructor;

@Service
public class MenuService <T, ID extends Serializable> extends BaseService<Menu, Long> {
			
	@Autowired
	private MenuRepository<Menu, Long> menuRepository;
	
	@Autowired
	private RestaurantRepository<Restaurant, Long> restaurantRepository;
	
	public Menu create(MenuAddFormDto menuAddFormDto, MultipartFile file) throws Exception {
		
		//Restaurant restaurant_id = restaurantRepository.findById(restaurant).get();
		Restaurant restaurant = restaurantRepository.findBySeq(menuAddFormDto.getRestaurant().getSeq());
		System.out.println("입력되었음");
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant);
		
		String img = null;
		MultipartFile uploadFile = menuAddFormDto.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReserve\\HotelReservation\\src\\main\\webapp\\resources\\img\\menuImg\\" + img));
		}
		menu.setImg(img);
		
		return menuRepository.save(menu);
	}
	
	// 메뉴 리스트
	@Transactional(readOnly = true)
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}
	
	/*
	@Transactional
	public Menu create(MenuAddFormDto menuAddFormDto, MultipartFile file) throws Exception {
		
		//Restaurant restaurant_id = restaurantRepository.findById(restaurant).get();
		Restaurant restaurant_id = restaurantRepository.findBySeq(menuAddFormDto.getRestaurant());
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant_id);
		
		String img = null;
		MultipartFile uploadFile = menuAddFormDto.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReserve\\HotelReservation\\src\\main\\webapp\\resources\\img\\menuImg\\" + img));
		}
		menu.setImg(img);
		
		return menuRepository.save(menu);
	}
	
	/*
	@Transactional
	public MenuAddFormDto create(Long seq, MenuAddFormDto menuAddFormDto, MultipartFile file) throws Exception {
		
		//Restaurant restaurant_id = restaurantRepository.findById(restaurant).get();
		Restaurant restaurant = restaurantRepository.findBySeq(seq);
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant);
		
		String img = null;
		MultipartFile uploadFile = null;
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReserve\\HotelReservation\\src\\main\\webapp\\resources\\img\\menuImg\\" + img));
		}
		menu.setImg(img);
		Menu created = menuRepository.save(menu);
		
		return MenuAddFormDto.createMenuAddFormDto(created);
	}
	*/
	
	/*
	// 메뉴 등록(관리자)
	@Transactional
	public MenuAddFormDto create(Long restaurant, MenuAddFormDto menuAddFormDto, MultipartFile file) throws Exception {
		
		//Restaurant restaurant_id = restaurantRepository.findById(restaurant).get();
		Restaurant restaurant_id = restaurantRepository.findBySeq(
		
		Menu menu = Menu.createMenu(menuAddFormDto, restaurant_id);
		String img = null;
		MultipartFile uploadFile = null;
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			img = uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\javastudy\\hotel\\HotelReserve\\HotelReservation\\src\\main\\webapp\\resources\\img\\menuImg\\" + img));
		}
		menu.setImg(img);
		Menu created = menuRepository.save(menu);
		
		return MenuAddFormDto.createMenuAddFormDto(created);
	}
	*/
}
