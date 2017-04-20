package com.zhaopin.dao;

import com.zhaopin.model.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by SYJ on 2017/4/20.
 */
public interface PersonDao {
	
	void save(Person p);
	
	Person selectPersonById(Integer personId);
	
	void update(Person p);
	
	void delete(Integer personId);
	
	List<Person> selectPersonByCondition(Map<String, Object> map);

}
