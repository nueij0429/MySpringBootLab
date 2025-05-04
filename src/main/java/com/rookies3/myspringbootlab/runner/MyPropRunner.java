package com.rookies3.myspringbootlab.runner;

import com.rookies3.myspringbootlab.config.MyEnvironment;
import com.rookies3.myspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class MyPropRunner implements ApplicationRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private MyPropProperties properties;

    @Autowired
    private MyEnvironment environment;

    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        logger.info("myprop.username: " + username);
        logger.info("myprop.port: " + port);
        logger.debug("환경 모드: " + environment.getMode());
    }
}