package com.example.BlogApp.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.BlogApp.model.BlogApp;
import com.example.BlogApp.repository.BlogAppRepo;

@RestController
public class BlogAppService {
	@Autowired
	private BlogAppRepo blogAppRepo;
	@PostMapping("/addBlog")
	public ResponseEntity<BlogApp> addBlogPost(@RequestBody BlogApp blogApp){
		BlogApp blogData=blogAppRepo.save(blogApp);
		return new ResponseEntity<>(blogData,HttpStatus.OK);
	}
	
	@GetMapping("/getBlogById/{Id}")
	public ResponseEntity<BlogApp> getBlogById(@PathVariable("Id") Long id){
		Optional<BlogApp> getBlog=blogAppRepo.findById(id);
		if(getBlog.isPresent()) {
			return new ResponseEntity<>(getBlog.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/getBlogDetails")
	public ResponseEntity<List<BlogApp>> getBlogDetails(){
		try {
			List<BlogApp> blogDetails=new ArrayList<>();
			blogAppRepo.findAll().forEach(blogDetails::add);
			if(blogDetails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(blogDetails,HttpStatus.OK);	
		}
		catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("/updatedBlogById/{Id}")
	public ResponseEntity<BlogApp> updateBlogDetails(@PathVariable("Id") Long id,@RequestBody BlogApp newBlog){
		Optional<BlogApp> oldBlogId=blogAppRepo.findById(id);
		if(oldBlogId.isPresent()) {
			BlogApp updatedBlogData=oldBlogId.get();
			updatedBlogData.setContent(newBlog.getContent());
			updatedBlogData.setGenre(newBlog.getGenre());
			updatedBlogData.setTitle(newBlog.getTitle());
			BlogApp updatedBlog=blogAppRepo.save(updatedBlogData);
			return new ResponseEntity<>(updatedBlog,HttpStatus.OK);		
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteBlogById/{Id}")
	public ResponseEntity<HttpStatus> deleteBlogById(@PathVariable("Id") Long id){
		blogAppRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
