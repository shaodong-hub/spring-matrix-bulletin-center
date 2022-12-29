package com.matrixboot.bulletin.center.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * create in 2022/12/30 00:12
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {
}
