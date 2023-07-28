package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Book;
import com.app.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(){
		List<Book> bookList = bookRepository.findAll();
		return bookList;
	}
	
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	public Optional<Book> findBookById(Integer id) {
		return bookRepository.findById(id);
	}
	
	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

}
