package ex.wookis.mvc2.repository.mybatis;

import ex.wookis.mvc2.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Member findById(Long id);

    Optional<Member> findByUserLoginId(String userLoginId);

    List<Member> findAll();
}
