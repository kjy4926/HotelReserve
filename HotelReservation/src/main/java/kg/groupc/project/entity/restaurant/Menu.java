package kg.groupc.project.entity.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import kg.groupc.project.dto.restaurant.MenuAddFormDto;
import kg.groupc.project.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class Menu extends BaseEntity<Long>{

	// 식당 id(외래키)
	@ManyToOne(optional = false, targetEntity = Restaurant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant")
	private Restaurant restaurant;
	
	// 메뉴 이름
	@Column(nullable = false, length = 100)
	private String name;
	
	// 메뉴 가격
	@Column(nullable = false)
	private Long price;
	
	// 메뉴 설명 
	@Column(nullable = true, length = 1000)
	private String description;
	
	// 메뉴 사진
	@Column(nullable = true, length = 255)
	private String img;
		
	public static Menu createMenu(MenuAddFormDto menuAddFormDto, Restaurant restaurant) {
		
		Menu menu = new Menu();
		
		menu.setRestaurant(restaurant);
		menu.setName(menuAddFormDto.getName());
		menu.setPrice(menuAddFormDto.getPrice());
		menu.setDescription(menuAddFormDto.getDescription());
		menu.setImg(menuAddFormDto.getImg());
		
		return menu;
	}
}
