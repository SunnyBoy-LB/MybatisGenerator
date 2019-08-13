package com.lgb.dbAutoIncrement;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * v1.0
 * 指定表添加自增
 */
public class TableAutoIncrementConfig
{
	@Test
	public void tableAutoTest(){

		Properties properties = new Properties();
		try {
			// 使用FileInputStream加载properties配置文件生成对应的输入流
			FileInputStream in = new FileInputStream("target/classes/dp.properties");
			// 从输入流中读取属性列表
			properties.load(in);
		} catch (IOException e) {
			System.out.println("load file faile." + e);
			return;
		} catch (Exception e) {
			System.out.println("load file faile." + e);
			return;
		}

		String tableAutoIncrement = "ALTER TABLE %s MODIFY COLUMN %s  int(11) NOT NULL AUTO_INCREMENT COMMENT '%s' FIRST;" ;
		// 返回Properties中包含的key-value的Set视图
		Set<Entry<Object, Object>> set = properties.entrySet();
		// 返回在此Set中的元素上进行迭代的迭代器
		Iterator<Entry<Object, Object>> it = set.iterator();
		String key = null, value = null;
		// 循环取出key-value
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			key = String.valueOf(entry.getKey());
			value = String.valueOf(entry.getValue());
			key = key == null ? key : key.trim();
			value = value == null ? value : value.trim();

			System.out.println(String.format(tableAutoIncrement,key,value,value));
		}

	}


}