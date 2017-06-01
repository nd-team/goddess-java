package com.bjike.goddess.materialsummary.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyYearSum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 物资分类年汇总业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:48 ]
* @Description:	[ 物资分类年汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialsummarySerCache")
@Service
public class MaterialClassifyYearSumSerImpl extends ServiceImpl<MaterialClassifyYearSum, MaterialClassifyYearSumDTO> implements MaterialClassifyYearSumSer { 

 }