package com.example.sell.service;

import com.example.sell.dao.ProduceCategoryDao;
import com.example.sell.po.ProduceCategory;

import java.util.List;

public interface ProduceCategoryService {
    //根据id查询类目表
    ProduceCategory getOne(Integer id);
    //查询全部类目
    List<ProduceCategory> findAll();
    //根据类目类型查询类目
    List<ProduceCategory> findByCategoryTypeIn(List<Integer> list);
    //新增和修改
    ProduceCategory save(ProduceCategory produceCategory);
}
