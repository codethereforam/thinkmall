package priv.thinkam.thinkmall.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.common.exception.UsernamePasswordNotMatchException;
import priv.thinkam.thinkmall.common.util.Md5Util;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.dao.entity.AdministratorExample;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author thinkam
 * @date 2018/03/23
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext.xml",
        "classpath:applicationContext-listener.xml"
})
@Transactional(transactionManager = "transactionManager")
public class AdministratorServiceTest {
    private static final String USERNAME = "u1";
    private static final String PASSWORD = "p1";
    private static Administrator testCase1;
    @Resource
    private AdministratorService administratorService;

    @BeforeClass
    public static void beforeClass() {
        String salt = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        testCase1 = new Administrator();
        testCase1.setUsername(USERNAME);
        testCase1.setPassword(Md5Util.digest(PASSWORD + salt));
        testCase1.setSalt(salt);
        testCase1.setCreateTime(now);
        testCase1.setModifiedTime(now);
    }

    @Before
    public void before() {
        // delete all data
        administratorService.deleteByExample(new AdministratorExample());
        // prepare test data
        int affectRow = administratorService.insert(testCase1);
        assertEquals(1, affectRow);
    }

    @Test
    public void testValidate1() throws UsernamePasswordNotMatchException {
        Administrator administrator = administratorService.validate(USERNAME, PASSWORD);
        assertEquals(testCase1, administrator);
    }

    @Test(expected = UsernamePasswordNotMatchException.class)
    public void testValidate2() throws UsernamePasswordNotMatchException {
        administratorService.validate(USERNAME, "111");
    }
}