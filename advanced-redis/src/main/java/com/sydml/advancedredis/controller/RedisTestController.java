package com.sydml.advancedredis.controller;

import com.sydml.advancedredis.queue.RedisDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-07
 * 时间： 22:58
 */
@RestController
@RequestMapping("/test")
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisDelayQueue redisDelayQueue;

    @Autowired
    private Jedis jedis;

    @GetMapping
    public void test() {
//        jedis.set("test", "value");
        redisDelayQueue.testRedisQueue("queue-demo");
    }


    @GetMapping("/send-message")
    public void sendMessage() {
        redisDelayQueue.sendMessage("message", "test");
    }

    @GetMapping("/batch-send-message")
    public void batchSendMessage() {
        for (int i = 0; i < 10; i++) {
            redisDelayQueue.sendMessage("message", "test" + i);
        }
    }
}
