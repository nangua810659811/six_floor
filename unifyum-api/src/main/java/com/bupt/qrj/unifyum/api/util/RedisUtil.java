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
			"104.194.79.148");

	public static void saveObject(String key, Object data) {
		Jedis jedis = pool.getResource();
		jedis.set(key, JSONObject.toJSONString(data));
		pool.returnResource(jedis);
	}

	public static void saveObject(String key, Object data, int seconds) {
		Jedis jedis = pool.getResource();
		jedis.setex(key, seconds, JSONObject.toJSONString(data));
		pool.returnResource(jedis);
	}

	public static Object getObject(String key) {
		Jedis jedis = pool.getResource();
		String jstr = jedis.get(key);
		pool.returnResource(jedis);
		try {
			return JSONObject.parse(jstr);
		} catch (Exception e) {
			return null;
		}
	}
}
