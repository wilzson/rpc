package com.example.example;

import com.example.rpc.springboot.starter.annotation.RpcService;
import frpc.model.User;
import frpc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
