package com.example.dbmongo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ControlOne {
	@Autowired
	MongoTemplate subTemplate;
	
	@GetMapping(path="/")
	public Object test() {
		return "HI";
	}
	@GetMapping(path="/one")
	public Object one() {
		List<String> names = new ArrayList<String>();
		names.add("abc");
		Query query = new Query();
		query.addCriteria(Criteria
				.where("name").in(names));
		List<DataOne> result = subTemplate.find(query, DataOne.class);
		return result;
	}

}


@Document(collection = "tb1")
class DataOne {
	private String _id;
	@Indexed
	private String name;
	@Indexed
	private String age;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}

interface IRepOne extends MongoRepository<DataOne, String> {
	
}
