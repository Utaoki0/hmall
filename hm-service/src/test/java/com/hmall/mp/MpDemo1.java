package com.hmall.mp;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.hmall.common.utils.BeanUtils;
import com.hmall.domain.po.Address;
import com.hmall.domain.po.Item;
import com.hmall.domain.po.User;
import com.hmall.mapper.UserMapper;
import com.hmall.service.IItemService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MpDemo1 {
    @Autowired
    private IItemService itemService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        System.out.println("itemService = " + itemService);
        List<Item> list = itemService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectAndUpdate() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .select("username", "phone", "balance")
                .gt("balance", 900000)
                .like("username", "o");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.err::println);

        User user = new User();
        user.setBalance(2000);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
                .eq("username", "Jack");
        userMapper.update(user, updateWrapper);

        UpdateWrapper<User> updateWrapper2 = new UpdateWrapper<User>()
                .setSql("balance=balance-200")
                .in("id", 1, 2, 3, 4);
        userMapper.update(null, updateWrapper2);
    }

    @Test
    public void LambdaQueryWrapper() {
        //new LambdaQueryWrapper<>()
        LambdaQueryWrapper<User> o = new QueryWrapper<User>().lambda()
                .gt(User::getBalance, 1)
                .like(User::getUsername, "o");
        userMapper.selectList(o);
    }

    @Test
    public void LambdaQueryWrapper2() {
        String name = null;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, User::getUsername, name);
        userMapper.selectList(wrapper);
    }

    @Test
    public void LambdaQueryWrapper3() {
        //mp的批量新增，基于预编译的批处理，性能较好
        itemService.saveBatch(List.of(new Item(), new Item()));
    }
    @Test
    public void testStaticTool() {
        List<Address> list = Db.lambdaQuery(Address.class).list();
        list.forEach(System.out::println);
    }

}



