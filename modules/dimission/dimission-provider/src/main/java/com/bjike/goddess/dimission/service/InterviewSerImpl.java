package com.bjike.goddess.dimission.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.bo.InterviewBO;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.dto.InterviewDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.dimission.entity.Interview;
import com.bjike.goddess.dimission.enums.GuideAddrStatus;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.InterviewTO;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 离职管理面谈业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dimissionSerCache")
@Service
public class InterviewSerImpl extends ServiceImpl<Interview, InterviewDTO> implements InterviewSer {
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DimissionInfoSer dimissionInfoSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
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
    public InterviewBO add(InterviewTO to) throws SerException {
        Interview entity = BeanTransform.copyProperties(to, Interview.class, true);
        entity = getDataByName(entity);
        super.save(entity);
        InterviewBO interviewBO = BeanTransform.copyProperties(entity, InterviewBO.class, false);
        return interviewBO;
    }

    @Override
    public InterviewBO edit(InterviewTO to) throws SerException {
        Interview entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity = getDataByName(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        InterviewBO interviewBO = BeanTransform.copyProperties(entity, InterviewBO.class, false);
        return interviewBO;
    }

    @Override
    public InterviewBO delete(String id) throws SerException {
        Interview entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
        return BeanTransform.copyProperties(entity, InterviewBO.class, false);
    }

    @Override
    public List<InterviewBO> list(InterviewDTO dto) throws SerException {
        searchCondict(dto);
        List<Interview> interviews = super.findByPage(dto);
        List<InterviewBO> interviewBOs = BeanTransform.copyProperties(interviews, InterviewBO.class, false);
        return interviewBOs;
    }

    @Override
    public InterviewBO getById(String id) throws SerException {
        Interview entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        InterviewBO interviewBO = BeanTransform.copyProperties(entity, InterviewBO.class, false);
        return interviewBO;
    }

    @Override
    public Boolean judgeManager(String name) throws SerException {
        return isManager(name);
    }

    @Override
    public Boolean judgePrincipal(String name) throws SerException {
        return isExecutiveLevel(name);
    }

    @Override
    public Boolean judgeWelfare(String name) throws SerException {
        if (isExecutiveLevel(name) || isManager(name)) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean judgeOffice(String name) throws SerException {
        return isManager(name);
    }

    @Override
    public Boolean detainment(String name) throws SerException {
        DimissionInfoDTO dimissionInfoDTO = new DimissionInfoDTO();
        dimissionInfoDTO.getConditions().add(Restrict.eq("type", 2));
        dimissionInfoDTO.getConditions().add(Restrict.eq("username", name));
        List<DimissionInfo> dimissionInfos = dimissionInfoSer.findByCis(dimissionInfoDTO);
        if (null != dimissionInfos && dimissionInfos.size() > 0) {
            String opinion = dimissionInfos.get(0).getLiableOpinion();
            if ("可挽留".equals(opinion.substring(0, 3))) {
                return true;
            } else if ("不可挽留".equals(opinion.substring(0, 4))) {
                return false;
            }
        }
        return null;
    }

    @Override
    public Long getTotal() throws SerException {
        InterviewDTO interviewDTO = new InterviewDTO();
        return super.count(interviewDTO);
    }


    //根据名字获取地区,部门,岗位,岗位层级
    private Interview getDataByName(Interview entity) throws SerException {
        List<PositionDetailBO> positionDetailBOs = new ArrayList<>(0);
        if (moduleAPI.isCheck("organize")) {
            positionDetailBOs = positionDetailUserAPI.getPositionDetail(entity.getName());
        }
        if (null != positionDetailBOs && positionDetailBOs.size() > 0) {
            entity.setArea(positionDetailBOs.get(0).getArea());
            entity.setDepartment(positionDetailBOs.get(0).getDepartmentName());
            entity.setPosition(positionDetailBOs.get(0).getPosition());
            entity.setPositionLever(positionDetailBOs.get(0).getArrangementName());
        } else {
            entity.setArea(" ");
            entity.setDepartment(" ");
            entity.setPosition(" ");
            entity.setPositionLever(" ");
        }
        DimissionInfoDTO dimissionInfoDTO = new DimissionInfoDTO();
        dimissionInfoDTO.getConditions().add(Restrict.eq("username", entity.getName()));
        List<DimissionInfo> dimissionInfos = dimissionInfoSer.findByCis(dimissionInfoDTO);
        if (null != dimissionInfos && dimissionInfos.size() > 0) {
            entity.setApplyTime(dimissionInfos.get(0).getApplyDate());
            entity.setDismissTime(dimissionInfos.get(0).getDimissionDate());
        }
        return entity;
    }

    private void searchCondict(InterviewDTO dto) {
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }
    }

    //判断是否是执行层
    public Boolean isExecutiveLevel(String name) throws SerException {
        UserBO userBO = userAPI.findByUsername(name);
        List<OpinionBO> arrangements = arrangementAPI.findThawOpinion();

        String arrangementId = "";
        for (OpinionBO opinionBO : arrangements) {
            if ("执行层".equals(opinionBO.getValue())) {
                arrangementId = opinionBO.getId();
            }
        }
        if (StringUtils.isNotBlank(arrangementId) && userBO != null) {
            positionDetailUserAPI.checkAsUserArrangement(userBO.getId(), arrangementId);
            return positionDetailUserAPI.checkAsUserArrangement(userBO.getId(), arrangementId);
        }
        return false;
    }

    //判断是否是管理层模块负责人
    public Boolean isManager(String name) throws SerException {
        Boolean tar = false;
        UserBO userBO = userAPI.findByUsername(name);
        List<OpinionBO> arrangements = arrangementAPI.findThawOpinion();

        String arrangementId = "";
        for (OpinionBO opinionBO : arrangements) {
            if ("管理层".equals(opinionBO.getValue())) {
                arrangementId = opinionBO.getId();
            }
        }
        if (StringUtils.isNotBlank(arrangementId) && userBO != null) {
            return positionDetailUserAPI.checkAsUserArrangement(userBO.getId(), arrangementId);
        }
        return false;
    }
}