package com.heima.search.service.impl;

import com.aliyun.oss.common.utils.DateUtil;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dtos.HistorySearchDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.search.pojos.ApUserSearch;
import com.heima.search.service.ApUserSearchService;
import com.heima.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ApUserSearchServiceImpl implements ApUserSearchService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Async
    public void insert(String keyword, Integer userId) {

        Query query = Query.query(Criteria.where("userId").is(userId).and("keyword").is(keyword));
        ApUserSearch apUserSearch = mongoTemplate.findOne(query, ApUserSearch.class);

        if (apUserSearch != null) {
            apUserSearch.setCreatedTime(new Date());
            mongoTemplate.save(apUserSearch);
            return;
        }

        apUserSearch = new ApUserSearch();
        apUserSearch.setUserId(userId);
        apUserSearch.setKeyword(keyword);
        apUserSearch.setCreatedTime(new Date());

        // 若已经有10条数据覆盖最早的数据
        query = Query.query(Criteria.where("userId").is(userId)).with(Sort.by(Sort.Direction.DESC, "createdTime"));
        List<ApUserSearch> list = mongoTemplate.find(query, ApUserSearch.class);
        if (list == null && list.size() < 10) {
            mongoTemplate.save(apUserSearch);
        } else {
            ApUserSearch lastData = list.get(list.size() - 1);
            mongoTemplate.findAndReplace(Query.query(Criteria.where("id").is(lastData.getId())), apUserSearch);
        }



        mongoTemplate.save(apUserSearch);
    }

    @Override
    public ResponseResult findUserSearch() {
        ApUser apUser = AppThreadLocalUtil.getUser();
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        Query query = Query.query(Criteria.where("userId").is(apUser.getId())).with(Sort.by(Sort.Direction.DESC, "createdTime"));
        List<ApUserSearch> list = mongoTemplate.find(query, ApUserSearch.class);

        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult delUserSearch(HistorySearchDto dto) {
        if (dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApUser apUser = AppThreadLocalUtil.getUser();
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        mongoTemplate.remove(Query.query(Criteria.where("userId").is(apUser.getId()).and("id").is(dto.getId())), ApUserSearch.class);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
