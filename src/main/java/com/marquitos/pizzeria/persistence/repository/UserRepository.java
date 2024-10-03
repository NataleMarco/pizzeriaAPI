package com.marquitos.pizzeria.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.marquitos.pizzeria.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String>{}
