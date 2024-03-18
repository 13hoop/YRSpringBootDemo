package com.yongren.github.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yongren.github.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao extends BaseMapper<Member> {
}
