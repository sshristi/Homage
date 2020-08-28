package com.homage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.homage.model.Member;

public interface MembersRepository extends MongoRepository<Member, Long>{
	
}
