package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户控制器")
@RequestMapping("/guazi/com/user")
@RestController
public class UserController {
//	private static final int List = 0;
//	private static final int Integer = 0;
	@Autowired
	private  UserService userService;
	
	/**
	 * 官网JPA
	 * https://docs.spring.io/spring-data/jpa/docs/2.2.9.RELEASE/reference/html/#jpa.query-methods
	 * 
	 * localhost:8080/user/findAll
	 * @return
	 */

	
	/**
	 * Integer pageNumber,Integer pageSize
	 * 分页
	 */
	@ApiOperation("查询所有数据并分页")
	@GetMapping("/findAllLimit")
	@Transactional(readOnly = true) 
	@ApiImplicitParams(value={
			@ApiImplicitParam(value="第几页",name="pageNumber"),
			@ApiImplicitParam(value="每页几条数据",name="pageSize")	
	   })
	public Page<User> findAllLimit(int pageNumber,@RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		pageNumber = pageNumber - 1;
		Pageable pageable =PageRequest.of(pageNumber,pageSize,Sort.Direction.DESC,"id");
		return userService.findAllLimit(pageable);
	}
	
	
	/**
	 * 根据姓名模糊查询并分页
	 */
	@ApiOperation("根据姓名模糊查询并分页")
	@ApiImplicitParams(value={
			@ApiImplicitParam(value="用户名",name="name"),
			@ApiImplicitParam(value="第几页",name="pageNumber"),
			@ApiImplicitParam(value="每页几条数据",name="pageSize")
	})
	@GetMapping("/likeAllByName/{name}")
	public List<User> likeAllByName(@PathVariable("name") String name,int pageNumber,@RequestParam(value="pageSize",defaultValue="5") int pageSize) {
		return userService.findLimit(name,pageNumber,pageSize);		
	}
	
	
	
	/**
	 * 多删除和单个删除
	 * 测试接口直接在swagger输入框填写 20,21,22
	 */
	@ApiOperation("一个或多个删除")
	@ApiImplicitParam(value="字符串ID",name="ids")
	@DeleteMapping("/delete")
	public void Delete(@RequestParam("ids") String  ids) {
		String[] idsByte = ids.split(",");
		List<Integer> idsList = new ArrayList<>();
		for (String idString : idsByte) {
			idsList.add(Integer.parseInt(idString));
		}
			userService.deleteBatch(idsList);
	}
	
	
	/**
	 * 可以测通
	 * 测试接口直接在swagger输入框填写 20,21,22
	 * @param ids
	 */
/*	@ApiOperation("一个或多个删除")
	@ApiImplicitParam(value="集合ID",name="ids[]")
	@DeleteMapping("/deleteTwo")
	public void DeleteTwo(@RequestParam("ids[]") List<Integer> ids) {
			userService.deleteBatch(ids);
	}
	*/
	
	
	/**
	 * 根据用户名和性别查询
	 */
	
	
	
	
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询用户信息")
	@ApiImplicitParam(value = "用户ID" ,name = "id")
	@GetMapping("/findById/{id}")
	public Object findById(@PathVariable Integer id) {
		return userService.findById(id);
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "根据用户名查询用户信息")
	@ApiImplicitParam(value= "用户名称" ,name="name")
	@GetMapping("/findByName/{name}")
	public User findByName(@PathVariable String name) {
		return userService.findByName(name);
	}
	
	@ApiOperation("根据用户名称模糊查询")
	@ApiImplicitParam(value= "用户名称" ,name="name")
	@GetMapping("/likeUserByName/{name}")
	public List<User> likeUserByName(@PathVariable String name) {
		return userService.likeUserByName(name);
	}
	
	
	@ApiOperation(value = "根据ID修改用户名字")
	@ApiImplicitParam(value= "用户ID" ,name="id")
	@PostMapping("/updateNameById/{name}/{id}")
	public int updateNameById(@PathVariable("id") Integer id,@PathVariable("name") String name) {
			return userService.updateById(id,name);
	}
	

	
	
	/**
	 * id存在更新，id不存在新增
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "新增用户和修改用户")
	@PostMapping("/saveAndUpdate")
	//public User updateAndSave(@RequestBody User user) {
	public User updateAndSave(User user) {
		User userNew = userService.findById(user.getId());
		if (userNew == null) {
			// 新增
			return userService.save(user);
		}else {
			// 修改
			userNew.setAge(user.getAge());
			userNew.setName(user.getName());
			userNew.setSex(user.getSex());
			return userService.save(user);
		}
	}
	
	
}
