package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.entity.CompanysScheme;
import com.bjike.goddess.staffshares.entity.Dividends;
import com.bjike.goddess.staffshares.entity.Scheme;
import com.bjike.goddess.staffshares.enums.Status;
import com.bjike.goddess.staffshares.to.DividendsTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 干股分红表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class DividendsSerImpl extends ServiceImpl<Dividends, DividendsDTO> implements DividendsSer {
    @Autowired
    private SchemeSer schemeSer;
    @Autowired
    private CompanysSchemeSer companysSchemeSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<CompanySchemeBO> detail() throws SerException {
        List<Scheme> schemeList = schemeSer.findAll().stream().filter(obj -> obj.getStatus().equals(Status.ISSUED)).collect(Collectors.toList());
        List<CompanySchemeBO> companySchemeBOs = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(schemeList)) {
            for (Scheme scheme : schemeList) {
                CompanysScheme companySchemeBO = new CompanysScheme();
                companySchemeBO.setCode(scheme.getCode());
                companySchemeBO.setName(scheme.getName());
                companySchemeBO.setPublisher(scheme.getPublisher());
                companySchemeBO.setNumber(scheme.getNumber());
                companySchemeBO.setPrice(scheme.getPrice());
                companySchemeBO.setTime(scheme.getTime());
                companySchemeBO.setQuantityNum(scheme.getNumber() - scheme.getSharesNum());
                companySchemeBO.setSharesNum(scheme.getSharesNum());
                companySchemeBO.setMoney((scheme.getNumber() - scheme.getSharesNum()) * scheme.getPrice());
                if (companySchemeBO.getSharesNum() > 0) {
                    companySchemeBO.setStatus("发行中");
                } else {
                    companySchemeBO.setStatus("结束");
                }
                companysSchemeSer.save(companySchemeBO);
            }
        }
        BeanTransform.copyProperties(companysSchemeSer.findAll(), companySchemeBOs);
        return companySchemeBOs;
    }

    @Override
    public void dividends(DividendsTO to) throws SerException {
        CompanysScheme companysScheme = companysSchemeSer.findById(to.getId());
        if (companysScheme == null) {
            throw new SerException("数据对象为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (!cheakFinance(userBO)) {
            throw new SerException("当前用户不是财务部人员，没有权限");
        }
        Dividends entity = new Dividends();
        BeanTransform.copyProperties(to, entity, true);
        entity.setShareholder("公司");
        entity.setCode(companysScheme.getCode());
        entity.setName(companysScheme.getName());
        entity.setTaxProfit(to.getTaxProfit());
        entity.setNum(companysScheme.getNumber());
        entity.setTotalEquity(companysScheme.getNumber() * companysScheme.getPrice());
        entity.setEarnings(entity.getTaxProfit() / entity.getTotalEquity());
        entity.setTotalEarnings(entity.getEarnings() * entity.getNum());
//        entity.setBuyTime();
//        entity.setDuration();
        entity.setDividendTime(LocalDate.parse(to.getDividendTime()));
        entity.setTime(to.getTime());
//        entity.setRemark(to.getRemark());
//        entity.setSituation();
        super.save(entity);
    }

    @Override
    public List<DividendsBO> maps(DividendsDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        List<Scheme> list = super.findByPage(dto);
        List<SchemeApplicationBO> schemeBOList = BeanTransform.copyProperties(list, SchemeApplicationBO.class, false, "sharesNum");
        return schemeBOList;
    }

    @Override
    public DividendsBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Scheme scheme = super.findById(id);
        return BeanTransform.copyProperties(scheme, SchemeBO.class);
    }

}

    @Override
    public Long getTotal(DividendsDTO dto) throws SerException {
        searchCondition(schemeDTO);
        Long count = super.count(schemeDTO);
        return count;
    }

    private Boolean cheakFinance(UserBO userBO) throws SerException {
        Boolean tar = false;
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId()).stream().filter(str -> str.getStatus().equals(com.bjike.goddess.common.api.type.Status.THAW)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) && "admin".equals(userBO.getUsername())) {
                    tar = true;
                }
            }
        }
        return tar;
    }

    public void searchCondition(DividendsDTO dto) throws SerException {
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(dto.getCode())) {
            dto.getConditions().add(Restrict.eq("code", dto.getCode()));
        }
        /**
         * 方案名称
         */
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }/**
         * 税后利润
         */
        if (null != dto.getTaxProfit()) {
            dto.getConditions().add(Restrict.like("taxProfit", dto.getTaxProfit()));
        }
        /**
         * 持股数
         */
        if (0 <= dto.getNum()) {
            dto.getConditions().add(Restrict.like("num", dto.getNum()));
        }
        /**
         * 总股本
         */
        if (null != dto.getTotalEquity()) {
            dto.getConditions().add(Restrict.eq("totalEquity", dto.getTotalEquity()));
        }
        /**
         * 每股收益
         */
        if (null != dto.getEarnings()) {
            dto.getConditions().add(Restrict.eq("earnings", dto.getEarnings()));
        }/**
         * 总收益/分红
         */
        if (null != dto.getTotalEarnings()) {
            dto.getConditions().add(Restrict.like("totalEarnings", dto.getTotalEarnings()));
        }
        /**
         * 购入时间
         */
        if (StringUtils.isNotBlank(dto.getBuyTime())) {
            dto.getConditions().add(Restrict.like("buyTime", dto.getBuyTime()));
        }
        /**
         * 持股时长
         */
        if (0 <= dto.getDuration()) {
            dto.getConditions().add(Restrict.eq("duration", dto.getDuration()));
        }/**
         * 分红发放时间
         */
        if (StringUtils.isNotBlank(dto.getDividendTime())) {
            dto.getConditions().add(Restrict.like("dividendTime", dto.getDividendTime()));
        }
        /**
         * 本次红利收益时间段
         */
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto.getConditions().add(Restrict.like("time", dto.getTime()));
        }

        /**
         * 备注
         */
        if (StringUtils.isNotBlank(dto.getRemark())) {
            dto.getConditions().add(Restrict.eq("remark", dto.getRemark()));
        }/**
         * 持股人确认情况
         */
        if (null != dto.getSituation()) {
            dto.getConditions().add(Restrict.like("situation", dto.getSituation()));
        }
        /**
         * 持股人
         */
        if (StringUtils.isNotBlank(dto.getShareholder())) {
            dto.getConditions().add(Restrict.like("shareholder", dto.getShareholder()));
        }
    }
}