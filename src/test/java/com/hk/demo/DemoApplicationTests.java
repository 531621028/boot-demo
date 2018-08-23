package com.hk.demo;

import com.google.common.collect.Lists;

import com.hk.demo.aop.BusinessService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    BusinessService businessService;

    @Test
    public void aopTest() {
        businessService.say(1, 2);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest() {
        String luaSetStr = "for key,value in pairs(KEYS)\n"
                + "do\n"
                + "   redis.call('set',value,ARGV[key])\n"
                + "end\n";
        DefaultRedisScript<String> setScript = new DefaultRedisScript<>();
        setScript.setScriptText(luaSetStr);
        setScript.setResultType(String.class);
        List<String> keys = Lists.newArrayList("lua1", "lua2", "lua3", "lua4", "lua5");
        List<String> values = Lists.newArrayList("lua11", "lua22", "lua33", "lua44", "lua55");
        stringRedisTemplate.execute(setScript, keys, values.toArray());
        String luaGetStr = "local result = {}\n"
                + "for key,value in pairs(KEYS)\n"
                + "do\n"
                + "   table.insert(result,redis.call('get',value))\n"
                + "end\n"
                + "return result\n";
        DefaultRedisScript<List> getScript = new DefaultRedisScript<>();
        getScript.setScriptText(luaGetStr);
        getScript.setResultType(List.class);
        for (Object result : stringRedisTemplate.execute(getScript, keys)) {
            System.out.println(result);
        }
    }

}
