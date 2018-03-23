package priv.thinkam.thinkmall.service;

import priv.thinkam.thinkmall.common.base.BaseService;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;

import java.util.List;

/**
 * AdministratorService接口
 *
 * @author thinkam
 * @date 18-03-22
 */
public interface AdministratorService extends BaseService<Administrator, AdministratorExample> {

    /**
     * 根据用户名获取管理员集合
     * @param username 用户名
     * @return 管理员集合
     */
    List<Administrator> listByUsername(String username);
}