package com.lgb.MybatisGenerator;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

public class MyIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl{

	/* 
	 * 定义xml文件名字
	 */
	@Override
	protected String calculateMyBatis3XmlMapperFileName() {
		StringBuilder sb = new StringBuilder();
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("_SqlMap.xml");
        return sb.toString();
	}

}
