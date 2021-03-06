package com.sydml.advancedredis.queue;

import com.sydml.advancedredis.basebean.RedisMessage;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-09
 * 时间： 17:24
 */
public interface IRedisDelayQueue<T> {

    void sendMessage(String queueKey, T msg);

    RedisMessage<T> getMessage(String queueKey);
}
