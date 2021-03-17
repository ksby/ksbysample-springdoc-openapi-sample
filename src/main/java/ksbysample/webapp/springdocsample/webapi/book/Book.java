package ksbysample.webapp.springdocsample.webapi.book;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Book データクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Book {

    /**
     * ISBN
     */
    @NotBlank
    private String isbn;

    /**
     * 書籍名
     */
    @NotBlank
    private String name;

    /**
     * 価格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 発売日
     */
    private LocalDate releaseDate;

}
