package kg.groupc.project.dto.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotelDetailRoomFormDto {
	
	private Long seq;//room seq
	private String img;//img경로
	private String name;//방이름
	private Long people;//최대 인원수
	private String desc;//방 설명
	
}
