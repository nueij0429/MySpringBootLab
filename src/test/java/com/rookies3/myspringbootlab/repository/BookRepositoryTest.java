package com.rookies3.myspringbootlab.repository;

import com.rookies3.myspringbootlab.entity.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Rollback(value = false)
    @Disabled
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("스프링부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        String dateStr = "2025-05-07";
        book.setPublishDate(LocalDate.parse(dateStr));

        Book addbook = bookRepository.save(book);
        assertThat(addbook).isNotNull();
        assertThat(addbook.getTitle()).isEqualTo("스프링부트 입문");
        assertThat(addbook.getAuthor()).isEqualTo("홍길동");
    }

    @Test
    @Rollback(value = false)
    @Disabled
    void testCreateBook2() {
        Book book = new Book();
        book.setTitle("JPA 프로그래밍");
        book.setAuthor("박둘리");
        book.setIsbn("9788956746432");
        book.setPrice(35000);
        String dateStr = "2025-04-30";
        book.setPublishDate(LocalDate.parse(dateStr));

        Book addbook = bookRepository.save(book);
        assertThat(addbook).isNotNull();
        assertThat(addbook.getTitle()).isEqualTo("JPA 프로그래밍");
        assertThat(addbook.getAuthor()).isEqualTo("박둘리");
    }

    @Test
    @Rollback(value = false)
    @Disabled
    void testCreateBook3() {
        Book book = new Book();
        book.setTitle("고양이와 사이좋게 지내는 법");
        book.setAuthor("최지은");
        book.setIsbn("111111111111");
        book.setPrice(90000);
        String dateStr = "2025-05-07";
        book.setPublishDate(LocalDate.parse(dateStr));

        Book addbook = bookRepository.save(book);
        assertThat(addbook).isNotNull();
        assertThat(addbook.getTitle()).isEqualTo("고양이와 사이좋게 지내는 법");
        assertThat(addbook.getAuthor()).isEqualTo("최지은");
    }

    @Test
    @Disabled
    void testFindByIsbn() {
        // 1. ID 기준으로 조회
        Optional<Book> optionalBook = bookRepository.findById(1L);
        if (optionalBook.isPresent()) {
            Book existBook = optionalBook.get();
            assertThat(existBook.getId()).isEqualTo(1L);
        }

        // 2. ISBN이 "9788956746425"인 책 찾기
        Optional<Book> optionalBook2 = bookRepository.findByIsbn("9788956746425");
        Book Book1 = optionalBook2.orElseGet(() -> new Book());
        assertThat(Book1.getTitle()).isEqualTo("스프링 부트 입문");

        // 3. ISBN이 "1111111111111"인 책은 없으므로 새 Book() 반환됨
        Book notFoundBook = bookRepository.findByIsbn("1111111111111").orElseGet(() -> new Book());
        assertThat(notFoundBook.getTitle()).isNull();
    }

    @Test
    @Disabled
    void testFindByAuthor() {
        // 1. 존재하는 Author으로 조회
        List<Book> booksByAuthor = bookRepository.findByAuthor("홍길동");
        assertThat(booksByAuthor).isNotEmpty();

        // 2. 첫 번째 책의 Author가 홍길동인지 확인
        Book firstBook = booksByAuthor.get(0);
        assertThat(firstBook.getAuthor()).isEqualTo("홍길동");

        // 3. 존재하지 않는 Author 이름으로 조회
        List<Book> unknownAuthorBooks = bookRepository.findByAuthor("최지은");
        assertThat(unknownAuthorBooks).isEmpty();
    }

    @Test
    @Rollback(value = false)
    @Disabled
    void testUpdateBook() {
        Book book = bookRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        book.setTitle("스프링 부트 입문");
        assertThat(book.getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    @Rollback(value = false)
    void testDeleteBook() {
        Book book = bookRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        bookRepository.delete(book);
    }
}