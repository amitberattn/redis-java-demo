package com.redis.demo;

import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;

public class RedisUtility {

    private static final String REDIS_SERVER_URL = "localhost";
    private static final int REDIS_PORT = 6379;

    public static Jedis getConnection() {
        Jedis jedis = new Jedis(REDIS_SERVER_URL, REDIS_PORT);
        //jedis.auth("");
       // System.out.println("Connection to server sucessfully");
        return jedis;
    }

    public static String saveString(String key, String value) {
        Jedis jedis = getConnection();
        jedis.set(key, value);
        String savedString = jedis.get(key);
        System.out.println("Saved string : " + savedString);
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
        return savedSet;
    }

    public static Set<String> getAllKey(){
        Jedis jedis = getConnection();
        Set<String> keySet = jedis.keys("*");
        System.out.println();
        System.out.print("Saved keys : ");
        for (String s : keySet) {
            System.out.print(s + ",");
        }
        return keySet;
    }

    public static List<String> getSortedList(String key){
        Jedis jedis = getConnection();
        List<String> sortedList = jedis.sort(key);

        System.out.println();
        System.out.print("Saved set : ");
        for (String s : sortedList) {
            System.out.print(s + ",");
        }
        return sortedList;
    }

}
