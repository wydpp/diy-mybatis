package com.wydpp.test;

import java.util.List;

/**
 * @Auther: duanpp
 * @Date: 2019/10/24 10:07
 * @Description:
 */
public interface UserMapper {

    List<User> selectAll(User user);
}