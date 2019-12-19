package com.codegym.repository.impl;

import com.codegym.model.Book;
import com.codegym.repository.IBookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book(1, "Tham tu lung danh CoNan", 18000, "Maria Orawa", "Nhat Ban", "messi.jpg"));
    }

    @Override
    public List<Book> findAll() {
        return bookList;
    }

    @Override
    public Book findById(int id) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                return bookList.get(i);
            }
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        bookList.add(book);
    }

    @Override
    public void editBook(int id, Book book) {
        int index = -1;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                index = i;
            }
        }
        bookList.set(index, book);
    }

    @Override
    public void deleteBook(int id) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == id) {
                bookList.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> receptionists = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getName().contains(name)) {
                receptionists.add(bookList.get(i));
            }
        }
        return receptionists;
    }
}
