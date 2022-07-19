package kg.groupc.project.dto;

import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantAddFormDto {

	/*
	private long seq;
	private String name;
	private String address;
	private String phone;
	private String imgName;
	private String imgUrl;
	private String description;
	private Integer status;
	
	//검색어
	private String searchKeyword;
	
	private String uploadFile;
		
	//추가
	public Restaurant toRestaurant() {
		return new Restaurant(seq, name, address, phone, imgName, imgUrl, description, status);
	}
	*/
	private Long seq;
	private String name;
	private String address;
	private String phone;
	private String img;
	private String description;
	private Long status;
	
	private String searchKeyword;
	
	private MultipartFile uploadFile;
	
	public Restaurant toRestaurant() {
		return new Restaurant(seq, name, address, phone, img, description, status);
	}
}