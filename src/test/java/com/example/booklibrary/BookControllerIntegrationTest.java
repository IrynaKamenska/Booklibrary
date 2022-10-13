package com.example.booklibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLOutput;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DirtiesContext
    void getBooks() throws Exception {
        String body = """
                [
                    {
                        "bookCovers": "SOFT_COVER",
                        "isbn": "0345391801",
                        "title": "Java",
                        "author": "Ullenbom"
                    },
                    {
                        "bookCovers": "E_BOOK",
                        "isbn": "0345391802",
                        "title": "Java-Skript",
                        "author": "Mueller"
                    },
                    {
                        "bookCovers": "HARD_COVER",
                        "isbn": "0345391803",
                        "title": "Java",
                        "author": "Ullenbom"
                    }
                ]
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(body));

    }


    @Test
    @DirtiesContext
    void addBook() throws Exception {
        String newBook = """
                    {
                        "bookCovers": "HARD_COVER",
                        "isbn": "0345391803",
                        "title": "CSS",
                        "author": "Tom"
                    }
                """;
        // MvcResult result statt content
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/books/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newBook))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);

// konvertieren in list - string in java object
        Book book = objectMapper.readValue(content, Book.class);
//        System.out.println(list.get);

        //when
        String expected = "[\n" +
                "    {\n" +
                "        \"bookCovers\": \"SOFT_COVER\",\n" +
                "        \"isbn\": \"0345391801\",\n" +
                "        \"title\": \"Java\",\n" +
                "        \"author\": \"Ullenbom\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCovers\": \"E_BOOK\",\n" +
                "        \"isbn\": \"0345391802\",\n" +
                "        \"title\": \"Java-Skript\",\n" +
                "        \"author\": \"Mueller\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"bookCovers\": \"HARD_COVER\",\n" +
                "        \"isbn\": \"0345391803\",\n" +
                "        \"title\": \"Java\",\n" +
                "        \"author\": \"Ullenbom\"\n" +
                "    },\n" +
                "                        {\n" +
                "                        \"bookCovers\": \"HARD_COVER\",\n" +
                "                        \"isbn\": \"0345391803\",\n" +
                "                        \"title\": \"CSS\",\n" +
                "                        \"author\": \"Tom\"\n" +
                "                    }\n" +
                "]";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))

                // then
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

//String expected = """
//        [{"id": "<ID>", "pductList": []}]}]
//        """.replace("<ID>", order.getId());


    }


}