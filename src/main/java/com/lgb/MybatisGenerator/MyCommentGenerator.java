package com.lgb.MybatisGenerator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Properties;

public class MyCommentGenerator implements CommentGenerator{

	public void addClassComment(InnerClass arg0, IntrospectedTable arg1) {
		// TODO Auto-generated method stub
		
	}

	public void addClassComment(InnerClass arg0, IntrospectedTable arg1,
			boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	public void addComment(XmlElement arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addConfigurationProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addEnumComment(InnerEnum arg0, IntrospectedTable arg1) {
		// TODO Auto-generated method stub
		
	}

	public void addFieldComment(Field arg0, IntrospectedTable arg1) {
		// TODO Auto-generated method stub
		
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// TODO Auto-generated method stub
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
	        //field.addJavaDocLine("/**");
	        field.addJavaDocLine("//" + introspectedColumn.getRemarks());
	        //addJavadocTag(field, false);
	        //field.addJavaDocLine(" */");
	    }
	}

	public void addGeneralMethodComment(Method arg0, IntrospectedTable arg1) {
		// TODO Auto-generated method stub
		
	}

	public void addGetterComment(Method arg0, IntrospectedTable arg1,
			IntrospectedColumn arg2) {
		// TODO Auto-generated method stub
		
	}

	public void addJavaFileComment(CompilationUnit arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addRootComment(XmlElement arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addSetterComment(Method arg0, IntrospectedTable arg1,
			IntrospectedColumn arg2) {
		// TODO Auto-generated method stub
		
	}

}
