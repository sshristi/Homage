package com.homage.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.homage.model.Member;
import com.homage.model.ResponseDeathToll;
import com.homage.repository.MembersRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MembersRepository membersRepository;
	
	static List<Member> membersList;
	@Override
	public ResponseDeathToll deathCount(){
		ResponseDeathToll responseDeathToll = new ResponseDeathToll();
		List<String> result = new ArrayList<>();
		Map<String, Long> counter= new HashMap<>();
		counter.put("TOTAL DEATH TOLL", membersRepository.count());
		responseDeathToll.setTotal(counter.get("TOTAL DEATH TOLL"));
		Query query = new Query();
		query.addCriteria(Criteria.where("cause").is("corona"));
//		System.out.println(query);
		
//		Example<Member> example = Example.);
//		counter.put("CORONA", (long)membersRepository.findAll(example).size());
		counter.put("CORONA", mongoTemplate.count(query, Member.class));
		responseDeathToll.setCorona(counter.get("CORONA"));
		counter.entrySet().forEach(e ->{
			result.add("| " + e.getKey() + "---> " + e.getValue() + " |");
		});
		System.out.println(result);
		return responseDeathToll;
	}
	
	@Override
	public Member addMember(Member newMember){
	
		if(newMember == null)
			return null;
//		Member member = new Member();
//		member.setFirstName(newMember.getFirstName());
//		member.setLastName(newMember.getLastName());
//		member.setPhoto(newMember.getPhoto());
//		member.setDob(newMember.getDob());
//		member.setDod(newMember.getDod());
//		member.setLastWords(newMember.getLastWords());
//		member.setAccomplishment(newMember.getAccomplishment());
//		member.setStreet(newMember.getStreet());
//		member.setCity(newMember.getCity());
//		member.setState(newMember.getState());
//		member.setCountry(newMember.getCountry());
//		member.setCause(newMember.getCause());
		
		Member member = new Member(
				newMember.getFirstName(),
				newMember.getLastName(),
				newMember.getPhoto(),
				newMember.getDob(),
				newMember.getDod(),
				newMember.getCandle(), 					// or keep as : 0 (if not giving from frontend
				newMember.getLastWords(),
				newMember.getAccomplishment(),
				newMember.getStreet(),
				newMember.getCity(),
				newMember.getState(),
				newMember.getCountry(),
				newMember.getCause(),
				new ArrayList<String>()
				//newMember.getTribute()
				);
		membersRepository.save(member);
		System.out.println("member added");
		return member;
	}
	
	//get all is not working
	@Override
	public List<Member> getAllMembers(){
		System.out.println("inside service /allmembers");
		deathCount();
		//Query query = new Query();
		//query.addCriteria(Criteria.where("lastName").is("Stephen"));
		membersList = membersRepository.findAll();
		System.out.println(membersList.get(0).getTribute().get(2));
		System.out.println(membersList + "showed");
		return membersList;
	}

	@Override
	public boolean litACandle(String memberId) {
		Query query = new Query();
		//membersRepository.findById(memberId);
		query.addCriteria(Criteria.where("id").is(memberId));
		Member member = mongoTemplate.findOne(query , Member.class);
		if(member == null)
			return false;
		member.setCandle(member.getCandle()+1);
		membersRepository.save(member);
		return true;
	}
	
	@Override
	public boolean addTribute(Member member) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(member.getId()));
		Member m = mongoTemplate.findOne(query, Member.class);
		if(m == null)
			return false;
		m.getTribute().add(member.getTribute().get(0));
		membersRepository.save(m);
		return true;
	}
	
	@Override
	public List<Member> findMembers(Member member) {
		Query query = new Query();
		if(member.getFirstName()!=null) 
			query.addCriteria(Criteria.where("firstName").is(member.getFirstName()));
		else if(member.getLastName()!=null) 
			query.addCriteria(Criteria.where("lastName").is(member.getLastName()));
		else if(member.getDob()!=null) 
			query.addCriteria(Criteria.where("dob").is(member.getDob()));
		else if(member.getDod()!=null) 
			query.addCriteria(Criteria.where("dod").is(member.getDod()));
		else if(member.getCause()!=null) 
			query.addCriteria(Criteria.where("cause").is(member.getCause()));
		else if(member.getCause()!=null) 
			query.addCriteria(Criteria.where("cause").is(member.getCause()));
		else if(member.getCause()!=null) 
			query.addCriteria(Criteria.where("cause").is(member.getCause()));

		return null;
	}
	
	@Override
	public List<Member> getMembersBy(String name, Map<String, String> header){
		Query query = new Query();
		query.addCriteria(Criteria.where(header.get("field")).is(name));
		List<Member> list = mongoTemplate.find(query, Member.class);
		return list;
	}
}
