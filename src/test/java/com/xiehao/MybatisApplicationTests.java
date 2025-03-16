package com.xiehao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiehao.entity.User;
import com.xiehao.entity.query.queryUser;
import com.xiehao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetAll() {
//        方式一
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lt("id",2);
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);
//                方式2 lamda
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lambda().lt(User::getId, 2);
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);
        //3
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(User::getId, 2);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void testAll2() {
        queryUser qu = new queryUser();
        qu.setAge2(20);
        qu.setAge(16);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(null != qu.getAge2(),User::getAge,qu.getAge2())
               .gt(null != qu.getAge(),User::getAge,qu.getAge());

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

    //查询投影
    //选择查询字段
    @Test
    public void testAll3() {
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.select(User::getId,User::getName,User::getAge);
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as count,gender");
        wrapper.groupBy("gender");
        List<Map<String, Object>> list = userMapper.selectMaps(wrapper);
        System.out.println(list);
    }

    //    分页功能：
    //    1.先配置拦截器
    //    2.直接用Ipage
    //    3.实现分页
    @Test
    public void pageTest() {
        IPage page = new Page(1, 1);
        userMapper.selectPage(page, null);
        System.out.println(page.getRecords());
    }

    @Test
    public void insert(){
        User user = new User();
        user.setAge(20);
        user.setTel(1238);
        user.setSex("男");
        user.setName("kuhaha");
        userMapper.insert(user);
    }

    @Test
    public void selectAll(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("deleted",1);
        List<Map<String, Object>> list = userMapper.selectMaps(wrapper);
        System.out.println(list);
    }

    @Test
    public void deleteByid(){
        userMapper.deleteById("1");
    }

    @Test
    public void updateById(){

        User user = userMapper.selectById(3);
        User user2 = userMapper.selectById(3);
        user2.setName("888");
        userMapper.updateById(user2);
        user.setName("999");
        userMapper.updateById(user);
    }

}