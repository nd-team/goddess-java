package com.bjike.goddess.common.jpa.dao;

import com.bjike.goddess.common.api.dto.BaseDto;
import com.bjike.goddess.common.api.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa dao接口，实现该接口可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface JpaRep<BE extends BaseEntity, BD extends BaseDto> extends JpaRepository<BE, String>
        , JpaSpecificationExecutor<BE> {

    BE findById(String id);

}
