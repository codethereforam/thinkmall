package priv.thinkam.thinkmall.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.dao.entity.Category;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.dao.enums.CategoryLevelEnum;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

/**
 * @author thinkam
 * @date 2018/03/22
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext.xml",
        "classpath:applicationContext-listener.xml"
})
@Transactional(transactionManager = "transactionManager")
public class CategoryServiceTest {
    @Resource
    private CategoryService categoryService;

    private static List<Category> testCases = new ArrayList<>();
    private static CategoryLevelEnum level = CategoryLevelEnum.ONE;
    private static final int SIZE_OF_TEST_CASE = 5;

    @BeforeClass
    public static void beforeClass() {
        for (int i = 0; i < SIZE_OF_TEST_CASE; i++) {
            Date now = new Date();
            Category category = new Category();
            category.setName("c" + (i + 1));
            category.setDescription("c" + (i + 1) + " desc");
            category.setParentId((long) 0);
            category.setLeaf(true);
            category.setLevel(level);
            category.setDeleted(false);
            category.setCreateTime(now);
            category.setModifiedTime(now);
            testCases.add(category);
        }
    }

    @Before
    public void before() {
        // delete data
        categoryService.deleteByExample(new CategoryExample());
        // check count
        int count = categoryService.countByExample(new CategoryExample());
        assertEquals(0, count);
        // prepare test data
        testCases.forEach(t -> {
            int affectRow = categoryService.insert(t);
            assertEquals(1, affectRow);
        });
    }

    @Test
    public void testCountByExample() {
        int count = categoryService.countByExample(new CategoryExample());
        assertEquals(testCases.size(), count);
    }

    @Test
    public void testInsert() {
        Date now = new Date();
        Category category = new Category();
        category.setName("t");
        category.setDescription("t desc");
        category.setParentId((long) 0);
        category.setLeaf(true);
        category.setLevel(CategoryLevelEnum.ONE);
        category.setDeleted(false);
        category.setCreateTime(now);
        category.setModifiedTime(now);
        int affectRow = categoryService.insert(category);
        assertEquals(1, affectRow);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Category testCase = testCases.get(0);
        Category category = categoryService.selectByPrimaryKey(testCase.getCategoryId());
        assertEquals(testCase, category);
    }

    @Test
    public void testSelectByExample() {
        Category testCase = testCases.get(0);
        CategoryExample example = new CategoryExample();
        example.setDistinct(true);
        example.createCriteria()
                .andLevelEqualTo(level)
                .andCategoryIdGreaterThan((long) 0)
                .andNameLike("%c%")
                .andCategoryIdEqualTo(testCase.getCategoryId());
        List<Category> categories = categoryService.selectByExample(example);
        assertEquals(1, categories.size());
        assertEquals(testCase, categories.get(0));
    }

    @Test
    public void testSelectByExampleForStartPage() {
        assertEquals(3, categoryService.selectByExampleForStartPage(new CategoryExample(), 1, 3).size());
        List<Category> categories = categoryService.selectByExampleForStartPage(new CategoryExample(), 2, 3);
        assertEquals(2, categories.size());
        assertEquals(testCases.get(3), categories.get(0));
        assertEquals(testCases.get(4), categories.get(1));
    }
}
