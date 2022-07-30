package kg.groupc.project.entity.restaurant;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import kg.groupc.project.entity.BaseEntity;
import kg.groupc.project.entity.account.Account;
import lombok.Getter;
import lombok.Setter;

// 맛집 리뷰
@Entity
@Getter
@Setter
public class RestaurantScore extends BaseEntity<Long> {
	
	// 점포 id(외래키)
	@ManyToOne(optional = false, targetEntity = Restaurant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Restaurant restaurant;
	
	// 작성자
	@ManyToOne(optional = false, targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private Account writer;
	
	// 리뷰 평점
	@Column(nullable = false)
	private Long score;
	
	// 리뷰 내용
	@Column(nullable = true, length = 1000)
	private String description;
	
	// 작성일
	@Column(nullable = false)
	private Date day;
}
