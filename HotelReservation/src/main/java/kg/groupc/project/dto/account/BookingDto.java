package kg.groupc.project.dto.account;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingDto {
	private Long seq;
	private String hotel;
	private String room;
	private String reserver;
	private Date reserveDate;
	private Date reserveEndDate;
	private Long status;
	private Long price;
	private Long people;
}
