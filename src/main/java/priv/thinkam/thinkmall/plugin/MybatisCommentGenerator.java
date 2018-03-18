package priv.thinkam.thinkmall.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义生成的类注释
 *
 * @author thinkam
 * @date 2018/03/18
 */
public class MybatisCommentGenerator extends DefaultCommentGenerator {
	private SimpleDateFormat classCommentDateFormat = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		super.addFieldComment(field, introspectedTable, introspectedColumn);
		if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			field.addJavaDocLine(" */");
		}
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		super.addModelClassComment(topLevelClass, introspectedTable);
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine("* @author thinkam");
		topLevelClass.addJavaDocLine("* @date " + classCommentDateFormat.format(new Date()));
		topLevelClass.addJavaDocLine("*/");
	}
}
