package com.xiehao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("tbl_user")
public class User {
    @TableId(type = IdType.AUTO)//自增
//    @TableId(type = IdType.INPUT)//自己输入
//    @TableId(type = IdType.ASSIGN_ID)//雪花算法
    private Integer id;
    private String name;
    private Integer age;
    @TableField(value = "gender")
    private String sex;
    private Integer tel;
    @TableLogic(value = "0",delval = "1")//逻辑删除标记
    private Integer deleted;

    @Version//乐观锁
    private Integer version;

    @TableField(exist = false)
    private boolean online;
}
