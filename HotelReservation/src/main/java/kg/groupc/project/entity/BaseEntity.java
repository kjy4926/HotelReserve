package kg.groupc.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private Long seq;

	@Column
	private String writer;
	
	@PrePersist
	protected void prePersist() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(object instanceof User) {
			User user = (User) object;
			this.writer = user.getUsername();
		}
	}
}
