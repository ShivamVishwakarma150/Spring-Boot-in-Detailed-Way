package com.app.shivam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shivam.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> {

}
