package com.bookStore.bookStore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.bookStore.Entity.Book;
import com.bookStore.bookStore.Entity.User;
import com.bookStore.bookStore.Exception.CustomException;
import com.bookStore.bookStore.repo.BookRepo;
import com.bookStore.bookStore.repo.UserRepo;

@Service
public class BookService {

	@Autowired
	BookRepo bookRepo;

	@Autowired
	UserRepo userRepo;

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	public List<Book> getAllBooks() {
		List<Book> book = null;
		try {
			book = bookRepo.findAll();
			if(book == null) {
				throw new CustomException("No Book Found");
			}
		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return book;

	}

	public Book getBookByID(Long id) {
		logger.info("In getBookByID method of BookService");
		Book book = null;
		try {
			Optional<Book> optBook = bookRepo.findById(id);
			if (optBook.isPresent()) {
				book = optBook.get();
			}
		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return book;
	}

	public String addBook(Book book) {
		logger.info("In getBookByID method of BookService");
		String result = null;
		try {
			bookRepo.save(book);
			result = "success";
		} catch (Exception e) {
			result = "Error";
			logger.error("Error Occurred " + e);
		}
		return result;
	}

	public String updateBook(Long id, Book newBook) {
		logger.info("In getBookByID method of BookService");
		String result = null;
		Book oldBook = null;
		try {
			Optional<Book> optBook = bookRepo.findById(id);
			if (optBook.isPresent()) {
				oldBook = optBook.get();
				oldBook.setAuthor(newBook.getAuthor());
				oldBook.setTitle(newBook.getTitle());
				oldBook.setIsbn(newBook.getIsbn());
				oldBook.setPrice(newBook.getPrice());
				oldBook.setQuantity(newBook.getQuantity());
				bookRepo.save(oldBook);
				result = "Success";
			}
		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return result;
	}

	public String deleteBook(Long id, Book book) {
		logger.info("In deleteBook method of BookService");
		String result = null;
		try {
			Optional<Book> optBook = bookRepo.findById(id);
			if (optBook.isPresent()) {
				bookRepo.deleteById(id);
				result = "Success";
			}
		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return result;
	}

	public String purchaseBook(User user) {
		logger.info("In deleteBook method of BookService");
		String result = null;
		try {
			User existingUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
			if (existingUser != null) {
				Book book = bookRepo.findByTitleAndIsbn(user.getBookTitle(), user.getIsdn());
				if (book != null && book.getQuantity() != 0) {
					long newQuantity = book.getQuantity() - 1;
					book.setQuantity(newQuantity);
					bookRepo.save(book);
					result = "Success";
				} else {
					result = "Book Not Found";
				}
			} else {
				result = "User Not found";
			}
		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return result;
	}

	public User orderHistory(User user) {
		logger.info("In orderHistory method of BookService");
		User existingUser = null;
		try {
			existingUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());

		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return existingUser;
	}

}
