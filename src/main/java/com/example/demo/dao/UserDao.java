package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.pojo.User;

public interface UserDao<S> extends JpaRepository<User, Integer>,CrudRepository<User, Integer>,
JpaSpecificationExecutor<User>{

	User findByName(String name);

	/**
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@Modifying
	@Transactional
	@Query(value="update user u set u.name =?2 where u.id =?1",nativeQuery = true)
//	@Query("update User u set u.name =:name where u.id =:id")
	int updateNameById(@Param("id") Integer id, @Param("name") String name);

	@Query(value="select id,age,name,sex from user where id=:id",nativeQuery = true)
	User findOne(@Param("id") Integer id);

	@Query(value="select id,age,name,sex from user where name like %?1% order by id desc",nativeQuery = true)
	List<User> likeUserByName(@Param("name") String name);

	

//	@Query(value="select id,age,name,sex from user where name like %?1%",nativeQuery = true)
	@Query(value="select id,age,name,sex from user where name like %:name%",nativeQuery = true)
	List<User> findAll(@Param("name") String name, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value="delete from user where id in (?1)",nativeQuery = true)
	void deleteBatch(List<Integer> idsList);

	



}
