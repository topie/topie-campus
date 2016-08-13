package com.topie.campus.security;

import com.topie.campus.security.model.User;
import com.topie.campus.security.service.FunctionService;
import com.topie.campus.security.service.RoleService;
import com.topie.campus.security.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * 工程：os-app 创建人 : ChenGJ 创建时间： 2015/9/2 说明：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserRoleFunctionTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    FunctionService functionService;

    @Test
    public void listUser() {
        List<User> users = userService.selectAll();
        for (User user : users) {
            System.out.println("获取用户：执行结果===============" + user.getDisplayName());
        }
    }

}
