package priv.thinkam.thinkmall.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.entity.Category;
import priv.thinkam.thinkmall.enums.CategoryLevelEnum;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author thinkam
 * @date 2018/03/22
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext.xml"
})
@Transactional(transactionManager = "transactionManager")
public class CategoryDAOTest {
    @Resource
    private CategoryDAO categoryDAO;

    @Test
    public void testSave() {
        Date now = new Date();
        Category category = new Category();
        category.setName("c1");
        category.setDescription("c1d1");
        category.setParentId((long) 0);
        category.setLeaf(true);
        category.setLevel(CategoryLevelEnum.THREE);
        category.setDeleted(false);
        category.setCreateTime(now);
        category.setModifiedTime(now);
        int affectRow = categoryDAO.save(category);
        assertEquals(1, affectRow);
    }
}