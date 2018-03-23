package com.parfait.study.simpleevent.mapper;

import com.parfait.study.simpleevent.model.member.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member(name, email, phone_no) VALUES (#{name}, #{email}, #{phoneNo})")
    @SelectKey(statement = "CALL SCOPE_IDENTITY()", resultType = Long.class, keyProperty = "id", before = false)
    int insert(Member member);

    @Select("SELECT count(1) FROM member")
    long count();
}
