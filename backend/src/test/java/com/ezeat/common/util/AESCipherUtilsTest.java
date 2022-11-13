package com.ezeat.common.util;

import com.ezeat.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ezeat.common.util.AESCipherUtils.decrypt;
import static com.ezeat.common.util.AESCipherUtils.encrypt;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AESCipherUtilsTest {
    @Autowired
    private UsersService service;

    /**
     * 登陆测试
     */
    @Test
    public void encryptTest() {
        String key = "0123456789abcdef"; // 128 bit key
        String initVector = "fedcba9876543210"; // 16 bytes IV

        String encryptStr = encrypt(key, initVector, "Hello World!!!!");
        String decryptStr = decrypt(key, encryptStr);

        System.out.println(encryptStr);
        System.out.println(decryptStr);

//        System.out.println(decrypt(key, "Kx+c85B7tWjre4j6cdXHJQ==:ZmVkY2JhOTg3NjU0MzIxMA==")); // output from PHP code Testing
    }
}
