package com.fengling.demo.service;

import com.fengling.demo.domain.ResultBase;
import com.fengling.demo.domain.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * @author wangbaotong@u51.com
 * @create 2017-09-24
 */
public interface UserService {

    public ResultBase<UserDomain> getUserById(Integer userId);

    public ResultBase<PageInfo<UserDomain>> getUsersByUserName(String userName, Integer pageIndex, Integer pageSize);

    public ResultBase<String> saveUser(UserDomain user);

    public ResultBase<String> deleteUserById(Integer userId);

    public ResultBase<String> updateUser(Integer userId, UserDomain user);
}
