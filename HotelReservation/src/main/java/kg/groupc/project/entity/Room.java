package kg.groupc.project.entity;

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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private long seq;
	
	@Column(nullable = false)
	private long hotel;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private long people;
	
	@Column(nullable = true, length = 500)
	private String description;
	
	@Column(nullable = true, length = 255)
	private String img;
}
