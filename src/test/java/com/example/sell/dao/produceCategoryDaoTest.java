package com.example.sell.dao;

import com.example.sell.po.ProduceCategory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class produceCategoryDaoTest {
    @Autowired
    private ProduceCategoryDao produceCategoryDao;
    @Test
    public void findOneTest() {
        ProduceCategory produceCategory2 =  produceCategoryDao.getOne(1);
        System.out.println(produceCategory2.toString());
    }
    @Test
    public void saveData(){
        ProduceCategory produceCategory2 =  produceCategoryDao.getOne(1);
        ProduceCategory produceCategory = new ProduceCategory();
        produceCategory2.setCategoryName("阿福");
        produceCategory2.setCategoryType(12);
        produceCategoryDao.save(produceCategory2);
    }
    @Test
    public void checkByCategoryType(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProduceCategory> list1 = produceCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,list1.size());
    }
}
