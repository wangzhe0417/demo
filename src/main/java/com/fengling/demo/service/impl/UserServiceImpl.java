package com.fengling.demo.service.impl;

import com.fengling.demo.dao.UserDao;
import com.fengling.demo.domain.ResultBase;
import com.fengling.demo.domain.UserDomain;
import com.fengling.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author wangbaotong@u51.com
 * @create 2017-09-24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    @Override
    public ResultBase<UserDomain> getUserById(Integer userId) {
        UserDomain user = null;
        try {
            user = userDao.selectByPrimaryKey(userId);
            Preconditions.checkNotNull(user, "不存在对应用户！");
        } catch (Exception e) {
            return ResultBase.getErrorResult(e.getLocalizedMessage());
        }
        return ResultBase.getSuccessResult(user);
    }

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public ResultBase<PageInfo<UserDomain>> getUsersByUserName(String userName, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        } else if (pageSize > 100) {
            pageSize = 100;
        }
        PageHelper.startPage(pageIndex, pageSize);
        PageInfo<UserDomain> result = null;

        Example example = new Example(UserDomain.class);
        if (StringUtils.isNotBlank(userName)){
            example.createCriteria().andLike("userName", "%" + userName + "%");
        }
        List<UserDomain> users = userDao.selectByExample(example);

        result = new PageInfo<>(users);
        return ResultBase.getSuccessResult(result);
    }

    @Override
    public ResultBase<String> saveUser(UserDomain user) {
        Example example = new Example(UserDomain.class);
        example.createCriteria().andEqualTo("userName", user.getUserName());
        List<UserDomain> userInDb = userDao.selectByExample(example);
        if (userInDb == null || userInDb.size() == 0) {
            Integer userId = userDao.insert(user);
            return ResultBase.getSuccessResult(user.getUserName());
        } else {
            return ResultBase.getErrorResult("用户名已存在！");
        }
    }

    @Override
    public ResultBase<String> deleteUserById(Integer userId) {
        UserDomain user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultBase.getSuccessResult("用户已被删除！");
        }
        userDao.deleteByPrimaryKey(userId);

        return ResultBase.getSuccessResult(user.getUserName());
    }

    @Override
    public ResultBase<String> updateUser(Integer userId, UserDomain user) {
        UserDomain userInDb = userDao.selectByPrimaryKey(userId);
        if (userInDb == null) {
            return ResultBase.getErrorResult("用户不存在！");
        }
        Example example = new Example(UserDomain.class);
        example.createCriteria().andEqualTo("userName", user.getUserName()).andNotEqualTo("userId", userId);
        List<UserDomain> users = userDao.selectByExample(example);
        if (users == null || users.size() == 0) {
            user.setUserId(userId);
            userDao.updateByPrimaryKey(user);
            return ResultBase.getSuccessResult(user.getUserName());
        }
        return ResultBase.getErrorResult("已存在的用户名！");
    }
}
