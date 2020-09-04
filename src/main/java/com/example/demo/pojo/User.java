package com.example.demo.pojo;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3740502302343040450L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	
	private Integer age;
	private String name;
	private String sex;
	
}
