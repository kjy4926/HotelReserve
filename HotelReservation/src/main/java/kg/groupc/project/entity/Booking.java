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
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private long seq;
	
	@Column(nullable = false)
	private long room;
	
	@Column(nullable = false)
	private String reserver;
	
	@Column(nullable = false)
	private Date reserveDate;
	
	@Column(nullable = false)
	private Date reserveEndDate;
	
	@Column(nullable = false)
	private long status;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private long people;
}
