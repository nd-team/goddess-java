package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.entity.AgeAssist;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 工龄补助业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class AgeAssistSerImpl extends ServiceImpl<AgeAssist, AgeAssistDTO> implements AgeAssistSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AssistanceEmpSer assistanceEmpSer;
    @Autowired
    private AssistancePlanSer assistancePlanSer;
    @Autowired
    private AssistanceRecordSer assistanceRecordSer;
    @Autowired
    private AssistanceStandardSer assistanceStandardSer;
    @Autowired
    private ComputerAssistSer computerAssistSer;
    @Autowired
    private HotAssistSer hotAssistSer;
    @Autowired
    private HouseAssistSer houseAssistSer;

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
            flag = cusPermissionSer.busCusPermission("1");
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
            flag = cusPermissionSer.busCusPermission("1");
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
        obj.setName("ageassist");
        obj.setDescribesion("工龄补助");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = assistanceEmpSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("assistanceemp");
        obj.setDescribesion("补助员工名单");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = assistancePlanSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("assistanceplan");
        obj.setDescribesion("补助方案");
        if (flagSeeDis1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis2 = assistanceRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("assistancerecord");
        obj.setDescribesion("公司员工补助信息记录");
        if (flagSeeDis2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis3 = assistanceStandardSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("assistancestandard");
        obj.setDescribesion("补助标准");
        if (flagSeeDis3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis4 = computerAssistSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("computerassist");
        obj.setDescribesion("电脑补助");
        if (flagSeeDis4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis5 = hotAssistSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("hotassist");
        obj.setDescribesion("高温补助");
        if (flagSeeDis5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis6 = houseAssistSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("houseassist");
        obj.setDescribesion("住宿补助");
        if (flagSeeDis6) {
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
            case COLLECT:
                flag = guideSeeIdentity();
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
    public Long countAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        if (StringUtils.isNotBlank(ageAssistDTO.getArea())) {
            ageAssistDTO.getConditions().add(Restrict.eq("area", ageAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getProjectGroup())) {
            ageAssistDTO.getConditions().add(Restrict.eq("projectGroup", ageAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getEmpName())) {
            ageAssistDTO.getConditions().add(Restrict.eq("empName", ageAssistDTO.getEmpName()));
        }
        ageAssistDTO.getSorts().add("createTime=desc");
        Long count = super.count(ageAssistDTO);
        return count;
    }

    @Override
    public AgeAssistBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AgeAssist list = super.findById(id);

        return BeanTransform.copyProperties(list, AgeAssistBO.class);
    }

    @Override
    public List<AgeAssistBO> listAgeAssist(AgeAssistDTO ageAssistDTO) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isNotBlank(ageAssistDTO.getArea())) {
            ageAssistDTO.getConditions().add(Restrict.eq("area", ageAssistDTO.getArea()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getProjectGroup())) {
            ageAssistDTO.getConditions().add(Restrict.eq("projectGroup", ageAssistDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(ageAssistDTO.getEmpName())) {
            ageAssistDTO.getConditions().add(Restrict.eq("empName", ageAssistDTO.getEmpName()));
        }
        ageAssistDTO.getSorts().add("createTime=desc");
        List<AgeAssist> list = super.findByCis(ageAssistDTO, true);

        return BeanTransform.copyProperties(list, AgeAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AgeAssistBO addAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        checkAddIdentity();
        if (ageAssistTO.getJobAge() == null || ageAssistTO.getJobAge().isNaN()) {
            ageAssistTO.setJobAge(0d);
        }
        AgeAssist ageAssist = BeanTransform.copyProperties(ageAssistTO, AgeAssist.class, true);

        ageAssist.setCreateTime(LocalDateTime.now());
        super.save(ageAssist);
        return BeanTransform.copyProperties(ageAssist, AgeAssistBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public AgeAssistBO editAgeAssist(AgeAssistTO ageAssistTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(ageAssistTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (ageAssistTO.getJobAge() == null || ageAssistTO.getJobAge().isNaN()) {
            ageAssistTO.setJobAge(0d);
        }
        AgeAssist ageAssist = BeanTransform.copyProperties(ageAssistTO, AgeAssist.class, true);
        AgeAssist rs = super.findById(ageAssistTO.getId());

        rs.setEmpName(ageAssist.getEmpName());
        rs.setModifyTime(LocalDateTime.now());
        super.update(rs);
        return BeanTransform.copyProperties(rs, AgeAssistBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAgeAssist(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public Double getJobAge(String userName) throws SerException {
        Double jobAge = 0d;
        AgeAssistDTO ageAssistDTO = new AgeAssistDTO();
        ageAssistDTO.getConditions().add(Restrict.eq("empName", userName));
        List<AgeAssist> ageAssists = super.findByCis(ageAssistDTO);
        if (!CollectionUtils.isEmpty(ageAssists)) {
            jobAge = ageAssists.get(0).getJobAge();
        }
        return jobAge;
    }

    @Override
    public AgeAssistBO findAge(String employeeName) throws SerException {
        AgeAssistDTO dto = new AgeAssistDTO();
        dto.getConditions().add(Restrict.eq("empName",employeeName));
        AgeAssist ageAssist = super.findOne(dto);
        AgeAssistBO bo = BeanTransform.copyProperties(ageAssist,AgeAssistBO.class,false);
        return bo;
    }
}