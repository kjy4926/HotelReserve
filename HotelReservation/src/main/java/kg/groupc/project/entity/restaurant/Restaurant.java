package kg.groupc.project.entity.restaurant;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;

import kg.groupc.project.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
public class Restaurant extends BaseEntity<Long>{

	// 상호명
	@Column(nullable = false, length = 50)
	private String name;
	
	// 주소
	@Column(nullable = false, length = 100)
	private String address;
	
	// 연락처
	@Column(nullable = false, length = 20)
	private String phone;
	
	// 맛집 이미지
	@Column(nullable = false, length = 255)
	private String img;
		
	// 맛집 소개(내용)
	@Column(nullable = false, length = 1000)
	private String description;
	
	// 맛집 상태
	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
	
	@OneToMany(mappedBy = "seq", targetEntity = Stars.class, fetch = FetchType.LAZY)
	private List<Stars> stars;
	
	@OneToMany(mappedBy = "seq", targetEntity = Menu.class, fetch = FetchType.LAZY)
	private List<Menu> menus;
	
	public void patch(Restaurant restaurant) {
		if (restaurant.name != null) {
			this.name = restaurant.name;
		}
		if (restaurant.address != null) {
			this.address = restaurant.address;
		}
		if (restaurant.phone != null) {
			this.phone = restaurant.phone;
		}
		if (restaurant.img != null) {
			this.img = restaurant.img;
		}
		if (restaurant.description != null) {
			this.description = restaurant.description;
		}
		if (restaurant.status != null) {
			this.status = restaurant.status;
		}
	}
}
