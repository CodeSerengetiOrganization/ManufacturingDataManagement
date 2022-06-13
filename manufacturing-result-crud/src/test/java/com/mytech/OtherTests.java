package com.mytech;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2022-05-02
 * @description :
 */
@SpringBootTest
public class OtherTests {

    @Test
    public void printIntergerValueWhenNull(){
        Integer it=null;
        int value = it.intValue();
        System.out.println("value:"+value);
    }

}//class
