package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.businessproject.entity.BaseInfoManage;
import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.to.BaseInfoManageTO;
import com.bjike.goddess.businessproject.utils.ChineseConvert;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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


    @Cacheable
    @Override
    public List<BaseInfoManageBO> listBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        List<BaseInfoManage> list = super.findByPage(baseInfoManageDTO);
        List<BaseInfoManageBO> baseInfoManageBOList = BeanTransform.copyProperties(list, BaseInfoManageBO.class);
        return baseInfoManageBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BaseInfoManageBO addBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {
        //签订年份
        String tempTime = StringUtils.isBlank(baseInfoManageTO.getSiginTime())?"0000":baseInfoManageTO.getSiginTime().substring(0, 4);
        baseInfoManageTO.setSiginYear( tempTime );
        //生成合同档案编号
        generateContractNum(baseInfoManageTO);
        //生成内部项目编码
        generateInnerProjectNum(baseInfoManageTO);

        BaseInfoManage baseInfoManage = BeanTransform.copyProperties( baseInfoManageTO,BaseInfoManage.class,true);
        baseInfoManage.setCreateTime(LocalDateTime.now());

        super.save( baseInfoManage );

        BaseInfoManageBO bo = BeanTransform.copyProperties( baseInfoManage ,BaseInfoManageBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BaseInfoManageBO editBaseInfoManage(BaseInfoManageTO baseInfoManageTO) throws SerException {

        BaseInfoManage baseInfoManage =  BeanTransform.copyProperties( baseInfoManageTO, BaseInfoManage.class,true);
        baseInfoManage.setModifyTime(LocalDateTime.now());
        super.update( baseInfoManage );

        BaseInfoManageBO bo = BeanTransform.copyProperties(baseInfoManage,BaseInfoManageBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBaseInfoManage(String id) throws SerException {
        super.remove( id );
    }

    @Cacheable
    @Override
    public BaseInfoManageBO getInfoByInnerProjectNum(String innerProjectNum) throws SerException {
        BaseInfoManage baseInfoManage = new BaseInfoManage();

        if( StringUtils.isNotBlank( innerProjectNum)){
            BaseInfoManageDTO dto = new BaseInfoManageDTO();
            dto.getConditions().add(Restrict.eq("innerProjectNum",innerProjectNum));
            baseInfoManage = super.findOne( dto );
        }

        BaseInfoManageBO bo = BeanTransform.copyProperties( baseInfoManage,BaseInfoManageBO.class);
        return bo;
    }

    @Cacheable
    @Override
    public List<BaseInfoManageBO> searchSiginManage(BaseInfoManageDTO baseInfoManageDTO) throws SerException {
        /**
         * 业务类型
         */
        if(baseInfoManageDTO.getBusinessType() != null ){
            baseInfoManageDTO.getConditions().add(Restrict.eq("businessType",baseInfoManageDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getBusinessSubject())){
            baseInfoManageDTO.getConditions().add(Restrict.like("businessSubject",baseInfoManageDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if(baseInfoManageDTO.getBusinessCooperate() != null ){
            baseInfoManageDTO.getConditions().add(Restrict.eq("businessCooperate",baseInfoManageDTO.getBusinessCooperate()));
        }
        /**
         * 甲方公司
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getFirstCompany())){
            baseInfoManageDTO.getConditions().add(Restrict.like("firstCompany",baseInfoManageDTO.getFirstCompany()));
        }
        /**
         * 乙方公司
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getSecondCompany())){
            baseInfoManageDTO.getConditions().add(Restrict.like("secondCompany",baseInfoManageDTO.getSecondCompany()));
        }
        /**
         * 地区
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getArea())){
            baseInfoManageDTO.getConditions().add(Restrict.like("area",baseInfoManageDTO.getArea()));
        }
        /**
         * 合同属性
         */
        if(baseInfoManageDTO.getContractProperty() != null ){
            baseInfoManageDTO.getConditions().add(Restrict.eq("contractProperty",baseInfoManageDTO.getContractProperty()));
        }
        /**
         * 支付方式
         */
        if(baseInfoManageDTO.getPayWays() != null  ){
            baseInfoManageDTO.getConditions().add(Restrict.eq("payWays",baseInfoManageDTO.getPayWays()));
        }
        /**
         * 客户名称
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getCustomerName())){
            baseInfoManageDTO.getConditions().add(Restrict.like("customerName",baseInfoManageDTO.getCustomerName()));
        }
        /**
         *  签订年份
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getSiginYear())){
            baseInfoManageDTO.getConditions().add(Restrict.eq("siginYear",baseInfoManageDTO.getSiginYear()));
        }
        /**
         *  合同是否已归档
         */
        if(StringUtils.isNotBlank(baseInfoManageDTO.getFileCondition())){
            baseInfoManageDTO.getConditions().add(Restrict.eq("fileCondition",baseInfoManageDTO.getFileCondition()));
        }

        List<BaseInfoManage> baseInfoManageList = super.findByCis( baseInfoManageDTO );

        List<BaseInfoManageBO> baseInfoManageBOList = BeanTransform.copyProperties(baseInfoManageList ,BaseInfoManageBO.class);
        return baseInfoManageBOList;
    }

    @Override
    public List<String> listFirstCompany() throws SerException {
        String[] fields = new String[]{"firstCompany"};
        List<BaseInfoManageBO> baseInfoManageBOS =super.findBySql("select firstCompany,1 from businessproject_baseinfomanage order by area asc ", BaseInfoManageBO.class, fields);

        List<String> firstCompanyList  = baseInfoManageBOS.stream().map(BaseInfoManageBO::getArea)
                .filter(firstCompany -> (firstCompany != null || !"".equals(firstCompany.trim())) ).distinct().collect(Collectors.toList());


        return firstCompanyList;
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

        String partyBChinese ="";
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
        String contractNum = getInnerProjectCountByArea( area);

        //设置合同编号  格式：  JMTX20160001
        StringBuffer tempCode = new StringBuffer();

        area = StringUtils.isBlank(baseInfoManageTO.getArea()) ? "":ChineseConvert.getTargetNumber(area,area.length());
        if( area.length()>=2){
            area = area.substring(0,2);
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