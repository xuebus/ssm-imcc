package com.zhaopin.dao.impl;

import java.util.List;
import java.util.Map;

import com.zhaopin.core.common.CommonDao;
import com.zhaopin.dao.PersonDao;
import com.zhaopin.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl extends CommonDao implements PersonDao {

	String ns = "sql.mapper.PersonMapper.";
	@Override
	public void save(Person p) {
		this.getSqlSession().insert(ns+"insert", p);
	}

	@Override
	public Person selectPersonById(Integer personId) {
		return (Person) this.getSqlSession().selectOne(ns+"selectPersonByIdWithRM", personId);
	}

	@Override
	public void update(Person p) {
		this.getSqlSession().update(ns+"dynamicUpdate", p);
	}

	@Override
	public void delete(Integer personId) {
		this.getSqlSession().delete(ns+"delete", personId);
	}

	@Override
	public List<Person> selectPersonByCondition(Map<String, Object> map) {
		return this.getSqlSession().selectList(ns+"selectPersonByCondition", map);
	}

}
