package org.esport;


import org.esport.presentation.ConsoleMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsoleMenu menu = context.getBean("consoleMenu", ConsoleMenu.class);
        menu.start();
    }
}