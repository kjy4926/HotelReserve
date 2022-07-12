package kg.groupc.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@IdClass(StarsId.class) // 복합키 IdClass
public class Stars {
	@Id
	private String userId;
	@Id
	private long restaurant;
}
