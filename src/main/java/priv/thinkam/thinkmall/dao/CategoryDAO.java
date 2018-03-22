package priv.thinkam.thinkmall.dao;

import priv.thinkam.thinkmall.entity.Category;

/**
 * @author thinkam
 * @date 2018/03/21
 */
public interface CategoryDAO {
    /**
     * 保存类别
     * @param category 类别
     * @return 影响的行数
     */
    int save(Category category);
}
