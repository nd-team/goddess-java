package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.bo.OperatExpensesBO;
import com.bjike.goddess.fundcheck.dto.OperatExpensesDTO;
import com.bjike.goddess.fundcheck.entity.OperatExpenses;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OperatExpensesCollectTO;
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
 * 营业费用业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class OperatExpensesSerImpl extends ServiceImpl<OperatExpenses, OperatExpensesDTO> implements OperatExpensesSer {
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
    public Long count(OperatExpensesDTO operatExpensesDTO) throws SerException {
        Long count = super.count(operatExpensesDTO);
        return count;
    }

    @Override
    public OperatExpensesBO getOne(String id) throws SerException {
        OperatExpenses operatExpenses = super.findById(id);
        return BeanTransform.copyProperties(operatExpenses,OperatExpensesBO.class);
    }

    @Override
    public List<OperatExpensesBO> findList(OperatExpensesDTO operatExpensesDTO) throws SerException {
        checkSeeIdentity();
        operatExpensesDTO.getSorts().add("createTime=desc");
        List<OperatExpenses> operatExpenses = super.findByPage(operatExpensesDTO);
        List<OperatExpensesBO> operatExpensesBOS = BeanTransform.copyProperties(operatExpenses,OperatExpensesBO.class);
        return operatExpensesBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperatExpensesBO insert(OperatExpensesCollectTO operatExpensesTO) throws SerException {
        checkAddIdentity();
        OperatExpenses operatExpenses = BeanTransform.copyProperties(operatExpensesTO,OperatExpenses.class);
        operatExpenses.setCreateTime(LocalDateTime.now());
        super.save(operatExpenses);
        return BeanTransform.copyProperties(operatExpenses,OperatExpensesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperatExpensesBO edit(OperatExpensesCollectTO operatExpensesTO) throws SerException {
        checkAddIdentity();
        OperatExpenses operatExpenses = super.findById(operatExpensesTO.getId());
        BeanTransform.copyProperties(operatExpensesTO,operatExpenses,true);
        operatExpenses.setModifyTime(LocalDateTime.now());
        super.update(operatExpenses);
        return BeanTransform.copyProperties(operatExpenses,OperatExpensesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
    @Override
    public List<OperatExpensesBO> collect(OperatExpensesCollectTO to) throws SerException {
        List<OperatExpensesBO> operatExpensesBOList = new ArrayList<>();
        OperatExpensesDTO dto = new OperatExpensesDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取所有类型(科目)
        List<String> typeList = new ArrayList<>();
        dto.getSorts().add("type=desc");
        String sql = "SELECT type FROM fundcheck_operatexpenses WHERE date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY type ";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            typeList.addAll((List) objects);
        }
        //获取所有类型(科目)对应的金额
        List<Double> moneyList = new ArrayList<>();
        sql = "SELECT money FROM fundcheck_operatexpenses WHERE date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY money;";
        List<Object> objectList = super.findBySql(sql);
        if(null != objectList && objectList.size() > 0){
            moneyList.addAll((List) objectList);
        }
        //获取金额合计
        String[] fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_operatexpenses where date between '" + startTime + "' and '" + endTime + "' ";
        List<OperatExpensesBO> operatExpensesBOS = super.findBySql(sql, OperatExpensesBO.class, fields);
        Double money = operatExpensesBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(OperatExpensesBO::getMoney).sum();

        OperatExpensesBO operatExpensesBO = new OperatExpensesBO();
        operatExpensesBO.setDate(startTime+"-"+endTime);
        operatExpensesBO.setTypeList(typeList);
        operatExpensesBO.setMoneyList(moneyList);
        operatExpensesBO.setMoney(money);
        operatExpensesBOList.add(operatExpensesBO);
        return operatExpensesBOList;
    }
}