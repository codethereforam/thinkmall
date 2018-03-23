package priv.thinkam.thinkmall.dao;

import priv.thinkam.thinkmall.common.util.MybatisGeneratorUtil;
import priv.thinkam.thinkmall.common.util.PropertiesFileUtil;

/**
 * 运行mybatis生成器
 *
 * @author thinkam
 * @date 2018/03/22
 */
public class Generator {
    private static final String DATABASE = "thinkmall";
    private static final String PACKAGE_NAME = "priv.thinkam.thinkmall";
    private static final String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static final String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static final String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static final String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");

    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE, PACKAGE_NAME);
    }
}
