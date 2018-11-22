package com.example.sell.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProduceInfoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void getProduceList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/produceInfo/getProduceList"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void getProduceInfoList() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/produceInfo/getProduceInfoList")
                .param("num","1").param("size","3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.content().string("ssssss"));


    }
}