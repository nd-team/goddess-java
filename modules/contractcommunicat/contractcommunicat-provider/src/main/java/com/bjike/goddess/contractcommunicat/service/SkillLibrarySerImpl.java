package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.HistoryAppraiseBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.bo.SkillLibraryBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.excel.ProjectOutsourcingExcel;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 谈判技巧库业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class SkillLibrarySerImpl extends ServiceImpl<SkillLibrary, SkillLibraryDTO> implements SkillLibrarySer {

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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


    public void getCusPermission() throws SerException {
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
//        Boolean permission = cusPermissionSer.getCusPermission("1");
//
//        if (!permission) {
//            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
//        }
//    }
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
    public Long count(SkillLibraryDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public SkillLibraryBO getOne(String id) throws SerException {
        SkillLibrary skillLibrary = super.findById(id);
        SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
        return skillLibraryBO;
    }

    @Override
    public List<SkillLibraryBO> list(SkillLibraryDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getPhase())) {
            dto.getConditions().add(Restrict.eq("phase", dto.getPhase()));
        }
        if (StringUtils.isNotBlank(dto.getSceneType())) {
            dto.getConditions().add(Restrict.eq("sceneType", dto.getSceneType()));
        }
        if (StringUtils.isNotBlank(dto.getSkills())) {
            dto.getConditions().add(Restrict.eq("skills", dto.getSkills()));
        }
        if (StringUtils.isNotBlank(dto.getStrategy())) {
            dto.getConditions().add(Restrict.eq("strategy", dto.getStrategy()));
        }
        if (StringUtils.isNotBlank(dto.getSource())) {
            dto.getConditions().add(Restrict.eq("source", dto.getSource()));
        }
        List<SkillLibrary> skillLibraries = super.findByPage(dto);
        List<SkillLibraryBO> skillLibraryBOS = BeanTransform.copyProperties(skillLibraries, SkillLibraryBO.class);
        return skillLibraryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillLibraryBO insert(SkillLibraryTO to) throws SerException {
        SkillLibrary skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
        skillLibrary.setCreateTime(LocalDateTime.now());
        super.save(skillLibrary);
        SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
        return skillLibraryBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillLibraryBO edit(SkillLibraryTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            SkillLibrary skillLibrary = super.findById(to.getId());
            LocalDateTime createTime = skillLibrary.getCreateTime();
            skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
            skillLibrary.setCreateTime(createTime);
            skillLibrary.setModifyTime(LocalDateTime.now());
            super.update(skillLibrary);
            SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
            return skillLibraryBO;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public SkillLibraryBO appraise(SkillLibraryTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            SkillLibrary skillLibrary = super.findById(to.getId());
            LocalDateTime createTime = skillLibrary.getCreateTime();
            skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
            skillLibrary.setCreateTime(createTime);
            skillLibrary.setModifyTime(LocalDateTime.now());
            super.update(skillLibrary);

            SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
            return skillLibraryBO;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<HistoryAppraiseBO> historyAppraise(String id) throws SerException {
        List<HistoryAppraiseBO> historyAppraiseBOS = new ArrayList<>();
        if (null == id) {
            throw new SerException("id不能为空");
        }
        SkillLibraryDTO dto = new SkillLibraryDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        List<SkillLibrary> skillLibraries = super.findByCis(dto);
        for (SkillLibrary skillLibrary : skillLibraries) {
            HistoryAppraiseBO bo = new HistoryAppraiseBO();
            bo.setHistoryAppraise(skillLibrary.getAppraise());
            historyAppraiseBOS.add(bo);
        }
        return historyAppraiseBOS;

    }
}