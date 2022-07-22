package kg.groupc.project.dto.restaurant;

import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.entity.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantAddFormDto {

	private Long seq;
	private String name;
	private String address;
	private String phone;
	private String img;
	private String description;
	private Long status;
		
	private MultipartFile uploadFile;
	
	public Restaurant toRestaurant() {
		return new Restaurant(seq, name, address, phone, img, description, status);
	}
}