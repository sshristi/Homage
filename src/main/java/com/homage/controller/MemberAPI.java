package com.homage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homage.model.Member;
import com.homage.model.ResponseDeathToll;
import com.homage.services.MemberService;

@RestController
@RequestMapping(path = "/homage")
public class MemberAPI {
	
	@Autowired
	MemberService memberService;
	
	private static List<String> result = new ArrayList<>();
	
//	public MemberAPI() {
//		System.out.println("Inside constructor");
//		Map<String, Long> counter = memberService.deathCount();
//		counter.entrySet().forEach(e ->{
//			result.add("| " + e.getKey() + "---> " + e.getValue() + " |");
//		});
//	}
	
	@RequestMapping(path = "/newmember", method = RequestMethod.POST)
	public ResponseEntity<?> addMember(@RequestBody Member newMember){
		try {
			
			Member member = memberService.addMember(newMember);
			if(member == null)
				return new ResponseEntity<>(member, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(member,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}

	@RequestMapping(path = "/allmembers", method = RequestMethod.GET)
	public ResponseEntity<?> getMembers(){
		try {
			System.out.println("inside api /allmembers");
			List<Member> memberList = memberService.getAllMembers();
			System.out.println(memberList);
			ResponseDeathToll responseDeathToll = memberService.deathCount();
			responseDeathToll.setMemberList(memberList);
			responseDeathToll.setMemberList(memberList);
			if(memberList == null)
				return new ResponseEntity<>(memberList, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(responseDeathToll,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	@RequestMapping(path = "/lit/{memberId}", method = RequestMethod.PUT)
	public ResponseEntity<?> litACandle(@PathVariable String memberId){
		try {
			boolean lit = memberService.litACandle(memberId);
			if(lit == false)
				return new ResponseEntity<>(memberId,HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(memberId+" successfully lighted", HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/findmembers", method = RequestMethod.PUT)
	public ResponseEntity<?>findMembers(@RequestBody Member member){
		try {
			return null;
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
	@RequestMapping(path = "/members", method = RequestMethod.GET)
	public ResponseEntity<?>getMembersBy(@RequestBody String name, @RequestHeader Map<String, String> header){
		try {
			System.out.println("inside api /members");
		List<Member> memberList = memberService.getMembersBy(name, header);
		System.out.println(memberList);
		ResponseDeathToll responseDeathToll = memberService.deathCount();
		responseDeathToll.setMemberList(memberList);
		responseDeathToll.setMemberList(memberList);
		if(memberList == null)
			return new ResponseEntity<>(memberList, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(responseDeathToll,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
		
//	
//	@RequestMapping(path = "/members/dob", method = RequestMethod.PUT)
//	public ResponseEntity<?>viewTribute(@PathVariable String memberId){
//		try {
//			return null;
//		}catch(Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	@RequestMapping(path = "/members/dod", method = RequestMethod.GET)
//	public ResponseEntity<?>viewTribute(@PathVariable String memberId){
//		try {
//			return null;
//		}catch(Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@RequestMapping(path = "/tribute/add", method = RequestMethod.PUT)
	public ResponseEntity<?> addTribute(@RequestBody Member member){
		try {
			boolean result = memberService.addTribute(member);
			if(result)
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/tribute/delete", method = RequestMethod.PUT)	//requires login epic | each user can delete own tributes
	public ResponseEntity<?> deleteTribute(@PathVariable String memberId){
		try {
			return null;
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/tribute/{memberId}/all", method = RequestMethod.PUT)
	public ResponseEntity<?>viewTribute(@PathVariable String memberId){
		try {
			return null;
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
