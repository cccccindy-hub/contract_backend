package com.nnroad;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = NNRoadApplication.class)
@RunWith(SpringRunner.class)
public class ENCTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encry() {
        String encrypt1 = stringEncryptor.encrypt("tj3WFgSM6g8qjiiI1fT25DKJ");
        String encrypt2 = stringEncryptor.encrypt("GongzigeHROne699!");
        System.out.println(encrypt1);
        System.out.println(encrypt2);
    }
}
