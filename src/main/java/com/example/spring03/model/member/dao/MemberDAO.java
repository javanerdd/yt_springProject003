package com.example.spring03.model.member.dao;

import com.example.spring03.model.member.dto.MemberDTO;

public interface MemberDAO {

	public String loginCheck(MemberDTO dto);
}
