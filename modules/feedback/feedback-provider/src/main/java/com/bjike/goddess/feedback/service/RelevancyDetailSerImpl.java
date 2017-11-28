package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.entity.RelevancyDetail;
import com.bjike.goddess.feedback.enums.GuideAddrStatus;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 各模块关联明细业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:23 ]
 * @Description: [ 各模块关联明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class RelevancyDetailSerImpl extends ServiceImpl<RelevancyDetail, RelevancyDetailDTO> implements RelevancyDetailSer {
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
            flag = cusPermissionSer.getCusPermission("2");
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
    public Long count(RelevancyDetailDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public RelevancyDetailBO getOne(String id) throws SerException {
        RelevancyDetail relevancyDetail = super.findById(id);
        return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
    }

    @Override
    public List<RelevancyDetailBO> list(RelevancyDetailDTO dto) throws SerException {
        checkSeeIdentity();
        List<RelevancyDetail> relevancyDetails = super.findByCis(dto);
        List<RelevancyDetailBO> relevancyDetailBOS = BeanTransform.copyProperties(relevancyDetails, RelevancyDetailBO.class);
        return relevancyDetailBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public RelevancyDetailBO insert(RelevancyDetailTO to) throws SerException {
        checkAddIdentity();
        RelevancyDetail relevancyDetail = BeanTransform.copyProperties(to, RelevancyDetail.class, true);
        relevancyDetail.setCreateTime(LocalDateTime.now());
        super.save(relevancyDetail);
        return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public RelevancyDetailBO edit(RelevancyDetailTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            RelevancyDetail relevancyDetail = super.findById(to.getId());
            LocalDateTime createTime = relevancyDetail.getCreateTime();
            relevancyDetail = BeanTransform.copyProperties(to, RelevancyDetail.class, true);
            relevancyDetail.setCreateTime(createTime);
            relevancyDetail.setModifyTime(LocalDateTime.now());
            super.update(relevancyDetail);
            return BeanTransform.copyProperties(relevancyDetail, RelevancyDetailBO.class);
        } else {
            throw new SerException("id不能为空");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<String> getMainFunction() throws SerException {
        String[] fields = new String[]{"mainFunction"};
        List<RelevancyDetailBO> relevancyDetailBOS = super.findBySql("select mainFunction from feedback_relevancydetail group by mainFunction order by mainFunction asc ", RelevancyDetailBO.class, fields);

        List<String> list = relevancyDetailBOS.stream().map(RelevancyDetailBO::getMainFunction)
                .filter(mainFunction -> (mainFunction != null || !"".equals(mainFunction.trim()))).distinct().collect(Collectors.toList());
        return list;
    }
}