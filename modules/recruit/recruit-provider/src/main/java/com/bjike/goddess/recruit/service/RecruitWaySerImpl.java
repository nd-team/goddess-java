package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.entity.RecruitWay;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.util.parsing.combinator.testing.Str;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitWaySerImpl extends ServiceImpl<RecruitWay, RecruitWayDTO> implements RecruitWaySer {
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询招聘渠道
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<RecruitWay> list = super.findByPage(dto);
        List<RecruitWayBO> listBO = BeanTransform.copyProperties(list, RecruitWayBO.class);
        return listBO;
    }

    private List<RecruitWayBO> search(RecruitWayDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getRecruitSite())){
            dto.getConditions().add(Restrict.like("recruitSite",dto.getRecruitSite()));
        }
        if(StringUtils.isNotBlank(dto.getChannelContact())) {
            dto.getConditions().add(Restrict.like("channelContact",dto.getChannelContact()));
        }
        if(null!= dto.getStatus()){
            dto.getConditions().add(Restrict.like("status",dto.getStatus()));
        }
        List<RecruitWay> list = super.findByCis(dto);
        List<RecruitWayBO> recruitWayBOS = BeanTransform.copyProperties(list,RecruitWayBO.class);
        return recruitWayBOS;
    }

    /**
     * 保存招聘渠道
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RecruitWayBO save(RecruitWayTO to) throws SerException {
        checkAddIdentity();
        RecruitWay recruitWay = BeanTransform.copyProperties(to, RecruitWay.class, true, "suitPosition");
        recruitWay.setSuitPosition(StringUtils.join(to.getSuitPosition(), ","));
        recruitWay = super.save(recruitWay);
        RecruitWayBO bo = BeanTransform.copyProperties(recruitWay, RecruitWayBO.class);
        return bo;
    }

    /**
     * 更新招聘渠道
     *
     * @param to 招聘渠道to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitWayTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitWay model = super.findById(to.getId());

            if (model != null) {
                updateRecruitWay(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新招聘渠道
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRecruitWay(RecruitWayTO to, RecruitWay model) throws SerException {
        BeanTransform.copyProperties(to, model, true, "suitPosition");
        model.setSuitPosition(StringUtils.join(to.getSuitPosition(), ","));
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除招聘渠道
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(RecruitWay entity) throws SerException {
        checkAddIdentity();
        super.remove(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thaw(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            RecruitWay recruitWay = super.findById(id);
            recruitWay.setStatus(Status.THAW);
            super.update(recruitWay);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congeal(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            RecruitWay recruitWay = super.findById(id);
            recruitWay.setStatus(Status.CONGEAL);
            super.update(recruitWay);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Set<String> allRecruitName() throws SerException {
        Set<String> set = new HashSet<>();
        List<RecruitWay> list = super.findAll();
        for (RecruitWay r : list) {
            set.add(r.getRecruitName());
        }
        return set;
    }

}
