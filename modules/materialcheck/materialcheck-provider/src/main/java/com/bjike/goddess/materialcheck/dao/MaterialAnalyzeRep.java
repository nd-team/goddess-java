package com.bjike.goddess.materialcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.entity.MaterialAnalyze;

/**
 * 物资分析持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MaterialAnalyzeRep extends JpaRep<MaterialAnalyze, MaterialAnalyzeDTO> {

}