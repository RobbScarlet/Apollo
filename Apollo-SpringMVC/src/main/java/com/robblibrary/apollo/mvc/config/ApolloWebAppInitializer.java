package com.robblibrary.apollo.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-20
 */
public class ApolloWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApolloWebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
