package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyDaySumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyDaySum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 物资分类日汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:27 ]
* @Description:	[ 物资分类日汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class MaterialClassifyDaySumSerImpl extends ServiceImpl<MaterialClassifyDaySum, MaterialClassifyDaySumDTO> implements MaterialClassifyDaySumSer { 

 }