package ksbysample.webapp.springdocsample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springdoc-openapi サンプル Web アプリ用 Application クラス
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ksbysample-springdoc-openapi-sample",   // (1)
        description = "Spring Boot + springdoc-openapi のサンプルアプリ",           // (2)
        version = "v1"))    // (3)
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
