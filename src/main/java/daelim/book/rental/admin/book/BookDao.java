package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.awt.print.Book;

@Component
public class BookDao {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isISBN(String isbn) {
        System.out.println("[BookDao] isbn : "+ isbn);
        String sql = "SELECT COUNT(*) FROM TB_BOOK WHERE ISBN = ? ";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        return result > 0 ? true : false;
    }
    public int insertBook(BookVo bookVo) {
        System.out.println("[BookDao] insertBook()");
        String sql = "INSERT INTO TB_BOOK (thumbnail, name, author, publisher, publishYear, isbn, callNumber, rentalAble, regDate, modDate) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        int result = -1;

        try {
            result = jdbcTemplate.update(sql,
                    bookVo.getThumbnail(), bookVo.getName(), bookVo.getAuthor(), bookVo.getPublisher(), bookVo.getPublishYear(),
                    bookVo.getIsbn(), bookVo.getCallNumber(), bookVo.getRentalAble());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
