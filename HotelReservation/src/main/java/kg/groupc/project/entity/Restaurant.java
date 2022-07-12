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
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String address;
	
	@Column(nullable = false, length = 20)
	private String phone;
	
	@Column(nullable = true, length = 255)
	private String img;
}
