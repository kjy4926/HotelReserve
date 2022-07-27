package kg.groupc.project.dto.hotel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor//모든 필드에 대한 생성자 자동생성
public class HotelAvgDto {
	
	
	public long hotel;
	public double avg;
	
	
	
}
