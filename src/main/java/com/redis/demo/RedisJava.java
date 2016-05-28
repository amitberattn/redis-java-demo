package com.redis.demo;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());

        /*Save string in redis*/

        RedisUtility.saveString("redis", "version-2.8.4");

        /*Save list in redis*/

        List<String> versionList = new ArrayList<String>();
        versionList.add("v-1.2.0");
        versionList.add("v-1.3.0");
        versionList.add("v-1.4.0");
        RedisUtility.saveList("version", versionList);



        /*Save set in redis*/


        List<String> nameList = new ArrayList<String>();
        nameList.add("Jon");
        nameList.add("Tim");
        nameList.add("Smith");
        nameList.add("Smith");
        RedisUtility.saveSet("name",nameList);


        /*Save sorted set in redis*/

        RedisUtility.saveSortedSet("name-sort",nameList);

        /*Get all key's stroed in redis*/

        RedisUtility.getAllKey();
    }
}