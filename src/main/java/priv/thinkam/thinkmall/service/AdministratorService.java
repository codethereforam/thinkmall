package priv.thinkam.thinkmall.service;

import priv.thinkam.thinkmall.entity.Administrator;

import java.util.List;

/**
 * @author thinkam
 * @date 2018/03/19
 */
public interface AdministratorService {
    /**
     * 根据用户名获取管理员集合
     * @param username 用户名
     * @return 管理员集合
     */
    List<Administrator> listByUsername(String username);
}
