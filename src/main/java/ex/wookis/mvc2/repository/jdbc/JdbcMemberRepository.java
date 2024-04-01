package ex.wookis.mvc2.repository.jdbc;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

//@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate template;

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
    public Optional<Member> findById(Long id) {
        String sql = "selelct * from member where id = ?";
        try {
            Member findMember = template.queryForObject(sql, memberRowMapper(), id);
            return Optional.of(findMember);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
