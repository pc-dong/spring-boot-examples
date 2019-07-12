package cn.dpc.flyway.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
	@Column(name = "id")
	@Id
	private Long id;

	@Column(name = "username")
	private String username;
}
