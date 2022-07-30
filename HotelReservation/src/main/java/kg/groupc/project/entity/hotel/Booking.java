package kg.groupc.project.entity.hotel;

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

// 예약
@Entity
@Getter
@Setter
public class Booking extends BaseEntity<Long>{
	
	// 방 id(외래키)
	@ManyToOne(optional = false, targetEntity = Room.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "room")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;
	
	// 예약자(외래키)
	@ManyToOne(optional = false, targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "reserver")
	private Account reserver;
	
	// 예약일
	@Column(nullable = false)
	private Date reserveDate;
	
	// 예약 종료일
	@Column(nullable = false)
	private Date reserveEndDate;
	
	// 예약 상태
	@Column(nullable = false, columnDefinition = "number(1) default 1")
	private Long status;
	
	// 가격
	@Column(nullable = false)
	private Long price;
	
	// 인원 수
	@Column(nullable = false)
	private Long people;
}
