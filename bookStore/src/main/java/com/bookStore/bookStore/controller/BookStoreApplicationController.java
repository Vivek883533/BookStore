package com.bookStore.bookStore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.bookStore.Entity.Book;
import com.bookStore.bookStore.Entity.User;
import com.bookStore.bookStore.service.BookService;

@RestController
public class BookStoreApplicationController {

	@Autowired
	private BookService bookService;

	private static final Logger logger = LoggerFactory.getLogger(BookStoreApplicationController.class);

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {

		logger.info("Inside getAllBooks method of bookService");
		try {
			List<Book> bookList = bookService.getAllBooks();

			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}
			return new ResponseEntity<>(bookList,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getBookByID/{id}")
	public ResponseEntity<Book> getBookByID(@PathVariable Long id) {
		try {
			Book book = bookService.getBookByID(id);

			if (book == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		try {
			String result = bookService.addBook(book);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/updateBookById/{id}")
	public ResponseEntity<String> updateBookById(@PathVariable Long id, @RequestBody Book book) {
		try {
			String result = bookService.updateBook(id, book);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("deleteBookById/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Long id, @RequestBody Book book) {
		try {
			String result = bookService.deleteBook(id, book);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/purchaseBook")
	public ResponseEntity<String> purchaseBook(@RequestBody User user) {
		try {
			String result = bookService.purchaseBook(user);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/orderHistory")
	public ResponseEntity<User> orderHistory(@RequestBody User user) {
		try {
			User result = bookService.orderHistory(user);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
