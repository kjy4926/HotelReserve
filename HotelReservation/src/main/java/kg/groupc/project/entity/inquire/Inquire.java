package kg.groupc.project.entity.inquire;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import kg.groupc.project.entity.BaseEntity;
import kg.groupc.project.entity.account.Account;
import kg.groupc.project.entity.hotel.Hotel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 문의글
@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
public class Inquire extends BaseEntity<Long>{
	
	// 작성자(외래키)
	@ManyToOne(optional = false, targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private Account writer;
	
	// 호텔 id(외래키) -> 카테고리 호텔 문의 일 경우만 값이 입력됨(nullable)
	@ManyToOne(optional = true, targetEntity = Hotel.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hotel hotel;
	
	// 문의글 제목
	@Column(nullable = false)
	private String title;
	
	// 문의글 내용
	@Column(nullable = false, length = 1000)
	private String description;
	
	// 답변자(관리자 계정)
	@ManyToOne(optional = true, targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "reply")
	private Account reply;
	
	//답변내용
	@Column(nullable = true, length = 1000)
	private String comments;
	
	// 작성일
	@CreationTimestamp
	@Column(nullable = false)
	private Date day;
	
	// 카테고리
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
	
	@Builder
	public Inquire(Account writer, String category, Hotel hotel, String title, String description, String comments, Date day, Long status) {
		this.writer = writer;
		this.category = category;
		this.hotel = hotel;
		this.title = title;
		this.description = description;
		this.comments = comments;
		this.day = day;
		this.status = status;
	}


	public void patch(Inquire inquire) {
		if (inquire.writer != null) {
			this.writer = inquire.writer;
		}
		if (inquire.category != null) {
			this.category = inquire.category;
		}
		if (inquire.title != null) {
			this.title = inquire.title;
		}
		if (inquire.description != null) {
			this.description = inquire.description;
		}
		if (inquire.comments != null) {
			this.comments = inquire.comments;
		}
		if (inquire.day != null) {
			this.day = inquire.day;
		}
		if (inquire.status != null) {
			this.status = inquire.status;
		}
	}
}
