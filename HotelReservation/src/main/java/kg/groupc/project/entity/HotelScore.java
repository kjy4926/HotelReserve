package kg.groupc.project.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class HotelScore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private long seq;
	
	@Column(nullable = false)
	private long hotel;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private long score;
	
	@Column(nullable = true, length = 500)
	private String reply;
	
	@Column(nullable = false)
	private Date day;
}
