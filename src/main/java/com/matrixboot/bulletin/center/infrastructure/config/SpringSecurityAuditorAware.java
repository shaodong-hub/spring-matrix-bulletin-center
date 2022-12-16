package com.matrixboot.bulletin.center.infrastructure.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * create in 2022/12/16 19:24
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public @NotNull Optional<Long> getCurrentAuditor() {
        return Optional.of(1L);
    }
}
