package ex.wookis.mvc2.repository.jdbc;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
public class JdbcMemberRepositoryV2 implements MemberRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcMemberRepositoryV2(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Member save(Member member) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);
        Number key = jdbcInsert.executeAndReturnKey(param);
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "selelct * from member where id = :id";

        try {
            Map<String, Object> param = Map.of("id", id);
            Member findMember = template.queryForObject(sql, param, memberRowMapper());
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
        return template.query(sql, memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class);   //camel 지원
    }
}
