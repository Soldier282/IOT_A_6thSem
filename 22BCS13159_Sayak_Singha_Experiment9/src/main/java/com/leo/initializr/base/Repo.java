package com.leo.initializr.base;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Repository
public class Repo {
	
	private static final Logger log = LoggerFactory.getLogger(Repo.class);
	private final JdbcClient jdbcClient;
	
	public Repo(JdbcClient jdbcClient)
	{
		this.jdbcClient = jdbcClient;
	}
	//private List<Tool_info> infos = new ArrayList<>();
	List<Tool_info> findAll(){
		return jdbcClient.sql("SELECT * FROM tbl_APP").query(Tool_info.class).list();
	}
	
	void create(Tool_info info){
		int updated = jdbcClient.sql("INSERT INTO tbl_APP(_id, _name, _author, _auth_tag, _size, _valid, _date_added) VALUES(?,?,?,?,?,?,?)")
		.params(List.of(info._id(), info._name(), info._author(), info._authTag(), info._size(), info._valid(), info._dateAdded())).update();
		Assert.state(updated == 1, "Failed to create App"+info._name());
	}
	
	Optional<Tool_info> findById(Long ID) {
		return jdbcClient.sql("SELECT * FROM tbl_APP WHERE _id= :ID").param("ID", ID).query(Tool_info.class).optional();
		//return infos.stream().filter(tool_info -> Objects.equals(tool_info._id(), ID)).findFirst();
	}
	
	void update(Tool_info info, Long ID)
	{
		int updated = jdbcClient.sql("UPDATE tbl_APP SET _name = ?, _author = ?, _auth_tag = ?, _size = ?, _valid = ?, _date_added = ? WHERE _id = ?")
				.params(List.of(info._name(), info._author(), info._authTag(), info._size(), info._valid(), info._dateAdded(), ID)).update();
		Assert.state(updated == 1, "Failed to update App Data");
		/*
		Optional<Tool_info> optionalApp = findById(ID);
		if(optionalApp.isPresent())
			infos.set(infos.indexOf(optionalApp.get()), info);*/
	}
	
	void delete(Long ID)
	{
		int deleted = jdbcClient.sql("DELETE FROM tbl_APP WHERE _id= :ID").param("ID", ID).update();
		Assert.state(deleted == 1, "Failed to delete App Data");
		//infos.removeIf(appInfo -> Objects.equals(appInfo._id(), ID)); 
	}
	/*
	@PostConstruct
	private void init()
	{
		infos.add(new Tool_info(23, "SomeApp", "ABC", "ABC.SomeApp.23", 1024, null, LocalDate.now()));
		infos.add(new Tool_info(128, "SomeTool", "DEF", "DEF.SomeTool.128", 2048, null, LocalDate.now()));
	}
	*/
}
