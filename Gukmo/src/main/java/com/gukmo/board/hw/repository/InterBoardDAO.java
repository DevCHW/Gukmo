package com.gukmo.board.hw.repository;

import java.util.List;

import com.gukmo.board.model.MemberDTO;

public interface InterBoardDAO {

	List<MemberDTO> select_member();
	
}
