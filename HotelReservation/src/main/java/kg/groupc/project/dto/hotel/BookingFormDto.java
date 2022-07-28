package kg.groupc.project.dto.hotel;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingFormDto {
	private Long seq;
	private Long people;
	private Date checkin;
	private Date checkout;
	private Long price;
}
