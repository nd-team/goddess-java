package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.bo.BeginBalanceBO;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.entity.BeginBalance;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.BeginBalanceTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.vo.BeginBalanceVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.LazilyConcatenatedByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 期初余额业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class BeginBalanceSerImpl extends ServiceImpl<BeginBalance, BeginBalanceDTO> implements BeginBalanceSer {
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
    public Long count(BeginBalanceDTO beginBalanceDTO) throws SerException {
        Long count = super.count(beginBalanceDTO);
        return count;
    }

    @Override
    public BeginBalanceBO getOne(String id) throws SerException {
        BeginBalance beginBalance = super.findById(id);
        return BeanTransform.copyProperties(beginBalance,BeginBalanceBO.class);
    }

    @Override
    public List<BeginBalanceBO> findList(BeginBalanceDTO beginBalanceDTO) throws SerException {
        checkSeeIdentity();
        beginBalanceDTO.getSorts().add("createTime=desc");
        List<BeginBalance> beginBalances = super.findByPage(beginBalanceDTO);
        List<BeginBalanceBO> beginBalanceBOS = BeanTransform.copyProperties(beginBalances, BeginBalanceBO.class);
        return beginBalanceBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BeginBalanceBO insert(BeginBalanceTO beginBalanceTO) throws SerException {
        checkAddIdentity();
        BeginBalance beginBalance = BeanTransform.copyProperties(beginBalanceTO,BeginBalance.class,true);
        beginBalance.setCreateTime(LocalDateTime.now());
        super.save(beginBalance);
        return BeanTransform.copyProperties(beginBalance,BeginBalanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BeginBalanceBO edit(BeginBalanceTO beginBalanceTO) throws SerException {
        checkAddIdentity();
        BeginBalance beginBalance = super.findById(beginBalanceTO.getId());
        BeanTransform.copyProperties(beginBalanceTO,beginBalance,true);
        beginBalance.setModifyTime(LocalDateTime.now());
        super.update(beginBalance);
        return BeanTransform.copyProperties(beginBalance,BeginBalanceBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
    @Override
    public List<BeginBalanceBO> getBeginBalace(String startTime,String endTime) throws SerException {
        BeginBalanceDTO dto = new BeginBalanceDTO();
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            String[] condi = new String[]{startTime,endTime};
            dto.getConditions().add(Restrict.between("date",condi));
        }
        //获取期初余额
        String[] field = new String[]{"beginBalance"};
        String sql = "SELECT sum(beginBalance) as beginBalance FROM fundcheck_beginbalance WHERE date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY beginBalance;";
        List<BeginBalanceBO> beginBalanceBOS = super.findBySql(sql,BeginBalanceBO.class,field);
        return beginBalanceBOS;
    }
}