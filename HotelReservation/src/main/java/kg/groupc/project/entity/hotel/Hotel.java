package kg.groupc.project.entity.hotel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import kg.groupc.project.entity.BaseEntity;
import kg.groupc.project.entity.inquire.Inquire;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 호텔
@Entity
@Getter
@Setter
public class Hotel extends BaseEntity<Long>{
	
	// 호텔명
	@Column(nullable = false, length = 100)
	private String name;
	
	// 호텔 전화번호
	@Column(nullable = false, length = 20)
	private String phone;
	
	// 호텔 주소
	@Column(nullable = false, length = 100)
	private String address;
	
	// 호텔 설명
	@Column(nullable = true, length = 1000)
	private String description;
	
	// 호텔 이미지
	@Column(nullable = true, length = 255)
	private String img;
	
	// 호텔 상태(0 = 폐점), 기본적으로 1
	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
	
	@OneToMany(mappedBy = "hotel", targetEntity = Room.class, fetch = FetchType.LAZY)
	private List<Room> rooms;
	
	@OneToMany(mappedBy = "hotel", targetEntity = HotelScore.class, fetch = FetchType.LAZY)
	private List<HotelScore> hotelScores;
	
	@OneToMany(mappedBy = "hotel", targetEntity = Inquire.class, fetch = FetchType.LAZY)
	private List<Inquire> inquires;
}
