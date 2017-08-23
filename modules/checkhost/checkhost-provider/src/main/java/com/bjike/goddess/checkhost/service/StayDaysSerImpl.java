package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.CollectNameBO;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.enums.GuideAddrStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.entity.StayDays;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 员工住宿天数汇总业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class StayDaysSerImpl extends ServiceImpl<StayDays, StayDaysDTO> implements StayDaysSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case DEPARTADUIT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        Long count = super.count(stayDaysDTO);
        return count;
    }
    @Override
    public StayDaysBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        StayDays stayDays = super.findById(id);
        return BeanTransform.copyProperties(stayDays,StayDaysBO.class);
    }
    @Override
    public List<StayDaysBO> findListStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        checkSeeIdentity();
        List<StayDays> stayDaysList = super.findByCis(stayDaysDTO, true);
        return BeanTransform.copyProperties(stayDaysList, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO insertStayDays(StayDaysTO stayDaysTO) throws SerException {
        checkAddIdentity();
        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        stayDays.setCreateTime(LocalDateTime.now());
        super.save(stayDays);
        return BeanTransform.copyProperties(stayDays, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO editStayDays(StayDaysTO stayDaysTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(stayDaysTO.getId())) {
            StayDays stayDays = super.findById(stayDaysTO.getId());
            LocalDateTime createTime = stayDays.getCreateTime();
            stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
            stayDays.setCreateTime(createTime);
            stayDays.setModifyTime(LocalDateTime.now());
            super.update(stayDays);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(stayDaysTO, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayDays(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public StayDaysBO auditStayDays(String id, StayDaysDTO dto) throws SerException {
        checkAddIdentity();
//        stayDaysTO.setComprehensiveVerify(userAPI.currentUser().getUsername());
//        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        StayDays stayDays=super.findById(id);
        stayDays.setCheckStatus(dto.getCheckStatus());
        stayDays.setModifyTime(LocalDateTime.now());
        super.update(stayDays);

        StayDaysBO stayDaysBO = BeanTransform.copyProperties(stayDays, StayDaysBO.class);
        return stayDaysBO;
    }
    @Override
    public List<CollectNameBO> collectName(String[] names) throws SerException {
        checkSeeIdentity();
        String[] namesTemp = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            namesTemp[i] = "'" + names[i] + "'";
        }
        String namesStr = StringUtils.join(namesTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT name,num AS num,area as area,projectGroup as projectGroup,address AS address, ");
        sb.append(" stayTime AS stayTime,hostTime AS hostTime,is_receiveKey AS receiveKey,is_bed AS bed ");
        sb.append(" FROM checkhost_staydays WHERE name IN (%s) ");
        sb.append(" GROUP BY num,area,projectGroup,address,stayTime,hostTime,receiveKey,bed, ");
        sb.append(" name ORDER BY name ");
        String sql = sb.toString();
        sql = String.format(sql, namesStr);
        String[] fields = new String[]{"name", "num", "area", "projectGroup", "address", "stayTime",
                "hostTime", "receiveKey", "bed"};
        List<CollectNameBO> collectNameBOS = super.findBySql(sql, CollectNameBO.class, fields);
        return collectNameBOS;

    }

    @Override
    public List<String> getNames() throws SerException {
        String[] fields = new String[]{"name"};
        List<StayDaysBO> stayDaysBOS = super.findBySql("select distinct name from checkhost_staydays group by name order by name asc ", StayDaysBO.class, fields);

        List<String> nameList = stayDaysBOS.stream().map(StayDaysBO::getName)
                .filter(name -> (StringUtils.isNotBlank(name))).distinct().collect(Collectors.toList());


        return nameList;
    }
}