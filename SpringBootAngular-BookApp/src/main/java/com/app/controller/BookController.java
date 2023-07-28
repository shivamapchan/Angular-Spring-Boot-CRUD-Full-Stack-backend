package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BookNotFoundException;
import com.app.model.Book;
import com.app.service.BookService;

@RestController
@RequestMapping("/angular")
@CrossOrigin
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// Find all books api
	@GetMapping("/books")
	public List<Book> selectAllBooks(){
		return bookService.findAllBooks();
	}
	
	// Adding books to database
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}
	
	// Get Book by it's Id api
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		Book book = bookService.findBookById(id).
				orElseThrow(()->new BookNotFoundException("Could not find book with ID: "+id));
		return ResponseEntity.ok(book);
	}
	
	// Editing book with respect to it's id
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails){
		Book book = bookService.findBookById(id).
				orElseThrow(()->new BookNotFoundException("Could not find book with ID: "+id));
		book.setBook_name(bookDetails.getBook_name());
		book.setBook_price(bookDetails.getBook_price());
		book.setBook_author(bookDetails.getBook_author());
		book.setBook_publisher(bookDetails.getBook_publisher());
		Book updatedBook = bookService.createBook(book);
		return ResponseEntity.ok(updatedBook);
		
	}
	
	// Deleting book with respect to it's id
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id){
		Book book = bookService.findBookById(id).
				orElseThrow(()->new BookNotFoundException("Could not find book with ID: "+id));
		bookService.deleteBook(book);
		return ResponseEntity.ok(null);
		
	}

}
