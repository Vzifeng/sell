package com.example.sell.service.impl;

import com.example.sell.dao.ProduceCategoryDao;
import com.example.sell.po.ProduceCategory;
import com.example.sell.service.ProduceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目service
 */
@Service
public class ProduceCategoryServiceImpl implements ProduceCategoryService {
    @Autowired
    private ProduceCategoryDao produceCategoryDao;

    /**
     * 根据id查询类目表
     * @param id
     * @return
     */
    @Override
    public ProduceCategory getOne(Integer id) {
        return produceCategoryDao.getOne(id);
    }

    /**
     * 查询全部类目
     * @return
     */
    @Override
    public List <ProduceCategory> findAll() {
        return produceCategoryDao.findAll();
    }

    /**
     * 根据类目类型查询类目
     * @param list
     * @return
     */
    @Override
    public List <ProduceCategory> findByCategoryTypeIn(List <Integer> list) {
        return produceCategoryDao.findByCategoryTypeIn(list);
    }

    /**
     * 新增修改类目表
     * @param produceCategory
     * @return
     */
    @Override
    public ProduceCategory save(ProduceCategory produceCategory) {
        return produceCategoryDao.save(produceCategory);
    }
}
