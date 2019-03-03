package com.devix.number.dao;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory factory;

	private MyBatisUtil() {
	}

	static {
		System.out.println("Initializing myBatis.");
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("configuration.xml");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
		System.out.println("Finished initializing of MyBatis.");
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
