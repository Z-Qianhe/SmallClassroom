package com.offcn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class UeduApplicationTests {

    @Test
    void contextLoads() {
            //
            String ids = "1,2,3,4";
            String[] split = ids.split(",");
//            List list = Arrays.asList(split);
//            System.out.println(list);

            List list = new ArrayList<>();

        Collections.addAll(list,split);

        System.out.println(list);


    }

}
