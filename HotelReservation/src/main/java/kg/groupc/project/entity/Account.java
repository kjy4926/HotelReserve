package kg.groupc.project.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import kg.groupc.project.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
	@Id
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String birth;
	@Enumerated(EnumType.STRING)
	private Role role;
	private long status;
}