package com.bjike.goddess.account.service;

import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.entity.AccountInfo;
import com.bjike.goddess.account.enums.GuideAddrStatus;
import com.bjike.goddess.account.excel.SonPermissionObject;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 明细账信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "accountSerCache")
@Service
public class AccountInfoSerImpl extends ServiceImpl<AccountInfo, AccountInfoDTO> implements AccountInfoSer {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("accountinfo");
        obj.setDescribesion("明细账信息");
        if (flagSeeInfo || flagAddInfo) {
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

    /**
     * 核对日期格式(年月日)
     */
     private void checkDate(AccountInfoTO accountInfoTO) throws SerException{
         try {
             DateUtil.parseDate(accountInfoTO.getDate());
         }catch (Exception e){
             throw new SerException("您输入的日期格式有误");
         }
     }


    @Override
    public Long countAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        Long count = super.count(accountInfoDTO);
        return count;
    }

    @Override
    public AccountInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AccountInfo accountInfo = super.findById(id);
        return BeanTransform.copyProperties(accountInfo, AccountInfoBO.class);
    }

    @Override
    public List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        checkSeeIdentity();
        List<AccountInfo> accountInfos = super.findByCis(accountInfoDTO, true);
        List<AccountInfoBO> accountInfoBOS = BeanTransform.copyProperties(accountInfos, AccountInfoBO.class);
        return accountInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        checkAddIdentity();
        checkDate(accountInfoTO);
        AccountInfo accountInfo = BeanTransform.copyProperties(accountInfoTO, AccountInfo.class, true);
        accountInfo.setCreateTime(LocalDateTime.now());
        super.save(accountInfo);
        return BeanTransform.copyProperties(accountInfo, AccountInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(accountInfoTO.getId())) {
            AccountInfo accountInfo = super.findById(accountInfoTO.getId());
            BeanTransform.copyProperties(accountInfoTO, accountInfo, true);
            checkDate(accountInfoTO);
            accountInfo.setModifyTime(LocalDateTime.now());
            super.update(accountInfo);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(accountInfoTO, AccountInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccountInfo(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<AccountCollectBO> subCollect(AccountInfoDTO dto) throws SerException {
        String first = dto.getFirstSubject();
        String second = dto.getSecondSubject();
        String third = dto.getThirdSubject();

        String[] field = new String[]{"firstSubject", "debitAmount", "creditAmount"};
        StringBuffer sql = new StringBuffer("");
        List<AccountCollectBO> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(debitAmount) as debitAmount , sum(creditAmount) as creditAmount ")
                    .append(" from account_accountinfo where 1=1   ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , debitAmount ,  creditAmount ")
                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where firstSubject = '" + first + "'   ");

            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , debitAmount ,  creditAmount ")

                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'   ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  debitAmount , creditAmount ")

                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "'   ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list, AccountCollectBO.class);
    }

    @Override
    public List<AccountCollectBO> areaCollect(AccountInfoDTO dto) throws SerException {
        String area = dto.getArea();

        String[] field = new String[]{"area", "debitAmount", "creditAmount"};
        StringBuffer sql = new StringBuffer("");
        List<AccountCollectBO> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area)) {
            sql.append(" select area , sum(debitAmount) as debitAmount , sum(creditAmount) as creditAmount ")
                    .append(" from account_accountinfo where 1=1  ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");
            }
            sql.append(" group by area ");

            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , debitAmount ,  creditAmount ")
                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where area = '" + area + "'  ");

            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list, AccountCollectBO.class);
    }

    @Override
    public List<AccountCollectBO> groupCollect(AccountInfoDTO dto) throws SerException {
        String group = dto.getProjectGroup();

        String[] field = new String[]{"projectGroup", "debitAmount", "creditAmount"};
        StringBuffer sql = new StringBuffer("");
        List<AccountCollectBO> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , sum(debitAmount) as debitAmount , sum(creditAmount) as creditAmount ")
                    .append(" from account_accountinfo where 1=1 ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");
            }
            sql.append(" group by projectGroup ");

            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  debitAmount ,  creditAmount ")
                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where projectGroup = '" + group + "'  ");

            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list, AccountCollectBO.class);
    }

    @Override
    public List<AccountCollectBO> nameCollect(AccountInfoDTO dto) throws SerException {
        String projectName = dto.getProjectName();

        String[] field = new String[]{"projectName", "debitAmount", "creditAmount"};
        StringBuffer sql = new StringBuffer("");
        List<AccountCollectBO> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(projectName)) {
            sql.append(" select projectName , sum(debitAmount) as debitAmount , sum(creditAmount) as creditAmount ")
                    .append(" from account_accountinfo where 1=1  ");
            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");

            }
            sql.append(" group by projectName ");

            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "debitAmount",
                    "creditAmount", "date", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  debitAmount ,  creditAmount ")
                    .append(" , date , area , projectGroup , projectName ")
                    .append(" from account_accountinfo where projectName = '" + projectName + "'  ");

            if (StringUtils.isNotBlank(dto.getStartDate())
                    && StringUtils.isNotBlank(dto.getEndDate())) {
                sql.append(" and date between '" + dto.getStartDate() + "' and '" + dto.getEndDate() + "' ");
            }
            list = super.findBySql(sql.toString(), AccountCollectBO.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list, AccountCollectBO.class);
    }


    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<AccountCollectBO> accountCollectBOS = super.findBySql("select distinct area from account_accountinfo group by area order by area asc ", AccountCollectBO.class, fields);

        List<String> areaList = accountCollectBOS.stream().map(AccountCollectBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> getProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<AccountCollectBO> accountCollectBOS = super.findBySql("select distinct projectName from account_accountinfo group by projectName order by projectName asc ", AccountCollectBO.class, fields);

        List<String> projectNameList = accountCollectBOS.stream().map(AccountCollectBO::getProjectName)
                .filter(projectName -> (projectName != null || !"".equals(projectName.trim()))).distinct().collect(Collectors.toList());


        return projectNameList;
    }

    @Override
    public List<String> getProjectGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<AccountCollectBO> accountCollectBOS = super.findBySql("select distinct area from account_accountinfo group by area order by area asc ", AccountCollectBO.class, fields);

        List<String> projectGroupList = accountCollectBOS.stream().map(AccountCollectBO::getProjectGroup)
                .filter(projectGroup -> (projectGroup != null || !"".equals(projectGroup.trim()))).distinct().collect(Collectors.toList());


        return projectGroupList;
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        List<String> firstSubject = voucherGenerateAPI.listFirstSubject();
        return firstSubject;
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        List<String> secondSubject = voucherGenerateAPI.listSubByFirst(firstSub);
        return secondSubject;
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        List<String> thirdSubject = voucherGenerateAPI.listTubByFirst(firstSub, secondSub);
        return thirdSubject;
    }


}