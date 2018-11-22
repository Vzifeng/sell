package com.example.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 13:41 2018/11/22
 * @Version ： $version$
 */
@Controller
public class TestOneBranch {
    @RequestMapping(value = "/testOneBranch",method = RequestMethod.GET)
    public String testOneBranch(){
        return "one branch";
    }
}

