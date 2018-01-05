package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.entity.RecruitPro;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.type.AuditType;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitProSerImpl extends ServiceImpl<RecruitPro, RecruitProDTO> implements RecruitProSer {
    @Autowired
    private UserAPI userAPI;
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.getCusPermission("2",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 运营商务部意见
     */
    private void checkSwIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 总经办审核
     */
    private void checkMaIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.manCusPermission("5",null);
            if (!flag) {
                throw new SerException("您不是总经办的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.getCusPermission("2",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 运营商务部意见
     */
    private Boolean guideSwIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 总经办审核
     */
    private Boolean guideMaIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.manCusPermission("5",null);
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
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSw = guideSwIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMa = guideMaIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAdd || flagSw || flagMa) {
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case BUINESS:
                flag = guideSwIdentity();
                break;
            case MANAGER:
                flag = guideMaIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询招聘方案
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<RecruitProBO> list(RecruitProDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<RecruitPro> list = super.findByPage(dto);
        List<RecruitProBO> listBO = BeanTransform.copyProperties(list, RecruitProBO.class);
        return listBO;
    }

    private List<RecruitProBO> search(RecruitProDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getRecruitSite())) {
            dto.getConditions().add(Restrict.like("recruitSite", dto.getRecruitSite()));
        }
        if (null != dto.getHaveContract()) {
            dto.getConditions().add(Restrict.eq("haveContract", dto.getHaveContract()));
        }
        List<RecruitPro> list = super.findByCis(dto);
        List<RecruitProBO> recruitProBOS = BeanTransform.copyProperties(list, RecruitProBO.class);
        return recruitProBOS;
    }

    /**
     * 保存招聘方案
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RecruitProBO save(RecruitProTO to) throws SerException {
        checkAddIdentity();
        RecruitPro failFirstInterviewReason = BeanTransform.copyProperties(to, RecruitPro.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        RecruitProBO bo = BeanTransform.copyProperties(failFirstInterviewReason, RecruitProBO.class);
        return bo;
    }

    /**
     * 更新招聘方案
     *
     * @param to 招聘方案to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitProTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitPro model = super.findById(to.getId());
            if (model != null) {
                updateRecruitPro(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新招聘方案
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRecruitPro(RecruitProTO to, RecruitPro model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除招聘方案
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(RecruitPro entity) throws SerException {
        checkAddIdentity();
        super.remove(entity);
    }

    /**
     * 综合资源部意见
     *
     * @param id        招聘方案唯一标识
     * @param zhOpinion 综合资源部意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void zhOpinion(String id, String zhOpinion) throws SerException {
        checkAddIdentity();
        RecruitPro model = super.findById(id);
        model.setZhOpinion(zhOpinion);
        super.update(model);
    }

    /**
     * 运营商务部审核
     *
     * @param id        招聘方案唯一标识
     * @param yyOpinion 运营商务部意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void yyOpinion(String id, String yyOpinion, Boolean moneyReady) throws SerException {
        checkSwIdentity();
        RecruitPro model = super.findById(id);
        model.setYyOpinion(yyOpinion);
        model.setMoneyReady(moneyReady);
        super.update(model);
    }

    /**
     * 总经办意见
     *
     * @param id         招聘方案唯一标识
     * @param zjbOpinion 总经办意见
     * @param auditType  审核类型
     * @throws SerException
     */
    @Override
    public void zjbOpinion(String id, String zjbOpinion, AuditType auditType) throws SerException {
        checkMaIdentity();
        RecruitPro model = super.findById(id);
        model.setZjbOpnion(zjbOpinion);
        model.setAuditType(auditType);
        super.update(model);
    }
}
