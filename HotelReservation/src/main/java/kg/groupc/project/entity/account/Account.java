package kg.groupc.project.entity.account;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.password.PasswordEncoder;

import kg.groupc.project.constant.Role;
import kg.groupc.project.dto.account.SignupFormDto;
import kg.groupc.project.entity.BaseEntity;
import kg.groupc.project.entity.hotel.Booking;
import kg.groupc.project.entity.hotel.HotelScore;
import kg.groupc.project.entity.inquire.Inquire;
import kg.groupc.project.entity.inquire.InquireReply;
import kg.groupc.project.entity.restaurant.RestaurantScore;
import kg.groupc.project.entity.restaurant.Stars;
import lombok.Getter;
import lombok.Setter;

// 계정
@Entity
@Getter
@Setter
public class Account extends BaseEntity<Long>{

	@Column(length=20, updatable = false, nullable=false, unique = true)
	private String userId;
	
	// 암호화 후 password 값 세팅
	@Column(nullable = false)
	private String password;
	
	// 이름
	@Column(nullable = false, length=50)
	private String name;
	
	// 이메일
	@Column(nullable = false, length=50)
	private String email;
	
	// 핸드폰 번호
	@Column(nullable = false)
	private String phone;
	
	// 주소
	@Column(nullable = false)
	private String address;
	
	// 생년월일
	@Column(nullable = false)
	private String birth;
	
	// 권한(기본적으로 CLIENT)
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	// ID 상태(0 = 탈퇴한 계정[사용불가], 1 = 사용 가능 계정, 2부터는 추가사항 있다면 구현)
	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
	
	@OneToMany(mappedBy = "reserver", targetEntity = Booking.class, fetch = FetchType.LAZY)
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "writer", targetEntity = HotelScore.class, fetch = FetchType.LAZY)
	private List<HotelScore> hotelScores;
	
	@OneToMany(mappedBy = "writer", targetEntity = RestaurantScore.class, fetch = FetchType.LAZY)
	private List<RestaurantScore> restaurantScores;
	
	@OneToMany(mappedBy = "userId", targetEntity = Stars.class, fetch = FetchType.LAZY)
	private List<Stars> stars;
	
	@OneToMany(mappedBy = "writer", targetEntity = Inquire.class, fetch = FetchType.LAZY)
	private List<Inquire> inquires;
	
	@OneToMany(mappedBy = "admin", targetEntity = InquireReply.class, fetch = FetchType.LAZY)
	private List<InquireReply> inquireReplys;
	
	public static Account createAccount(SignupFormDto signupFormDto, PasswordEncoder passwordEncoder) {
		Account account = new Account();
		account.setUserId(signupFormDto.getUserId());
		account.setPassword(passwordEncoder.encode(signupFormDto.getPassword()));
		account.setName(signupFormDto.getUsername());
		account.setEmail(signupFormDto.getEmail());
		account.setPhone(signupFormDto.getPhone());
		account.setAddress((signupFormDto.getAddress() + " " + signupFormDto.getAddressDetail()));
		account.setBirth(signupFormDto.getBirth());
		account.setRole(Role.CLIENT);
		account.setStatus(1L);
		return account;
	}
}