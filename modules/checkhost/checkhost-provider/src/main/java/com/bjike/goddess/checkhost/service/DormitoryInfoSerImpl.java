package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.entity.DormitoryInfo;
import com.bjike.goddess.checkhost.enums.GuideAddrStatus;
import com.bjike.goddess.checkhost.to.DormitoryInfoTO;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 宿舍信息管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class DormitoryInfoSerImpl extends ServiceImpl<DormitoryInfo, DormitoryInfoDTO> implements DormitoryInfoSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private HostApplySer hostApplySer;
    @Autowired
    private StayApplySer stayApplySer;
    @Autowired
    private StayDaysSer stayDaysSer;


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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("dormitoryinfo");
        obj.setDescribesion("宿舍信息管理");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = hostApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("hostapply");
        obj.setDescribesion("离宿申请");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeSta = stayApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("stayapply");
        obj.setDescribesion("住宿申请");
        if (flagSeeSta) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeStd = stayDaysSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("staydays");
        obj.setDescribesion("员工住宿天数汇总");
        if (flagSeeStd) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    public Long countDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        Long count = super.count(dormitoryInfoDTO);
        return count;
    }

    @Override
    public DormitoryInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DormitoryInfo dormitoryInfo = super.findById(id);
        return BeanTransform.copyProperties(dormitoryInfo, DormitoryInfoBO.class);
    }

    @Override
    public List<DormitoryInfoBO> findListDormitoryInfo(DormitoryInfoDTO dormitoryInfoDTO) throws SerException {
        checkSeeIdentity();
        List<DormitoryInfo> dormitoryInfos = super.findByCis(dormitoryInfoDTO, true);
        return BeanTransform.copyProperties(dormitoryInfos, DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryInfoBO insertDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        checkAddIdentity();
        DormitoryInfo dormitoryInfo = BeanTransform.copyProperties(dormitoryInfoTO, DormitoryInfo.class, true);
        dormitoryInfo.setCreateTime(LocalDateTime.now());
        super.save(dormitoryInfo);
        return BeanTransform.copyProperties(dormitoryInfo, DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DormitoryInfoBO editDormitoryInfo(DormitoryInfoTO dormitoryInfoTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(dormitoryInfoTO.getId())) {
            DormitoryInfo dormitoryInfo = super.findById(dormitoryInfoTO.getId());
            LocalDateTime createTime = dormitoryInfo.getCreateTime();
            dormitoryInfo = BeanTransform.copyProperties(dormitoryInfoTO, DormitoryInfo.class, true);
            dormitoryInfo.setCreateTime(createTime);
            dormitoryInfo.setModifyTime(LocalDateTime.now());
            super.update(dormitoryInfo);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(dormitoryInfoTO, DormitoryInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeDormitoryInfo(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    //chenjunhao
    public Set<String> allAddress() throws SerException {
        Set<String> set = new HashSet<>();
        List<DormitoryInfo> list = super.findAll();
        for (DormitoryInfo d : list) {
            set.add(d.getAddress());
        }
        return set;
    }

    @Override
    //chenjunhao
    public String findContact(String address) throws SerException {
        DormitoryInfoDTO dto = new DormitoryInfoDTO();
        dto.getConditions().add(Restrict.eq("address", address));
        DormitoryInfo dormitoryInfo = super.findOne(dto);
        if (dormitoryInfo != null) {
            return dormitoryInfo.getHeadContact();
        }
        return "";
    }
}