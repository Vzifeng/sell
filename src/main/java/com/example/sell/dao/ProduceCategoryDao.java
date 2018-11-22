package com.example.sell.dao;

import com.example.sell.po.ProduceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceCategoryDao extends JpaRepository<ProduceCategory,Integer> {
    List<ProduceCategory> findByCategoryTypeIn(List<Integer> list);
}
