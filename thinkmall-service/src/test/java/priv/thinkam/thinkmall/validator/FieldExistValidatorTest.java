package priv.thinkam.thinkmall.validator;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;
import priv.thinkam.thinkmall.dao.entity.Category;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.dao.enums.CategoryLevelEnum;
import priv.thinkam.thinkmall.service.AdministratorService;
import priv.thinkam.thinkmall.service.CategoryService;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author thinkam
 * @date 2018/03/31
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext.xml",
        "classpath:applicationContext-listener.xml"
})
@Transactional(transactionManager = "transactionManager")
public class FieldExistValidatorTest {
    private static Category categoryTestCase;
    private static Administrator administratorTestCase;

    @Resource
    private CategoryService categoryService;
    @Resource
    private AdministratorService administratorService;

    @BeforeClass
    public static void beforeClass() {
        Date now = new Date();
        categoryTestCase = new Category();
        categoryTestCase.setName("c1");
        categoryTestCase.setDescription("c1 desc");
        categoryTestCase.setParentId((long) 0);
        categoryTestCase.setLevel(CategoryLevelEnum.ONE);
        categoryTestCase.setDeleted(false);
        categoryTestCase.setCreateTime(now);
        categoryTestCase.setModifiedTime(now);

        administratorTestCase = new Administrator();
        administratorTestCase.setUsername("admin");
        administratorTestCase.setPassword("1");
        administratorTestCase.setSalt("1");
        administratorTestCase.setCreateTime(now);
        administratorTestCase.setModifiedTime(now);
    }

    @Before
    public void before() {
        // delete data
        categoryService.deleteByExample(new CategoryExample());
        administratorService.deleteByExample(new AdministratorExample());
        // check count
        assertEquals(0, categoryService.countByExample(new CategoryExample()));
        assertEquals(0, administratorService.countByExample(new AdministratorExample()));
        // prepare test data
        assertEquals(1, categoryService.insert(categoryTestCase));
        assertEquals(1, administratorService.insert(administratorTestCase));
    }

    @Test
    public void testValidate() {
        Result result1 = FluentValidator.checkAll()
                .on(categoryTestCase.getName(), new FieldExistValidator<>(categoryService, "name",
                        "已存在该类别"))
                .doValidate()
                .result(ResultCollectors.toSimple());
        assertFalse(result1.isSuccess());

        Result result2 = FluentValidator.checkAll()
                .on(administratorTestCase.getUsername(), new FieldExistValidator<>(administratorService, "username",
                        "已存在改用户名"))
                .doValidate()
                .result(ResultCollectors.toSimple());
        assertFalse(result2.isSuccess());
    }
}