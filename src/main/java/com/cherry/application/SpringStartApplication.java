package com.cherry.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.cherry.application.jetty.CustomeServletContextHandler;
import com.cherry.application.logback.LogbackConfigurer;

public class SpringStartApplication {
	private  static ApplicationContext applicationContext;
	public static String fileConfigPath = "";
	public static void main(String[] args) {
		try{
			if(args != null && args.length>0){
				fileConfigPath = args[0];
				String filePah = fileConfigPath+"spring.xml";
				applicationContext = new FileSystemXmlApplicationContext("file:"+filePah);
				Server server = applicationContext.getBean(Server.class);
				CustomeServletContextHandler context = 	applicationContext.getBean(CustomeServletContextHandler.class);
				//logback
				LogbackConfigurer.initLogging("file:"+fileConfigPath+"logback.xml");
				//springmvc 
				ServletHolder holder=new ServletHolder(new DispatcherServlet());
				holder.setInitParameter("contextConfigLocation", "file:"+fileConfigPath+"spring-mvc.xml");
			    context.addServlet(holder, "/*");
			    
			    if (server!= null) {
					if (server.isStarting() || server.isStarted() || server.isRunning() ) {
						return;
					}
				}
		        server.start();
			}else{
				System.out.println("请指定配置文件的路径");
				System.exit(1);
			}	
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}


}
