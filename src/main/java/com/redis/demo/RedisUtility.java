package com.redis.demo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtility {

    private static final String REDIS_SERVER_URL = "localhost";
    private static final int REDIS_PORT = 6379;

    public static Jedis getConnection() {
        Jedis jedis = new Jedis(REDIS_SERVER_URL, REDIS_PORT);
        //jedis.auth("");
        return jedis;
    }

    public static String saveString(String key, String value) {
        Jedis jedis = getConnection();
        jedis.set(key, value);
        String savedString = jedis.get(key);
        System.out.println("Saved string : " + savedString);
        jedis.close();
        return savedString;
    }

    public static List<String> saveList(String key, List<String> value) {
        Jedis jedis = getConnection();

        for (String s : value) {
            jedis.lpush(key, s);
        }
        List<String> savedList = jedis.lrange(key, 0, -1);
        System.out.print("Saved list : ");
        for (String e : savedList) {
            System.out.print(e + ", ");
        }
        jedis.close();
        return savedList;
    }

    public static Set<String> saveSet(String key, List<String> value) {
        Jedis jedis = getConnection();

        for (String s : value) {
            jedis.sadd(key, s);
        }
        Set<String> savedSet = jedis.smembers(key);

        System.out.println();
        System.out.print("Saved set : ");
        for (String s : savedSet) {
            System.out.print(s + ",");
        }
        jedis.close();
        return savedSet;
    }

    public static Set<String> saveSortedSet(String key, List<String> value) {
        Jedis jedis = getConnection();
        for (String s : value) {
            jedis.zadd(key, s.length(), s);
        }
        Set<String> savedSet = jedis.zrange(key, 0, 3);

        System.out.println();
        System.out.print("Saved sorted set : ");
        for (String s : savedSet) {
            System.out.print(s + ",");
        }
        jedis.close();
        return savedSet;
    }

    public static void saveHash(String key, Map<String, String> value) {
        Jedis jedis = getConnection();
        jedis.hmset(key, value);
        jedis.close();
    }

    public static void getHash(String key, String... feilds) {
        Jedis jedis = getConnection();
        List<String> values = jedis.hmget(key, feilds);
        System.out.println();
        System.out.print("Saved hashes : ");
        for (String s : values) {
            System.out.print(s + ",");
        }
        jedis.close();
    }


    public static Set<String> getAllKey() {
        Jedis jedis = getConnection();
        Set<String> keySet = jedis.keys("*");
        System.out.println();
        System.out.print("Saved keys : ");
        for (String s : keySet) {
            System.out.print(s + ",");
        }
        jedis.close();
        return keySet;
    }
    

}
