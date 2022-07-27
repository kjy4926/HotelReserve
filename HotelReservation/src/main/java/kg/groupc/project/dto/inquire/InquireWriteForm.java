package kg.groupc.project.dto.inquire;

import java.sql.Date;

import org.modelmapper.ModelMapper;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.inquire.Inquire;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< Updated upstream

@Getter
@Setter
=======
import lombok.ToString;

@Getter
@Setter
@ToString
>>>>>>> Stashed changes
@NoArgsConstructor
public class InquireWriteForm {
	private String category; // 카테고리
	private Hotel hotel; // 호텔코드
	private String title; // 제목
	private Account writer; // 작성자
	private String description; // 내용
	private Date day; // 등록일
	private Long status; // 처리상태
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static Inquire of (Inquire inquire) {
		return modelMapper.map(inquire, Inquire.class);
	}
	
	public Inquire toEntity() {
		Inquire inquire = Inquire.builder()
<<<<<<< Updated upstream
=======
				.category(category)
				.hotel(getHotel()) // 외래키
>>>>>>> Stashed changes
				.title(title)
				.category(category)
				.writer(writer)
				.hotel(hotel)
				.day(day)
				.description(description)
<<<<<<< Updated upstream
				.status(status)
=======
				.writer(getWriter()) // 외래키
				.day(day) // 자동생성
				.status(status) // 자동생성
>>>>>>> Stashed changes
				.build();
		return inquire;
	}
	
	@Builder
	public InquireWriteForm(Long seq, String category, Hotel hotel, String title, String description, Account writer, Date day, Long status) {
		this.category = category;
		this.hotel = hotel;
		this.title = title;
		this.description = description;
		this.writer = writer;
		this.day = day;
		this.status = status;
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}
}
