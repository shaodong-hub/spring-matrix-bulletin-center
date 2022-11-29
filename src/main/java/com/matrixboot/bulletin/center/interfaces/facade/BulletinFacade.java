package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinService;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create in 2022/11/29 00:06
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class BulletinFacade {

    private final BulletinService service;

    @GetMapping("bulletins")
    public Page<BulletinResult> findAll(Pageable pageable) {
        return service.findByUserId(1L, pageable);
    }

    @PostMapping("bulletin")
    public void create(){

    }

    @PutMapping("bulletin")
    public void update(){

    }

    @DeleteMapping("bulletin")
    public void delete(){

    }

}
