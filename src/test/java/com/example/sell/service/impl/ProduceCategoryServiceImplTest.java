package com.example.sell.service.impl;

import com.example.sell.po.ProduceCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceCategoryServiceImplTest {
    @Autowired
    private ProduceCategoryServiceImpl produceCategoryService;
    @Test
    public void getOne() {
        ProduceCategory produceCategory = produceCategoryService.getOne(2);
        Assert.assertEquals(new Integer(2),produceCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProduceCategory> list = produceCategoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProduceCategory> list = produceCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
        ProduceCategory produceCategory = new ProduceCategory("安康角度看",23);
        ProduceCategory result = produceCategoryService.save(produceCategory);
        Assert.assertNotNull(result);
    }
}
