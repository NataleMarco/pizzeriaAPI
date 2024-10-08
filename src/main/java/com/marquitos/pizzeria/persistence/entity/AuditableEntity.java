package com.marquitos.pizzeria.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditableEntity {
	@Column(name = "created_date")
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
