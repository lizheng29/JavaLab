package com.lizheng.play.respository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lizheng.play.model.Person;

public interface PersonRepository extends JpaRepository<Person,Integer>{

	@Query("select p from Person p order by p.age asc")
	List<Person> findAllOrderByAgeAsc();
	
	List<Person> findByAgeLessThanEqual(Integer age);
	
	Page<Person> findAll(Pageable pageable);
	
	@Query(value = "select * from Person where 1=1 order by age desc", nativeQuery = true)
	List<Person> myfindAll();
	
	@Query("select p from Person p order by p.age asc")
	Stream<Person> streamAll();
}
