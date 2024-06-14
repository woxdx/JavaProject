package daelim.book.rental.user;

import daelim.book.rental.user.member.UserMemberService;
import daelim.book.rental.user.member.UserMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired
    private UserMemberService userMemberService;

    @PostMapping("/createAccountConfirm")
    public String createAccountConfirm(UserMemberVo userMemberVo){

        System.out.println("[UserMemberController] createAccountConfirm()");
        String nextPage = "/user/member/create_account_ok";
        int result = userMemberService.createAccount(userMemberVo);
        if(result <= 0){
            nextPage = "/admin/member/create_account_ng";
        }
        return nextPage;
    }

    @GetMapping({"", "/"})
    public String home() {
        System.out.println("[UserHomeController] home()");

        String nextPage = "user/home";

        return nextPage;

    }
}
