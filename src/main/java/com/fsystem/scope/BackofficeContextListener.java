package com.fsystem.scope;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BackofficeContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}