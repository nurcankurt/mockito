import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;  //this will be injected into bookservice



    @Test
    public void testAddBook() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        bookService.addBook(book);
        verify(bookRepository).save(book);
    }
    @Test
    public void testAddBookWitDigital() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"),true);
        bookService.addBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void testAddBooks() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        //Book book2 = new Book(null,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        bookService.addBook(book);
        bookService.addBook(book);
        verify(bookRepository,times(2)).save(book);
    }
    @Test
    public void testAddListOfBooks() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        Book book2 = new Book(12345,"Harry Potter and the Chamber of Secrets","J. K. Rowling",40,LocalDate.parse("1998-07-02"));
        books.add(book1);
        books.add(book2);
        bookService.addBooks(books);
        verify(bookRepository).saveAll(books);
    }
    @Test
    public void testSetTitleNull(){
        Book book =Mockito.mock(Book.class);
        doThrow(IllegalArgumentException.class).when(book).setTitle(null);
        assertThrows(IllegalArgumentException.class, () -> book.setTitle(null));
    }

    @Test
    public void testUpdatePrice() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        Book updatedBook = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",50,LocalDate.parse("1997-06-27"));
        when(bookRepository.findBookById(anyInt())).thenReturn(book);
        bookService.updatePrice(12345,50);
        verify(bookRepository).save(updatedBook);
    }
    @Test
    public void testNegativePrice() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",-35,LocalDate.parse("1997-06-27"));
        assertThrows(ArithmeticException.class, () -> {bookService.addBook(book);;});
        verify(bookRepository,never()).save(book);
    }
    @Test
    public void testNegativePrice2() {
        Book book = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",-35,LocalDate.parse("1997-06-27"));
        assertThrows(ArithmeticException.class, () -> {bookService.addBook(book);});
        verifyNoInteractions(bookRepository);
    }

    @Test
    public void testCountTotalPrice() {
        bookService.getTotalPriceOfBooks();
        verify(bookRepository).findAllBooks();
    }

    @Test
    public void testCountTotalPriceSpy() {
        BookRepositorySpy bookRepositorySpy= Mockito.spy(BookRepositorySpy.class);
        BookService bookService1 = new BookService(bookRepositorySpy);
        Book book1 = new Book(12345,"Harry Potter and the Philosopher's Stone","J. K. Rowling",35,LocalDate.parse("1997-06-27"));
        Book book2 = new Book(12345,"Harry Potter and the Chamber of Secrets","J. K. Rowling",40,LocalDate.parse("1998-07-02"));
        bookService1.addBook(book1);
        bookService1.addBook(book2);
        assertEquals(75,bookService1.getTotalPriceOfBooks());
    }


    @Test
    void getBookByTitleAndPriceAndIsDigital() {
        bookService.getBookByTitleAndPriceAndIsDigital(anyString(),anyInt(),anyBoolean());
        verify(bookRepository).findBookByTitleAndPriceAndIsDigital(anyString(),anyInt(),anyBoolean());
    }
}
