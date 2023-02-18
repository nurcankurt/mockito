
import java.time.LocalDate;
import java.util.List;

public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	public void addBook(Book book) {
		if(book.getPrice() <= 0){
			 throw new ArithmeticException("Price can not be negative value");
		}
		bookRepository.save(book);
	}

	public void updatePrice(int bookId, int updatedPrice){
		Book book = bookRepository.findBookById(bookId);
		book.setPrice(updatedPrice);
		bookRepository.save(book);
	}

	public Book getBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital) {
		return bookRepository.findBookByTitleAndPriceAndIsDigital(title, price, isDigital);
	}

	public void addBooks(List<Book> books) {
		bookRepository.saveAll(books);
	}
	public int getTotalPriceOfBooks() {
		List<Book> books = bookRepository.findAllBooks();

		int totalPrice = 0;
		for(Book book : books){
			totalPrice = totalPrice + book.getPrice();
		}
		return totalPrice;
	}

}