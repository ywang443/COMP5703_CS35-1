package com.ezeat.service;

import com.ezeat.vo.ResultVo;
import com.ezeat.validator.LoginVd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {
    @Autowired
    private UsersService service;

    /**
     * 登陆测试
     */
    @Test
    public void loginTest() {
        LoginVd loginVd = new LoginVd();
        loginVd.setName("neo");
        loginVd.setPassword("123456");
        ResultVo resultVo = service.login(loginVd);
        System.out.println(resultVo.toString());
    }

}
