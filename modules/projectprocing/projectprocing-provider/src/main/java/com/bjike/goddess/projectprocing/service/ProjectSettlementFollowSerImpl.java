package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.ProjectSettlementFollowBO;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.entity.ProjectSettlementFollow;
import com.bjike.goddess.projectprocing.entity.ProjectSettlementFollow;
import com.bjike.goddess.projectprocing.to.ProjectSettlementFollowTO;
import com.bjike.goddess.projectprocing.utils.CollectData;
import com.bjike.goddess.projectprocing.utils.CollectDataForBusiness;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目结算跟进业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class ProjectSettlementFollowSerImpl extends ServiceImpl<ProjectSettlementFollow, ProjectSettlementFollowDTO> implements ProjectSettlementFollowSer {

    @Override
    public Long countProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        return super.count(projectSettlementFollowDTO);
    }

    @Override
    public List<ProjectSettlementFollowBO> listProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        List<ProjectSettlementFollow> list = super.findByCis(projectSettlementFollowDTO, true);
        List<ProjectSettlementFollowBO> projectSettlementFollowBOS =  BeanTransform.copyProperties(list, ProjectSettlementFollowBO.class);
        return projectSettlementFollowBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSettlementFollowBO addProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException {
        ProjectSettlementFollow projectSettlementFollow = BeanTransform.copyProperties(projectSettlementFollowTO, ProjectSettlementFollow.class, true);
        projectSettlementFollow.setCreateTime(LocalDateTime.now());
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.save(projectSettlementFollow);
        return BeanTransform.copyProperties(projectSettlementFollow, ProjectSettlementFollowBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectSettlementFollowBO editProjectSettlementFollow(ProjectSettlementFollowTO projectSettlementFollowTO) throws SerException {
        if (StringUtils.isBlank(projectSettlementFollowTO.getId())) {
            throw new SerException("编号不能为空");
        }
        ProjectSettlementFollow projectSettlementFollow = super.findById(projectSettlementFollowTO.getId());
        ProjectSettlementFollow temp = BeanTransform.copyProperties(projectSettlementFollowTO, ProjectSettlementFollow.class, true);
        BeanUtils.copyProperties(temp, projectSettlementFollow, "id", "createTime", "outerNameId", "innerNameId", "saleNumId", "businessId");
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.update(projectSettlementFollow);
        return BeanTransform.copyProperties(projectSettlementFollow, ProjectSettlementFollowBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteProjectSettlementFollow(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        //TODO: tanghaixiang 2017-03-31 链接关系没做
        super.remove(id);
    }

    @Override
    public List<ProjectSettlementFollowBO> searchListProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        List<ProjectSettlementFollow> list = super.findByCis(projectSettlementFollowDTO, true);
        return BeanTransform.copyProperties(list, ProjectSettlementFollowBO.class);
    }


    @Override
    public List<CollectData> collect(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws SerException {
        List<CollectData> collectDataList = new ArrayList<>();
        //查询所有外包单位
        String[] fields = new String[]{"outsourcingUnit"};
        List<ProjectSettlementFollow> projectSettlementFollows = super.findBySql("select outsourcingUnit,1 from projectprocing_projectsettlementfollow order by outsourcingUnit asc ", ProjectSettlementFollow.class, fields);
        List<String> units = projectSettlementFollows.stream().map(ProjectSettlementFollow::getOutsourcingUnit).filter(str -> (str != null || !"".equals(str.trim()))).distinct().collect(Collectors.toList());
        for (String unit : units) {
            CollectData collectData = new CollectData();
            String[] businessFields = new String[]{"name", "settleMoney", "noSettleMoney", "counts"};
            List<CollectDataForBusiness> collectDataForBusinesses = super.findBySql(
                    "SELECT businessSubject AS name, sum(settleMoney)  AS settleMoney ,sum(restSettleNum) as noSettleMoney ," +
                            "  count(businessSubject) as counts FROM projectprocing_projectsettlementfollow" +
                            " where outsourcingUnit = '" + unit.trim() + "' GROUP BY businessSubject  ORDER BY businessSubject ASC ", CollectDataForBusiness.class, businessFields);
            collectData.setCollectDataForBusinessList( collectDataForBusinesses );
            //统计是否初验
            String[] startCheckFields = new String[]{"startCheckCount","noStartCheckCount"};
            List<CollectData> startCheck = super.findBySql("SELECT count(ck1.startCheckCondition) AS startCheckCount," +
                    "  ( SELECT count(ck2.startCheckCondition) FROM projectprocing_projectsettlementfollow ck2 " +
                    "    WHERE ck2.outsourcingUnit = '" + unit.trim() + "' AND ck2.startCheckCondition = '已初验' " +
                    "  ) noStartCheckCount " +
                    " FROM projectprocing_projectsettlementfollow ck1 " +
                    " WHERE ck1.outsourcingUnit = '" + unit.trim() + "' AND ck1.startCheckCondition = '未初验'", CollectData.class, startCheckFields);
            collectData.setStartCheckCount( startCheck.get(0).getStartCheckCount() ==null ? 0 : startCheck.get(0).getStartCheckCount() );
            collectData.setNoStartCheckCount( startCheck.get(0).getNoStartCheckCount() ==null ? 0 : startCheck.get(0).getNoStartCheckCount() );
            //统计是否已制作申请结算资料
            String[] settleFileFields = new String[]{"settleFileCount","noSettleFileCount"};
            List<CollectData> settleFile = super.findBySql("SELECT count(ck1.settleFileCondition) AS settleFileCount," +
                    "  ( SELECT count(ck2.settleFileCondition) FROM projectprocing_projectsettlementfollow ck2 " +
                    "    WHERE ck2.outsourcingUnit = '" + unit.trim() + "' AND ck2.settleFileCondition = '否' " +
                    "  ) noSettleFileCount " +
                    " FROM projectprocing_projectsettlementfollow ck1 " +
                    " WHERE ck1.outsourcingUnit = '" + unit.trim() + "' AND ck1.settleFileCondition = '是'", CollectData.class, startCheckFields);
            collectData.setSettleFileCount( settleFile.get(0).getSettleFileCount() ==null ? 0 : settleFile.get(0).getSettleFileCount() );
            collectData.setNoSettleFileCount( settleFile.get(0).getNoSettleFileCount() ==null ? 0 : settleFile.get(0).getNoSettleFileCount() );
            //统计结算进度A、B、C
            String[] settleABCFields = new String[]{"settleProcingACount","settleProcingBCount","settleProcingCCount"};
            List<CollectData> settleABC = super.findBySql("SELECT count(settleProcingA) AS settleProcingA, " +
                            "  count(settleProcingB) AS settleProcingB,  count(settleProcingC) AS settleProcingC " +
                            " FROM projectprocing_projectsettlementfollow WHERE outsourcingUnit = '"+unit.trim()+"'",CollectData.class,settleABCFields);
            collectData.setSettleProcingACount( settleABC.get(0).getSettleProcingACount() ==null ? 0 : settleABC.get(0).getSettleProcingACount() );
            collectData.setSettleProcingBCount( settleABC.get(0).getSettleProcingBCount() ==null ? 0 :  settleABC.get(0).getSettleProcingBCount() );
            collectData.setSettleProcingCCount( settleABC.get(0).getSettleProcingCCount() ==null ? 0 : settleABC.get(0).getSettleProcingCCount() );
            //统计金额
            String[] moneyFields = new String[]{"betraMoney","manageFee","settleMoney","resetSettleMoney","receiveSettleMoney"};
            List<CollectData> money = super.findBySql( "select sum(betraFee) as betraMoney , " +
                    "  sum(manageFee) as manageFee , sum(settleMoney) as settleMoney , " +
                    "  sum(restSettleMoney) as resetSettleMoney , sum(receiveMoney) as receiveSettleMoney " +
                    " from  projectprocing_projectsettlementfollow where outsourcingUnit = '"+unit.trim()+"'", CollectData.class , moneyFields);
            collectData.setBetraMoney( money.get(0).getBetraMoney() ==null ? 0 : money.get(0).getBetraMoney() );
            collectData.setManageFee( money.get(0).getManageFee() ==null ? 0 :  money.get(0).getManageFee() );
            collectData.setSettleMoney( money.get(0).getSettleMoney() ==null ? 0 : money.get(0).getSettleMoney() );
            collectData.setResetSettleMoney( money.get(0).getResetSettleMoney() ==null ? 0 : money.get(0).getResetSettleMoney() );
            collectData.setReceiveSettleMoney( money.get(0).getReceiveSettleMoney() ==null ? 0 : money.get(0).getReceiveSettleMoney() );

            collectData.setOutsourcingUnit( unit );
            collectDataList.add( collectData );
        }
        //合计
        collectTotal( collectDataList );

        return collectDataList;
    }

    //合计
    public void collectTotal ( List<CollectData> collectDataList ) throws SerException {
        CollectData collectData = new CollectData();
        String[] businessFields = new String[]{"name", "settleMoney", "noSettleMoney", "counts"};
        List<CollectDataForBusiness> collectDataForBusinesses = super.findBySql(
                "SELECT businessSubject AS name, sum(settleMoney)  AS settleMoney ,sum(restSettleNum) as noSettleMoney ," +
                        "  count(businessSubject) as counts FROM projectprocing_projectsettlementfollow" +
                        "  GROUP BY businessSubject  ORDER BY businessSubject ASC ", CollectDataForBusiness.class, businessFields);
        collectData.setCollectDataForBusinessList( collectDataForBusinesses );

        //统计是否初验
        String[] startCheckFields = new String[]{"startCheckCount","noStartCheckCount"};
        List<CollectData> startCheck = super.findBySql("SELECT count(ck1.startCheckCondition) AS startCheckCount," +
                "  ( SELECT count(ck2.startCheckCondition) FROM projectprocing_projectsettlementfollow ck2 " +
                "   where ck2.startCheckCondition = '已初验' " +
                "  ) noStartCheckCount " +
                " FROM projectprocing_projectsettlementfollow ck1 " +
                " where ck1.startCheckCondition = '未初验'", CollectData.class, startCheckFields);
        collectData.setStartCheckCount( startCheck.get(0).getStartCheckCount()==null ? 0 : startCheck.get(0).getStartCheckCount() );
        collectData.setNoStartCheckCount( startCheck.get(0).getNoStartCheckCount() ==null ? 0 : startCheck.get(0).getNoStartCheckCount() );
        //统计是否已制作申请结算资料
        String[] settleFileFields = new String[]{"settleFileCount","noSettleFileCount"};
        List<CollectData> settleFile = super.findBySql("SELECT count(ck1.settleFileCondition) AS settleFileCount," +
                "  ( SELECT count(ck2.settleFileCondition) FROM projectprocing_projectsettlementfollow ck2 " +
                "   where  ck2.settleFileCondition = '否' " +
                "  ) noSettleFileCount " +
                " FROM projectprocing_projectsettlementfollow ck1 " +
                " where ck1.settleFileCondition = '是'", CollectData.class, startCheckFields);
        collectData.setSettleFileCount( settleFile.get(0).getSettleFileCount() ==null ? 0 : settleFile.get(0).getSettleFileCount() );
        collectData.setNoSettleFileCount( settleFile.get(0).getNoSettleFileCount() ==null ? 0 : settleFile.get(0).getNoSettleFileCount() );
        //统计结算进度A、B、C
        String[] settleABCFields = new String[]{"settleProcingACount","settleProcingBCount","settleProcingCCount"};
        List<CollectData> settleABC = super.findBySql("SELECT count(settleProcingA) AS settleProcingA, " +
                "  count(settleProcingB) AS settleProcingB,  count(settleProcingC) AS settleProcingC " +
                " FROM projectprocing_projectsettlementfollow ",CollectData.class,settleABCFields);
        collectData.setSettleProcingACount( settleABC.get(0).getSettleProcingACount() ==null ? 0 : settleABC.get(0).getSettleProcingACount() );
        collectData.setSettleProcingBCount( settleABC.get(0).getSettleProcingBCount() ==null ? 0 : settleABC.get(0).getSettleProcingBCount() );
        collectData.setSettleProcingCCount( settleABC.get(0).getSettleProcingCCount() ==null ? 0 :  settleABC.get(0).getSettleProcingCCount() );
        //统计金额
        String[] moneyFields = new String[]{"betraMoney","manageFee","settleMoney","resetSettleMoney","receiveSettleMoney"};
        List<CollectData> money = super.findBySql( "select sum(betraFee) as betraMoney , " +
                "  sum(manageFee) as manageFee , sum(settleMoney) as settleMoney , " +
                "  sum(restSettleMoney) as resetSettleMoney , sum(receiveMoney) as receiveSettleMoney " +
                " from  projectprocing_projectsettlementfollow ", CollectData.class , moneyFields);
        collectData.setBetraMoney( money.get(0).getBetraMoney() ==null ? 0 : money.get(0).getBetraMoney() );
        collectData.setManageFee( money.get(0).getManageFee() ==null ? 0 : money.get(0).getManageFee() );
        collectData.setSettleMoney( money.get(0).getSettleMoney() ==null ? 0 :  money.get(0).getSettleMoney() );
        collectData.setResetSettleMoney( money.get(0).getResetSettleMoney() ==null ? 0 : money.get(0).getResetSettleMoney() );
        collectData.setReceiveSettleMoney( money.get(0).getReceiveSettleMoney() ==null ? 0 :  money.get(0).getReceiveSettleMoney() );

        collectData.setOutsourcingUnit("合计");
        collectDataList.add(collectData);
    }


}