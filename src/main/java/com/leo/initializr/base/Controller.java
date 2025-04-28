package com.leo.initializr.base;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leo.initializr.exceptions.AppNotFoundException;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/appInf")
public class Controller {
	private final Repo appRepo;
	
	public Controller(Repo newRepo) {
		this.appRepo = newRepo;
	}
	
	//read
	@GetMapping("")
	List<Tool_info> findAll(){
		return appRepo.findAll();
	}
	
	//find
	@GetMapping("/{id}")
	Tool_info findById(@PathVariable Long id) {
		Optional<Tool_info> appInfOptional =  appRepo.findById(id);
		if(appInfOptional.isEmpty())
			throw new AppNotFoundException();
		return appInfOptional.get();
	}
	
	//create
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	void create(@Valid @RequestBody Tool_info info)
	{
		appRepo.create(info);
	}
	
	//update
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	void update(@Valid @RequestBody Tool_info info, @PathVariable Long id) {
		appRepo.update(info, id);
	}
	
	//delete
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	void delete(@PathVariable Long id)
	{
		appRepo.delete(id);
	}
}
