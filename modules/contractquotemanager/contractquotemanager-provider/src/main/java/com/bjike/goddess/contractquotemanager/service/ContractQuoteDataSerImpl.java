package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 合同单价资料信息业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.323 ]
 * @Description: [ 合同单价资料信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractQuoteDataSerCache")
@Service
public class ContractQuoteDataSerImpl extends ServiceImpl<ContractQuoteData, ContractQuoteDataDTO> implements ContractQuoteDataSer {

    /**
     * 分页查询合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ContractQuoteDataBO> list(ContractQuoteDataDTO dto) throws SerException {
        List<ContractQuoteData> list = super.findByPage(dto);
        List<ContractQuoteDataBO> boList = BeanTransform.copyProperties(list, ContractQuoteDataBO.class);
        return boList;
    }

    /**
     * 保存合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ContractQuoteDataBO save(ContractQuoteDataTO to) throws SerException {
        ContractQuoteData entity = BeanTransform.copyProperties(to, ContractQuoteData.class, true);
        entity.setStatus(Status.THAW);//设置状态为解冻状态
        entity = super.save(entity);
        ContractQuoteDataBO bo = BeanTransform.copyProperties(entity, ContractQuoteDataBO.class);
        return bo;
    }

    /**
     * 根据id删除合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新合同单价资料信息
     *
     * @param to 合同单价资料信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ContractQuoteDataTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ContractQuoteData model = super.findById(to.getId());
            if (model != null) {
                updateContractQuoteData(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新合同单价资料信息
     *
     * @param to    合同单价资料信息to
     * @param model 合同单价资料信息
     * @throws SerException
     */
    private void updateContractQuoteData(ContractQuoteDataTO to, ContractQuoteData model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 冻结合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void congealStatus(String id) throws SerException {
        ContractQuoteData model = super.findById(id);
        model.setStatus(Status.CONGEAL);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 解冻合同单价资料信息唯一标识
     *
     * @param id 合同单价资料信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void thawStatus(String id) throws SerException {
        ContractQuoteData model = super.findById(id);
        model.setStatus(Status.THAW);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 汇总合同单价资料信息
     *
     * @param dto 合同单价资料信息dto
     * @return
     * @throws SerException
     */
    @Override
    public List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto) throws SerException {

        String[] areas = dto.getArea();
        String customerName = dto.getCustomerName();
        String startDate = dto.getStartDate();
        String endDate = dto.getEndDate();

        Boolean areasNotEmpty = (areas != null) && (areas.length > 0);//地区不为空
        LocalDate[] suitableDateStart = null;
        try {
            suitableDateStart = new LocalDate[]{DateUtil.parseDate(startDate), DateUtil.parseDate(endDate)};
        } catch (Exception e) {
            throw new SerException("时间为空或者时间格式错误(例:2010-12-31)");
        }

        if (areasNotEmpty) {
            List<ContractQuoteDataBO> totallist = new ArrayList<>(0);
            for (String area : areas) {
                ContractQuoteDataDTO contractDto = new ContractQuoteDataDTO();
                contractDto.getConditions().add(Restrict.eq("area", area));
                contractDto.getConditions().add(Restrict.eq("customerName", customerName));
                contractDto.getConditions().add(Restrict.between("suitableDateStart", suitableDateStart));
                contractDto.getSorts().add("suitableDateStart=desc");
                List<ContractQuoteData> dataList = super.findByCis(contractDto);

                if (!dataList.isEmpty()) {
                    //当dataList为空时,拷贝会出错
                    List<ContractQuoteDataBO> boList = BeanTransform.copyProperties(dataList, ContractQuoteDataBO.class);
                    totallist.addAll(boList);
                }
            }
            return totallist;
        } else {
            throw new SerException("您好,地区为空,无法进行查询.");
        }
    }

    /**
     * 根据地区或者项目组查询合同单价资料信息
     *
     * @param area    地区
     * @param project 项目组
     * @return class ContractQuoteDataBO
     * @throws SerException
     */
    @Override
    public List<ContractQuoteDataBO> searchs(String area, String project) throws SerException {
        ContractQuoteDataDTO dto = new ContractQuoteDataDTO();

        if (StringUtils.isNotBlank(area)) {
            dto.getConditions().add(Restrict.eq("area", area));
        }

        if (StringUtils.isNotBlank(project)) {
            dto.getConditions().add(Restrict.eq("project", project));
        }

        List<ContractQuoteData> list = super.findByCis(dto);
        List<ContractQuoteDataBO> boList = BeanTransform.copyProperties(list, ContractQuoteDataBO.class);

        return boList;
    }

}