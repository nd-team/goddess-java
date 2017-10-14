package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.bo.overtime.ExtralOverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDayDTO;
import com.bjike.goddess.attendance.to.overtime.ExtralOverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.ExtralOverWorkDayVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.overtime.ExtralOverWorkDTO;
import com.bjike.goddess.attendance.entity.overtime.ExtralOverWork;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 补班设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class ExtralOverWorkSerImpl extends ServiceImpl<ExtralOverWork, ExtralOverWorkDTO> implements ExtralOverWorkSer {

    @Autowired
    private UserAPI userAPI;


    @Override
    public Long countExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        Long count = super.count(extralOverWorkDTO);
        return count;
    }

    @Override
    public ExtralOverWorkBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ExtralOverWork extralOverWork = super.findById(id);
        return BeanTransform.copyProperties(extralOverWork, ExtralOverWorkBO.class);
    }

    @Override
    public List<ExtralOverWorkBO> listExtralOverWork(ExtralOverWorkDTO extralOverWorkDTO) throws SerException {
        extralOverWorkDTO.getSorts().add("createTime=desc");
        List<ExtralOverWork> list = super.findByCis(extralOverWorkDTO, true);
        List<ExtralOverWorkBO> boList = BeanTransform.copyProperties(list, ExtralOverWorkBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExtralOverWorkBO addExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        ExtralOverWork temp = BeanTransform.copyProperties(extralOverWorkTO, ExtralOverWork.class, true);
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        temp.setCreator( userBO.getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());

        super.save(temp);
        return BeanTransform.copyProperties(extralOverWorkTO, ExtralOverWorkBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExtralOverWorkBO editExtralOverWork(ExtralOverWorkTO extralOverWorkTO) throws SerException {
        if (StringUtils.isBlank(extralOverWorkTO.getId())) {
            throw new SerException("id不能为空");
        }
        ExtralOverWork temp = super.findById(extralOverWorkTO.getId());
        BeanTransform.copyProperties( extralOverWorkTO , temp,"createTime","creator","id");
        temp.setModifyTime( LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties(temp, ExtralOverWorkBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteExtralOverWork(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ExtralOverWork temp = super.findById(id);
        if (null == temp){
            throw new SerException("该数据不存在，请刷新数据");
        }
        super.remove( id );
    }

    @Override
    public ExtralOverWorkDayVO caculateTime(ExtralOverWorkDayDTO overDTO) throws SerException {
        Double overLong = 0d;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse( overDTO.getStartTime(), formatter );
        LocalDateTime end = LocalDateTime.parse( overDTO.getEndTime(), formatter );
        Long hour = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()-start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        overLong = new BigDecimal((double) hour/1000/60/60).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        ExtralOverWorkDayVO vo = new ExtralOverWorkDayVO();
        vo.setOverDay( overLong/8 );

        return vo;
    }
}