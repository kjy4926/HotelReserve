package kg.groupc.project.dto.hotel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class HotelDetailFormDto {//호텔 상세보기 페이지에서 사용합니다(hotelDetail.jsp)

	private long hotel;//호텔id
	private String name;//호텔이름
	private String phone;//호텔 전화번호
	private String address;//호텔 주소
	private String description;//호텔 설명
	private String img;//호텔 사진
	private double avg;//호텔 평점, 리뷰테이블의 평균값을 가져옴
	private String scoreString;//평점에 대한 별의 개수를 표현하기 위함
	
	
	
	private List<Long> hotelScore;//리뷰테이블 id
	private List<HotelDetailRoomFormDto> roomList;
	//Room 엔티티에서 꺼내올 정보를 저장할 DTO
	

}
