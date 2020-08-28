package com.homage.services;

import java.util.List;
import java.util.Map;

import com.homage.model.Member;
import com.homage.model.ResponseDeathToll;

public interface MemberService {

	Member addMember(Member newMember);

	List<Member> getAllMembers();

	ResponseDeathToll deathCount();

	boolean litACandle(String memberId);

	List<Member> findMembers(Member member);

	List<Member> getMembersBy(String name, Map<String, String> header);

	boolean addTribute(Member member);

	//Map<String, Long> deathCount();

}
