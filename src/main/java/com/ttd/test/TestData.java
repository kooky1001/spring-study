package com.ttd.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestData {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
}
