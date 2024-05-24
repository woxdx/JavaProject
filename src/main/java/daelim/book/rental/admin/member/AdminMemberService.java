package daelim.book.rental.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMemberService {

    @Autowired
    private AdminMemberDao adminMemberDao;

    final static public int ADMIN_ACCOUNT_CREATE_SUCCES = 1;
    final static public int ADMIN_ACCOUNT_CREATE_EXISTS = 0;
    final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;

    public int createAccount(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberService] createAccount()");

        boolean isMember = adminMemberDao.existAdminAccount(adminMemberVo.getId());
        if(!isMember){
            // 신규 가입
            int result = adminMemberDao.insertAdminAccount(adminMemberVo);
            if(result > 0) {
                System.out.println("[AdminMemberService] createAccount SUCCESS!!");
                return ADMIN_ACCOUNT_CREATE_SUCCES;
            } else {
                System.out.println("[AdminMemberService] createAccount FAIL!!");
                return ADMIN_ACCOUNT_CREATE_FAIL;
            }
        }
        else {
            System.out.println("[AdminMemberService] createAccount exists!!");
            return ADMIN_ACCOUNT_CREATE_EXISTS;
        }
    }
    public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberService] loginConfirm");
        AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);

        if(loginedAdminMemberVo != null) {
            System.out.println("login success");
        } else {
            System.out.println("login fail");
        }
        return loginedAdminMemberVo;
    }
    public List<AdminMemberVo> selectAllAdmin() {
        System.out.println("[AdminMemberService] getAllAdminMembers");
        return adminMemberDao.selectAllAdmin();
    }
    public void setAdminApproval(int no) {
        System.out.println("[AdminMemberService] setAdminApproval " + no);
        int result = adminMemberDao.updateAdminAccount(no);
    }
}
