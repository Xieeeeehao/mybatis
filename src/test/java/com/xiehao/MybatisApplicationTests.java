package com.xiehao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiehao.entity.User;
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
        List<User> users = userMapper.selectList(null);
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
