package com.bjike.goddess.attendance.service.overtime;

import com.bjike.goddess.attendance.bo.overtime.AreaBO;
import com.bjike.goddess.attendance.bo.overtime.OverWorkBO;
import com.bjike.goddess.attendance.dto.overtime.OverLongAndRelaxdayDTO;
import com.bjike.goddess.attendance.to.overtime.OverWorkTO;
import com.bjike.goddess.attendance.vo.overtime.OverLongAndRelaxDayVO;
import com.bjike.goddess.attendance.vo.overtime.PositionAndDepartVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.attendance.dto.overtime.OverWorkDTO;
import com.bjike.goddess.attendance.entity.overtime.OverWork;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加班业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attendanceSerCache")
@Service
public class OverWorkSerImpl extends ServiceImpl<OverWork, OverWorkDTO> implements OverWorkSer {

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;


    @Override
    public Long countOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        Long count = super.count(overWorkDTO);

        return count;
    }

    @Override
    public OverWorkBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById( id );
        OverWorkBO overWorkBO = BeanTransform.copyProperties(overWork, OverWorkBO.class);
        return overWorkBO;
    }

    @Override
    public List<OverWorkBO> listOverWork(OverWorkDTO overWorkDTO) throws SerException {
        if (StringUtils.isBlank(overWorkDTO.getOverWorker())){
            overWorkDTO.getConditions().add(Restrict.eq("overWorker", overWorkDTO.getOverWorker()));
        }
        overWorkDTO.getSorts().add("createTime=desc");
        List<OverWork> list = super.findByCis( overWorkDTO ,true );
        List<OverWorkBO> boList = BeanTransform.copyProperties( list, OverWorkBO.class);

        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OverWorkBO addOverWork(OverWorkTO overWorkTO) throws SerException {
        OverWork overWork = BeanTransform.copyProperties(overWorkTO,OverWork.class,true);
        super.save( overWork );
        return BeanTransform.copyProperties( overWork, OverWorkBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteOverWork(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        OverWork overWork = super.findById( id );
        if( null!= overWork){
            super.remove( id );
        }
    }

    @Override
    public List<AreaBO> areaList() throws SerException {
        List<AreaBO> area = BeanTransform.copyProperties(departmentDetailAPI.findArea(),AreaBO.class);
        return area;
    }

    @Override
    public List<String> peopleList() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        List<String> userList = new ArrayList<>();
        if( userBOS != null && userBOS.size()>0 ){
            userList = userBOS.stream().filter(str->StringUtils.isNotBlank( str.getUsername()))
                    .map(UserBO::getUsername).collect(Collectors.toList());
        }
        return userList;
    }

    @Override
    public PositionAndDepartVO getPositAndDepart(String overWorker) throws SerException {
        PositionAndDepartVO vo = new PositionAndDepartVO();
        List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.findPositionByUser( overWorker );
        if ( positionDetailBOList != null ){
            for( PositionDetailBO str : positionDetailBOList ){
                vo.setDepart( str.getDepartmentName() );
                vo.setPosition( str.getPosition() );
            }
        }
        return vo;
    }

    @Override
    public OverLongAndRelaxDayVO caculateTime(OverLongAndRelaxdayDTO overLongAndRelaxdayDTO) throws SerException {
        //加班时长：
        //若有午休： 加班时长= 结束时间 - 开始时间 -1.5午休
        //若没有午休：  加班时长= 结束时间 - 开始时间
        Double overLong = 0d;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime start = LocalDateTime.parse( overLongAndRelaxdayDTO.getStartTime(), formatter );
        LocalDateTime end = LocalDateTime.parse( overLongAndRelaxdayDTO.getEndTime(), formatter );

        //TODO:未做
//        end.getLong( )
        return null;
    }
}