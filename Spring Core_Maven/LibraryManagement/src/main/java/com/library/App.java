package com.library;

import com.library.service.BookService;
//import jdk.jpackage.internal.ApplicationLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService=context.getBean(BookService.class);

        bookService.displayBook();
    }
}
