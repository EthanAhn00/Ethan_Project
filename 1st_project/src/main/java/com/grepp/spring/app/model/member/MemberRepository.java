package com.grepp.spring.app.model.member;

import com.grepp.spring.app.model.member.dto.Member;
import com.grepp.spring.app.model.member.dto.MemberInfo;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    Optional<Member> selectById(String userId);
    
    @Select("select count(*) from member where user_id = #{userId}")
    Boolean existsMember(String userId);
    
    @Insert("insert into member (USER_ID, PASSWORD, EMAIL, TEL, ROLE) "
                + "values(#{userId}, #{password}, #{email}, #{tel}, #{role})")
    void insert(Member dto);
    
    @Insert("insert into member_info(USER_ID) values (#{userId})")
    void insertInfo(MemberInfo memberInfo);
}
