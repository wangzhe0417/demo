package com.fengling.demo.dao;

import com.fengling.demo.domain.UserDomain;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wangbaotong@u51.com
 * @create 2017-09-24
 */
@Repository
public interface UserDao extends Mapper<UserDomain> {
}
