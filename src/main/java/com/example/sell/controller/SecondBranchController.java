package com.example.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 16:38 2018/11/22
 * @Version ： $version$
 */
@Controller
public class SecondBranchController {
    @RequestMapping(value = "/ddd",method = RequestMethod.GET)
    public String ddd(){
        return "sssssssssssss";
    }

    @RequestMapping(value = "/fff",method = RequestMethod.GET)
    public String fff(){
        return "gggggggggggg";
    }
}
