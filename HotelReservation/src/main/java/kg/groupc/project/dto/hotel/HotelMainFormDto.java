package kg.groupc.project.dto.hotel;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class HotelMainFormDto {//

	public long seq;
	public String name;
	public String phone;
	public String address;
	public String description;
	public String img;
	public long status;
	
	public double avg; //평점
	
	public HotelMainFormDto() {}

	
}
