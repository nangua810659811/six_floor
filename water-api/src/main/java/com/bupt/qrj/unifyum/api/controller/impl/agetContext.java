package com.bupt.qrj.unifyum.api.controller.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class agetContext {

	public static ApplicationContext getContext() {
		// 获得Spring中定义的Bean实例，两个以上加 new String[]
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
		return context;
	}
}
