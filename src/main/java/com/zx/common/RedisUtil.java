package com.zx.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zx.entity.ZxUsers;

/**
 * redis 工具类
 * 
 * @author zx
 *
 */

@Component("RedisUtil")
public class RedisUtil {

	@Autowired
	private StringRedisTemplate redisTemplate;

	
	public void put(String key, String value) {
		if (key == null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForValue().set(key, value);

	}

	public void put(String key, Object value) {
		if (key == null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, JSON.toJSON(value));

	}

	public <T> T get(String key, Class<T> className) {
		Object obj = redisTemplate.opsForHash().get(key, key);
		if (obj == null) {
			return null;
		}
		
		return JSON.parseObject(obj.toString(), className);
	}

	public String get(String key) {
		String result = redisTemplate.opsForValue().get(key);
		if (result == null) {
			return null;
		} else {
			return result;
		}
	}
	
	
	/*public void putlist(List<ZxUsers> list,String key){
		//redisTemplate.opsForList().
	}
*/
}
