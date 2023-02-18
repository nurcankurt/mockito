import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
	void save(Book book);
	Book findBookById(int bookId);
	Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital);
	void saveAll(List<Book> books);
	List<Book> findAllBooks();
}