package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;

@Service
public class UserService {

	
	@SuppressWarnings("rawtypes")
	@Autowired
	private UserDao  userDao;
	


	/**
	 * findById(Integer id)，方法可以通过
	 * @param id
	 * @return
	 */
	public User findById(Integer id) {
		return  userDao.findOne(id);
	}

	
	
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	public int updateById(Integer id, String name) {
		return userDao.updateNameById(id,name);
	}

	@SuppressWarnings("unchecked")
	public User save(User user) {
		return  (User) userDao.save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> likeUserByName(String name) {
		return userDao.likeUserByName(name);
	}

	@SuppressWarnings("unchecked")
	public Page<User> findAllLimit(Pageable pageable) {
		return userDao.findAll(pageable);
	}

	@SuppressWarnings({ "unchecked" })
	public List<User> findLimit(String name, int pageNumber, int pageSize) {
		pageNumber = pageNumber - 1;
		Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.Direction.DESC,"id");
		return userDao.findAll(name, pageable);
	}

	/**
	 * 测试接口直接在swagger输入框填写 20,21,22。运行后数据库删除全部数据
	 * 1. 这个方法只执行一条删除语句
	 * userDao.deleteAllInBatch();
	 * 2.这个方法是先查询后删除(执行一次查询，执行三条删除语句)
	 * userDao.deleteAll();
	 */
	@SuppressWarnings("unchecked")
	public void deleteBatch(List<Integer> idsList) {
		userDao.deleteBatch(idsList);
		
		
		
	}


	
}
