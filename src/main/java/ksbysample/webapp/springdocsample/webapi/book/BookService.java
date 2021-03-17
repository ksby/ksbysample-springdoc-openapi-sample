package ksbysample.webapp.springdocsample.webapi.book;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Book データ用 Service クラス
 */
@Service
public class BookService {

    @SuppressWarnings("PMD.ImmutableField")
    private Map<String, Book> bookMap = new HashMap<>();

    /**
     * {@link BookService#bookMap} に初期データを追加する
     */
    @PostConstruct
    public void postConstruct() {
        bookMap.put("978-1492076988",
                Book.builder()
                        .isbn("978-1492076988")
                        .name("Spring Boot: Up and Running")
                        .price(new BigDecimal("7195"))
                        .releaseDate(LocalDate.of(2021, 3, 23))
                        .build());
        bookMap.put("978-1484237236",
                Book.builder()
                        .isbn("978-1484237236")
                        .name("The Definitive Guide to Spring Batch")
                        .price(new BigDecimal("5401"))
                        .build());
    }

    /**
     * Book データを {@link BookService#bookMap} に追加する
     *
     * @param book {@link Book} object
     */
    public void add(Book book) {
        bookMap.put(book.getIsbn(), book);
    }

    /**
     * 指定された ISBN のデータを {@link BookService#bookMap} から検索して返す
     *
     * @param isbn ISBN
     * @return {@link Book} object
     */
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookMap.get(isbn));
    }

}
