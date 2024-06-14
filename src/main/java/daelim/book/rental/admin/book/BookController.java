package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import daelim.book.rental.admin.util.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/book/admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("/registerBookForm")
    public String registerBookForm() {
        System.out.println("[BookController] registerBookForm()");
        String nextPage = "admin/book/register_book_form";
        return nextPage;
    }

    @PostMapping("/registerBookConfirm")
    public String registerBookConfirm(BookVo bookVo, MultipartFile file) {
        System.out.println("[BookController] registerBookConfirm()");
        String nextPage = "admin/book/register_book_ok";

        String saveFileName = uploadFileService.upload(file);
        if (saveFileName != null) {
            bookVo.setThumbnail(saveFileName);
            int result = bookService.register(bookVo);
            if (result <= 0) nextPage = "admin/book/register_book_ng";
            System.out.println(result);
        } else {
            nextPage = "admin/book/register_book_ng";
        }

        return nextPage;
    }

    @GetMapping("/searchBookConfirm")
    public String searchBookConfirm(BookVo bookVo, Model model) {
        System.out.println("[BookController] searchBookConfirm()");
        String nextPage = "admin/book/search_book";

        List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);

        model.addAttribute("bookVos", bookVos);

        return nextPage;
    }
    @GetMapping("/bookDetail")
    public String bookDetail(@RequestParam("no") int no, Model model) {
        System.out.println("[BookController] bookDetail()");

        String nextPage = "admin/book/book_detail";
        BookVo bookVo = bookService.bookDetail(no);
        model.addAttribute("bookVo", bookVo);
        return nextPage;
    }
    @GetMapping("/modifyBookForm")
    public String modifyBookForm(@RequestParam("no") int no, Model model) {
        System.out.println("[BookController] bookDetail()");
        String nextPage = "admin/book/modify_book_form";
        BookVo bookVo = bookService.modifyBookForm(no);
        model.addAttribute("bookVo", bookVo);
        return nextPage;
    }
    @PostMapping("/modifyBookConfirm")
    public String modifyBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
        System.out.println("[BookController] modifyBookConfirm()");
        String nextPage = "admin/book/modify_book_ok";
        if(!file.getOriginalFilename().equals("")) {
            String savedFileName = uploadFileService.upload(file);
            if(savedFileName != null)
                bookVo.setThumbnail(savedFileName);
        }

        int result = bookService.modifyBookConfirm(bookVo);

        if(result <= 0)
            nextPage = "admin/book/modify_book_ng";

        return nextPage;
    }
    @GetMapping("/deleteBookConfirm")
    public String deleteBookConfirm(@RequestParam("no") int no) {
        System.out.println("[BookController] deleteBookConfirm()");

        String nextPage = "admin/book/delete_book_ok";

        int result = bookService.deleteBookConfirm(no);

        if(result <= 0)
            nextPage = "admin/book/delete_book_ng";

        return nextPage;
    }
    @GetMapping("/getAllBooks")
    public String getAllBooks(BookVo bookVo, Model model) {
        System.out.println("[BookController] getAllBooks()");
        String nextPage = "admin/book/full_list_of_books";

        List<BookVo> bookVos = bookService.allListBook(bookVo);

        model.addAttribute("bookVos", bookVos);

        return nextPage;
    }
}
