package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcMemberRepository implements MemberRepository{
    private final JdbcTemplate template;

    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(login_id, name, password) values(?,?,?);";
        //Execute Update
        template.update(sql, member.getLoginId(), member.getName(), member.getPassword());
        return member;
    }

    @Override
    public Member findById(Long id) {
        String sql = "selelct * from member where id = ?";
        return template.queryForObject(sql, memberRowMapper(), id);
    }


    @Override
    public Optional<Member> findByUserLoginId(String userId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return template.query(sql,memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("login_id"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
