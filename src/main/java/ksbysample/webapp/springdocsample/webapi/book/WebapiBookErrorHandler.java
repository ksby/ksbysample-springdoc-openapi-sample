package ksbysample.webapp.springdocsample.webapi.book;

import ksbysample.webapp.springdocsample.exception.BookInvalidException;
import ksbysample.webapp.springdocsample.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * WebAPI 用 Error Handler クラス
 */
@RestControllerAdvice
public class WebapiBookErrorHandler {

    /**
     * Book データが見つからなかった時の Error Handler
     * HTTPステータスコード = 404(Not Found) とエラーメッセージを返す
     *
     * @param e {@link BookNotFoundException} object
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Book データの入力チェックでエラーが発生した時の Error Handler
     * HTTPステータスコード = 405(Method Not Allowed) とエラーメッセージを返す
     *
     * @param e {@link BookInvalidException} object
     * @return {@link ResponseEntity} object
     */
    @ExceptionHandler(BookInvalidException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<String> handleBookInvalid(BookInvalidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
