package kg.groupc.project.dto.inquire;

import java.sql.Date;

import org.modelmapper.ModelMapper;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import kg.groupc.project.entity.inquire.Inquire;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class InquireWriteForm {
	private Account writer; // 작성자
	private Hotel hotel; // 호텔코드
	private String category; // 카테고리
	private String title; // 제목
	private String description; // 내용
	private Date day; // 등록일
	private Long status; // 처리상태
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static Inquire of (Inquire inquire) {
		return modelMapper.map(inquire, Inquire.class);
	}
	
	public Inquire toEntity() {
		Inquire inquire = Inquire.builder()
				.category(category)
				.hotel(hotel) // 외래키
				.title(title)
				.description(description)
				.writer(writer) // 외래키
				.day(day) // 자동생성
				.status(status) // 자동생성
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
}
