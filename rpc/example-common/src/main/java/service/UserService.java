package service;


import frpc.model.User;

/**
 * 用户服务
 *
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 新方法
     */
    default short getNumber() {
        return 1;
    }

    default User mockUser() {
        return null;
    }
}
