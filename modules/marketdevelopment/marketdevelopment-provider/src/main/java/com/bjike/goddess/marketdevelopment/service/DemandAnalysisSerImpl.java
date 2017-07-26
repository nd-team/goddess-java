package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisBO;
import com.bjike.goddess.marketdevelopment.bo.DemandAnalysisExcelBO;
import com.bjike.goddess.marketdevelopment.dto.DemandAnalysisDTO;
import com.bjike.goddess.marketdevelopment.entity.DemandAnalysis;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.DemandAnalysisTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场需求分析业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:10 ]
 * @Description: [ 市场需求分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DemandAnalysisSerImpl extends ServiceImpl<DemandAnalysis, DemandAnalysisDTO> implements DemandAnalysisSer {

    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String demandManage = "demand-manage";


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
            flag = marPermissionSer.getMarPermission(demandManage);
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
            flag = marPermissionSer.getMarPermission(demandManage);
        } else {
            flag = true;
        }
        return flag;
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
    public DemandAnalysisBO save(DemandAnalysisTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(demandManage))
//            throw new SerException("您的帐号没有权限");
        DemandAnalysis entity = BeanTransform.copyProperties(to, DemandAnalysis.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO update(DemandAnalysisTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(demandManage))
//            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                DemandAnalysis entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemandAnalysisBO delete(DemandAnalysisTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(demandManage))
//            throw new SerException("您的帐号没有权限");
        DemandAnalysis entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByType(String type) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByCourse(String course) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysisBO> findByCourseType(String type, String course) throws SerException {
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<DemandAnalysis> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DemandAnalysisBO.class);
    }

    @Override
    public List<DemandAnalysis> findByPage(DemandAnalysisDTO dto) throws SerException {
//        if (!marPermissionSer.getMarPermission(demandManage))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("createTime=desc");
        return super.findByPage(dto);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(demandManage))
//            throw new SerException("您的帐号没有权限");
        DemandAnalysisDTO dto = new DemandAnalysisDTO();
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        dto.getSorts().add("createTime=desc");
        List<DemandAnalysis> list = super.findByCis(dto);
        List<DemandAnalysisExcelBO> boList = BeanTransform.copyProperties(list, DemandAnalysisExcelBO.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(boList, excel);
        return bytes;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        return marPermissionSer.getMarPermission(demandManage);
    }
}