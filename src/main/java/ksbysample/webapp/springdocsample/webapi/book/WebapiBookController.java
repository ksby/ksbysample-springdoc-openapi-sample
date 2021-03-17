package ksbysample.webapp.springdocsample.webapi.book;

import ksbysample.webapp.springdocsample.exception.BookInvalidException;
import ksbysample.webapp.springdocsample.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Book データ WebAPI 用 Controller クラス
 */
@Slf4j
@RestController
@RequestMapping("/webapi/book")
public class WebapiBookController {

    private final BookService bookService;

    /**
     * コンストラクタ
     *
     * @param bookService {@link BookService} bean
     */
    public WebapiBookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Book データを登録する
     *
     * @param book          {@link Book} object
     * @param bindingResult Book データ Validation 用の {@link BindingResult} object
     */
    @PostMapping
    public void add(@Validated @RequestBody Book book, BindingResult bindingResult) {
        log.info(book.toString());
        if (bindingResult.hasErrors()) {
            throw new BookInvalidException(
                    String.format("Book データの入力チェックでエラーが発生しました: %s", book.toString()));
        }

        bookService.add(book);
    }

    /**
     * 指定された ISBN の Book データを取得する
     *
     * @param isbn ISBN
     * @return {@link Book} object
     */
    @GetMapping("/{isbn}")
    public Book findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(
                        String.format("お探しの本は見つかりませんでした ( isbn = %s )", isbn)));
    }

}
