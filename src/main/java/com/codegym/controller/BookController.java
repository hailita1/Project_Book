package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.BookForm;
import com.codegym.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@PropertySource("classpath:upload_file.properties")
public class BookController {
    @Autowired
    Environment env;

    @Autowired
    BookService bookService;

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("/books/login");
        return modelAndView;
    }

    @PostMapping("/logins")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (username.equals("admin") && password.equals("123456")) {
            List<Book> books;
            books = bookService.findAll();
            ModelAndView modelAndView = new ModelAndView("/books/showBook");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/books/error_login");
            modelAndView.addObject("message", "Wrong username or wrong password, Please login again !!!");
            return modelAndView;
        }
    }

    @GetMapping("/books")
    public ModelAndView showBookList(@RequestParam("search") Optional<String> search) {
        List<Book> books;
        if (search.isPresent()) {
            books = bookService.findByName(search.get());
        } else {
            books = bookService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("/books/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create-book")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/books/create");
        modelAndView.addObject("bookForm", new BookForm());
        return modelAndView;
    }

    @PostMapping("/create-book")
    public ModelAndView saveReceptionist(@ModelAttribute("bookForm") BookForm bookForm) {
        MultipartFile multipartFile = bookForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(bookForm.getImg().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Book book = new Book(bookForm.getId(), bookForm.getName(),
                bookForm.getPrice(), bookForm.getAuthor(), bookForm.getNXB(), fileName);
        bookService.addBook(book);
        ModelAndView modelAndView = new ModelAndView("/books/create");
        modelAndView.addObject("bookForm", new BookForm());
        modelAndView.addObject("message", "Created new book successfully");
        return modelAndView;
    }

    @GetMapping("/edit-book/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Book book = bookService.findById(id);
        if (book != null) {
            BookForm bookForm = new BookForm(book.getId(), book.getName(),
                    book.getPrice(), book.getAuthor(), book.getNXB(), null);
            ModelAndView modelAndView = new ModelAndView("/books/edit");
            modelAndView.addObject("bookForm", bookForm);
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-book")
    public ModelAndView updateReceptionist(@ModelAttribute("book") BookForm bookForm) {
        MultipartFile multipartFile = bookForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(bookForm.getImg().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Book book = new Book(bookForm.getId(), bookForm.getName(),
                bookForm.getPrice(), bookForm.getAuthor(), bookForm.getNXB(), fileName);
        bookService.editBook(book.getId(), book);
        ModelAndView modelAndView = new ModelAndView("/books/edit");
        modelAndView.addObject("bookForm", bookForm);
        modelAndView.addObject("message", "Updated new book information successfully");
        return modelAndView;
    }

    @GetMapping("/delete-book/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Book book = bookService.findById(id);
        if (book != null) {
            BookForm bookForm = new BookForm(book.getId(), book.getName(),
                    book.getPrice(), book.getAuthor(), book.getNXB(), null);
            ModelAndView modelAndView = new ModelAndView("/books/delete");
            modelAndView.addObject("book", book);
            modelAndView.addObject("bookForm", bookForm);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-book")
    public String deleteReceptionist(@ModelAttribute("bookForm") Book book) {
        bookService.deleteBook(book.getId());
        return "redirect:books";
    }

    @GetMapping("/view-book/{id}")
    public ModelAndView viewReceptionist(@PathVariable int id) {
        Book book = bookService.findById(id);
        if (book != null) {
            BookForm bookForm = new BookForm(book.getId(), book.getName(),
                    book.getPrice(), book.getAuthor(), book.getNXB(), null);
            ModelAndView modelAndView = new ModelAndView("/books/view");
            modelAndView.addObject("bookForm", bookForm);
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;

        }
    }
}
