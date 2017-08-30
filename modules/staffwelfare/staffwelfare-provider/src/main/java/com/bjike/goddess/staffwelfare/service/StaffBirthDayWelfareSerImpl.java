package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfare.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDayWelfare;
import com.bjike.goddess.staffwelfare.entity.ThankStatement;
import com.bjike.goddess.staffwelfare.entity.WishesStatement;
import com.bjike.goddess.staffwelfare.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工生日福利记录业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class StaffBirthDayWelfareSerImpl extends ServiceImpl<StaffBirthDayWelfare, StaffBirthDayWelfareDTO> implements StaffBirthDayWelfareSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private WishesStatementSer wishesStatementSer;

    @Autowired
    private ThankStatementSer thankStatementSer;
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
    @Transactional(rollbackFor = SerException.class)
    public List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<StaffBirthDayWelfare> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list,StaffBirthDayWelfareBO.class);
    }

    @Override
    public List<WishesStatementBO> findWish(String employeeName) throws SerException {
        WishesStatementDTO dto = new WishesStatementDTO();
        dto.getConditions().add(Restrict.eq("createUser",employeeName));
        List<WishesStatement> bos = wishesStatementSer.findByCis(dto);
        List<WishesStatementBO> boList = BeanTransform.copyProperties(bos,WishesStatementBO.class);
        return boList;
    }

    @Override
    public List<ThankStatementBO> findThank(String employeeName) throws SerException {
        ThankStatementDTO dto = new ThankStatementDTO();
        dto.getConditions().add(Restrict.eq("createUser",employeeName));
        List<ThankStatement> thankStatements = thankStatementSer.findByCis(dto);
        List<ThankStatementBO> vos = BeanTransform.copyProperties(thankStatements,ThankStatementBO.class);
        return vos;
    }
}