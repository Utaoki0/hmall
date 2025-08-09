package com.hmall.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hmall.domain.po.Item;
import com.hmall.domain.po.User;
import com.hmall.mapper.UserMapper;
import com.hmall.service.IItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
}

