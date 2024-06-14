package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<BookVo> selectBooksBySearch(BookVo bookVo) {
        System.out.println("[BookDao] selectBooksBySearch()");
        String sql = "SELECT * FROM TB_BOOK "
                + "WHERE name LIKE ? "
                + "ORDER BY no DESC";

        List<BookVo> bookVos = null;

        try {
            bookVos = jdbcTemplate.query(sql,  new RowMapper<BookVo>() {
                @Override
                public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVo bookVo = new BookVo();

                    bookVo.setNo(rs.getInt("no"));
                    bookVo.setThumbnail(rs.getString("thumbnail"));
                    bookVo.setName(rs.getString("name"));
                    bookVo.setAuthor(rs.getString("author"));
                    bookVo.setPublisher(rs.getString("publisher"));
                    bookVo.setPublishYear(rs.getString("publishYear"));
                    bookVo.setIsbn(rs.getString("isbn"));
                    bookVo.setCallNumber(rs.getString("callNumber"));
                    bookVo.setRentalAble(rs.getInt("rentalAble"));
                    bookVo.setRegDate(rs.getString("regDate"));
                    bookVo.setModDate(rs.getString("modDate"));

                    return bookVo;
                }
            }, "%" + bookVo.getName() + "%");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return bookVos.size() > 0 ? bookVos : null;
    }
    public BookVo selectBook(int no) {
        System.out.println("[BookDao] selectBook()");

        String sql = "SELECT * FROM TB_BOOK WHERE no = ?";

        List<BookVo> bookVos = null;

        try {
            bookVos = jdbcTemplate.query(sql, new RowMapper<BookVo>() {
                @Override
                public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVo bookVo = new BookVo();

                    bookVo.setNo(rs.getInt("no"));
                    bookVo.setThumbnail(rs.getString("thumbnail"));
                    bookVo.setName(rs.getString("name"));
                    bookVo.setAuthor(rs.getString("author"));
                    bookVo.setPublisher(rs.getString("publisher"));
                    bookVo.setPublishYear(rs.getString("publishYear"));
                    bookVo.setIsbn(rs.getString("isbn"));
                    bookVo.setCallNumber(rs.getString("callNumber"));
                    bookVo.setRentalAble(rs.getInt("rentalAble"));
                    bookVo.setRegDate(rs.getString("regDate"));
                    bookVo.setModDate(rs.getString("modDate"));

                    return bookVo;
                }
            }, no);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return bookVos.size() > 0 ? bookVos.get(0) : null;
    }
    public int updateBook(BookVo bookVo) {
        System.out.println("[BookDao] updateBook()");

        String sql = "UPDATE TB_BOOK SET ";

        List<String> args = new ArrayList<String>();

        if(bookVo.getThumbnail() != null) {
            sql += "thumbnail = ?, ";
            args.add(bookVo.getThumbnail());
        }

        sql += "name = ?, ";
        args.add(bookVo.getName());

        sql += "author = ?, ";
        args.add(bookVo.getAuthor());

        sql += "publisher = ?, ";
        args.add(bookVo.getPublisher());

        sql += "publishYear = ?, ";
        args.add(bookVo.getPublishYear());

        sql += "isbn = ?, ";
        args.add(bookVo.getIsbn());

        sql += "callNumber = ?, ";
        args.add(bookVo.getCallNumber());

        sql += "rentalAble = ?, ";
        args.add(Integer.toString(bookVo.getRentalAble()));

        sql += "ModDate = NOW() ";

        sql += "WHERE no = ?";
        args.add(Integer.toString(bookVo.getNo()));

        int result = -1;

        try {
            result = jdbcTemplate.update(sql, args.toArray());
        }catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public int deleteBook(int no) {
        System.out.println("[BookDao] deleteBook()");

        String sql = "DELETE FROM TB_BOOK " + "WHERE no = ?";

        int result = -1;

        try {
            result = jdbcTemplate.update(sql,no);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<BookVo> searchAllBook(BookVo bookVo) {
        System.out.println("[BookDao] selectAllBooks()");
        String sql = "SELECT * FROM TB_BOOK ORDER BY no DESC";

        List<BookVo> bookVos = null;

        try {
            bookVos = jdbcTemplate.query(sql, new RowMapper<BookVo>() {
                @Override
                public BookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BookVo bookVo = new BookVo();

                    bookVo.setNo(rs.getInt("no"));
                    bookVo.setThumbnail(rs.getString("thumbnail"));
                    bookVo.setName(rs.getString("name"));
                    bookVo.setAuthor(rs.getString("author"));
                    bookVo.setPublisher(rs.getString("publisher"));
                    bookVo.setPublishYear(rs.getString("publishYear"));
                    bookVo.setIsbn(rs.getString("isbn"));
                    bookVo.setCallNumber(rs.getString("callNumber"));
                    bookVo.setRentalAble(rs.getInt("rentalAble"));
                    bookVo.setRegDate(rs.getString("regDate"));
                    bookVo.setModDate(rs.getString("modDate"));

                    return bookVo;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookVos != null && !bookVos.isEmpty() ? bookVos : null;
    }

}


