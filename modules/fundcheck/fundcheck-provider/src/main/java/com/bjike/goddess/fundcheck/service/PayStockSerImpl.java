package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.PayStockBO;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.PayStock;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.PayStockCollectTO;
import com.bjike.goddess.fundcheck.to.PayStockTO;
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
 * 支付给股东业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class PayStockSerImpl extends ServiceImpl<PayStock, PayStockDTO> implements PayStockSer {
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
    public Long count(PayStockDTO payStockDTO) throws SerException {
        Long count = super.count(payStockDTO);
        return count;
    }

    @Override
    public PayStockBO getOne(String id) throws SerException {
        PayStock payStock = super.findById(id);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);
    }

    @Override
    public List<PayStockBO> findListBack(PayStockDTO payStockDTO) throws SerException {
        checkSeeIdentity();
        payStockDTO.getSorts().add("createTime=desc");
        List<PayStock> payStocks = super.findByPage(payStockDTO);
        List<PayStockBO> payStockBOS = BeanTransform.copyProperties(payStocks,PayStockBO.class);
        return payStockBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayStockBO insert(PayStockTO payStockTO) throws SerException {
        checkAddIdentity();
        PayStock payStock = BeanTransform.copyProperties(payStockTO,PayStock.class);
        payStock.setCreateTime(LocalDateTime.now());
        super.save(payStock);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public PayStockBO edit(PayStockTO payStockTO) throws SerException {
        checkAddIdentity();
        PayStock payStock = super.findById(payStockTO.getId());
        BeanTransform.copyProperties(payStock,PayStockBO.class);
        payStock.setModifyTime(LocalDateTime.now());
        super.update(payStock);
        return BeanTransform.copyProperties(payStock,PayStockBO.class);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
    @Override
    public List<PayStockBO> collect(PayStockCollectTO to) throws SerException {
        List<PayStockBO> payStockBOList = new ArrayList<>();
        PayStockDTO dto = new PayStockDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取所有股东名
        List<String> stockNameList = new ArrayList<>();
        dto.getSorts().add("stockName=asc");
        String sql = "select stockName from  fundcheck_paystock where date between '" + startTime + "' and '" + endTime + "' group by stockName";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
//            String[] names = objects.toArray(new String[objects.size()]);
//            for(String n:names){
//                stockNameList.add(n);
//            }
            stockNameList.addAll((List) objects);
        }
        //获取股东名对应的金额
        List<Double> stockNameMoney = new ArrayList<>();
        sql = "select money from  fundcheck_paystock where date between '" + startTime + "' and '" + endTime + "' group by money ";
        List<Object> object = super.findBySql(sql);
        if (null != object && object.size() > 0) {
            stockNameMoney.addAll((List) object);
        }
        //获取金额的合计
        String[] fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_paystock where date between '" + startTime + "' and '" + endTime + "' ";
        List<PayStockBO> payStockBOS = super.findBySql(sql, PayStockBO.class, fields);
        Double money = payStockBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(PayStockBO::getMoney).sum();

        PayStockBO payStockBO = new PayStockBO();
        payStockBO.setDate(startTime+"-"+endTime);
        payStockBO.setStockNameList(stockNameList);
        payStockBO.setStockNameMoney(stockNameMoney);
        payStockBO.setMoney(money);
        payStockBOList.add(payStockBO);
        return payStockBOList;
    }
}