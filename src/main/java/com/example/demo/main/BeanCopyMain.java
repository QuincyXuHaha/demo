package com.example.demo.main;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import sun.misc.Cleaner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * bean copy test
 *
 * @author xuguangquan
 * @date 2020/5/18 周一
 */
public class BeanCopyMain {


    public static void main(String[] args) {
        Person person = new Person();
        person.setBalance(BigDecimal.TEN);
        person.setName("quincy");
        person.setCreatorTime(new Date());
        Person copy = new Person();
        BeanUtils.copyProperties(person, copy);
        System.out.println(copy.toString());

    }

    @Data
    static class Person {

        private String name;
        private BigDecimal balance;
        private Date creatorTime;

    }


}
