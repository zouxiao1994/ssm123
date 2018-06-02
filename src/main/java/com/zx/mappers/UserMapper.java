package com.zx.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zx.entity.ZxUsers;


@Repository
public interface UserMapper {
	
	public List<ZxUsers> getList();
}
