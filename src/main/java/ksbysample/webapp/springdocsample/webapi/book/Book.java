package ksbysample.webapp.springdocsample.webapi.book;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Book データ")
public class Book {

    /**
     * ISBN
     */
    @NotBlank
    @Schema(type = "string", required = true, description = "ISBN-13", example = "123-1234567890")
    private String isbn;

    /**
     * 書籍名
     */
    @NotBlank
    @Schema(type = "string", required = true, description = "書籍名",
            example = "サンプル本")
    private String name;

    /**
     * 価格
     */
    @NotNull
    @Schema(type = "number", required = true, description = "価格", example = "3600")
    private BigDecimal price;

    /**
     * 発売日
     */
    @Schema(type = "string", format="date", required = true, description = "発売日", example = "2021-03-24")
    private LocalDate releaseDate;

}
