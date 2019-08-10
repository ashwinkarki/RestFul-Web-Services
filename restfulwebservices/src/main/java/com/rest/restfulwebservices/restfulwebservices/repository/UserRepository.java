package com.rest.restfulwebservices.restfulwebservices.repository;


import com.rest.restfulwebservices.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {





}
