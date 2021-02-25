package com.celeste.redis;

import com.celeste.redis.util.PropertiesBuilder;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@Getter
public class RedisProcessor {

    public final JedisConnectionProvider provider;

    public RedisProcessor() {
        this.provider = new JedisConnectionProvider();
    }

    public void connect(String hostname) {
        provider.connect(new PropertiesBuilder()
            .with("hostname", hostname)
            .wrap()
        );
    }

    public void setupRedisChannel(final Object object, final String channel) {
        new Thread(() -> {

            final Jedis jedis = provider.getConnectionInstance();
            jedis.subscribe((JedisPubSub) object, channel);

        }).start();
    }

    public void disconnect() {
        provider.disconnect();
    }

}
