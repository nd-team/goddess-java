package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.entity.JoinInfo;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交接资料业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class JoinInfoSerImpl extends ServiceImpl<JoinInfo, JoinInfoDTO> implements JoinInfoSer {

   @Override
    public Long countJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
       Long count = super.count(joinInfoDTO);
       return count;
    }

    @Override
    public JoinInfoBO getOne(String id) throws SerException {
       JoinInfo joinInfo = super.findById(id);
       return BeanTransform.copyProperties(joinInfo,JoinInfoBO.class,true);
    }

    @Override
    public List<JoinInfoBO> findListJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
       List<JoinInfo> joinInfos = super.findByPage(joinInfoDTO);
       List<JoinInfoBO> joinInfoBOS = BeanTransform.copyProperties(joinInfos,JoinInfoBO.class,true);
       return joinInfoBOS;
    }

    @Override
    public JoinInfoBO insertJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
       JoinInfo joinInfo = BeanTransform.copyProperties(joinInfoTO,JoinInfo.class,true);
       joinInfo.setCreateTime(LocalDateTime.now());
       super.save(joinInfo);
       return BeanTransform.copyProperties(joinInfo,JoinInfoBO.class,true);
    }

    @Override
    public JoinInfoBO editJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
       JoinInfo joinInfo = super.findById(joinInfoTO.getId());
       BeanTransform.copyProperties(joinInfoTO,joinInfo,true);
       joinInfo.setModifyTime(LocalDateTime.now());
       super.update(joinInfo);
       return BeanTransform.copyProperties(joinInfoTO,JoinInfoBO.class,true);
    }

    @Override
    public void removeJoinInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void upload() throws SerException {
       //todo 未做上传
        return;

    }

    @Override
    public void download() throws SerException {
       //todo 未做下载
        return;

    }

}