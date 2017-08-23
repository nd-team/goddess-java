package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.RecommendAssessDetailBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendAssessDetailDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendAssessDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐内容业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:28 ]
 * @Description: [ 推荐内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendAssessDetailSerImpl extends ServiceImpl<RecommendAssessDetail, RecommendAssessDetailDTO> implements RecommendAssessDetailSer {


}