package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.SchemeApplicationBO;
import com.bjike.goddess.staffshares.bo.SchemeBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.entity.Scheme;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.enums.Status;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.SchemeApplyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工持股管理业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class SchemeSerImpl extends ServiceImpl<Scheme, SchemeDTO> implements SchemeSer {
    @Autowired
    private UserAPI userApi;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userApi.currentUser();
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
        UserBO userBO = userApi.currentUser();
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
        UserBO userBO = userApi.currentUser();
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
        UserBO userBO = userApi.currentUser();
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(SchemeApplyTO to) throws SerException {
//        checkAddIdentity();
        Scheme entity = BeanTransform.copyProperties(to, Scheme.class, true, "status", "code", "programmeTime", "setters", "manager", "opinion", "sharesNum");
        entity.setStatus(Status.SUBMIT);
        String time = LocalDateTime.now().toString();
        entity.setCode("BJIKE" + LocalDate.now().toString().replace("-", "") + time.substring(time.indexOf(":") - 2, time.lastIndexOf(":") + 3).replace(":", ""));
        entity.setProgrammeTime(LocalDateTime.now());
        String userToken = RpcTransmit.getUserToken();
        String name = userApi.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        entity.setSetters(name);
        entity.setSharesNum(to.getNumber());
        super.save(entity);
    }

    public static void main(String[] args) {
        String time = LocalDateTime.now().toString();
        System.out.print("BJIKE" + LocalDate.now().toString().replace("-", "") + time.substring(time.indexOf(":") - 2, time.lastIndexOf(":") + 3).replace(":", ""));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(SchemeApplyTO to) throws SerException {
//        checkAddIdentity();
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        Scheme temp = super.findById(to.getId());

        Scheme scheme = BeanTransform.copyProperties(to, Scheme.class, true);
        BeanUtils.copyProperties(scheme, temp, "id", "createTime", "programmeTime", "setters", "sharesNum", "code", "status");
        String userToken = RpcTransmit.getUserToken();
        String name = userApi.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        temp.setSetters(name);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
//        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<SchemeApplicationBO> maps(SchemeDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        List<Scheme> list = super.findByPage(dto);
        List<SchemeApplicationBO> schemeBOList = BeanTransform.copyProperties(list, SchemeApplicationBO.class, false, "sharesNum");
        return schemeBOList;
    }

    @Override
    public SchemeBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Scheme scheme = super.findById(id);
        return BeanTransform.copyProperties(scheme, SchemeBO.class);
    }

    @Override
    public Long getTotal(SchemeDTO schemeDTO) throws SerException {
        searchCondition(schemeDTO);
        Long count = super.count(schemeDTO);
        return count;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void examine(SchemeApplyTO to) throws SerException {
//        checkAddIdentity();
        if (null == to.getStatus()) {
            throw new SerException("审核状态不能空");
        }
        Scheme temp = super.findById(to.getId());

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userApi.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());

        Boolean tar = false;
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName())) {
                    tar = true;
                }
            }
        }
        if (tar || "admin".equals(userBO.getUsername())) {
            temp.setStatus(to.getStatus());
            temp.setOpinion(to.getOpinion());
            temp.setManager(userBO.getUsername());
        } else {
            throw new SerException("当前用户没有权限审核");
        }
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void issue(String id) throws SerException {
        Scheme scheme = super.findById(id);
        if(null == scheme){
            throw new SerException("目标对象不能为空");
        }

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userApi.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());

        Boolean tar = false;
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName())) {
                    tar = true;
                }
            }
        }
        if (tar || "admin".equals(userBO.getUsername())) {
            scheme.setStatus(Status.ISSUED);
        } else {
            throw new SerException("当前用户没有权限发行");
        }
    }


    @Override
    public List<SchemeIssueBO> list(SchemeDTO dto) throws SerException {
//        checkSeeIdentity();
        //查询已发行的数据
        dto.setStatus(Status.ISSUED);
        searchCondition(dto);
        List<Scheme> list = super.findByPage(dto);
        List<SchemeIssueBO> schemeBOList = BeanTransform.copyProperties(list, SchemeIssueBO.class);
        if (null != schemeBOList && schemeBOList.size() > 0) {
            for (SchemeIssueBO schemeIssueBO : schemeBOList) {
                schemeIssueBO.setData(LocalDate.now().toString());
            }
        }
        return schemeBOList;
    }

    @Override
    public SchemeIssueBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Scheme scheme = super.findById(id);
        SchemeIssueBO schemeIssueBO = BeanTransform.copyProperties(scheme, SchemeIssueBO.class, false, "status", "type", "programmeTime");
        schemeIssueBO.setData(LocalDate.now().toString());
        return schemeIssueBO;
    }

    @Override
    public Long count(SchemeDTO schemeDTO) throws SerException {
        schemeDTO.setStatus(Status.ISSUED);
        searchCondition(schemeDTO);
        Long count = super.count(schemeDTO);
        return count;
    }


    public void searchCondition(SchemeDTO schemeDTO) throws SerException {
        /**
         * 审核状态
         */
        if (null != schemeDTO.getStatus()) {
            schemeDTO.getConditions().add(Restrict.eq("status", schemeDTO.getStatus()));
        }
        /**
         * 股份种类
         */
        if (null != schemeDTO.getType()) {
            schemeDTO.getConditions().add(Restrict.eq("type", schemeDTO.getType()));
        }/**
         * 方案代码
         */
        if (StringUtils.isNotBlank(schemeDTO.getCode())) {
            schemeDTO.getConditions().add(Restrict.like("code", schemeDTO.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(schemeDTO.getName())) {
            schemeDTO.getConditions().add(Restrict.like("name", schemeDTO.getName()));
        }
    }

    /**
     * 日期格式(年月日)
     */
    private void checkDate(SchemeApplyTO to) throws SerException {
        try {
            DateUtil.parseDate(to.getTime());
            DateUtil.parseDate(to.getAccountingTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }
}