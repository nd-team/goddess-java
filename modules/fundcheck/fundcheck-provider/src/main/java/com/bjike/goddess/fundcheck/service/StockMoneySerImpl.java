package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.bo.StockMoneyBO;
import com.bjike.goddess.fundcheck.dto.StockMoneyDTO;
import com.bjike.goddess.fundcheck.entity.StockMoney;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.StockMoneyCollectTO;
import com.bjike.goddess.fundcheck.to.StockMoneyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收到股东款业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class StockMoneySerImpl extends ServiceImpl<StockMoney, StockMoneyDTO> implements StockMoneySer {
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
    public Long count(StockMoneyDTO stockMoneyDTO) throws SerException {
        Long count = super.count(stockMoneyDTO);
        return count;
    }

    @Override
    public StockMoneyBO getOne(String id) throws SerException {
        StockMoney stockMoney = super.findById(id);
        return BeanTransform.copyProperties(stockMoney, StockMoneyBO.class);
    }

    @Override
    public List<StockMoneyBO> findList(StockMoneyDTO stockMoneyDTO) throws SerException {
        checkSeeIdentity();
        stockMoneyDTO.getSorts().add("createTime=desc");
        List<StockMoney> stockMonies = super.findByCis(stockMoneyDTO);
        List<StockMoneyBO> stockMoneyBOS = BeanTransform.copyProperties(stockMonies, StockMoneyBO.class);
        return stockMoneyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StockMoneyBO insert(StockMoneyTO stockMoneyTO) throws SerException {
        checkAddIdentity();
        StockMoney stockMoney = BeanTransform.copyProperties(stockMoneyTO, StockMoney.class, true);
        stockMoney.setCreateTime(LocalDateTime.now());
        super.save(stockMoney);
        return BeanTransform.copyProperties(stockMoney, StockMoneyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StockMoneyBO edit(StockMoneyTO stockMoneyTO) throws SerException {
        checkAddIdentity();
        StockMoney stockMoney = super.findById(stockMoneyTO.getId());
        stockMoney.setModifyTime(LocalDateTime.now());
        super.update(stockMoney);
        return BeanTransform.copyProperties(stockMoney, StockMoneyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<StockMoneyBO> collect(StockMoneyCollectTO to) throws SerException {

        List<StockMoneyBO> stockMoneyBOList = new ArrayList<>();
        StockMoneyDTO dto = new StockMoneyDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取所有股东名
        List<String> stockNameList = new ArrayList<>();
        dto.getSorts().add("stockName=asc");
        String sql = "select stockName from  fundcheck_stockmoney where date between '" + startTime + "' and '" + endTime + "' group by stockName";
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
        sql = "select money from  fundcheck_stockmoney where date between '" + startTime + "' and '" + endTime + "' group by money ";
        List<Object> object = super.findBySql(sql);
        if (null != object && object.size() > 0) {
            stockNameMoney.addAll((List) object);
        }
        //获取金额的合计
        String[] fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_stockmoney where date between '" + startTime + "' and '" + endTime + "' ";
        List<StockMoneyBO> stockMoneyBOS = super.findBySql(sql, StockMoneyBO.class, fields);
        Double money = stockMoneyBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(StockMoneyBO::getMoney).sum();

        StockMoneyBO stockMoneyBO = new StockMoneyBO();
        stockMoneyBO.setDate( startTime+"-"+ endTime );
        stockMoneyBO.setStockNameList(stockNameList);
        stockMoneyBO.setStockNameMoney(stockNameMoney);
        stockMoneyBO.setMoney(money);
        stockMoneyBOList.add(stockMoneyBO);
        return stockMoneyBOList;

//        String sql = "SELECT stockName,sum(money) as moneys " +
//                " from fundcheck_stockmoney where date BETWEEN '"+startTime+"' AND '"+endTime+"' GROUP BY  stockName " +
//                " UNION ALL SELECT '合计' as stockName, sum(money) as moneys " +
//                " from fundcheck_stockmoney where date BETWEEN '"+startTime+"' AND '"+endTime+"' ";
//        String[] fields = new String[]{"stockName","money","stockNameMoney"};
//        List<StockMoneyBO> stockMoneyBOS = super.findBySql(sql,StockMoneyBO.class,fields);
//        for (StockMoneyBO stockMoneyBO : stockMoneyBOS){
//            stockMoneyBO.setStockNameList();
//        }
//        return stockMoneyBOS;


    }
}