package com.vrk.tms.service.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db.datasource")
@Getter
@Setter
@NoArgsConstructor
public class DbConfigProperties {
    private String url;
    private String userName;
    private String password;
}
