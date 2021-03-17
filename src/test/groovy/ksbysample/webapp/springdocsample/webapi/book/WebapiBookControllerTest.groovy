package ksbysample.webapp.springdocsample.webapi.book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class WebapiBookControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    def "未登録のBookデータを登録すると検索でヒットする"() {
        given:
        def isbn = "978-4798142470"
        String bookJson = """
                {
                    "isbn": "${isbn}",
                    "name": "Spring徹底入門 Spring FrameworkによるJavaアプリケーション開発",
                    "price": 4400,
                    "releaseDate": "2016-07-21" 
                }
            """.stripIndent()

        when: "未登録の ISBN を渡すと 404(Not Found) が返る"
        mockMvc.perform(get("/webapi/book/${isbn}"))
                .andExpect(status().isNotFound())

        then: "Book データを登録する"
        mockMvc.perform(post("/webapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isOk())

        then: "登録した Book データの ISBN を渡すと 200(OK) が返る"
        mockMvc.perform(get("/webapi/book/${isbn}"))
                .andExpect(status().isOk())
                .andExpect(json().isEqualTo(bookJson))
    }

    def "登録されているISBNを渡すとBookデータのJSONが返る"() {
        setup:
        String expectedBookJson = """
                {
                    "isbn": "978-1492076988",
                    "name": "Spring Boot: Up and Running",
                    "price": 7195,
                    "releaseDate": "2021-03-23" 
                }
            """.stripIndent()

        expect:
        mockMvc.perform(get("/webapi/book/978-1492076988"))
                .andExpect(status().isOk())
                .andExpect(json().isEqualTo(expectedBookJson))
    }

}
