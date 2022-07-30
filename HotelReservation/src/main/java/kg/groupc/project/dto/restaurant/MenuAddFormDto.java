package kg.groupc.project.dto.restaurant;

import org.springframework.web.multipart.MultipartFile;

import kg.groupc.project.entity.restaurant.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAddFormDto {
	
	//restaurant의 pk값
	private Long restaurant;
	private String name;
	private Long price;
	private String description;
	private String img;
	
	private MultipartFile uploadFile;
	
	/*
	public static MenuAddFormDto createMenuAddFormDto(Menu menu) {
		
		return new MenuAddFormDto (
				menu.getRestaurant().getSeq(),
				menu.getName(),
				menu.getPrice(),
				menu.getDescription(),
				menu.getImg()
		);
	}
	
	/*
	public Menu toEntity() {
		Menu menu = Menu.builder()
				.restaurant(restaurant)
				.name(name)
				.price(price)
				.description(description)
				.img(img)
				.build();
		return menu;
	}
	*/
}
