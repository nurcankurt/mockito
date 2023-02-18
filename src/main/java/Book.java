import java.time.LocalDate;
import java.util.Objects;

public class Book {
	private int bookId;
	private String title;

	private String author;
	private int price;
	private LocalDate publishedDate;//☺
	private boolean isDigital;

	public Book() {
	}

	public Book(int bookId, String title, String author, int price, LocalDate publishedDate) {
		this.bookId = bookId;
		this.title = title;
		this.author=author;
		this.price = price;
		this.publishedDate = publishedDate;
	}

	public Book(int bookId, String title, String author, int price, LocalDate publishedDate, boolean isDigital) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
		this.isDigital = isDigital;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public boolean isDigital() {
		return isDigital;
	}

	public void setDigital(boolean digital) {
		isDigital = digital;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return price == book.price &&
				Objects.equals(title, book.title) &&
				Objects.equals(publishedDate, book.publishedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, price, author,publishedDate);
	}
}