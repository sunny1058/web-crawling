package com.webscrapper.webcrawling.BookRepository;

import com.webscrapper.webcrawling.dto.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books,Long> {
}
