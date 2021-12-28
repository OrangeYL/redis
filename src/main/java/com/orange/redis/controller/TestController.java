package com.orange.redis.controller;


import com.orange.redis.entity.Student;
import com.orange.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据key获取对应的value
     * @param key
     * @return
     */
    @RequestMapping("/get")
    public Object getValue(Object key){
        Object value = redisUtil.get(key);
        return value;
    }

    /**
     * String 存入
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set")
    public Boolean set(Object key,Object value){
        return redisUtil.set(key,value);
    }

    /**
     * List 存入
     * @return
     */
    @RequestMapping("/setList")
    public Boolean setList(){
        List<Object> students=new ArrayList<>();
        Student stu = new Student();
        stu.setId(1);
        stu.setAge(18);
        stu.setSex("男");
        stu.setName("张三");

        Student stu1 = new Student();
        stu1.setId(2);
        stu1.setName("李四");
        stu1.setAge(18);
        stu1.setSex("女");

        Student stu2 = new Student();
        stu2.setId(3);
        stu2.setAge(18);
        stu2.setName("王五");
        stu2.setSex("男");

        students.add(stu);
        students.add(stu1);
        students.add(stu2);
        return redisUtil.lSet("student",students);
    }

    /**
     * List 获取全部
     * @return
     */
    @RequestMapping("/getList")
    public List<Object> getList(){
        return redisUtil.lGet("student",0,-1);
    }

}
