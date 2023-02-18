import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositorySpy implements BookRepository{

    ArrayList<Book> bookList = new ArrayList<>();
    @Override
    public void save(Book book) {
        bookList.add(book);
    }

    @Override
    public Book findBookById(int bookId) {
        return null;
    }

    @Override
    public Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital) {
        return null;
    }

    @Override
    public void saveAll(List<Book> books) {

    }

    @Override
    public List<Book> findAllBooks() {
        return bookList;
    }
}


