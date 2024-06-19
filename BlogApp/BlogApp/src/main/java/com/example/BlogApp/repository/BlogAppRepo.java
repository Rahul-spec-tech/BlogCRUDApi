package com.example.BlogApp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BlogApp.model.BlogApp;
@Repository
public interface BlogAppRepo extends JpaRepository<BlogApp,Long> {
	public BlogApp getById(Long id);
}
