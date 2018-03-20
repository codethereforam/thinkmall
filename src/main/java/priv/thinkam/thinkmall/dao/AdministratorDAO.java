package priv.thinkam.thinkmall.dao;

import priv.thinkam.thinkmall.entity.Administrator;

import java.util.List;

/**
 * @author thinkam
 * @date 2018/03/19
 */
public interface AdministratorDAO {
    /**
     * 根据用户名获取管理员集合
     *
     * @param username 用户名
     * @return 管理员集合
     */
    List<Administrator> listByUsername(String username);

    /**
     * 保存管理员
     * @param administrator 管理员
     * @return 影响的行数
     */
    int save(Administrator administrator);

    /**
     * 删除所有数据
     * @return 影响的行数
     */
    int removeAll();
}
