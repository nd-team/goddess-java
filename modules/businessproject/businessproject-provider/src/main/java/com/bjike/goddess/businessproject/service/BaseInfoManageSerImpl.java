package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.businessproject.entity.BaseInfoManage;
import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.excel.BaseInfoManageExcel;
import com.bjike.goddess.businessproject.excel.BaseInfoManageLeadExcel;
import com.bjike.goddess.businessproject.to.BaseInfoManageTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.utils.ChineseConvert;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商务项目合同基本信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.355 ]
 * @Description: [ 商务项目合同基本信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class BaseInfoManageSerImpl extends ServiceImpl<BaseInfoManage, BaseInfoManageDTO> implements BaseInfoManageSer {

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


    /**
     * 时间格式（年月日）
     */
    private void checkDate(BaseInfoManageTO baseInfoManageTO) throws SerException {
        try {
            DateUtil.parseDate(baseInfoManageTO.getSiginTime());
            DateUtil.parseDate(baseInfoManageTO.getStartProjectTime());
            DateUtil.parseDate(baseInfoManageTO.getEndProjectTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式不对");
        }
    }

    @Override
    public Long countBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        searchCondition(baseInfoManageDTO);
        Long count = super.count(baseInfoManageDTO);
        return count;
    }

    @Override
    public BaseInfoManageBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        BaseInfoManage baseInfoManage = super.findById(id);
        return BeanTransform.copyProperties(baseInfoManage, BaseInfoManageBO.class);
    }

    @Override
    public List<BaseInfoManageBO> listBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        checkSeeIdentity();

        searchCondition(baseInfoManageDTO);
        List<BaseInfoManage> list = super.findByPage(baseInfoManageDTO);
        List<BaseInfoManageBO> baseInfoManageBOList = BeanTransform.copyProperties(list, BaseInfoManageBO.class);
        return baseInfoManageBOList;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public BaseInfoManageBO addBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {
        checkAddIdentity();
        checkDate(baseInfoManageTO);

        //签订年份
        String tempTime = StringUtils.isBlank(baseInfoManageTO.getSiginTime()) ? "0000" : baseInfoManageTO.getSiginTime().substring(0, 4);
        baseInfoManageTO.setSiginYear(tempTime);
        //生成合同档案编号
        generateContractNum(baseInfoManageTO);
        //生成内部项目编码
        generateInnerProjectNum(baseInfoManageTO);

        BaseInfoManage baseInfoManage = BeanTransform.copyProperties(baseInfoManageTO, BaseInfoManage.class, true);
        baseInfoManage.setCreateTime(LocalDateTime.now());

        super.save(baseInfoManage);

        BaseInfoManageBO bo = BeanTransform.copyProperties(baseInfoManage, BaseInfoManageBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BaseInfoManageBO editBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {
        checkAddIdentity();

        BaseInfoManage temp = super.findById(baseInfoManageTO.getId());

        checkDate(baseInfoManageTO);
        BaseInfoManage baseInfoManage = BeanTransform.copyProperties(baseInfoManageTO, BaseInfoManage.class, true);
        BeanUtils.copyProperties(baseInfoManage, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        BaseInfoManageBO bo = BeanTransform.copyProperties(temp, BaseInfoManageBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBaseInfoManage(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }


    @Override
    public BaseInfoManageBO getInfoByInnerProjectNum(String innerProjectNum) throws SerException {
        BaseInfoManage baseInfoManage = new BaseInfoManage();

        if (StringUtils.isNotBlank(innerProjectNum)) {
            BaseInfoManageDTO dto = new BaseInfoManageDTO();
            dto.getConditions().add(Restrict.eq("innerProjectNum", innerProjectNum));
            baseInfoManage = super.findOne(dto);
        }

        BaseInfoManageBO bo = BeanTransform.copyProperties(baseInfoManage, BaseInfoManageBO.class);
        return bo;
    }


    public void searchCondition(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getInnerProject())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("innerProject", baseInfoManageDTO.getInnerProject()));
        }/**
         * 合同外部项目编号
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getOutProjectNum())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("outProjectNum", baseInfoManageDTO.getOutProjectNum()));
        }/**
         * 对应销售合同编号
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getSaleContractNum())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("saleContractNum", baseInfoManageDTO.getSaleContractNum()));
        }/**
         * 业务类型
         */
        if (baseInfoManageDTO.getBusinessType() != null) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("businessType", baseInfoManageDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getBusinessSubject())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("businessSubject", baseInfoManageDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if (baseInfoManageDTO.getBusinessCooperate() != null) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("businessCooperate", baseInfoManageDTO.getBusinessCooperate()));
        }
        /**
         * 甲方公司
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getFirstCompany())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("firstCompany", baseInfoManageDTO.getFirstCompany()));
        }
        /**
         * 乙方公司
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getSecondCompany())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("secondCompany", baseInfoManageDTO.getSecondCompany()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getArea())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("area", baseInfoManageDTO.getArea()));
        }
        /**
         * 合同属性
         */
        if (baseInfoManageDTO.getContractProperty() != null) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("contractProperty", baseInfoManageDTO.getContractProperty()));
        }
        /**
         * 支付方式
         */
        if (baseInfoManageDTO.getPayWays() != null) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("payWays", baseInfoManageDTO.getPayWays()));
        }
        /**
         * 客户名称
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getCustomerName())) {
            baseInfoManageDTO.getConditions().add(Restrict.like("customerName", baseInfoManageDTO.getCustomerName()));
        }
        /**
         *  签订年份
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getSiginYear())) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("siginYear", baseInfoManageDTO.getSiginYear()));
        }
        /**
         *  合同是否已归档
         */
        if (StringUtils.isNotBlank(baseInfoManageDTO.getFileCondition())) {
            baseInfoManageDTO.getConditions().add(Restrict.eq("fileCondition", baseInfoManageDTO.getFileCondition()));
        }

    }

    @Override
    public List<BaseInfoManageBO> searchSiginManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        searchCondition( baseInfoManageDTO );
        List<BaseInfoManage> list = super.findByCis( baseInfoManageDTO );
        List<BaseInfoManageBO> listBO = BeanTransform.copyProperties(list , BaseInfoManageBO.class);
        return listBO;
    }

    @Override
    public List<String> listFirstCompany() throws SerException {
        String[] fields = new String[]{"firstCompany"};
        List<BaseInfoManageBO> baseInfoManageBOS = super.findBySql("select firstCompany from businessproject_baseinfomanage group by firstCompany order by firstCompany asc ", BaseInfoManageBO.class, fields);

        List<String> firstCompanyList = baseInfoManageBOS.stream().map(BaseInfoManageBO::getFirstCompany)
                .filter(firstCompany -> (firstCompany != null || !"".equals(firstCompany.trim()))).distinct().collect(Collectors.toList());


        return firstCompanyList;
    }

    @Override
    public List<String> getInnerNum() throws SerException {
        String[] fields = new String[]{"innerProjectNum"};
        List<BaseInfoManageBO> baseInfoManageBOS = super.findBySql("select innerProjectNum from businessproject_baseinfomanage group by innerProjectNum order by area asc ", BaseInfoManageBO.class, fields);

        List<String> firstCompanyList = baseInfoManageBOS.stream().map(BaseInfoManageBO::getInnerProjectNum)
                .filter(firstCompany -> (firstCompany != null || !"".equals(firstCompany.trim()))).distinct().collect(Collectors.toList());


        return firstCompanyList;
    }

    @Override
    public Set<String> allInnerProjects() throws SerException {
        List<BaseInfoManage> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (BaseInfoManage b : list) {
            set.add(b.getInnerProject());
        }
        return set;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        List<BaseInfoManageLeadExcel> toList = new ArrayList<BaseInfoManageLeadExcel>();
        BaseInfoManageLeadExcel baseInfoManageLeadExcel = new BaseInfoManageLeadExcel();
        toList.add(baseInfoManageLeadExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcel(BaseInfoManageDTO dto) throws SerException {
        String[] innerProjects = dto.getInnerProjects();
        List<BaseInfoManageExcel> toList = new ArrayList<BaseInfoManageExcel>();
        if ((innerProjects != null) && (innerProjects.length > 0)) {
            List<BaseInfoManage> list = super.findByCis(dto);
            for (String s : innerProjects) {
                if (StringUtils.isNotBlank(s)) {
                    for (BaseInfoManage b : list) {
                        if (s.equals(b.getInnerProject())) {
                            BaseInfoManageExcel excel = new BaseInfoManageExcel();
                            BeanUtils.copyProperties(b, excel);
                            toList.add(excel);
                        }
                    }
                }
            }
        } else {
            List<BaseInfoManage> list = super.findByCis(dto);
            for (BaseInfoManage b : list) {
                BaseInfoManageExcel excel = new BaseInfoManageExcel();
                BeanUtils.copyProperties(b, excel);
                toList.add(excel);
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<BaseInfoManageTO> toList) throws SerException {
        for (BaseInfoManageTO to : toList) {
            checkAddIdentity();
            checkDate(to);

            //签订年份
            String tempTime = StringUtils.isBlank(to.getSiginTime()) ? "0000" : to.getSiginTime().substring(0, 4);
            to.setSiginYear(tempTime);
            //生成合同档案编号
            generateContractNum(to);
            //生成内部项目编码
            generateInnerProjectNum(to);

            BaseInfoManage baseInfoManage = BeanTransform.copyProperties(to, BaseInfoManage.class, true);
            baseInfoManage.setCreateTime(LocalDateTime.now());

            String fileCondition = baseInfoManage.getFileCondition();
            if ((!"未归档".equals(fileCondition)) && (!"已归档".equals(fileCondition))) {
                throw new SerException("合同是否已归档只能为未归档或已归档");
            }
            if (baseInfoManage.getBusinessType() == null) {
                throw new SerException("业务类型只能为移动通信类，软件开发类，智能系统集成类，广告策划营销类的其中一种");
            }
            if (baseInfoManage.getBusinessCooperate() == null) {
                throw new SerException("合作方式只能为租赁合同，承包的项目合同，分包项目合同，销售合同的其中一种");
            }
            if (baseInfoManage.getContractProperty() == null) {
                throw new SerException("合同属性只能为框架合同，单次合同的其中一种");
            }
            if (baseInfoManage.getPayWays() == null) {
                throw new SerException("支付方式只能为现金，银行汇兑，电汇，转账的其中一种");
            }
            if (baseInfoManage.getPayFeeOrigin() == null) {
                throw new SerException("结算费用来源只能为预付，背靠背，垫付的其中一种");
            }
            super.save(baseInfoManage);
        }
    }

    //生成合同档案编号
    public BaseInfoManageTO generateContractNum(BaseInfoManageTO baseInfoManageTO) throws SerException {

        String partyA = StringUtils.isBlank(baseInfoManageTO.getFirstCompany()) ? "" : baseInfoManageTO.getFirstCompany();
        String partyB = StringUtils.isBlank(baseInfoManageTO.getSecondCompany()) ? "" : baseInfoManageTO.getSecondCompany();
        //合同数
        String contractNum = getContract(partyA, partyB);

        //设置合同编号  格式：  TXWB-GZAJ-WSL-2016001
        StringBuffer tempCode = new StringBuffer();

        String partyAChinese = "";
        switch (partyA) {
            case "北京艾佳总公司":
                partyAChinese = "BJAJ";
                break;
            case "北京艾佳广州分公司":
                partyAChinese = "GZAJ";
                break;
            case "广州裕大":
                partyAChinese = "GZYD";
                break;
            default:
                partyAChinese = ChineseConvert.getTargetNumber(partyA, partyA.length());
                if (StringUtils.isNotBlank(partyAChinese) && partyAChinese.length() > 4) {
                    partyAChinese = partyAChinese.substring(0, 4);
                }
                break;
        }

        String partyBChinese = "";
        switch (partyB) {
            case "北京艾佳总公司":
                partyBChinese = "BJAJ";
                break;
            case "北京艾佳广州分公司":
                partyBChinese = "GZAJ";
                break;
            case "广州裕大":
                partyBChinese = "GZYD";
                break;
            default:
                partyBChinese = ChineseConvert.getTargetNumber(partyB, partyB.length());
                if (StringUtils.isNotBlank(partyBChinese) && partyBChinese.length() > 4) {
                    partyBChinese = partyBChinese.substring(0, 4);
                }
                break;
        }

        tempCode.append(BusinessType.getFirstLetter(baseInfoManageTO.getBusinessType()))
                .append(BusinessCooperate.getFirstLetter(baseInfoManageTO.getBusinessCooperate()))
                .append("-")
                .append(partyAChinese).append("-")
                .append(partyBChinese).append("-")
                .append(Integer.parseInt(baseInfoManageTO.getSiginYear())).append(contractNum);

        baseInfoManageTO.setContractNum(tempCode.toString());

        return baseInfoManageTO;
    }

    /**
     * 查询指定客户目前的合同数
     *
     * @param partyA
     * @param partyB
     * @return
     */
    public String getContract(String partyA, String partyB) throws SerException {

        BaseInfoManageDTO dto = new BaseInfoManageDTO();
        dto.getConditions().add(Restrict.eq("firstCompany", partyA));
        dto.getConditions().add(Restrict.eq("secondCompany", partyB));
        List<BaseInfoManage> sumList = super.findByCis(dto);

        if (sumList != null && sumList.size() > 0) {

            if (sumList.size() >= 99) {
                return (sumList.size() + 1) + "";
            }
            if (sumList.size() >= 9 && sumList.size() < 99) {
                return "0" + (sumList.size() + 1);
            } else {
                return "00" + (sumList.size() + 1);
            }
        } else {
            return "001";
        }

    }

    //生成内部项目编号
    public BaseInfoManageTO generateInnerProjectNum(BaseInfoManageTO baseInfoManageTO) throws SerException {

        String area = StringUtils.isBlank(baseInfoManageTO.getArea()) ? "" : baseInfoManageTO.getArea();
        //合同数
        String contractNum = getInnerProjectCountByArea(area);

        //设置合同编号  格式：  JMTX20160001
        StringBuffer tempCode = new StringBuffer();

        area = StringUtils.isBlank(baseInfoManageTO.getArea()) ? "" : ChineseConvert.getTargetNumber(area, area.length());
        if (area.length() >= 2) {
            area = area.substring(0, 2);
        }

        tempCode.append(area)
                .append(BusinessType.getFirstLetter(baseInfoManageTO.getBusinessType()))
                .append(Integer.parseInt(baseInfoManageTO.getSiginYear()))
                .append(contractNum);

        baseInfoManageTO.setInnerProjectNum(tempCode.toString());

        return baseInfoManageTO;
    }

    /**
     * 查询指定地区目前的合同数
     *
     * @param area
     * @return
     */
    public String getInnerProjectCountByArea(String area) throws SerException {

        BaseInfoManageDTO dto = new BaseInfoManageDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<BaseInfoManage> sumList = super.findByCis(dto);

        if (sumList != null && sumList.size() > 0) {

            if (sumList.size() >= 99) {
                return (sumList.size() + 1) + "";
            }
            if (sumList.size() >= 9 && sumList.size() < 99) {
                return "00" + (sumList.size() + 1);
            } else {
                return "000" + (sumList.size() + 1);
            }
        } else {
            return "0001";
        }

    }

}