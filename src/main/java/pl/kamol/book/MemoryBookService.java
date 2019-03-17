package pl.kamol.book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemoryBookService {
    private List<Book> bookList;

    public MemoryBookService() {
        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        bookList.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        bookList.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

//    public Book getBook(Long id) {
//        return bookList.stream()
//                .filter(b -> b.getId() == id)
//                .findFirst().get();
//        }


    public ResponseEntity<Book> getBook(Long id) {
        Optional<Book> optionalBook = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.notFound()
                    .build();
        }
    }

    public Book updateBook(long id, Book book) {
        Book bookToBeUpdated = bookList.stream()
                .filter(b -> b.getId() == id)
                .findFirst().get();
        bookToBeUpdated.setIsbn(book.getIsbn());
        bookToBeUpdated.setTitle(book.getTitle());
        bookToBeUpdated.setAuthor(book.getAuthor());
        bookToBeUpdated.setPublisher(book.getPublisher());
        bookToBeUpdated.setType(book.getType());
        return bookToBeUpdated;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book addBook(Book book) {
        Book max = Collections.max(bookList, Comparator.comparingLong(Book::getId));
        book.setId(max.getId()+1L);
        bookList.add(book);
        return book;
    }

    public ResponseEntity<Book> delateBook(Long bookId) {
        Optional<Book> bookToBeDeleted = bookList.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst();
        if(bookToBeDeleted.isPresent()){
            bookList.remove(bookToBeDeleted.get());
            return ResponseEntity.noContent().build();
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }


}
