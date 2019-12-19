package com.codegym.service;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();
    E findById(int id);
    void addBook(E e);
    void editBook(int id,E e);
    void deleteBook(int id);
    List<E> findByName(String name);
}
