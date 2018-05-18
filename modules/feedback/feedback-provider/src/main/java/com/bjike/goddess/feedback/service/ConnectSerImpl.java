package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ConnectBO;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.entity.Connect;
import com.bjike.goddess.feedback.enums.GuideAddrStatus;
import com.bjike.goddess.feedback.to.ConnectTO;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
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
 * 各类沟通模板业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ConnectSerImpl extends ServiceImpl<Connect, ConnectDTO> implements ConnectSer {
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
    public Long count(ConnectDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ConnectBO getOne(String id) throws SerException {
        Connect connect = super.findById(id);
        return BeanTransform.copyProperties(connect, ConnectBO.class);
    }

    @Override
    public List<ConnectBO> list(ConnectDTO dto) throws SerException {
        checkSeeIdentity();
        List<Connect> connects = super.findByCis(dto,true);
        List<ConnectBO> connectBOS = BeanTransform.copyProperties(connects, ConnectBO.class);
        return connectBOS;
    }

//    @Override
//    @Transactional(rollbackFor = SerException.class)
//    public ConnectBO insert(ConnectTO to) throws SerException {
//        return null;
//    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConnectBO edit(ConnectTO to) throws SerException {
        checkAddIdentity();
        Connect connect = super.findById(to.getId());
        BeanTransform.copyProperties(to, connect, true);
        connect.setModifyTime(LocalDateTime.now());
        super.update(connect);
        return BeanTransform.copyProperties(connect, ConnectBO.class);
    }

//    @Override
//    @Transactional(rollbackFor = SerException.class)
//    public void remove(String id) throws SerException {
//        if (StringUtils.isNotBlank(id)) {
//            super.remove(id);
//        } else {
//            throw new SerException("id不能为空");
//        }
//
//    }

    @Override
    public List<String> getSort() throws SerException {
        String[] feilds = new String[]{"sorting"};
        List<ConnectBO> connectBOS = super.findBySql("select sorting from feedback_connect group by sorting order by sorting asc ", ConnectBO.class, feilds);
        List<String> list = connectBOS.stream().map(ConnectBO::getSorting)
                .filter(sorting -> sorting != null || !"".equals(sorting.trim())).distinct().collect(Collectors.toList());

        return list;
    }

    @Override
    public List<ConnectBO> getConnect(String sorting) throws SerException {
        Connect connect = new Connect();
        if (StringUtils.isNotBlank(sorting)) {
            ConnectDTO dto = new ConnectDTO();
            dto.getConditions().add(Restrict.eq("sorting", sorting));
            connect = super.findOne(dto);
        }
        List<ConnectBO> connectBOS = BeanTransform.copyProperties(connect, ConnectBO.class);
        return connectBOS;
    }
}