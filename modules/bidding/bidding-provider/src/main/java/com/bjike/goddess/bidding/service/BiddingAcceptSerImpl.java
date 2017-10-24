package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAcceptBO;
import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.entity.BiddingAccept;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingAcceptTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionInstructionAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招标问题受理和处理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingAcceptSerImpl extends ServiceImpl<BiddingAccept, BiddingAcceptDTO> implements BiddingAcceptSer {
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
    public Long count(BiddingAcceptDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public BiddingAcceptBO getOne(String id) throws SerException {
        BiddingAccept biddingAccept = super.findById(id);
        return BeanTransform.copyProperties(biddingAccept, BiddingAcceptBO.class);
    }

    @Override
    public List<BiddingAcceptBO> list(BiddingAcceptDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<BiddingAccept> biddingAccepts = super.findByCis(dto);
        List<BiddingAcceptBO> biddingAcceptBOS = BeanTransform.copyProperties(biddingAccepts, BiddingAcceptBO.class);
        return biddingAcceptBOS;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAcceptBO save(BiddingAcceptTO to) throws SerException {
        checkAddIdentity();
        UserBO userBO = userAPI.currentUser();
        BiddingAccept biddingAccept = BeanTransform.copyProperties(to, BiddingAccept.class, true);
        biddingAccept.setCreateTime(LocalDateTime.now());
        biddingAccept.setInputUser(userBO.getUsername());
        String name = to.getProblemExhibitor();
        List<PositionDetailBO> positionDetailBOS = positionDetailUserAPI.getPositionDetail(name);

        for (PositionDetailBO positionDetailBO : positionDetailBOS) {
            biddingAccept.setArea(positionDetailBO.getArea());
            biddingAccept.setDepartment(positionDetailBO.getDepartmentName());
        }
        super.save(biddingAccept);
        BiddingAcceptBO bo = BeanTransform.copyProperties(biddingAccept, BiddingAcceptBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAcceptBO edit(BiddingAcceptTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            BiddingAccept biddingAccept = super.findById(to.getId());
            BeanTransform.copyProperties(to, biddingAccept, true);
            biddingAccept.setCreateTime(LocalDateTime.now());
            super.update(biddingAccept);
            BiddingAcceptBO bo = BeanTransform.copyProperties(biddingAccept, BiddingAcceptBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAcceptBO notification(BiddingAcceptTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            BiddingAccept biddingAccept = super.findById(to.getId());
            BeanTransform.copyProperties(to, biddingAccept, true);
            biddingAccept.setCreateTime(LocalDateTime.now());
            super.save(biddingAccept);
            BiddingAcceptBO bo = BeanTransform.copyProperties(biddingAccept, BiddingAcceptBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }
    private void search(BiddingAcceptDTO dto)throws SerException{
        //问题提出人
        if(StringUtils.isNotBlank(dto.getProblemExhibitor())){
            dto.getConditions().add(Restrict.like("problemExhibitor",dto.getProblemExhibitor()));
        }
        //状态
        if(StringUtils.isNotBlank(dto.getStatus())){
            dto.getConditions().add(Restrict.like("status",dto.getStatus()));
        }
        //是否闭环
        if(dto.getClosedLoop() != null){
            dto.getConditions().add(Restrict.like("closedLoop",dto.getClosedLoop()));
        }
        //是否通报
        if(dto.getNotification() != null){
            dto.getConditions().add(Restrict.like("notification",dto.getNotification()));
        }
    }
}