package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IBulletinElasticsearchRepository;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.query.BulletinQuery;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.center.infrastructure.mapper.IBulletinMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/30 00:11
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final IBulletinMapper mapper;

    private final IBulletinElasticsearchRepository repository;

    public Page<BulletinResult> findAll(BulletinQuery query, Pageable pageable) {
        

        return null;
    }

    public void createBulletin(BulletinModifyEvent event) {
        var entity = mapper.from(event);
        repository.save(entity);
    }

}
