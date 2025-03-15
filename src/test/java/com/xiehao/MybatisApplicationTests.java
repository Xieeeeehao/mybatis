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
        wrapper.lt(null != qu.getAge2(),User::getAge,qu.getAge2());
        wrapper.gt(null != qu.getAge(),User::getAge,qu.getAge());
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

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

}