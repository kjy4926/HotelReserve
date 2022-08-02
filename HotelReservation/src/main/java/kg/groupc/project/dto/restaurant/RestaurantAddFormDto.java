package kg.groupc.project.dto.restaurant;

import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.entity.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RestaurantAddFormDto {
	
	private Long seq;
	private String name;
	private String address;
	private String phone;
	private String img;
	private String description;
	private Long status;
	
	private MultipartFile uploadFile;
	
	public Restaurant toEntity() {
		Restaurant restaurant = Restaurant.builder()
						.name(name)
						.address(address)
						.phone(phone)
						.img(img)
						.description(description)
						.status(status)
						.build();
		return restaurant;
	}
}