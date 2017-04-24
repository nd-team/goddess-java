package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.entity.TowerInsure;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.entity.TowerInsure;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 塔工意外险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class TowerInsureSerImpl extends ServiceImpl<TowerInsure, TowerInsureDTO> implements TowerInsureSer {


    @Override
    public Long countTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureNumber())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureNumber", towerInsureDTO.getInsureNumber()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsurer())) {
            towerInsureDTO.getConditions().add(Restrict.like("insurer", towerInsureDTO.getInsurer()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureByPerson())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureByPerson", towerInsureDTO.getInsureByPerson()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getReSign())) {
            towerInsureDTO.getConditions().add(Restrict.eq("reSign", towerInsureDTO.getReSign()));
        }
        Long count = super.count(towerInsureDTO);
        return count;
    }

    @Override
    public List<TowerInsureBO> listTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureNumber())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureNumber", towerInsureDTO.getInsureNumber()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsurer())) {
            towerInsureDTO.getConditions().add(Restrict.like("insurer", towerInsureDTO.getInsurer()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getInsureByPerson())) {
            towerInsureDTO.getConditions().add(Restrict.like("insureByPerson", towerInsureDTO.getInsureByPerson()));
        }
        if (StringUtils.isNotBlank(towerInsureDTO.getReSign())) {
            towerInsureDTO.getConditions().add(Restrict.eq("reSign", towerInsureDTO.getReSign()));
        }
        towerInsureDTO.getSorts().add("createTime=desc");
        List<TowerInsure> list = super.findByCis(towerInsureDTO, true);

        return BeanTransform.copyProperties(list, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO addTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure towerInsure = BeanTransform.copyProperties(towerInsureTO, TowerInsure.class, true);
        towerInsure.setCreateTime(LocalDateTime.now());
        super.save(towerInsure);
        return BeanTransform.copyProperties(towerInsure, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure towerInsure = BeanTransform.copyProperties(towerInsureTO, TowerInsure.class, true);
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        BeanUtils.copyProperties(towerInsure, cusLevel, "id", "createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(towerInsure, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTowerInsure(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editAccount(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setAccountType(towerInsureTO.getAccountType());
        cusLevel.setBank(towerInsureTO.getBank());
        cusLevel.setAccount(towerInsureTO.getAccount());
        cusLevel.setAccountOwner(towerInsureTO.getAccountOwner());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editContext(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setInsureCode(towerInsureTO.getInsureCode());
        cusLevel.setInsureName(towerInsureTO.getInsureName());
        cusLevel.setInsureRespon(towerInsureTO.getInsureRespon());
        cusLevel.setInsureMoney(towerInsureTO.getInsureMoney());
        cusLevel.setInsureFee(towerInsureTO.getInsureFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO applicant(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setPersonName(towerInsureTO.getPersonName());
        cusLevel.setPersonSex(towerInsureTO.getPersonSex());
        cusLevel.setPersonBorn(LocalDate.parse(towerInsureTO.getPersonBorn()));
        cusLevel.setPersonCountry(towerInsureTO.getPersonCountry());
        cusLevel.setPersonFileType(towerInsureTO.getPersonFileType());
        cusLevel.setPersonFileNum(towerInsureTO.getPersonFileNum());
        cusLevel.setPersonFileEffTerm(towerInsureTO.getPersonFileEffTerm());
        cusLevel.setPersonJob(towerInsureTO.getPersonJob());
        cusLevel.setPersonJobCode(towerInsureTO.getPersonJobCode());
        cusLevel.setPersonAddr(towerInsureTO.getPersonAddr());
        cusLevel.setPersonContact(towerInsureTO.getPersonContact());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editInsurePerson(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setPersonByRelation(towerInsureTO.getPersonByRelation());
        cusLevel.setPersonByName(towerInsureTO.getPersonByName());
        cusLevel.setPersonBySex(towerInsureTO.getPersonBySex());
        cusLevel.setPersonByBorn(LocalDate.parse(towerInsureTO.getPersonByBorn()));
        cusLevel.setPersonByCountry(towerInsureTO.getPersonByCountry());
        cusLevel.setPersonByFileType(towerInsureTO.getPersonByFileType());
        cusLevel.setPersonByFileNum(towerInsureTO.getPersonByFileNum());
        cusLevel.setPersonByFileEffTerm(towerInsureTO.getPersonByFileEffTerm());
        cusLevel.setPersonByJob(towerInsureTO.getPersonByJob());
        cusLevel.setPersonByJobCode(towerInsureTO.getPersonByJobCode());
        cusLevel.setPersonByAddr(towerInsureTO.getPersonByAddr());
        cusLevel.setPersonByContact(towerInsureTO.getPersonByContact());
        cusLevel.setPersonBySocial(towerInsureTO.getPersonBySocial());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editBenefit(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setBenefitOrder(towerInsureTO.getBenefitOrder());
        cusLevel.setBenefiter(towerInsureTO.getBenefiter());
        cusLevel.setBenefitSex(towerInsureTO.getBenefitSex());
        cusLevel.setBenefitBorn(LocalDate.parse(towerInsureTO.getBenefitBorn()));
        cusLevel.setBenefitFileType(towerInsureTO.getBenefitFileType());
        cusLevel.setBenefitFileNum(towerInsureTO.getBenefitFileNum());
        cusLevel.setBenefitBy(towerInsureTO.getBenefitBy());
        cusLevel.setBenefitShare(towerInsureTO.getBenefitShare());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TowerInsureBO editSaleOrgan(TowerInsureTO towerInsureTO) throws SerException {
        TowerInsure cusLevel = super.findById(towerInsureTO.getId());

        cusLevel.setOrganName(towerInsureTO.getOrganName());
        cusLevel.setOrganCode(towerInsureTO.getOrganCode());
        cusLevel.setSaleName(towerInsureTO.getSaleName());
        cusLevel.setSalerCode(towerInsureTO.getSalerCode());
        cusLevel.setOrganContact(towerInsureTO.getOrganContact());
        cusLevel.setOrganAddr(towerInsureTO.getOrganAddr());
        cusLevel.setPostCode(towerInsureTO.getPostCode());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update(cusLevel);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }

    @Override
    public TowerInsureBO getTowerInsure(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        TowerInsure cusLevel = super.findById(id);
        return BeanTransform.copyProperties(cusLevel, TowerInsureBO.class);
    }


}