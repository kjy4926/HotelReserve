package kg.groupc.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 방정보
@Entity
@Getter
@Setter
@ToString
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private Long seq;
	
	// 호텔 id(외래키)
	@ManyToOne(optional = false, targetEntity = Hotel.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel")
	private Hotel hotel;
	
	// 방 이름
	@Column(nullable = false, length = 100)
	private String name;
	
	// 가격
	@Column(nullable = false)
	private Long price;
	
	// 수용 인원 수(최대 인원)
	@Column(nullable = false)
	private Long people;
	
	// 방 정보
	@Column(nullable = true, length = 1000)
	private String description;
	
	// 방 이미지
	@Column(nullable = true, length = 255)
	private String img;
}
