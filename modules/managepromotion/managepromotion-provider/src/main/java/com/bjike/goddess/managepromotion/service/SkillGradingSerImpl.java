package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.CalculateBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingABO;
import com.bjike.goddess.managepromotion.bo.SkillGradingBBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingCBO;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingBDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingCDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGrading;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;
import com.bjike.goddess.managepromotion.entity.SkillGradingB;
import com.bjike.goddess.managepromotion.entity.SkillGradingC;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.excel.SonPermissionObject;
import com.bjike.goddess.managepromotion.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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
 * 技能定级业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillGradingSerImpl extends ServiceImpl<SkillGrading, SkillGradingDTO> implements SkillGradingSer {

    @Autowired
    private SkillGradingASer skillGradingASer;
    @Autowired
    private SkillGradingBSer skillGradingBSer;
    @Autowired
    private SkillGradingCSer skillGradingCSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SkillPromotionApplySer skillPromotionApplySer;
    @Autowired
    private OverviewSkillLevelSer overviewSkillLevelSer;
    @Autowired
    private EmployeePromotedSer employeePromotedSer;
    @Autowired
    private EmployeeFunctionLevelSer employeeFunctionLevelSer;

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
        obj.setName("skillgrading");
        obj.setDescribesion("技能定级");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = employeeFunctionLevelSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("employeefunctionlevel");
        obj.setDescribesion("员工技能定级");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWeb = overviewSkillLevelSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("overviewskilllevel");
        obj.setDescribesion("技能等级情况概览");
        if (flagSeeWeb) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOpen = employeePromotedSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("employeepromoted");
        obj.setDescribesion("员工已晋升情况");
        if (flagSeeOpen) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = skillPromotionApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("skillpromotionapply");
        obj.setDescribesion("技能晋升申请");
        if (flagSeeEmail) {
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long countSkillGrading(SkillGradingCDTO skillGradingCDTO) throws SerException {
        Long count = skillGradingCSer.count(skillGradingCDTO);
        return count;
    }

    @Override
    public SkillGradingABO getOne(String id) throws SerException {
        SkillGradingA skillGradingA = skillGradingASer.findById(id);
        return BeanTransform.copyProperties(skillGradingA, SkillGradingABO.class);
    }

    @Override
    public List<SkillGradingABO> findListSkillGrading(SkillGradingADTO skillGradingADTO) throws SerException {
        checkSeeIdentity();
        SkillGradingADTO dtoA = new SkillGradingADTO();
        List<SkillGradingA> listA = skillGradingASer.findByCis(dtoA);
        List<SkillGradingABO> listABO = BeanTransform.copyProperties(listA, SkillGradingABO.class);

        if (listABO != null) {
            for (SkillGradingABO aBo : listABO) {
                SkillGradingBDTO dtoB = new SkillGradingBDTO();
                dtoB.getConditions().add(Restrict.eq("skillGradingA.id", aBo.getId()));
                List<SkillGradingB> listB = skillGradingBSer.findByCis(dtoB);
                List<SkillGradingBBO> bboList = BeanTransform.copyProperties(listB, SkillGradingBBO.class);
                aBo.setSkillGradingBBOS(bboList);
                if (bboList != null) {
                    for (SkillGradingBBO skillGradingBBO : bboList) {
                        SkillGradingCDTO dtoC = new SkillGradingCDTO();
                        dtoC.getConditions().add(Restrict.eq("skillGradingB.id", skillGradingBBO.getId()));
                        List<SkillGradingC> listC = skillGradingCSer.findByCis(dtoC);
                        List<SkillGradingCBO> cboList = BeanTransform.copyProperties(listC, SkillGradingCBO.class);
                        skillGradingBBO.setSkillGradingCBOS(cboList);
                    }
                }

            }
        }
        return listABO;

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void insertSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
        checkAddIdentity();
        SkillGradingA skillGradingA = BeanTransform.copyProperties(skillGradingATO, SkillGradingA.class, true);
        skillGradingASer.save(skillGradingA);
        List<SkillGradingBTO> skillGradingBTOS = skillGradingATO.getSkillGradingBTOS();

        if (skillGradingBTOS != null) {
            for (SkillGradingBTO skillGradingBTO : skillGradingBTOS) {
                SkillGradingB skillGradingB = BeanTransform.copyProperties(skillGradingBTO, SkillGradingB.class, true);
                skillGradingB.setSkillGradingA(skillGradingA);
                skillGradingBSer.save(skillGradingB);
                List<SkillGradingCTO> skillGradingCTOS = skillGradingBTO.getSkillGradingCTOS();
                if (skillGradingCTOS != null) {
                    for (SkillGradingCTO skillGradingCTO : skillGradingCTOS) {
                        SkillGradingC skillGradingC = BeanTransform.copyProperties(skillGradingCTO, SkillGradingC.class, true);
                        skillGradingC.setSkillGradingB(skillGradingB);

                        //职衔补助额度
                        Integer quotaJobTitle = skillGradingCTO.getTechnicalRank() * skillGradingCTO.getConvertLine();
                        skillGradingC.setQuotaJobTitle(quotaJobTitle);
                        //补助额度合计
                        Integer totalAllowance = skillGradingCTO.getSubsidiesAmount() + quotaJobTitle;
                        skillGradingC.setTotalAllowance(totalAllowance);
                        //每次晋升增长幅度
                        SkillGradingC skill = findBySql(skillGradingATO.getSystem(), skillGradingATO.getIndustry(), skillGradingATO.getSubject(), skillGradingCTO.getTechnicalRank());
                        if (skill != null) {
                            skillGradingC.setGrowth(skillGradingC.getTotalAllowance() - skill.getTotalAllowance());
                        } else {
                            skillGradingC.setGrowth(skillGradingC.getTotalAllowance());
                        }
                        skillGradingC.setCreateTime(LocalDateTime.now());
                        skillGradingCSer.save(skillGradingC);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void editSkillGrading(SkillGradingATO skillGradingATO) throws SerException {
        checkAddIdentity();
        SkillGradingA skillGradingA = skillGradingASer.findById(skillGradingATO.getId());
        BeanTransform.copyProperties(skillGradingATO, skillGradingA, true);
        skillGradingASer.update(skillGradingA);

        //先删掉B表对应数据
        SkillGradingBDTO skillGradingBDTO = new SkillGradingBDTO();
        skillGradingBDTO.getConditions().add(Restrict.eq("skillGradingA.id", skillGradingA.getId()));
        List<SkillGradingB> bList = skillGradingBSer.findByCis(skillGradingBDTO);
        if (bList != null && bList.size() > 0) {
            //查询对应C表的数据，先删除
            List<String> bIdList = bList.stream().map(SkillGradingB::getId).collect(Collectors.toList());
            String[] bids = new String[bIdList.size()];
            bids = bIdList.toArray(bids);
            SkillGradingCDTO skillGradingCDTO = new SkillGradingCDTO();
            skillGradingCDTO.getConditions().add(Restrict.in("skillGradingB.id", bids));
            List<SkillGradingC> cList = skillGradingCSer.findByCis(skillGradingCDTO);
            if (cList != null && cList.size() > 0) {
                skillGradingCSer.remove(cList);
            }

            skillGradingBSer.remove(bList);
        }

        //查询B表的数据并修改
        List<SkillGradingBTO> skillGradingBTOS = skillGradingATO.getSkillGradingBTOS();
        if (skillGradingBTOS != null) {
            for (SkillGradingBTO skillGradingBTO : skillGradingBTOS) {
                SkillGradingB skillGradingB = BeanTransform.copyProperties(skillGradingBTO, SkillGradingB.class, true);
                skillGradingB.setSkillGradingA(skillGradingA);
                skillGradingBSer.update(skillGradingB);

                //查询C表的数据并修改
                List<SkillGradingCTO> skillGradingCTOS = skillGradingBTO.getSkillGradingCTOS();
                if (skillGradingCTOS != null) {
                    for (SkillGradingCTO skillGradingCTO : skillGradingCTOS) {
                        SkillGradingC skillGradingC = BeanTransform.copyProperties(skillGradingCTO, SkillGradingC.class, true);
                        skillGradingC.setSkillGradingB(skillGradingB);

                        //职衔补助额度
                        Integer quotaJobTitle = skillGradingCTO.getTechnicalRank() * skillGradingCTO.getConvertLine();
                        skillGradingC.setQuotaJobTitle(quotaJobTitle);
                        //补助额度合计
                        Integer totalAllowance = skillGradingCTO.getSubsidiesAmount() + quotaJobTitle;
                        skillGradingC.setTotalAllowance(totalAllowance);

                        skillGradingCSer.update(skillGradingC);
                    }
                }
            }
        }


    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeSkillGrading(String id) throws SerException {
        checkAddIdentity();
        SkillGradingC c = skillGradingCSer.findById(id);
        if (c == null) {
            throw new SerException("该对象不存在");
        }
//       String b_id=c.getSkillGradingB().getId();
        skillGradingCSer.remove(id);
        List<SkillGradingB> bList = skillGradingBSer.findAll();
        List<SkillGradingA> aList = skillGradingASer.findAll();
        Set<String> bids = new HashSet<>();
        Set<String> aids = new HashSet<>();
        for (SkillGradingC c1 : skillGradingCSer.findAll()) {
            bids.add(c1.getSkillGradingB().getId());
        }
        for (SkillGradingB b : bList) {
            if (!bids.contains(b.getId())) {
                skillGradingBSer.remove(b.getId());

            }
        }
        for (SkillGradingB b1 : skillGradingBSer.findAll()) {
            aids.add(b1.getSkillGradingA().getId());
        }
        for (SkillGradingA a : aList) {
            if (!aids.contains(a.getId())) {
                skillGradingASer.remove(a.getId());
            }
        }

    }

    private SkillGradingC findBySql(String system, String industry, String subject, Integer technicalRank) throws SerException {
        Integer[] technicals = new Integer[]{technicalRank};
        String[] systems = new String[]{system};
        String[] industrys = new String[]{industry};
        String[] subjects = new String[]{subject};
        List<SkillGradingC> list = null;
        for (int i = 0; i < technicals.length; i++) {
            String sql = "SELECT max(technicalRank) technicalRank,skillLevel,totalAllowance " +
                    " FROM managepromotion_skillgrading WHERE system='" + systems[i] + "' and industry='" + industrys[i] + "' " +
                    "and subject='" + subjects[i] + "' and technicalRank='" + technicals[i] + "' GROUP BY skillLevel,totalAllowance ";
            String[] fields = new String[]{"technicalRank", "skillLevel", "totalAllowance"};
            list = super.findBySql(sql, SkillGradingC.class, fields);
        }
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CalculateBO> calculate(CalculateTO to) throws SerException {
//        SkillGradingADTO adto = new SkillGradingADTO();
//        adto.getConditions().add(Restrict.eq("major", to.getMain()));
//        List<SkillGradingA> listA = skillGradingASer.findByCis(adto);
//        List<CalculateBO> calculateBOS = new ArrayList<>();
//        String main = to.getMain();
//        Integer money = to.getMoney();
//        String[] fields = new String[]{"major","grade"};
//        String sql = "SELECT a.major,c.grade as grade FROM managepromotion_skillgradinga a," +
//                " managepromotion_skillgradingc c, managepromotion_skillgradingb b " +
//                " WHERE a.major='" + main + "' AND (c.subsidiesAmount+c.quotaJobTitle) = '" + money + "' and " +
//                " a.id=b.skillGradingA_id AND b.id= c.skillGradingB_id group by a.major,c.grade ";
//        List<SkillGradingC> listC = skillGradingCSer.findBySql(sql, SkillGradingC.class, fields);
//        if (listC != null) {
//            for (SkillGradingC skillGradingC : listC) {
//                CalculateBO bo = new CalculateBO();
//                bo.setMain(to.getMain());
//                bo.setSkill(skillGradingC.getGrade());
//                calculateBOS.add(bo);
//            }
//        }
//        return calculateBOS;
//    }
        SkillGradingADTO adto = new SkillGradingADTO();
        adto.getConditions().add(Restrict.eq("major", to.getMain()));
        List<SkillGradingA> listA = skillGradingASer.findByCis(adto);
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String main = to.getMain();
        Integer money = to.getMoney();
        String eventFirst= to.getEventFirst();
        String[] fields = new String[]{"major","grade"};
        String team = "" ;
        if(StringUtils.isNotEmpty(main)){
            team += " and a.major='" + main + "'";
        }
        if(null != money){
            team += " AND (c.subsidiesAmount+c.quotaJobTitle) = '" + money + "'";
        }
        String sql = "SELECT a.major,c.grade as grade FROM managepromotion_skillgradinga a," +
                " managepromotion_skillgradingc c, managepromotion_skillgradingb b " +
                " WHERE 1=1 and " +
                " a.id=b.skillGradingA_id AND b.id= c.skillGradingB_id "+
                team+
                " group by a.major,c.grade ";

        //如果有第三个条件
        if(StringUtils.isNotEmpty(eventFirst)){
            team += " and c.subsidiesAmount ="+eventFirst;
        }
//        if(StringUtils.isNotEmpty(条件2)){
//            team += " and 字段2 ="+条件2;
//        }
        List<SkillGradingC> listC = skillGradingCSer.findBySql(sql, SkillGradingC.class, fields);
        if (listC != null) {
            for (SkillGradingC skillGradingC : listC) {
                CalculateBO bo = new CalculateBO();
                bo.setMain(to.getMain());
                bo.setSkill(skillGradingC.getGrade());
                calculateBOS.add(bo);
            }
        }
        return calculateBOS;
    }
}