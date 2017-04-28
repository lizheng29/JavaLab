package com.lizheng.play.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lizheng.play.model.Person;

public interface PersonRepository extends JpaRepository<Person,Integer>{

}
