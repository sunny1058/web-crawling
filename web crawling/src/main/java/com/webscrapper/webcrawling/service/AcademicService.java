package com.webscrapper.webcrawling.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webscrapper.webcrawling.BookRepository.BookRepository;
import com.webscrapper.webcrawling.dto.Books;
import com.webscrapper.webcrawling.exception.BusinessError;
import com.webscrapper.webcrawling.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AcademicService {
    Map<String, Object> map = new HashMap<>();
    List<Books> bookdtos = new ArrayList<>();
    @Autowired
    private BookRepository bookRepository;
    public List<Books> connect() throws IOException {
        try {
            ObjectMapper oMapper = new ObjectMapper();
            Document doc = Jsoup.connect(
                    "https://academic.oup.com/journals/search-results?page=1&q=data+mining&SearchSourceType=1&allJournals=1")
                    .get();
            String title = doc.title();
            Elements repositories = doc.getElementsByClass("sr-list al-article-box al-normal clearfix");
            for (Element repository : repositories) {
                String titlebook = repository.getElementsByClass("sri-title customLink al-title").text();
                String author = repository.getElementsByClass("sri-authors al-authors-list").text();
                String publishedDate = repository.getElementsByClass("sri-date al-pub-date").text();
                String url = repository.getElementsByClass("al-citation-list").text();
                Books book = new Books();
                book.setAuthor(author);
                book.setTitle(titlebook);
                book.setPublishedDate(publishedDate);
                book.setUrl(url);
                Books model = bookRepository.save(book);
                Map<String, Object> map = oMapper.convertValue(model, HashMap.class);
                log.info("Object",map.get(model));
                bookdtos.add(book);
                for (int i = 0; i < bookdtos.size(); i++) {
                    bookdtos.get(i);
                }
            }
            return bookdtos;
        } catch (Exception e) {
            map.put("error", e);
            String message = String.format("Unable to get Books Data");
            log.error(message, e);
            throw new BusinessException("Unable to get Books Data",
                    BusinessError.GET_BOOK_DATA_EXCEPTION, map);
        }
    }
}
