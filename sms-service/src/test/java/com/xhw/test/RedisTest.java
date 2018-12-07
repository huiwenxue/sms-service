package com.xhw.test;

import com.xhw.sms.Application;
import com.xhw.sms.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01(){
        User user = new User();
        user.setUsername("aa");
        user.setAge(1);
        redisTemplate.opsForHash().put("user",user.getUsername(),user.getAge());
        //redisTemplate.opsForHash().get("user", user.getUsername());
        Long user1 = redisTemplate.opsForHash().increment("user", user.getUsername(), 1);
        System.out.println(user1);
        //redisTemplate.opsForHash().get("user", user.getUsername());
        //System.out.println("userAge1:"+userAge1+"userAge2"+userAge2);
    }

    @Test
    public void test02(){
        for (int i = 1; i <= 10; i++) {
           redisTemplate.boundListOps("seckillGoodsId").leftPush(i);
        }

        Long size = redisTemplate.boundListOps("seckillGoodsId").size();

        System.out.println(size);

        for (int i = 1; i <= 11; i++) {
            Integer num = (Integer) redisTemplate.boundListOps("seckillGoodsId").rightPop();
            System.out.println(num);
        }

    }


}
