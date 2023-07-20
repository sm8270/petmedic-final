package com.spring.pet.doctor.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.pet.doctor.DoctorVO;

@Repository
public class DoctorDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public DoctorVO getDoc(DoctorVO vo) {
		DoctorVO asdf = (DoctorVO) mybatis.selectOne("docDAO.getDoc", vo);
		System.out.println("닥다오 겟닥: "+asdf);
		return asdf;
	}

	public void delDoc(DoctorVO vo) {
		System.out.println("mybatis delDoc 실행");
		mybatis.delete("docDAO.delDoc", vo);
	}
	

	public void updDoc(DoctorVO vo) {
		System.out.println("mybatis updDoc 실행" + vo);
		System.out.println(mybatis.update("docDAO.updDoc", vo));
	}
	
	public List<DoctorVO> getDocList(DoctorVO vo) {
		List<DoctorVO> asdf = mybatis.selectList("docDAO.getDocList", vo);
		System.out.println("닥다오 겟닥리스트: "+asdf);
		return asdf;
	}
	
	public void insertDoc(DoctorVO vo) {
		System.out.println("mybatis insertDoc 실행" + vo);
		System.out.println(mybatis.insert("docDAO.insertDoc", vo));
	}
	
}
