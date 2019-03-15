package pl.kamol.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    MemoryBookService memoryBookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return memoryBookService.getBookList();
    }


    @RequestMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Long bookId) {
        ResponseEntity<Book> book = memoryBookService.getBook(bookId);
        return book;

    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable long bookId,
                           @RequestBody Book book) {
        Book updatedBook = memoryBookService.updateBook(bookId, book);
        return updatedBook;
    }

    @DeleteMapping("/{bookId}")
    public Book deleteBook(@PathVariable long bookId){
        Book deletedBook = memoryBookService.delateBook(bookId);
        return  deletedBook;
    }

}
