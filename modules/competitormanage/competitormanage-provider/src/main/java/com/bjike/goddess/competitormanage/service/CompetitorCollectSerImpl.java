package com.bjike.goddess.competitormanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.competitormanage.bo.CollectionTotalBO;
import com.bjike.goddess.competitormanage.bo.CompetitorCollectBO;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.entity.Competitor;
import com.bjike.goddess.competitormanage.entity.CompetitorCollect;
import com.bjike.goddess.competitormanage.enums.BusinessType;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 竞争对手汇总业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitormanageSerCache")
@Service
public class CompetitorCollectSerImpl extends ServiceImpl<CompetitorCollect, CompetitorCollectDTO> implements CompetitorCollectSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CompetitorSer competitorSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorCollectBO saveModel(CompetitorCollectTO to) throws SerException {
        getCusPermission();
        CompetitorCollect model = BeanTransform.copyProperties(to, CompetitorCollect.class, true);
        model.setOperateUser(userAPI.currentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, CompetitorCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public CompetitorCollectBO editModel(CompetitorCollectTO to) throws SerException {
        getCusPermission();
        if (!StringUtils.isEmpty(to.getId())) {
            CompetitorCollect model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, CompetitorCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        getCusPermission();
        if (!StringUtils.isEmpty(id)) {
            CompetitorCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        getCusPermission();
        if (!StringUtils.isEmpty(id)) {
            CompetitorCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CollectionTotalBO> collectionTotal() throws SerException {

        getCusPermission();
        //查询地区
        List<Competitor> competitorList = competitorSer.findBySql("select distinct area  from competitormanage_competitor", Competitor.class, new String[]{"area"});

        List<Competitor> list = competitorSer.findAll();
        List<CollectionTotalBO> returnList = new ArrayList<CollectionTotalBO>();

        //遍历地区
        for (Competitor competitor : competitorList) {
            CollectionTotalBO bo = new CollectionTotalBO();
            bo.setArea(competitor.getArea());
            for (Competitor model : list) {
                if (competitor.getArea().equals(model.getArea())) {

                    Long areaCommunicate = 0l;
                    Long areaSoftware = 0l;
                    Long areaMarketingplan = 0l;
                    Long areaIntelligentize = 0l;
                    Long areaElectroniccommerce = 0l;
                    Long areaRealty = 0l;
                    Long areaFinancial = 0l;
                    Long areaFood = 0l;

                    if (model.getBusinessType() == BusinessType.COMMUNICATE) {
                        bo.setCommunicate(areaCommunicate + 1l);
                    } else if (model.getBusinessType() == BusinessType.SOFTWARE) {
                        bo.setSoftware(areaSoftware + 1l);
                    } else if (model.getBusinessType() == BusinessType.MARKETINGPLAN) {
                        bo.setMarketingplan(areaMarketingplan + 1l);
                    } else if (model.getBusinessType() == BusinessType.INTELLIGENTIZE) {
                        bo.setIntelligentize(areaIntelligentize + 1l);
                    } else if (model.getBusinessType() == BusinessType.ELECTRONICCOMMERCE) {
                        bo.setElectroniccommerce(areaElectroniccommerce + 1l);
                    } else if (model.getBusinessType() == BusinessType.REALTY) {
                        bo.setRealty(areaRealty + 1l);
                    } else if (model.getBusinessType() == BusinessType.FINANCIAL) {
                        bo.setFinancial(areaFinancial + 1l);
                    } else if (model.getBusinessType() == BusinessType.FOOD) {
                        bo.setFood(areaFood + 1l);
                    }
                    returnList.add(bo);
                }
            }
        }

        Long toalCommunicate = returnList.stream().filter(p -> null != p.getCommunicate()).mapToLong(p -> p.getCommunicate()).sum();
        Long toalSoftware = returnList.stream().filter(p -> null != p.getSoftware()).mapToLong(p -> p.getSoftware()).sum();
        Long toalMarketingplan = returnList.stream().filter(p -> null != p.getMarketingplan()).mapToLong(p -> p.getMarketingplan()).sum();
        Long toalIntelligentize = returnList.stream().filter(p -> null != p.getIntelligentize()).mapToLong(p -> p.getIntelligentize()).sum();
        Long toalElectroniccommerce = returnList.stream().filter(p -> null != p.getElectroniccommerce()).mapToLong(p -> p.getElectroniccommerce()).sum();
        Long toalRealty = returnList.stream().filter(p -> null != p.getCommunicate()).mapToLong(p -> p.getCommunicate()).sum();
        Long toalFinancial = returnList.stream().filter(p -> null != p.getFinancial()).mapToLong(p -> p.getFinancial()).sum();
        Long toalFood = returnList.stream().filter(p -> null != p.getFood()).mapToLong(p -> p.getFood()).sum();

        CollectionTotalBO totalBO = new CollectionTotalBO("合计", toalCommunicate, toalSoftware, toalMarketingplan, toalIntelligentize,
                toalElectroniccommerce, toalRealty, toalFinancial, toalFood);
        returnList.add(totalBO);

        return returnList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void sendCollectEmail() throws SerException {
        CompetitorCollectDTO dto = new CompetitorCollectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<CompetitorCollect> list = super.findByCis(dto);
        //遍历所有未冻结汇总定时器,
        for (CompetitorCollect model : list) {
            if (model.getLastSendTime() != null) {
                switch (model.getSendIntervalType()) {
                    case MINUTE:


                        break;

                    case HOUR:
                        break;

                    case DAY:


                        break;

                    case WEEK:
                        break;

                    case MONTH:


                        break;

                    case QUARTER:
                        break;

                    case YEAR:
                        break;

                }
            }

        }

    }


    //校验分钟
    public void minute(CompetitorCollect model) throws SerException {

        if (LocalDateTime.now().minusMinutes(model.getSendInterval()).compareTo(model.getLastSendTime()) > 0) {

            //查询指定汇总间隔的数据
            CompetitorDTO dto = new CompetitorDTO();

            LocalDateTime start = null;
            LocalDateTime end = null;


            switch (model.getCollectInterval()) {

                case ONEDAY:
                    LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
                    start = startOfDay;
                    end = startOfDay.plusDays(1);
                    break;

                case ONEWEEK:

                    LocalDateTime startOfWeek = DateUtil.getStartWeek().atStartOfDay();
                    start = startOfWeek;
                    end = startOfWeek.plusWeeks(1);

                    break;

                case ONEMONTH:

                    LocalDateTime startOfMonth = DateUtil.getStartMonth().atStartOfDay();
                    start = startOfMonth;
                    end = startOfMonth.plusMonths(1);

                    break;
            }

            dto.getConditions().add(Restrict.gt("createTime", start));
            dto.getConditions().add(Restrict.lt("createTime", end));
            List<Competitor> competitorList = competitorSer.findByCis(dto);
            sendEmail(competitorList, model.getSendUser());
        }


    }

    public void sendEmail(List<Competitor> competitorList, String sendUser) {

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CompetitorCollectBO> pageList(CompetitorCollectDTO dto) throws SerException {
        getCusPermission();
        dto.getSorts().add("createTime=desc");
        List<CompetitorCollect> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, CompetitorCollectBO.class);
    }

    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该模块只有商务模块负责人可操作，您的帐号尚无没有权限");
        }
    }
}