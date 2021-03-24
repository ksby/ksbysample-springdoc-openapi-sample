package ksbysample.webapp.springdocsample.webapi.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Book", description = "the Book API")
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
    @Operation(summary = "Book データを登録する",
            description = "Book データを受け取ってリストに追加する")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "405", description = "入力チェックエラー")
    })
    @PostMapping
    public void add(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book データ")
            @Validated @RequestBody Book book,
            BindingResult bindingResult) {
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
    @Operation(summary = "Book データを検索する",
            description = "指定された ISBN の Book データを検索する")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "指定された ISBN の Book データが存在しない",
                    content = @Content)
    })
    @GetMapping("/{isbn}")
    public Book findByIsbn(
            @Parameter(required = true, description = "ISBN-13", example = "978-4873119038")
            @PathVariable String isbn) {
        return bookService.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(
                        String.format("お探しの本は見つかりませんでした ( isbn = %s )", isbn)));
    }

}
