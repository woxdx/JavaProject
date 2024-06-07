package daelim.book.rental.admin.book;

import daelim.book.rental.BookVo;
import daelim.book.rental.admin.util.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/book/admin")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    private UploadFileService upLoadFileService;

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

        String saveFileName = upLoadFileService.upload(file);
        if(saveFileName != null){
            bookVo.setThumbnail(saveFileName);
            int result = bookService.registerBook(bookVo);
            if (result<= 0) nextPage = "admin/book/register_book_ng";
        } else {
            nextPage = "admin/book/register_book_ng";
        }
        return nextPage;
    }

}
