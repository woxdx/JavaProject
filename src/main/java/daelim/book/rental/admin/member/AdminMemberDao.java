package daelim.book.rental.admin.member;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminMemberDao {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public AdminMemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insertAdminAccount(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberDao] insertAdminAccount()");
        List<String> args = new ArrayList<>();

        String sql = "INSERT INTO TB_ADMIN_ACCOUNT (";
        if(adminMemberVo.getId().equals("system")) {
            sql += "approval, ";
            args.add("1");
        }
        sql += "id, ";
        args.add(adminMemberVo.getId());

        sql += "password, ";
        args.add(bCryptPasswordEncoder.encode(adminMemberVo.getPassword()));

//        args.add(adminMemberVo.getPassword());
        sql += "name, ";
        args.add(adminMemberVo.getName());

        sql += "gender, ";
        args.add(adminMemberVo.getGender());

        sql += "part, ";
        args.add(adminMemberVo.getPart());

        sql += "position, ";
        args.add(adminMemberVo.getPosition());

        sql += "email, ";
        args.add(adminMemberVo.getEmail());

        sql += "phone, ";
        args.add(adminMemberVo.getPhone());

        sql += "regDate, modDate) ";

        if(adminMemberVo.getId().equals("system")) {
            sql += "VALUES (?,?,?,?,?,?,?,?,?,NOW(), NOW())";
        } else {
            sql += "VALUES (?,?,?,?,?,?,?,?,NOW(), NOW())";
        }

        int result = -1;
        try {
            result = jdbcTemplate.update(sql, args.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
    public boolean existAdminAccount(String id) {
        System.out.println("[AdminMemberDao.exsitAdminAccount]");
        String sql = "SELECT COUNT(*) FROM TB_ADMIN_ACCOUNT WHERE id = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        if(result > 0) return true;
        else return false;
    }

    public AdminMemberVo selectAdmin(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberDao.selectAdmin]");
        String sql = "SELECT * FROM TB_ADMIN_ACCOUNT WHERE id = ? AND approval > 0";
        List<AdminMemberVo> adminMemberVoList = new ArrayList<>();
        try {
            adminMemberVoList = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {
                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminMemberVo adminMemberVo1 = new AdminMemberVo();
                    adminMemberVo1.setNo(rs.getInt("no"));
                    adminMemberVo1.setId(rs.getString("id"));
                    adminMemberVo1.setName(rs.getString("name"));
                    adminMemberVo1.setPassword(rs.getString("password"));
                    adminMemberVo1.setGender(rs.getString("gender"));
                    adminMemberVo1.setPart(rs.getString("part"));
                    adminMemberVo1.setPosition(rs.getString("position"));
                    adminMemberVo1.setEmail(rs.getString("email"));
                    adminMemberVo1.setPhone(rs.getString("phone"));
                    adminMemberVo1.setRegDate(rs.getString("regDate"));
                    adminMemberVo1.setModDate(rs.getString("modDate"));
                    return adminMemberVo1;
                }
            }, adminMemberVo.getId());

            if(!bCryptPasswordEncoder.matches(adminMemberVo.getPassword(), adminMemberVoList.get(0).getPassword())) {
                adminMemberVoList.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminMemberVoList.size() > 0 ? adminMemberVoList.get(0) : null;
    }

    public List<AdminMemberVo> selectAllAdmin() {
        System.out.println("AdminMemberDao] selectAllAdmin()");
        String sql = "SELECT * FROM TB_ADMIN_ACCOUNT";

        List<AdminMemberVo> adminMemberVoList = new ArrayList<>();
        try {
            adminMemberVoList = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {

                @Override
                public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminMemberVo adminMemberVo1 = new AdminMemberVo();
                    adminMemberVo1.setNo(rs.getInt("no"));
                    adminMemberVo1.setId(rs.getString("id"));
                    adminMemberVo1.setName(rs.getString("name"));
                    adminMemberVo1.setPassword(rs.getString("password"));
                    adminMemberVo1.setGender(rs.getString("gender"));
                    adminMemberVo1.setPart(rs.getString("part"));
                    adminMemberVo1.setPosition(rs.getString("position"));
                    adminMemberVo1.setEmail(rs.getString("email"));
                    adminMemberVo1.setPhone(rs.getString("phone"));
                    adminMemberVo1.setRegDate(rs.getString("regDate"));
                    adminMemberVo1.setModDate(rs.getString("modDate"));
                    adminMemberVo1.setApproval(rs.getInt("approval"));
                    return adminMemberVo1;
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        return adminMemberVoList;
    }

    public int updateAdminAccount(int no) {
        System.out.println("[AdminMemberDao.updateAdminAccount]");
        String sql = "UPDATE TB_ADMIN_ACCOUNT SET approval = 1 WHERE no = ?";
        int result = -1;
        try {

            result = jdbcTemplate.update(sql, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
