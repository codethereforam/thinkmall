package priv.thinkam.thinkmall.service;

import priv.thinkam.thinkmall.common.base.BaseService;
import priv.thinkam.thinkmall.common.exception.UsernamePasswordNotMatchException;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;

/**
 * AdministratorService接口
 *
 * @author thinkam
 * @date 18-03-22
 */
public interface AdministratorService extends BaseService<Administrator, AdministratorExample> {

    /**
     * 验证用户名密码
     *
     * @param username 用户名
     * @param password 密码
     * @return 符合条件的用户
     * @throws UsernamePasswordNotMatchException 如果用户名和密码不匹配
     */
    Administrator validate(String username, String password) throws UsernamePasswordNotMatchException;
}