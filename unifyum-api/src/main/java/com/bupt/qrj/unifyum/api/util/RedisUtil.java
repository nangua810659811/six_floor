/**
 * 
 */
package com.bupt.qrj.unifyum.api.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * @author renjun.qrj 2015年10月31日:下午9:08:50
 *         com.bupt.qrj.unifyum.api.util.RedisUtil unifyum-api 用途:
 *
 */
public class RedisUtil {

	public static JedisPool pool = new JedisPool(new JedisPoolConfig(),
			"104.194.79.148", 6378);

	public static void saveObject(String key, Object data) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, JSONObject.toJSONString(data));
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	public static void saveObject(String key, Object data, int seconds) {
		Jedis jedis = pool.getResource();
		try {
			jedis.setex(key, seconds, JSONObject.toJSONString(data));
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}

	public static JSONObject getObject(String key) {
		Jedis jedis = pool.getResource();
		try {
			String jstr = jedis.get(key);
			return (JSONObject) JSONObject.parse(jstr);
		} catch (Exception e) {

		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return null;
	}

	public static void removeObject(String key) {
		Jedis jedis = pool.getResource();
		try {
			jedis.del(key);
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}
}
