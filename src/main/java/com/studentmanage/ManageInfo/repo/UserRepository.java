
package com.studentmanage.ManageInfo.repo;

import org.springframework.data.repository.CrudRepository;

import com.studentmanage.ManageInfo.model.UserLogin;

public interface UserRepository extends CrudRepository<UserLogin, Long>{
	//根据用户名和密码
	Iterable<UserLogin>findByNameAndPassword(String name,String password);
}
