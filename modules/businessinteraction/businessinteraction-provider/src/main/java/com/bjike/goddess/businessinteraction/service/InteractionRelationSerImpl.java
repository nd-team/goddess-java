package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.excel.InteractionRelationImport;
import com.bjike.goddess.businessinteraction.excel.InteractionRelationImportTemple;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class InteractionRelationSerImpl extends ServiceImpl<InteractionRelation, InteractionRelationDTO> implements InteractionRelationSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对添加删除修改查看权限（部门级别）
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
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
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


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
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
    public Long countInter(InteractionRelationDTO interactionRelationDTO) throws SerException {
        Long count = super.count(interactionRelationDTO);
        return count;
    }

    @Override
    public InteractionRelationBO getOneById(String id) throws SerException {
        InteractionRelation interactionRelation = super.findById(id);
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class);
    }

    @Override
    public List<InteractionRelationBO> listIntera(InteractionRelationDTO interactionRelationDTO) throws SerException {
        checkSeeIdentity();
        List<InteractionRelation> interactionRelationList = super.findByCis(interactionRelationDTO, true);
        return BeanTransform.copyProperties(interactionRelationList, InteractionRelationBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public InteractionRelationBO addIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        checkSeeIdentity();
        InteractionRelation interactionRelation = BeanTransform.copyProperties(interactionRelationTO, InteractionRelation.class, true);
        interactionRelation.setCreateTime(LocalDateTime.now());
        interactionRelation.setInteractiveInfoDate(LocalDate.now());
        super.save(interactionRelation);
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public InteractionRelationBO editIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        checkSeeIdentity();
        InteractionRelation interactionRelation = super.findById(interactionRelationTO.getId());
        LocalDateTime dateTime = interactionRelation.getCreateTime();
        LocalDate date = interactionRelation.getInteractiveInfoDate();
        interactionRelation = BeanTransform.copyProperties(interactionRelationTO, InteractionRelation.class, true);
        interactionRelation.setCreateTime(dateTime);
        interactionRelation.setModifyTime(LocalDateTime.now());
        interactionRelation.setInteractiveInfoDate(date);
        super.update(interactionRelation);
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deleteIntera(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<InteractionRelation> list = super.findAll();
        List<InteractionRelationImport> interactionRelationImports = new ArrayList<>();
        for (InteractionRelation interactionRelation : list) {
            InteractionRelationImport excel = BeanTransform.copyProperties(interactionRelation, InteractionRelationImport.class);
            interactionRelationImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(interactionRelationImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<InteractionRelationImportTemple> interactionRelationImportTemples = new ArrayList<>();
        InteractionRelationImportTemple excel = new InteractionRelationImportTemple();
        excel.setArea("广州");
        excel.setCompanyName("北京艾佳天城");
        excel.setCompanyTel("1369856954");
        excel.setCompanyEmail("bjike_aj@163.com");
        excel.setCompanyMajorPage("主业");
        excel.setCompanyBussWay("通信");
        excel.setCompanyWebchat("52261");
        excel.setCompanyPublic("55623");
        excel.setCompanyQQ("452165425");
        excel.setCompanyTalk("论坛");
        interactionRelationImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(interactionRelationImportTemples, exce);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<InteractionRelationTO> interactionRelationTOS) throws SerException {
        List<InteractionRelation> interactionRelationList = BeanTransform.copyProperties(interactionRelationTOS, InteractionRelation.class, true);
        interactionRelationList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
            str.setInteractiveInfoDate(LocalDate.now());
        });
        super.save(interactionRelationList);
    }
}