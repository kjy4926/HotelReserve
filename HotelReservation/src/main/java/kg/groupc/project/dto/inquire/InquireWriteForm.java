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
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class InquireWriteForm {
	private Long seq; //글 번호 (시퀀스)
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
				.seq(seq)
				.title(title)
				.category(category)
				.writer(writer)
				.hotel(hotel)
				.day(day)
				.description(description)
				.status(status)
				.build();
		return inquire;
	}
	
	@Builder
	public InquireWriteForm(Long seq, String category, Hotel hotel, String title, String description, Account writer, Date day, Long status) {
		this.seq = seq;
		this.category = category;
		this.hotel = hotel;
		this.title = title;
		this.description = description;
		this.writer = writer;
		this.day = day;
		this.status = status;
	}


/*	
  	public InquireDto(Inquire entity) {
		this.seq = entity.getSeq();
		this.category = entity.getCategory();
		this.hotel = entity.getHotel();
		this.title = entity.getTitle();
		this.writer = entity.getWriter();
		this.description = entity.getDescription();
		this.regDate = entity.getRegDate();		
	} 
*/

}
