package com.bookStore.bookStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.bookStore.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	
	User findByEmailAndPassword(String email,String password);

}
