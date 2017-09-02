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

    @Override
    public List<String> getSkillLevel() throws SerException {
        String[] fields = new String[]{"skillLevel"};
        String sql = "SELECT skillLevel FROM managepromotion_skillgradingb GROUP BY skillLevel ";
        List<SkillGradingB> skillGradingBS = super.findBySql(sql, SkillGradingB.class, fields);
        List<String> list = skillGradingBS.stream().map(SkillGradingB::getSkillLevel).collect(Collectors.toList());
        return list;
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


//    @Override
//    public List<SkillBO> calculate(CalculateTO to) throws SerException {
//        List<SkillBO> skillBOS = new ArrayList<>(0);
//        String main = to.getMain();
//        Integer money = to.getMoney();
//        String[] fields = new String[]{"major", "grade"};
//        StringBuilder sb = new StringBuilder();
//        if (StringUtils.isNotBlank(main) && null != money) {
//            sb.append(" SELECT a.major,c.grade as grade FROM managepromotion_skillgradinga a," +
//                    " managepromotion_skillgradingc c, managepromotion_skillgradingb b " +
//                    " WHERE 1=1 and " +
//                    " a.id=b.skillGradingA_id AND b.id= c.skillGradingB_id " +
//                    " and a.major='" + main + "' AND c.totalAllowance = '" + money + "'");
//        }
//        sb.append("  group by a.major,c.grade ");
//        skillBOS = super.findBySql(sb.toString(), SkillBO.class, fields);
//        init(skillBOS, money);
//        return skillBOS;

//    }


    public static List<List<Object>> str(List<String> list) {
        List<List<Object>> result = new ArrayList<List<Object>>();
        long n = (long) Math.pow(2, list.size());
        List<Object> combine;
        for (long l = 0L; l < n; l++) {
            combine = new ArrayList<Object>();
            for (int i = 0; i < list.size(); i++) {
                if ((l >>> i & 1) == 1)
                    combine.add(list.get(i));
            }
            result.add(combine);
        }
        return result;
    }


//    public static void main(String[] args) {
//        Integer[] a3 = new Integer[]{2000, 1200, 500, 300, 1975, 1425, 400, 3300, 2800, 1150, 1700, 2250, 3800, 2525, 1000, 1250};
//        List<Integer> list = Arrays.asList(a3);
//        Comparator comp = Collections.reverseOrder();
//        Collections.sort(list, comp);
//        List<String> strlist = new ArrayList<String>();
//        for (int i = 0, len = list.size(); i < len; i++) {
//            strlist.add(list.get(i) + "");
//        }
//        System.out.println(strlist);
//        //所需要的组合
//        List<List<Object>> all = new ArrayList<List<Object>>();
//        //所有组合
//        List<List<Object>> aa = str(strlist);
//        for (List<Object> ss : aa) {
//            //计算组合
//            int temp = 0;
//            for (Object s : ss) {
//                temp = temp + Integer.parseInt(s.toString());
//            }
//            //比较输入金额，得到所需组合
//            if (2000 == temp) {
//                all.add(ss);
//            }
//        }
//        System.out.println(all);
//    }


    @Override
    public List<CalculateBO> calculate(CalculateTO to, SkillGradingADTO dto) throws SerException {

        Integer money = null;
        String mainProject = "";
        if (StringUtils.isNotBlank(to.getMain()) && null != to.getMoney()) {
            money = to.getMoney();
            mainProject = to.getMain();
        } else {
            throw new SerException("主项和金额不能为空");
        }
        //第一种情况
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};

        StringBuilder sb = new StringBuilder();

        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill,'' AS eventFirst, ")
                .append(" '' AS eventFirstSkill,'' AS eventSecond, '' AS eventSecondSkill, ")
                .append(" '' AS eventThird, '' AS eventThirdSkill,'' AS eventFour, ")
                .append(" '' AS eventFourSkill,'' AS eventFive, '' AS eventFiveSkill ")
                .append(" from ")
                .append(sqlComboTable.toString())
                .append(" WHERE skill.subsidiesAmount+skill.quotaJobTitle=" + money + " AND skill.major='" + mainProject + "'")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");

        //第二中情况
        List<CalculateBO> calculateTwo = new ArrayList<>();
        calculateTwo = calculateSecond(to);
//        calculateTwo = caculateTwo(to);


        //第三中情况:补助+主项+两个其他补助
        List<CalculateBO> calculateThird = new ArrayList<>();
//        calculateThird = caculateThird(to);
        calculateThird = calculateThird(to);
        //第四中情况:补助+主项+三个其他补助
        List<CalculateBO> calculateFour = new ArrayList<>();
//        calculateFour = caculateFour(to);
        calculateFour = calculateFour(to);
        //第五中情况:补助+主项+四个其他补助
        List<CalculateBO> calculateFive = new ArrayList<>();
//        calculateFive = caculateFive(to);
//        calculateFive = calculateFive(to);
        //第六中情况:补助+主项+五个其他补助
//        List<CalculateBO> calculateSix = new ArrayList<>();
//        calculateSix = caculateSix(to);
//        calculateSix = calculateSix(to);


        List<CalculateBO> calculateOne = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        calculateTwo.addAll(calculateOne);
        calculateThird.addAll(calculateTwo);
        calculateFour.addAll(calculateThird);
//        calculateFive.addAll(calculateFour);
//        calculateSix.addAll(calculateFive);
//        int limit = dto.getLimit();
//        int start = limit * dto.getPage();
//        return calculateTwo.stream().skip(start).limit(limit).collect(Collectors.toList());
        return calculateFour;
    }

    private List<CalculateBO> caculateTwo(CalculateTO to) throws SerException {
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        StringBuffer sqlComboTable1 = new StringBuffer("");
        sqlComboTable1.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill1 ");

        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};
        StringBuilder sb = new StringBuilder();


        //第二中情况:补助+主项+一个其他补助
        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill, ")
                .append(" skill1.major AS eventFirst,skill1.grade AS eventFirstSkill, ")
                .append(" '' AS eventSecond,'' AS eventSecondSkill,'' AS eventThird,'' AS eventThirdSkill, ")
                .append(" '' AS eventFour,'' AS eventFourSkill,'' AS eventFive,'' AS eventFiveSkill ")
                .append(" FROM ")
                .append(sqlComboTable.toString())
                .append("JOIN")
                .append(sqlComboTable1.toString())
                .append("ON")
                //AND skill1.major != skill.major
                .append(" skill1.grade != skill.grade  AND skill.quotaJobTitle + skill.quotaJobTitle < " + to.getMoney() + " ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount = " + to.getMoney() + " ")
                .append(" where skill.grade<skill1.grade ")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");
        //拼小项1
        if (StringUtils.isNotBlank(to.getEventFirst())) {
            sb.append(" and a.eventFirst  = '" + to.getEventFirst() + "'  ");
            //拼小项2
            if (StringUtils.isNotBlank(to.getEventSecond())) {
                sb.append(" a.eventSecond = '" + to.getEventSecond() + "'  ");
                //拼小项3
                if (StringUtils.isNotBlank(to.getEventThird())) {
                    sb.append(" a.eventThird = '" + to.getEventThird() + "'  ");
                    //拼小项4
                    if (StringUtils.isNotBlank(to.getEventFour())) {
                        sb.append(" a.eventFour = '" + to.getEventFour() + "'  ");
                        if (StringUtils.isNotBlank(to.getEventFive())) {
                            //拼小项5
                            sb.append(" a.eventFive = '" + to.getEventFive() + "'  ");
                        }
                    }
                }
            }
        }
        List<CalculateBO> calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        return calculateBOS;

    }

    private List<CalculateBO> caculateThird(CalculateTO to) throws SerException {
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        StringBuffer sqlComboTable1 = new StringBuffer("");
        sqlComboTable1.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill1 ");

        StringBuffer sqlComboTable2 = new StringBuffer("");
        sqlComboTable2.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill2 ");


        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};

        StringBuilder sb = new StringBuilder();

        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill, ")
                .append(" skill1.major AS eventFirst,skill1.grade AS eventFirstSkill, ")
                .append(" skill2.major AS eventSecond,skill2.grade  AS eventSecondSkill,'' AS eventThird,'' AS eventThirdSkill, ")
                .append(" '' AS eventFour,'' AS eventFourSkill,'' AS eventFive,'' AS eventFiveSkill ")
                .append(" FROM ")
                .append(sqlComboTable.toString())
                .append("JOIN")
                .append(sqlComboTable1.toString())
                .append("ON")
                .append(" skill1.grade != skill.grade  AND skill.subsidiesAmount + skill.quotaJobTitle < " + to.getMoney() + " ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable2.toString())
                .append(" ON ")
                .append(" skill2.grade != skill1.grade AND skill2.grade != skill.grade ")
//                .append(" AND skill2.major != skill1.major AND skill2.major != skill.major ")AND skill1.major != skill.major
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + skill2.subsidiesAmount = " + to.getMoney() + " ")
                .append(" where skill.grade<skill1.grade AND skill1.grade <skill2.grade ")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");
        //拼小项1
        if (StringUtils.isNotBlank(to.getEventFirst())) {
            sb.append(" and a.eventFirst  = '" + to.getEventFirst() + "'  ");
            //拼小项2
            if (StringUtils.isNotBlank(to.getEventSecond())) {
                sb.append(" a.eventSecond = '" + to.getEventSecond() + "'  ");
                //拼小项3
                if (StringUtils.isNotBlank(to.getEventThird())) {
                    sb.append(" a.eventThird = '" + to.getEventThird() + "'  ");
                    //拼小项4
                    if (StringUtils.isNotBlank(to.getEventFour())) {
                        sb.append(" a.eventFour = '" + to.getEventFour() + "'  ");
                        if (StringUtils.isNotBlank(to.getEventFive())) {
                            //拼小项5
                            sb.append(" a.eventFive = '" + to.getEventFive() + "'  ");
                        }
                    }
                }
            }
        }
        List<CalculateBO> calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        return calculateBOS;
    }

    private List<CalculateBO> caculateFour(CalculateTO to) throws SerException {
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        StringBuffer sqlComboTable1 = new StringBuffer("");
        sqlComboTable1.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill1 ");

        StringBuffer sqlComboTable2 = new StringBuffer("");
        sqlComboTable2.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill2 ");

        StringBuffer sqlComboTable3 = new StringBuffer("");
        sqlComboTable3.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill3 ");

        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};

        StringBuilder sb = new StringBuilder();

        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill, ")
                .append(" skill1.major AS eventFirst,skill1.grade AS eventFirstSkill, ")
                .append(" skill2.major AS eventSecond,skill2.grade  AS eventSecondSkill,skill3.major AS eventThird,skill3.grade AS eventThirdSkill, ")
                .append(" '' AS eventFour,'' AS eventFourSkill,'' AS eventFive,'' AS eventFiveSkill ")
                .append(" FROM ")
                .append(sqlComboTable.toString())
                .append("JOIN")
                .append(sqlComboTable1.toString())
                .append("ON")
                .append(" skill1.grade != skill.grade AND skill.subsidiesAmount + skill.quotaJobTitle  < " + to.getMoney() + " ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable2.toString())
                .append(" ON ")
                .append(" skill2.grade != skill1.grade AND skill2.grade != skill.grade ")
//                .append(" and skill2.major != skill1.major AND skill2.major != skill.major ") AND skill1.major != skill.major
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + skill2.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable3.toString())
                .append(" ON ")
                .append(" skill3.grade != skill2.grade AND skill3.grade != skill1.grade ")
                .append(" AND skill3.grade != skill.grade ")
//                .append(" and skill3.major != skill2.major AND skill3.major != skill1.major ")
//                .append(" AND skill3.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + skill2.subsidiesAmount + skill3.subsidiesAmount = " + to.getMoney() + " ")
                .append(" where skill.grade<skill1.grade AND skill1.grade <skill2.grade AND skill2.grade<skill3.grade ")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");
        //拼小项1
        if (StringUtils.isNotBlank(to.getEventFirst())) {
            sb.append(" and a.eventFirst  = '" + to.getEventFirst() + "'  ");
            //拼小项2
            if (StringUtils.isNotBlank(to.getEventSecond())) {
                sb.append(" a.eventSecond = '" + to.getEventSecond() + "'  ");
                //拼小项3
                if (StringUtils.isNotBlank(to.getEventThird())) {
                    sb.append(" a.eventThird = '" + to.getEventThird() + "'  ");
                    //拼小项4
                    if (StringUtils.isNotBlank(to.getEventFour())) {
                        sb.append(" a.eventFour = '" + to.getEventFour() + "'  ");
                        if (StringUtils.isNotBlank(to.getEventFive())) {
                            //拼小项5
                            sb.append(" a.eventFive = '" + to.getEventFive() + "'  ");
                        }
                    }
                }
            }
        }
        List<CalculateBO> calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        return calculateBOS;
    }

    private List<CalculateBO> caculateFive(CalculateTO to) throws SerException {
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        StringBuffer sqlComboTable1 = new StringBuffer("");
        sqlComboTable1.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill1 ");

        StringBuffer sqlComboTable2 = new StringBuffer("");
        sqlComboTable2.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill2 ");

        StringBuffer sqlComboTable3 = new StringBuffer("");
        sqlComboTable3.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill3 ");

        StringBuffer sqlComboTable4 = new StringBuffer("");
        sqlComboTable4.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill4 ");


        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};

        StringBuilder sb = new StringBuilder();

        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill, ")
                .append(" skill1.major AS eventFirst,skill1.grade AS eventFirstSkill, ")
                .append(" skill2.major AS eventSecond,skill2.grade  AS eventSecondSkill,skill3.major AS eventThird,skill3.grade AS eventThirdSkill, ")
                .append(" skill4.major AS eventFour,skill4.grade AS eventFourSkill,'' AS eventFive,'' AS eventFiveSkill ")
                .append(" FROM ")
                .append(sqlComboTable.toString())
                .append("JOIN")
                .append(sqlComboTable1.toString())
                .append("ON")
                .append(" skill1.grade != skill.grade  AND skill.subsidiesAmount + skill.quotaJobTitle < " + to.getMoney() + " ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable2.toString())
                .append(" ON ")
                .append(" skill2.grade != skill1.grade AND skill2.grade != skill.grade ")
//                .append(" and skill2.major != skill1.major AND skill2.major != skill.major ")AND skill1.major != skill.major
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + skill2.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable3.toString())
                .append(" ON ")
                .append(" skill3.grade != skill2.grade AND skill3.grade != skill1.grade ")
                .append(" AND skill3.grade != skill.grade ")
//                .append(" AND skill3.major != skill2.major AND skill3.major != skill1.major ")
//                .append(" AND skill3.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + ")
                .append(" skill1.subsidiesAmount + skill2.subsidiesAmount + skill3.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable4.toString())
                .append(" ON ")
                .append(" skill4.grade != skill3.grade AND skill4.grade != skill2.grade ")
                .append(" AND skill4.grade != skill1.grade AND skill4.grade != skill.grade ")
//                .append(" and skill4.major != skill3.major AND skill4.major != skill2.major ")
//                .append(" AND skill4.major != skill1.major AND skill4.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + ")
                .append(" skill2.subsidiesAmount + skill3.subsidiesAmount + skill4.subsidiesAmount = " + to.getMoney() + " ")
                .append(" where skill.grade<skill1.grade AND skill1.grade <skill2.grade AND skill2.grade<skill3.grade AND skill3.grade<skill4.grade ")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");
        //拼小项1
        if (StringUtils.isNotBlank(to.getEventFirst())) {
            sb.append(" and a.eventFirst  = '" + to.getEventFirst() + "'  ");
            //拼小项2
            if (StringUtils.isNotBlank(to.getEventSecond())) {
                sb.append(" a.eventSecond = '" + to.getEventSecond() + "'  ");
                //拼小项3
                if (StringUtils.isNotBlank(to.getEventThird())) {
                    sb.append(" a.eventThird = '" + to.getEventThird() + "'  ");
                    //拼小项4
                    if (StringUtils.isNotBlank(to.getEventFour())) {
                        sb.append(" a.eventFour = '" + to.getEventFour() + "'  ");
                        if (StringUtils.isNotBlank(to.getEventFive())) {
                            //拼小项5
                            sb.append(" a.eventFive = '" + to.getEventFive() + "'  ");
                        }
                    }
                }
            }
        }
        List<CalculateBO> calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        return calculateBOS;
    }

    private List<CalculateBO> caculateSix(CalculateTO to) throws SerException {
        StringBuffer sqlComboTable = new StringBuffer("");
        sqlComboTable.append(" ( SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill ");

        StringBuffer sqlComboTable1 = new StringBuffer("");
        sqlComboTable1.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill1 ");

        StringBuffer sqlComboTable2 = new StringBuffer("");
        sqlComboTable2.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill2 ");

        StringBuffer sqlComboTable3 = new StringBuffer("");
        sqlComboTable3.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill3 ");

        StringBuffer sqlComboTable4 = new StringBuffer("");
        sqlComboTable4.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill4 ");

        StringBuffer sqlComboTable5 = new StringBuffer("");
        sqlComboTable5.append(" ( SELECT a.major,c.grade,c.subsidiesAmount FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id)as skill5 ");


        String[] feilds = new String[]{"main", "skill", "eventFirst", "eventFirstSkill"
                , "eventSecond", "eventSecondSkill", "eventThird", "eventThirdSkill",
                "eventFour", "eventFourSkill", "eventFive", "eventFiveSkill"};

        StringBuilder sb = new StringBuilder();

        sb.append(" select a.main , a.skill , a.eventFirst , a.eventFirstSkill ")
                .append(" , a.eventSecond , a. eventSecondSkill , a.eventThird , a.eventThirdSkill ")
                .append(" , a.eventFour , a.eventFourSkill , a.eventFive , a.eventFiveSkill  ")
                .append("  from ( ");
        sb.append(" SELECT  ")
                .append(" skill.major AS main,skill.grade AS skill, ")
                .append(" skill1.major AS eventFirst,skill1.grade AS eventFirstSkill, ")
                .append(" skill2.major AS eventSecond,skill2.grade  AS eventSecondSkill,skill3.major AS eventThird,skill3.grade AS eventThirdSkill, ")
                .append(" skill4.major AS eventFour,skill4.grade AS eventFourSkill,skill5.major AS eventFive,skill5.grade AS eventFiveSkill ")
                .append(" FROM ")
                .append(sqlComboTable.toString())
                .append("JOIN")
                .append(sqlComboTable1.toString())
                .append("ON")
                .append(" skill.grade != skill1.grade AND skill.subsidiesAmount + skill.quotaJobTitle <  " + to.getMoney() + " ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable2.toString())
                .append(" ON ")
                .append(" skill2.grade != skill1.grade AND skill2.grade != skill.grade ")
//                .append(" and skill2.major != skill1.major AND skill2.major != skill.major ")  AND skill.major != skill1.major
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + skill2.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable3.toString())
                .append(" ON ")
                .append(" skill3.grade != skill2.grade AND skill3.grade != skill1.grade ")
                .append(" AND skill3.grade != skill.grade ")
//                .append(" AND skill3.major != skill2.major AND skill3.major != skill1.major ")
//                .append(" AND skill3.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + ")
                .append(" skill1.subsidiesAmount + skill2.subsidiesAmount + skill3.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable4.toString())
                .append(" ON ")
                .append(" skill4.grade != skill3.grade AND skill4.grade != skill2.grade ")
                .append(" AND skill4.grade != skill1.grade AND skill4.grade != skill.grade ")
//                .append(" and skill4.major != skill3.major AND skill4.major != skill2.major ")
//                .append(" AND skill4.major != skill1.major AND skill4.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount +skill2.subsidiesAmount + ")
                .append(" skill3.subsidiesAmount +skill4.subsidiesAmount < " + to.getMoney() + " ")
                .append(" JOIN ")
                .append(sqlComboTable5.toString())
                .append(" ON ")
                .append(" skill5.grade != skill4.grade AND skill5.grade != skill3.grade ")
                .append(" AND skill5.grade != skill2.grade AND skill5.grade != skill1.grade ")
                .append(" AND skill5.grade != skill.grade ")
//                .append(" and skill5.major != skill4.major AND skill5.major != skill3.major ")
//                .append(" AND skill5.major != skill2.major AND skill5.major != skill1.major ")
//                .append(" AND skill5.major != skill.major ")
                .append(" AND skill.subsidiesAmount + skill.quotaJobTitle + skill1.subsidiesAmount + ")
                .append(" skill2.subsidiesAmount + skill3.subsidiesAmount + skill4.subsidiesAmount+skill5.subsidiesAmount = " + to.getMoney() + " ")
                .append(" where skill.grade<skill1.grade AND skill1.grade <skill2.grade ")
                .append(" AND skill2.grade<skill3.grade AND skill3.grade<skill4.grade AND skill4.grade<skill5.grade ")
                .append(" ) a where 1=1 ");
        //拼主项
        sb.append(" and a.main = '" + to.getMain() + "'  ");
        //拼小项1
        if (StringUtils.isNotBlank(to.getEventFirst())) {
            sb.append(" and a.eventFirst = '" + to.getEventFirst() + "'  ");
            //拼小项2
            if (StringUtils.isNotBlank(to.getEventSecond())) {
                sb.append(" a.eventSecond = '" + to.getEventSecond() + "'  ");
                //拼小项3
                if (StringUtils.isNotBlank(to.getEventThird())) {
                    sb.append(" a.eventThird = '" + to.getEventThird() + "'  ");
                    //拼小项4
                    if (StringUtils.isNotBlank(to.getEventFour())) {
                        sb.append(" a.eventFour = '" + to.getEventFour() + "'  ");
                        if (StringUtils.isNotBlank(to.getEventFive())) {
                            //拼小项5
                            sb.append(" a.eventFive = '" + to.getEventFive() + "'  ");
                        }
                    }
                }
            }
        }
        List<CalculateBO> calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        return calculateBOS;
    }


    private List<CalculateBO> calculateSecond(CalculateTO to) throws SerException {
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String[] feilds = new String[]{"main", "skill", "subsidiesAmount", "quotaJobTitle"};
        StringBuffer sb = new StringBuffer("");
        sb.append("  SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN mgraanagepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id ");
        calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        List<CalculateBO> calculateBOS1 = new ArrayList<>();
        List<CalculateBO> calculateBOS2 = new ArrayList<>();
        calculateBOS1.addAll(calculateBOS);
        calculateBOS.stream().forEach(str -> {
            Integer money = str.getSubsidiesAmount() + str.getQuotaJobTitle();
            String name = str.getMain();
            String grade = str.getSkill();
            calculateBOS1.stream().forEach(str1 -> {
                Integer totalMoney = money + str1.getSubsidiesAmount();
                if (totalMoney.equals(to.getMoney())
                        && (!name.equals(str1.getMain())) && grade.compareTo(str1.getSkill()) < 0) {

                    CalculateBO calculateBO = new CalculateBO(name, grade, str1.getMain(), str1.getSkill(), "", "", "", "", "", "", "", "");
                    calculateBOS2.add(calculateBO);
                }
            });

        });
        return calculateBOS2;
    }

    private List<CalculateBO> calculateThird(CalculateTO to) throws SerException {
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String[] feilds = new String[]{"main", "skill", "subsidiesAmount", "quotaJobTitle"};
        StringBuffer sb = new StringBuffer("");
        sb.append("  SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id ");
        calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        List<CalculateBO> calculateBOS1 = new ArrayList<>();
        List<CalculateBO> calculateBOS2 = new ArrayList<>();
        List<CalculateBO> calculateBOS3 = new ArrayList<>();
        calculateBOS1.addAll(calculateBOS);
        calculateBOS2.addAll(calculateBOS);
        calculateBOS.stream().forEach(str -> {
            Integer money = str.getSubsidiesAmount() + str.getQuotaJobTitle();
            String name = str.getMain();
            String grade = str.getSkill();
            calculateBOS1.stream().forEach(str1 -> {
                Integer secondMoney = money + str1.getSubsidiesAmount();
                calculateBOS2.stream().forEach(str2 -> {
                    Integer thirdMoney = secondMoney + str2.getSubsidiesAmount();
                    if (thirdMoney.equals(to.getMoney())
                            && (!name.equals(str1.getMain())) && !name.equals(str2.getMain()) && !str1.getMain().equals(str2.getMain()) &&
                            grade.compareTo(str1.getSkill()) < 0 && str1.getSkill().compareTo(str2.getSkill()) < 0) {

                        CalculateBO calculateBO = new CalculateBO(name, grade, str1.getMain(), str1.getSkill(), str2.getMain(), str2.getSkill(), "", "", "", "", "", "");
                        calculateBOS3.add(calculateBO);
                    }
                });
            });

        });
        return calculateBOS3;
    }

    private List<CalculateBO> calculateFour(CalculateTO to) throws SerException {
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String[] feilds = new String[]{"main", "skill", "subsidiesAmount", "quotaJobTitle"};
        StringBuffer sb = new StringBuffer("");
        sb.append("  SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id ");
        calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        List<CalculateBO> calculateBOS1 = new ArrayList<>();
        List<CalculateBO> calculateBOS2 = new ArrayList<>();
        List<CalculateBO> calculateBOS3 = new ArrayList<>();
        List<CalculateBO> calculateBOS4 = new ArrayList<>();
        calculateBOS1.addAll(calculateBOS);
        calculateBOS2.addAll(calculateBOS);
        calculateBOS3.addAll(calculateBOS);
        calculateBOS.stream().forEach(str -> {
            Integer money = str.getSubsidiesAmount() + str.getQuotaJobTitle();
            String name = str.getMain();
            String grade = str.getSkill();
            calculateBOS1.stream().forEach(str1 -> {
                Integer secondMoney = money + str1.getSubsidiesAmount();
                calculateBOS2.stream().forEach(str2 -> {
                    Integer thirdMoney = secondMoney + str2.getSubsidiesAmount();
                    calculateBOS3.stream().forEach(str3 -> {
                        Integer fourMoney = thirdMoney + str3.getSubsidiesAmount();
                        if (fourMoney.equals(to.getMoney())
                                && (!name.equals(str1.getMain())) && !name.equals(str2.getMain()) && !name.equals(str3.getMain()) &&
                                !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                !str2.getMain().equals(str3.getMain()) &&
                                !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                !str2.getMain().equals(str3.getMain()) &&
                                grade.compareTo(str1.getSkill()) < 0 && str1.getSkill().compareTo(str2.getSkill()) < 0 &&
                                str2.getSkill().compareTo(str3.getSkill()) < 0) {

                            CalculateBO calculateBO = new CalculateBO(name, grade, str1.getMain(), str1.getSkill(), str2.getMain(), str2.getSkill(), str3.getMain(), str3.getSkill(), "", "", "", "");
                            calculateBOS4.add(calculateBO);
                        }
                    });
                });
            });

        });
        return calculateBOS4;
    }

    private List<CalculateBO> calculateFive(CalculateTO to) throws SerException {
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String[] feilds = new String[]{"main", "skill", "subsidiesAmount", "quotaJobTitle"};
        StringBuffer sb = new StringBuffer("");
        sb.append("  SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id ");
        calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        List<CalculateBO> calculateBOS1 = new ArrayList<>();
        List<CalculateBO> calculateBOS2 = new ArrayList<>();
        List<CalculateBO> calculateBOS3 = new ArrayList<>();
        List<CalculateBO> calculateBOS4 = new ArrayList<>();
        List<CalculateBO> calculateBOS5 = new ArrayList<>();
        calculateBOS1.addAll(calculateBOS);
        calculateBOS2.addAll(calculateBOS);
        calculateBOS3.addAll(calculateBOS);
        calculateBOS4.addAll(calculateBOS);
        calculateBOS.stream().forEach(str -> {
            Integer money = str.getSubsidiesAmount() + str.getQuotaJobTitle();
            String name = str.getMain();
            String grade = str.getSkill();
            calculateBOS1.stream().forEach(str1 -> {
                Integer secondMoney = money + str1.getSubsidiesAmount();
                calculateBOS2.stream().forEach(str2 -> {
                    Integer thirdMoney = secondMoney + str2.getSubsidiesAmount();
                    calculateBOS3.stream().forEach(str3 -> {
                        Integer fourMoney = thirdMoney + str3.getSubsidiesAmount();
                        calculateBOS4.stream().forEach(str4 -> {
                            Integer fiveMoney = fourMoney + str4.getSubsidiesAmount();
                            if (fiveMoney.equals(to.getMoney())
                                    && (!name.equals(str1.getMain())) && !name.equals(str2.getMain()) && !name.equals(str3.getMain()) &&
                                    !name.equals(str4.getMain()) &&
                                    !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                    !str1.getMain().equals(str4.getMain()) &&
                                    !str2.getMain().equals(str3.getMain()) && !str2.getMain().equals(str4.getMain()) &&
                                    !str3.getMain().equals(str4.getMain()) &&
                                    !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                    !str1.getMain().equals(str4.getMain()) && !str2.getMain().equals(str3.getMain()) &&
                                    !str2.getMain().equals(str4.getMain()) && !str3.getMain().equals(str4.getMain()) &&
                                    grade.compareTo(str1.getSkill()) < 0 && str1.getSkill().compareTo(str2.getSkill()) < 0 &&
                                    str2.getSkill().compareTo(str3.getSkill()) < 0 && str3.getSkill().compareTo(str4.getSkill()) < 0) {

                                CalculateBO calculateBO = new CalculateBO(name, grade, str1.getMain(), str1.getSkill(), str2.getMain(), str2.getSkill(), str3.getMain(), str3.getSkill(), str4.getMain(), str4.getSkill(), "", "");
                                calculateBOS5.add(calculateBO);
                            }
                        });
                    });
                });
            });

        });
        return calculateBOS5;
    }

    private List<CalculateBO> calculateSix(CalculateTO to) throws SerException {
        List<CalculateBO> calculateBOS = new ArrayList<>();
        String[] feilds = new String[]{"main", "skill", "subsidiesAmount", "quotaJobTitle"};
        StringBuffer sb = new StringBuffer("");
        sb.append("  SELECT a.major,c.grade,c.subsidiesAmount,c.quotaJobTitle FROM ")
                .append(" managepromotion_skillgradinga a ")
                .append(" LEFT JOIN managepromotion_skillgradingb b ON a.id=b.skillGradingA_id ")
                .append(" LEFT JOIN managepromotion_skillgradingc c ON b.id=c.skillGradingB_id ");
        calculateBOS = super.findBySql(sb.toString(), CalculateBO.class, feilds);
        List<CalculateBO> calculateBOS1 = new ArrayList<>();
        List<CalculateBO> calculateBOS2 = new ArrayList<>();
        List<CalculateBO> calculateBOS3 = new ArrayList<>();
        List<CalculateBO> calculateBOS4 = new ArrayList<>();
        List<CalculateBO> calculateBOS5 = new ArrayList<>();
        List<CalculateBO> calculateBOS6 = new ArrayList<>();
        calculateBOS1.addAll(calculateBOS);
        calculateBOS2.addAll(calculateBOS);
        calculateBOS3.addAll(calculateBOS);
        calculateBOS4.addAll(calculateBOS);
        calculateBOS5.addAll(calculateBOS);
        calculateBOS.stream().forEach(str -> {
            Integer money = str.getSubsidiesAmount() + str.getQuotaJobTitle();
            String name = str.getMain();
            String grade = str.getSkill();
            calculateBOS1.stream().forEach(str1 -> {
                Integer secondMoney = money + str1.getSubsidiesAmount();
                calculateBOS2.stream().forEach(str2 -> {
                    Integer thirdMoney = secondMoney + str2.getSubsidiesAmount();
                    calculateBOS3.stream().forEach(str3 -> {
                        Integer fourMoney = thirdMoney + str3.getSubsidiesAmount();
                        calculateBOS4.stream().forEach(str4 -> {
                            Integer fiveMoney = fourMoney + str4.getSubsidiesAmount();
                            calculateBOS5.stream().forEach(str5 -> {
                                Integer sixMoney = fiveMoney + str5.getSubsidiesAmount();
                                if (sixMoney.equals(to.getMoney())
                                        && (!name.equals(str1.getMain())) && !name.equals(str2.getMain()) && !name.equals(str3.getMain()) &&
                                        !name.equals(str4.getMain()) && !name.equals(str5.getMain()) &&
                                        !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                        !str1.getMain().equals(str4.getMain()) && !str1.getMain().equals(str5.getMain()) &&
                                        !str2.getMain().equals(str3.getMain()) && !str2.getMain().equals(str4.getMain()) &&
                                        !str2.getMain().equals(str5.getMain()) &&
                                        !str3.getMain().equals(str4.getMain()) && !str3.getMain().equals(str5.getMain()) &&
                                        !str4.getMain().equals(str5.getMain()) &&
                                        !str1.getMain().equals(str2.getMain()) && !str1.getMain().equals(str3.getMain()) &&
                                        !str1.getMain().equals(str4.getMain()) && !str1.getMain().equals(str5.getMain()) &&
                                        !str2.getMain().equals(str3.getMain()) && !str2.getMain().equals(str4.getMain()) &&
                                        !str2.getMain().equals(str5.getMain()) && !str3.getMain().equals(str4.getMain()) &&
                                        !str3.getMain().equals(str5.getMain()) && !str4.getMain().equals(str5.getMain()) &&
                                        grade.compareTo(str1.getSkill()) < 0 && str1.getSkill().compareTo(str2.getSkill()) < 0 &&
                                        str2.getSkill().compareTo(str3.getSkill()) < 0 && str3.getSkill().compareTo(str4.getSkill()) < 0 &&
                                        str4.getSkill().compareTo(str5.getSkill()) < 0) {

                                    CalculateBO calculateBO = new CalculateBO(name, grade, str1.getMain(), str1.getSkill(), str2.getMain(), str2.getSkill(), str3.getMain(), str3.getSkill(), str4.getMain(), str4.getSkill(), str5.getMain(), str5.getSkill());
                                    calculateBOS6.add(calculateBO);
                                }
                            });
                        });
                    });
                });
            });

        });
        return calculateBOS6;
    }
}


