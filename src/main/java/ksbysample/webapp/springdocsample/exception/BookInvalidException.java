package ksbysample.webapp.springdocsample.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.lang.Nullable;

/**
 * Book データの Validation エラー時の RuntimeException 継承クラス
 */
public class BookInvalidException extends NestedRuntimeException {

    private static final long serialVersionUID = -5681521994807493974L;

    /**
     * コンストラクタ
     *
     * @param msg エラーメッセージ
     */
    public BookInvalidException(String msg) {
        super(msg);
    }

    /**
     * コンストラクタ
     *
     * @param msg   エラーメッセージ
     * @param cause 発生元の {@link Throwable} object
     */
    public BookInvalidException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

}
