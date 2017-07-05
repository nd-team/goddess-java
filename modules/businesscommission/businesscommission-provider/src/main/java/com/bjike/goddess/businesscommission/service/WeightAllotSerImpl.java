package com.bjike.goddess.businesscommission.service;

import com.bjike.goddess.businesscommission.bo.WeightAllotBO;
import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
import com.bjike.goddess.businesscommission.entity.WeightAllot;
import com.bjike.goddess.businesscommission.enums.GuideAddrStatus;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.excel.WeightAllotExcel;
import com.bjike.goddess.businesscommission.excel.WeightAllotTemplateExcel;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.WeightAllotTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务提成权重分配表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:34 ]
 * @Description: [ 业务提成权重分配表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businesscommissionSerCache")
@Service
public class WeightAllotSerImpl extends ServiceImpl<WeightAllot, WeightAllotDTO> implements WeightAllotSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CommissionQuotaSer commissionQuotaSer;


    @Override
    public Long countWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
        searchCondition(weightAllotDTO);
        Long count = super.count(weightAllotDTO);
        return count;
    }

    @Override
    public WeightAllotBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }

        WeightAllot weightAllot = super.findById(id);
        return BeanTransform.copyProperties(weightAllot, WeightAllotBO.class);
    }

    @Override
    public List<WeightAllotBO> listWeightAllot(WeightAllotDTO weightAllotDTO) throws SerException {
        //checkSeeIdentity();

        searchCondition(weightAllotDTO);
        List<WeightAllot> list = super.findByPage(weightAllotDTO);
        List<WeightAllotBO> weightAllotBOS = new ArrayList<>();
        list.stream().forEach(str -> {
            WeightAllotBO bo = BeanTransform.copyProperties(str, WeightAllotBO.class);
            weightAllotBOS.add(bo);
        });
        return weightAllotBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeightAllotBO addWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
        checkAddIdentity();
        WeightAllot weightAllot = BeanTransform.copyProperties(weightAllotTO, WeightAllot.class, true);

        weightAllot.setCreateTime(LocalDateTime.now());
        super.save(weightAllot);

        WeightAllotBO weightAllotBO = BeanTransform.copyProperties(weightAllot, WeightAllotBO.class);
        return weightAllotBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WeightAllotBO editWeightAllot(WeightAllotTO weightAllotTO) throws SerException {
        checkAddIdentity();

        WeightAllot temp = super.findById(weightAllotTO.getId());

        WeightAllot weightAllot = BeanTransform.copyProperties(weightAllotTO, WeightAllot.class, true);
        BeanUtils.copyProperties(weightAllot, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        WeightAllotBO weightAllotBO = BeanTransform.copyProperties(temp, WeightAllotBO.class);
        return weightAllotBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteWeightAllot(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    public void searchCondition(WeightAllotDTO weightAllotDTO) throws SerException {
        /**
         * 地区
         */
        if (weightAllotDTO.getArea() != null) {
            weightAllotDTO.getConditions().add(Restrict.eq("area", weightAllotDTO.getArea()));
        }
        /**
         * 项目名称
         */
        if (StringUtils.isNotBlank(weightAllotDTO.getProjectName())) {
            weightAllotDTO.getConditions().add(Restrict.like("projectName", weightAllotDTO.getProjectName()));
        }
        /**
         * 信息提供占比
         */
        if (weightAllotDTO.getMessageProportion() != null) {
            weightAllotDTO.getConditions().add(Restrict.eq("messageProportion", weightAllotDTO.getMessageProportion()));
        }
        /**
         * 业务揽接占比
         */
        if (null != (weightAllotDTO.getBusinessProportion())) {
            weightAllotDTO.getConditions().add(Restrict.like("businessProportion", weightAllotDTO.getBusinessProportion()));
        }
        /**
         * 业务洽谈占比
         */
        if (null != (weightAllotDTO.getTalkProportion())) {
            weightAllotDTO.getConditions().add(Restrict.like("talkProportion", weightAllotDTO.getTalkProportion()));
        }
        /**
         * 维护占比
         */
        if (null != (weightAllotDTO.getMaintainProportion())) {
            weightAllotDTO.getConditions().add(Restrict.like("maintainProportion", weightAllotDTO.getMaintainProportion()));
        }
        /**
         * 剩余占比
         */
        if (weightAllotDTO.getSurplusProportion() != null) {
            weightAllotDTO.getConditions().add(Restrict.eq("surplusProportion", weightAllotDTO.getSurplusProportion()));
        }
        /**
         * 总比例
         */
        if (null != (weightAllotDTO.getTotalProportion())) {
            weightAllotDTO.getConditions().add(Restrict.eq("totalProportion", weightAllotDTO.getTotalProportion()));
        }
    }

    @Override
    public WeightAllotBO importExcel(List<WeightAllotTO> weightAllotTO) throws SerException {

        List<WeightAllot> weightAllot = BeanTransform.copyProperties(weightAllotTO, WeightAllot.class, true);
        weightAllot.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(weightAllot);

        WeightAllotBO weightAllotBO = BeanTransform.copyProperties(new WeightAllot(), WeightAllotBO.class);
        return weightAllotBO;
    }

    @Override
    public byte[] exportExcel(WeightAllotDTO dto) throws SerException {
//        getCusPermission();

        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }

        List<WeightAllot> list = super.findByCis(dto);

        List<WeightAllotExcel> weightAllotExports = new ArrayList<>();
        list.stream().forEach(str -> {
            WeightAllotExcel excel = BeanTransform.copyProperties(str, WeightAllotExcel.class);
            weightAllotExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(weightAllotExports, excel);
        return bytes;
    }


    @Override
    public byte[] templateExport() throws SerException {
//        getCusPermission();

        List<WeightAllotTemplateExcel> weightAllotTemplateExcel = new ArrayList<>();

        WeightAllotTemplateExcel excel = new WeightAllotTemplateExcel();
        excel.setArea("dad");
        excel.setProjectName("fhdj");
        excel.setMessageProportion(45.1);
        excel.setBusinessProportion(45.1);
        excel.setTalkProportion(45.1);
        excel.setMaintainProportion(45.1);
        excel.setSurplusProportion(45.1);
        excel.setTotalProportion(45.1);
        excel.setRemark("fds");
        weightAllotTemplateExcel.add( excel );
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(weightAllotTemplateExcel, exce);
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




    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("weightallot");
        obj.setDescribesion("业务提成权重分配");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = commissionQuotaSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("commissionquota");
        obj.setDescribesion("业务提成定额");
        if (flagSeeDis) {
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
        return flag;
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


}