package org.grails.cxf.test;

public class HelloWorldImpl implements HelloWorld {
	@Override
	public String sayHi(String text) {
		return "hello";
	}
}
