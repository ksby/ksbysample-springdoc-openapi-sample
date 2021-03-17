package ksbysample.webapp.springdocsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springdoc-openapi サンプル Web アプリ用 Application クラス
 */
@SpringBootApplication
public class SpringdocSampleApplication {

    /**
     * メインメソッド
     *
     * @param args 起動時オプション
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringdocSampleApplication.class, args);
    }

}
