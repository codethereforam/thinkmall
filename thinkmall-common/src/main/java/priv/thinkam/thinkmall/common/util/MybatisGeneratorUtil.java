package priv.thinkam.thinkmall.common.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author thinkam
 * @date 2018/03/18
 */
public class MybatisGeneratorUtil {
    private static final String PROJECT_NAME = "thinkmall";
    /**
     * generatorConfig模板路径
     */
    private static String generatorConfig_vm = "/template/generatorConfig.vm";
    /**
     * Service模板路径
     */
    private static String service_vm = "/template/Service.vm";
    /**
     * ServiceImpl模板路径
     */
    private static String serviceImpl_vm = "/template/ServiceImpl.vm";

    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbcDriver   驱动路径
     * @param jdbcUrl      链接
     * @param jdbcUsername 帐号
     * @param jdbcPassword 密码
     * @param database     数据库
     * @param packageName  包名
     */
    public static void generator(
            String jdbcDriver,
            String jdbcUrl,
            String jdbcUsername,
            String jdbcPassword,
            String database,
            String packageName) throws Exception {

        generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
        System.out.println(generatorConfig_vm);
        service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
        serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");

        String targetProject = PROJECT_NAME + "-dao";
        System.out.println(MybatisGeneratorUtil.class.getResource("/").getPath());
        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "");
        System.out.println("basePath=" + basePath);
        String generatorconfigXml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
        System.out.println(generatorconfigXml);
        targetProject = basePath + targetProject;
        System.out.println("targetproject=" + targetProject);

        String targetProjectSqlMap = basePath + PROJECT_NAME + "-service";
        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("generator_javaModelGenerator_targetPackage", packageName + ".dao.entity");
        velocityContext.put("generator_sqlMapGenerator_targetPackage", packageName + ".dao.mapper");
        velocityContext.put("generator_javaClientGenerator_targetPackage", packageName + ".dao.mapper");
        velocityContext.put("targetProject", targetProject);
        velocityContext.put("targetProject_sqlMap", targetProjectSqlMap);
        VelocityUtil.generate(generatorConfig_vm, generatorconfigXml, velocityContext);
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

        System.out.println("========== 开始运行MybatisGenerator ==========");
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorconfigXml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        //设置覆盖之前以前的代码
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("========== 结束运行MybatisGenerator ==========");

        // 查询定制前缀项目的所有表
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "';";
        List<String> tableNames = new ArrayList<>();
        JdbcUtil jdbcUtil = new JdbcUtil(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
        List<Map> result = jdbcUtil.selectByParams(sql, null);
        for (Map map : result) {
            String tableName = ObjectUtils.toString(map.get("TABLE_NAME"));
            System.out.println(tableName);
            tableNames.add(tableName);
        }
        jdbcUtil.release();

        System.out.println("========== 开始生成Service ==========");
        String ctime = new SimpleDateFormat("yy-MM-dd").format(new Date());
        String servicePath = basePath + PROJECT_NAME + "-service" + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/service";
        String serviceImplPath = basePath + PROJECT_NAME + "-service" + "/src/main/java/" + packageName.replaceAll("\\.", "/") + "/service/impl";
        for (String tableName : tableNames) {
            String model = StringUtil.lineToHump(tableName);
            String service = servicePath + "/" + model + "Service.java";
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            // 生成service
            File serviceFile = new File(service);
            if (!serviceFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", packageName);
                context.put("model", model);
                context.put("ctime", ctime);
                VelocityUtil.generate(service_vm, service, context);
                System.out.println(service);
            }
            // 生成serviceImpl
            File serviceImplFile = new File(serviceImpl);
            if (!serviceImplFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", packageName);
                context.put("model", model);
                context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
                context.put("ctime", ctime);
                VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
                System.out.println(serviceImpl);
            }
        }
        System.out.println("========== 结束生成Service ==========");
    }

}
