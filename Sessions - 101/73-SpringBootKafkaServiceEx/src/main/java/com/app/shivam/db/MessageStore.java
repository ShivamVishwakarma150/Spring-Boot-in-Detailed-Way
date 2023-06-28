package com.app.shivam.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.shivam.entity.StockInfo;
import com.app.shivam.repo.StockInfoRepository;
import com.app.shivam.util.JsonUtil;

@Component
public class MessageStore {
	
	@Autowired
	private StockInfoRepository repo;

	public void add(String message) {
		//JSON TO Object
		StockInfo si = JsonUtil.convertToObj(message);
		repo.save(si);
	}

	public List<StockInfo> getAll() {
		return repo.findAll();
	}

}
