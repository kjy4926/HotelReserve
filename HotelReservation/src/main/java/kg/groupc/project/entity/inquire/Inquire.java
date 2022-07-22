package kg.groupc.project.entity.inquire;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 문의글
@Entity
@Getter
@Setter
@ToString
public class Inquire {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private Long seq;
	
	// 작성자(외래키)
	@ManyToOne(optional = false, targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private Account writer;
	
	// 호텔 id(외래키) -> 카테고리 호텔 문의 일 경우만 값이 입력됨(nullable)
	@ManyToOne(optional = false, targetEntity = Hotel.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel")
	private Hotel hotel;
	
	// 문의글 제목
	@Column(nullable = false)
	private String title;
	
	// 문의글 내용
	@Column(nullable = false, length = 1000)
	private String description;
	
	// 작성일
	@Column(nullable = false)
	private Date day;
	
	// 카테고리
	@Column(nullable = false)
	private String category;

	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
}
