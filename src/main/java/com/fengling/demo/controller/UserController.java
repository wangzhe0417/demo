package com.fengling.demo.controller;

import com.fengling.demo.domain.ResultBase;
import com.fengling.demo.domain.UserDomain;
import com.fengling.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangbaotong@u51.com
 * @create 2017-09-24
 */
@RequestMapping("/demo/api/v1")
@RestController
@Api(description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "根据用户ID获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResultBase<UserDomain> getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @ApiOperation(value = "根据用户名获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "页号", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResultBase<PageInfo<UserDomain>> getUsersByUserName(@RequestParam String userName,
                                                               @RequestParam Integer pageIndex,
                                                               @RequestParam Integer pageSize) {
        return userService.getUsersByUserName(userName, pageIndex, pageSize);
    }

    @ApiOperation(value = "新建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "UserDomain")
    })
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResultBase<String> saveUser(@RequestBody UserDomain user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResultBase<String> deleteUserById(@PathVariable Integer userId) {
        return userService.deleteUserById(userId);
    }

    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "UserDomain")
    })
    @RequestMapping(value = "user/{userId}", method = RequestMethod.PUT)
    public ResultBase<String> updateUser(@PathVariable Integer userId,
                                          @RequestBody UserDomain user) {
        return userService.updateUser(userId, user);
    }
}
