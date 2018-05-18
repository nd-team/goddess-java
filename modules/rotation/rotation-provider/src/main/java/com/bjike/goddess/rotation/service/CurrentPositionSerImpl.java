package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.rotation.bo.CurrentPositionBO;
import com.bjike.goddess.rotation.dto.CurrentPositionDTO;
import com.bjike.goddess.rotation.entity.CurrentPosition;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.excel.CurrentPositionExport;
import com.bjike.goddess.rotation.excel.SonPermissionObject;
import com.bjike.goddess.rotation.to.CurrentPositionTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
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
 * 目前岗位情况业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:30 ]
 * @Description: [ 目前岗位情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class CurrentPositionSerImpl extends ServiceImpl<CurrentPosition, CurrentPositionDTO> implements CurrentPositionSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    UserAPI userAPI;

    @Override
    public Long count(CurrentPositionDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<CurrentPositionBO> list(CurrentPositionDTO dto) throws SerException {

        //todo 普通岗位只能查看自己的，特殊岗位可以查看全部
        List<CurrentPositionBO> bos = BeanTransform.copyProperties(super.findByPage(dto), CurrentPositionBO.class);
        return bos;
    }

    @Override
    public void add(CurrentPositionTO to) throws SerException {
        CurrentPosition entity = BeanTransform.copyProperties(to, CurrentPosition.class);
        super.save(entity);
    }

    @Override
    public void update(CurrentPositionTO to) throws SerException {
        CurrentPosition oldEntity = super.findById(to.getId());
        if (null == oldEntity) {
            throw new SerException("更新实体不存在");
        }
        CurrentPosition entity = BeanTransform.copyProperties(to, CurrentPosition.class);
        entity.setCreateTime(oldEntity.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
    }

    @Override
    public void update(String id, String arrangement) throws SerException {
        CurrentPosition oldEntity = super.findById(id);
        if (null == oldEntity) {
            throw new SerException("更新实体不存在");
        }

        oldEntity.setArrangement(arrangement);
        oldEntity.setModifyTime(LocalDateTime.now());
        super.update(oldEntity);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CurrentPositionBO getOne(String id) throws SerException {
        CurrentPositionBO bo = BeanTransform.copyProperties(super.findById(id), CurrentPositionBO.class);
        return bo;
    }

    @Override
    public void importExcel(List<CurrentPositionTO> tos) throws SerException {
        List<CurrentPosition> entities = BeanTransform.copyProperties(tos, CurrentPosition.class);
        super.save(entities);
    }

    @Override
    public byte[] exportExcel(CurrentPositionDTO dto) throws SerException {

        List<CurrentPosition> list = super.findByCis(dto);
        List<CurrentPositionExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            CurrentPositionExport export = BeanTransform.copyProperties(str, CurrentPositionExport.class,
                    "rotation", "delay", "delayRotation");
            //是否应该轮岗
            if (null != str.getRotation()) {
                export.setRotation(str.getRotation() ? "是" : "否");
            }
            //是否延后
            if (null != str.getDelay()) {
                export.setDelay(str.getDelay() ? "是" : "否");
            }
            //（延后）是否应该轮岗
            if (null != str.getDelayRotation()) {
                export.setDelayRotation(str.getDelayRotation() ? "是" : "否");
            }
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;

    }

    @Override
    public byte[] exportTemplate() throws SerException {
        List<CurrentPositionExport> exports = new ArrayList<>();
        CurrentPositionExport export = new CurrentPositionExport();
        export.setName("张三");
        export.setArea("广州");
        export.setDepartment("广州研发项目组");
        export.setEntryTime("2017-09-12");
        export.setTurnPositiveTime("2017-12-12");
        export.setArrangement("管理层");
        export.setRotationArrangement("执行层");
        export.setGetTime("2017-12-12");
        export.setRotationCycle(4);
        export.setSubsidy(67373.0);
        export.setRotation("是");
        export.setDelay("否");
        export.setDelayTimes(0);
        export.setDelayCycle(0.0);
        export.setDelayRotation("否");
        export.setRotationTimes(1);

        exports.add(export);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

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
            flag = cusPermissionSer.getCusPermission("2");
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
}