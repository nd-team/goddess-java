package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.AwardInfoBO;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.service.AwardInfoSer;
import com.bjike.goddess.interiorrecommend.to.AwardInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐奖励信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("awardInfoApiImpl")
public class AwardInfoApiImpl implements AwardInfoAPI {

    @Autowired
    private AwardInfoSer awardInfoSer;

    @Override
    public AwardInfoBO editModel(AwardInfoTO to) throws SerException {
        return awardInfoSer.updateModel(to);
    }

    @Override
    public List<AwardInfoBO> pageList(AwardInfoDTO dto) throws SerException {
        return awardInfoSer.pageList(dto);
    }

}