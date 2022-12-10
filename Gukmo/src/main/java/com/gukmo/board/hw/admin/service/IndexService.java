package com.gukmo.board.hw.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gukmo.board.hw.admin.repository.InterIndexDAO;


@Service
public class IndexService implements InterIndexService{
	@Autowired
	private InterIndexDAO dao;

}
