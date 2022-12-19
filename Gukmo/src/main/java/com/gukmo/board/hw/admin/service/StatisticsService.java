package com.gukmo.board.hw.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterStatisticsDAO;

@Service
public class StatisticsService implements InterStatisticsService{
	@Autowired
	private InterStatisticsDAO dao;
	
	
	
	
}
