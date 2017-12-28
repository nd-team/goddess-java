package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.TableHeadValue;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 进度表表头对应Value业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class TableHeadValueSerImpl extends ServiceImpl<TableHeadValue, TableHeadValueDTO> implements TableHeadValueSer {


}