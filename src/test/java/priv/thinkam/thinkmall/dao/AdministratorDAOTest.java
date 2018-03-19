package priv.thinkam.thinkmall.dao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import priv.thinkam.thinkmall.model.Administrator;
import priv.thinkam.thinkmall.util.Md5Util;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author thinkam
 * @date 2018/03/19
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({
        "classpath:applicationContext-jdbc.xml",
        "classpath:applicationContext.xml"
})
@Transactional(transactionManager = "transactionManager")
public class AdministratorDAOTest {
    @Resource
    private AdministratorDAO administratorDAO;

    private static Administrator testData1;

    @BeforeClass
    public static void beforeClass() {
        String salt = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        testData1 = new Administrator();
        testData1.setUsername("u1");
        testData1.setPassword(Md5Util.digest("p1" + salt));
        testData1.setSalt(salt);
        testData1.setCreateTime(now);
        testData1.setModifiedTime(now);
    }

    @Before
    public void before() {
        // delete data
        administratorDAO.removeAll();
        // prepare test data
        int affectRow = administratorDAO.save(testData1);
        assertEquals(affectRow, 1);
    }

    @Test
    public void testSave() {
        Administrator administrator = new Administrator();
        String salt = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        administrator.setUsername("u2");
        administrator.setPassword(Md5Util.digest("p2" + salt));
        administrator.setSalt(salt);
        administrator.setCreateTime(now);
        administrator.setModifiedTime(now);
        int affectRow = administratorDAO.save(administrator);
        assertEquals(affectRow ,1);
    }

    @Test
    public void testListByUsername() {
        List<Administrator> administrators = administratorDAO.listByUsername("u1");
        assertEquals(administrators.size(), 1);
        assertEquals(testData1, administrators.get(0));
    }
}