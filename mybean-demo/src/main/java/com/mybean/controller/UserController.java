package com.mybean.controller;

import com.mybean.domain.User;
import com.mybean.mapper.UserMapper;
import org.mybeanframework.core.annotation.MyBean;
import org.mybeanframework.core.annotation.SetBean;
import org.mybeanframework.web.mvc.annotation.RequestParam;
import org.mybeanframework.web.mvc.annotation.RequestPath;
import org.mybeanframework.web.mvc.response.enums.ResponseTypeEnum;

import java.util.*;

/**
 * @author herenpeng
 * @since 2020-12-02 16:06
 */
@MyBean
@RequestPath("user")
public class UserController {

    @SetBean
    private UserMapper userMapper;

    @RequestPath("index")
    public String index() {
        return "index.html";
    }

    @RequestPath(value = "list", type = ResponseTypeEnum.JSON)
    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    @RequestPath(value = "logic/delete", type = ResponseTypeEnum.JSON)
    public void logicDelete(@RequestParam("id") Integer id) {
        userMapper.logicDelete(id);
    }

    @RequestPath(value = "logic/recover", type = ResponseTypeEnum.JSON)
    public void logicRecover(@RequestParam("id") Integer id) {
        userMapper.logicRecover(id);
    }

    @RequestPath(value = "delete", type = ResponseTypeEnum.JSON)
    public void delete(@RequestParam("id") Integer id) {
        userMapper.delete(id);
    }


}
