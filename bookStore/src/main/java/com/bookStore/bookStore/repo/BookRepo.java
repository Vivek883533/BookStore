package com.bookStore.bookStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.bookStore.Entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
	Book findByTitleAndIsbn(String Title,String isbn);

}
