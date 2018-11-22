package com.example.sell.dao;

import com.example.sell.po.ProduceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ProduceInfoDao extends JpaRepository<ProduceInfo,String> {

    List<ProduceInfo> findByProduceStatus(Integer produceStatus);

}
