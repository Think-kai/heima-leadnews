package com.heima.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.service.ApArticleConfigService;
import com.heima.model.article.pojos.ApArticleConfig;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {

    @Override
    public void updateByMap(Map map) {
        Long id = (Long) map.get("articleId");
        Object enable = map.get("enable");
        if (id == null || enable == null) {
            return;
        }
        boolean flag = !enable.equals(1);

        update(Wrappers.<ApArticleConfig>lambdaUpdate()
                .eq(ApArticleConfig::getArticleId, id)
                .set(ApArticleConfig::getIsDown, flag));
    }
}
