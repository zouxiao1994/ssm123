package com.zx.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zx.entity.Users;



@Repository
public interface UsersMapper {
	
	public List<Users> getList();
	
	
	public Users getUsersByName(String username);
}
