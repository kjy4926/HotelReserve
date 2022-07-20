package kg.groupc.project.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor//모든 필드에 대한 생성자 자동생성
public class HotelMainFormDto {//

	public long seq;
	public String name;
	public String phone;
	public String address;
	public String description;
	public String img;
	public long status;
	
	public double avg; //평점
	
	

	
}
