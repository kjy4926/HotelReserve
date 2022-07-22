package kg.groupc.project.entity.restaurant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kg.groupc.project.entity.account.Account;
import lombok.Data;
import lombok.ToString;

// 찜하기
@Entity
@Data
@ToString
@IdClass(StarsId.class) // 복합키 IdClass
public class Stars {
	// 복합키 사용
	// 계정 id(외래키)
	@ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@Id
	private Account userId;
	
	// 식당 id(외래키)
	@ManyToOne(targetEntity = Restaurant.class, fetch =  FetchType.LAZY)
	@JoinColumn(name = "restaurant")
	@Id
	private Restaurant restaurant;
}
