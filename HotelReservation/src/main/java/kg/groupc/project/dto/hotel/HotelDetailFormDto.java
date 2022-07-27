package kg.groupc.project.dto.hotel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotelDetailFormDto {//호텔 상세보기 페이지에서 사용합니다(hotelDetail.jsp)

	public long hotel;//호텔id
	public String name;//호텔이름
	public String phone;//호텔 전화번호
	public String address;//호텔 주소
	public String description;//호텔 설명
	public String img;//호텔 사진
	public double avg;//호텔 평점, 리뷰테이블의 평균값을 가져옴
	
	public List<Long> hotelScore;//리뷰테이블 id
	public List<Long> room;//room의 id
	
	public HotelDetailFormDto() {}
	
}
