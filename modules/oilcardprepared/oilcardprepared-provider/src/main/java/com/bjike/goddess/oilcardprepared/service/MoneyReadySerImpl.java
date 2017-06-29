package com.bjike.goddess.oilcardprepared.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardprepared.bo.MoneyReadyBO;
import com.bjike.goddess.oilcardprepared.bo.MoneyReadyCountBO;
import com.bjike.goddess.oilcardprepared.dto.MoneyReadyDTO;
import com.bjike.goddess.oilcardprepared.entity.MoneyReady;
import com.bjike.goddess.oilcardprepared.enums.GuideAddrStatus;
import com.bjike.goddess.oilcardprepared.to.GuidePermissionTO;
import com.bjike.goddess.oilcardprepared.to.MoneyReadyTO;
import com.bjike.goddess.oilcardprepared.vo.SonPermissionObject;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资金审核准备业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-13 11:15 ]
 * @Description: [ 资金审核准备业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "oilcardpreparedSerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private WaitPaySer waitPaySer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("moneyready");
        obj.setDescribesion("资金审核准备");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = waitPaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("waitpay");
        obj.setDescribesion("等待付款");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady m = BeanTransform.copyProperties(to, MoneyReady.class, true);
        super.save(m);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(to.getId());
        LocalDateTime a = moneyReady.getCreateTime();
        LocalDateTime b = moneyReady.getModifyTime();
        moneyReady = BeanTransform.copyProperties(to, MoneyReady.class, true);
        moneyReady.setCreateTime(a);
        moneyReady.setModifyTime(b);
        super.update(moneyReady);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        List<MoneyReady> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, MoneyReadyBO.class);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        MoneyReady m = super.findById(id);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyCountBO> count(Integer month) throws SerException {
        checkSeeIdentity();
        Integer year = LocalDateTime.now().getYear();
        Set<String> groupTeams = findAllGroupTeams();
        MoneyReadyDTO dto = new MoneyReadyDTO();
        List<MoneyReady> list = super.findByCis(dto);
        List<MoneyReadyCountBO> boList = new ArrayList<MoneyReadyCountBO>();
        Double currentMonthReserveSum = 0.00;
        Double lastMonthReserveSum = 0.00;
        if (month != 1) {
            for (String groupTeam : groupTeams) {
                for (MoneyReady m : list) {
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        currentMonthReserveSum += m.getReserve();
                    }
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month - 1)) {
                        lastMonthReserveSum += m.getReserve();
                    }
                }
                if ((currentMonthReserveSum != 0) || (lastMonthReserveSum != 0)) {
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(groupTeam);
                    bo.setMonth(month);
                    bo.setCurrentMonthReserveSum(currentMonthReserveSum);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    bo.setDifferences(currentMonthReserveSum - lastMonthReserveSum);
                    if (lastMonthReserveSum != 0) {
                        bo.setGrowth((currentMonthReserveSum - lastMonthReserveSum) / lastMonthReserveSum);
                    } else {
                        bo.setGrowth(1.00);
                    }
                    boList.add(bo);
                    currentMonthReserveSum = 0.00;
                    lastMonthReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        } else {
            for (String groupTeam : groupTeams) {
                for (MoneyReady m : list) {
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        currentMonthReserveSum += m.getReserve();
                    }
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year - 1) && m.getMonth().equals(12)) {
                        lastMonthReserveSum += m.getReserve();
                    }
                }
                if ((currentMonthReserveSum != 0) || (lastMonthReserveSum != 0)) {
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(groupTeam);
                    bo.setMonth(month);
                    bo.setCurrentMonthReserveSum(currentMonthReserveSum);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    bo.setDifferences(currentMonthReserveSum - lastMonthReserveSum);
                    if (lastMonthReserveSum != 0) {
                        bo.setGrowth((currentMonthReserveSum - lastMonthReserveSum) / lastMonthReserveSum);
                    } else {
                        bo.setGrowth(1.00);
                    }
                    boList.add(bo);
                    currentMonthReserveSum = 0.00;
                    lastMonthReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        }
    }

    @Override
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        return super.count(dto);
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllGroupTeams() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getGroupTeam());
        }
        return set;
    }

//    /**
//     * 获取所有年份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> findAllYears() throws SerException {
//        List<MoneyReady> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (MoneyReady m : list) {
//            set.add(m.getYear());
//        }
//        return set;
//    }
//
//    /**
//     * 获取所有月份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> findAllMonths() throws SerException {
//        List<MoneyReady> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (MoneyReady m : list) {
//            set.add(m.getMonth());
//        }
//        return set;
//    }
}