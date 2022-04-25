package org.Revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import AccountModelPkg.*;
import AccountDaoPkg.*;

public class App 
{

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println( "Hello, user!" );

        logger.trace("We've just greeted the user!");
        logger.debug("We've just greeted the user!");
        logger.info("We've just greeted the user!");
        logger.warn("We've just greeted the user!");
        logger.error("We've just greeted the user!");
        logger.fatal("We've just greeted the user!");



    }

    public static int add(int x, int y){
        return x + y;
    }
}
