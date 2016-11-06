package com.topie.campus.basedao.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * Created by cgj on 2016/5/12.
 */
public class Migrate {

    @Value("${jdbc_url}")
    private String jdbcUrl;

    @Value("${jdbc_username}")
    private String userName;

    @Value("${jdbc_password}")
    private String password;

    @PostConstruct
    public void init() {
       /* Flyway flyway = new Flyway();
        flyway.setDataSource(jdbcUrl, userName, password);
        flyway.migrate();*/
    }
}
