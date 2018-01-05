package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.royalty.bo.*;
import com.bjike.goddess.royalty.dto.*;
import com.bjike.goddess.royalty.entity.*;
import com.bjike.goddess.royalty.enums.GuideAddrStatus;
import com.bjike.goddess.royalty.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 体系间对赌表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-11 11:31 ]
 * @Description: [ 体系间对赌表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class SystemBetSerImpl extends ServiceImpl<SystemBet, SystemBetDTO> implements SystemBetSer {

    @Autowired
    private SystemBetASer systemBetASer;
    @Autowired
    private SystemBetBSer systemBetBSer;
    @Autowired
    private SystemBetCSer systemBetCSer;
    @Autowired
    private SystemBetDSer systemBetDSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
        return flag;
    }


    @Override
    public Long count(SystemBetDDTO dto) throws SerException {
        Long count = systemBetDSer.count(dto);
        return count;
    }

    @Override
    public SystemBetABO getOne(String id) throws SerException {
        SystemBetA systemBetA = systemBetASer.findById(id);
        SystemBetABO listABO = BeanTransform.copyProperties(systemBetA, SystemBetABO.class);
        if (listABO != null) {
            SystemBetBDTO dtoB = new SystemBetBDTO();
            dtoB.getConditions().add(Restrict.eq("systemBetA.id", listABO.getId()));
            List<SystemBetB> listB = systemBetBSer.findByCis(dtoB);
            List<SystemBetBBO> listBBO = BeanTransform.copyProperties(listB, SystemBetBBO.class);
            listABO.setSystemBetBBOS(listBBO);
            if (listBBO != null) {
                for (SystemBetBBO systemBetBBO : listBBO) {
                    SystemBetCDTO dtoC = new SystemBetCDTO();
                    dtoC.getConditions().add(Restrict.eq("systemBetB.id", systemBetBBO.getId()));
                    List<SystemBetC> listC = systemBetCSer.findByCis(dtoC);
                    List<SystemBetCBO> listCBO = BeanTransform.copyProperties(listC, SystemBetCBO.class);
                    systemBetBBO.setSystemBetCBOS(listCBO);
                    if (listCBO != null) {
                        for (SystemBetCBO systemBetCBO : listCBO) {
                            SystemBetDDTO dtoD = new SystemBetDDTO();
                            dtoD.getConditions().add(Restrict.eq("systemBetC.id", systemBetCBO.getId()));
                            List<SystemBetD> listD = systemBetDSer.findByCis(dtoD);
                            List<SystemBetDBO> listDBO = BeanTransform.copyProperties(listD, SystemBetDBO.class);
                            systemBetCBO.setSystemBetDBOS(listDBO);
                        }
                    }
                }

            }
        }
//        List<SystemBetABO> systemBetABOList=new ArrayList<>();
//        systemBetABOList.add(listABO);
        return listABO;


    }

    @Override
    public List<SystemBetABO> list(SystemBetADTO dto) throws SerException {
        checkSeeIdentity();
        SystemBetADTO dtoA = new SystemBetADTO();
        List<SystemBetA> listA = systemBetASer.findByCis(dtoA);
        List<SystemBetABO> listABO = BeanTransform.copyProperties(listA, SystemBetABO.class);
        if (listABO != null) {
            for (SystemBetABO systemBetABO : listABO) {
                SystemBetBDTO dtoB = new SystemBetBDTO();
                dtoB.getConditions().add(Restrict.eq("systemBetA.id", systemBetABO.getId()));
                List<SystemBetB> listB = systemBetBSer.findByCis(dtoB);
                List<SystemBetBBO> listBBO = BeanTransform.copyProperties(listB, SystemBetBBO.class);
                systemBetABO.setSystemBetBBOS(listBBO);
                if (listBBO != null) {
                    for (SystemBetBBO systemBetBBO : listBBO) {
                        SystemBetCDTO dtoC = new SystemBetCDTO();
                        dtoC.getConditions().add(Restrict.eq("systemBetB.id", systemBetBBO.getId()));
                        List<SystemBetC> listC = systemBetCSer.findByCis(dtoC);
                        List<SystemBetCBO> cboList = BeanTransform.copyProperties(listC, SystemBetCBO.class);
                        systemBetBBO.setSystemBetCBOS(cboList);
                        if (cboList != null) {
                            for (SystemBetCBO systemBetCBO : cboList) {
                                SystemBetDDTO dtoD = new SystemBetDDTO();
                                dtoD.getConditions().add(Restrict.eq("systemBetC.id", systemBetCBO.getId()));
                                List<SystemBetD> listD = systemBetDSer.findByCis(dtoD);
                                List<SystemBetDBO> dboList = BeanTransform.copyProperties(listD, SystemBetDBO.class);
                                systemBetCBO.setSystemBetDBOS(dboList);
                            }

                        }


                    }
                }

            }
        }

        return listABO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insert(SystemBetATO systemBetATO) throws SerException {
        checkAddIdentity();
//        SystemBetATO systemBetATO = systemBetTO.getSystemBetATO();
        SystemBetA systemBetA = BeanTransform.copyProperties(systemBetATO, SystemBetA.class, true);
        systemBetASer.save(systemBetA);
        List<SystemBetB> systemBetBS = new ArrayList<>();
        List<SystemBetC> systemBetCS = new ArrayList<>();
        List<SystemBetBTO> systemBetBTOS = systemBetATO.getSystemBetBTOS();
        for (SystemBetBTO systemBetBTO : systemBetBTOS) {
            SystemBetB systemBetB = BeanTransform.copyProperties(systemBetBTO, SystemBetB.class, true);
            systemBetB.setSystemBetA(systemBetA);
            //目标基础得分（分值（利润额）*目标-部门分配基础权重）
            Double basesScore = systemBetATO.getScoreProfit() * systemBetBTO.getBaseWeight();
            systemBetB.setBasesScore(basesScore);
            //计划基础得分（计划分值（利润额）*目标-部门分配基础权重）
            Double basesScorePlan = systemBetATO.getPlanProfit() * systemBetBTO.getBaseWeight();
            systemBetB.setBasesScorePlan(basesScorePlan);
            //实际基础得分（实际分值（利润额）*目标-部门分配基础权重）
            Double basesScorePractice = systemBetATO.getPracticeProfit() * systemBetBTO.getBaseWeight();
            systemBetB.setBasesScorePractice(basesScorePractice);
            //制约得分
            //判断是否含有“是”
            //Boolean scoreFlag = systemBetBTO.getSystemBetCTOS().stream().anyMatch(str -> str.getStandard().equals("是"));
            //if (scoreFlag) {
            //说明含有“是” ,给相对应体系
            //Double restrictScore = 0.0;
//                systemBetB.setRestrictScore();
//                systemBetB.setRestrictScore(systemBetBTO.getUnmetAllocation()* betScore);
            //}
            //体系目标总得分（目标制约得分+目标基础得分）
            systemBetB.setSystemTotalScore(systemBetBTO.getRestrictScore() + basesScore);
            //体系计划总得分（计划制约得分+计划基础得分）
            systemBetB.setSystemTotalScorePlan(systemBetBTO.getRestrictScorePlan() + basesScorePlan);
            //体系实际总得分（实际制约得分+实际基础得分）
            systemBetB.setSystemTotalScorePractice(systemBetBTO.getRestrictScorePractice() + basesScorePractice);

            systemBetB = systemBetBSer.save(systemBetB);
            systemBetBS.add(systemBetB);
            List<SystemBetCTO> systemBetCTOS = systemBetBTO.getSystemBetCTOS();
            if (null != systemBetCTOS) {
                for (SystemBetCTO systemBetCTO : systemBetCTOS) {
                    SystemBetC systemBetC = BeanTransform.copyProperties(systemBetCTO, SystemBetC.class, true);
                    systemBetC.setSystemBetB(systemBetB);
                    //目标对赌得分（分值（利润额）*目标-部门分配对赌权重）
                    Double betScore = systemBetATO.getScoreProfit() * systemBetCTO.getBetWeight();
                    systemBetC.setBetScore(betScore);
                    //计划对赌得分（计划分值（利润额）*目标-部门分配对赌权重）
                    Double betScorePlan = systemBetATO.getPlanProfit() * systemBetCTO.getBetWeight();
                    systemBetC.setBetScorePlan(betScorePlan);
                    //实际对赌得分（实际分值（利润额）*目标-部门分配对赌权重）
                    Double betScorePractice = systemBetATO.getPracticeProfit() * systemBetCTO.getBetWeight();
                    systemBetC.setBetScorePractice(betScorePractice);

//                if(systemBetCTO.getStandard()){
//                    //说明含有“是” ,给相对应体系
//                    Double restrictScore = betScore;
//                    systemBetB.setRestrictScore(restrictScore);
//                }else{
//                }


                    systemBetC = systemBetCSer.save(systemBetC);
                    systemBetCS.add(systemBetC);


                    List<SystemBetETO> systemBetETOS = systemBetCTO.getSystemBetETOS();
                    if (null != systemBetETOS) {
                        for (SystemBetETO systemBetETO : systemBetETOS) {
                            SystemBetD systemBetD = BeanTransform.copyProperties(systemBetETO, SystemBetD.class, true);
                            systemBetD.setSystemBetC(systemBetC);
                            if(systemBetB.getSystem().equals(systemBetD.getUnmetAllocationSystem())){
                                throw new SerException("体系不能与未达标分配体系是同一个");
                            }
                            systemBetDSer.save(systemBetD);
                        }
                    }

                }
            }

        }
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(SystemBetATO systemBetATO) throws SerException {
        checkAddIdentity();
        SystemBetA systemBetA = systemBetASer.findById(systemBetATO.getId());
        BeanTransform.copyProperties(systemBetATO, systemBetA, true);
        systemBetASer.update(systemBetA);

        List<SystemBetB> systemBetBS = new ArrayList<>();
        List<SystemBetC> systemBetCS = new ArrayList<>();
        List<SystemBetD> systemBetDS = new ArrayList<>();

        //先删掉B表对应数据
        SystemBetBDTO systemBetBDTO = new SystemBetBDTO();
        systemBetBDTO.getConditions().add(Restrict.eq("systemBetA.id", systemBetA.getId()));
        List<SystemBetB> bList = systemBetBSer.findByCis(systemBetBDTO);
        if (bList != null && bList.size() > 0) {
            //查询对应C表的数据，先删除
            List<String> bIdList = bList.stream().map(SystemBetB::getId).collect(Collectors.toList());
            String[] bids = new String[bIdList.size()];
            bids = bIdList.toArray(bids);
            SystemBetCDTO systemBetCDTO = new SystemBetCDTO();
            systemBetCDTO.getConditions().add(Restrict.in("systemBetB.id", bids));
            List<SystemBetC> cList = systemBetCSer.findByCis(systemBetCDTO);
            if (cList != null && cList.size() > 0) {
                //查询对应D表的数据，先删除
                List<String> cIdList = cList.stream().map(SystemBetC::getId).collect(Collectors.toList());
                String[] cids = new String[cIdList.size()];
                cids = cIdList.toArray(cids);
                SystemBetDDTO systemBetDDTO = new SystemBetDDTO();
                systemBetDDTO.getConditions().add(Restrict.in("systemBetC.id", cids));
                List<SystemBetD> dList = systemBetDSer.findByCis(systemBetDDTO);
                if (dList != null && dList.size() > 0) {
                    systemBetDSer.remove(dList);
                }
                systemBetCSer.remove(cList);
            }

            systemBetBSer.remove(bList);
        }
        List<SystemBetBTO> systemBetBTOS = systemBetATO.getSystemBetBTOS();
        if (systemBetBTOS != null) {
            //查询B表的数据并修改
            for (SystemBetBTO systemBetBTO : systemBetBTOS) {
                SystemBetB systemBetB = BeanTransform.copyProperties(systemBetBTO, SystemBetB.class, true);
                systemBetB.setSystemBetA(systemBetA);
                //目标基础得分（分值（利润额）*目标-部门分配基础权重）
                Double basesScore = systemBetATO.getScoreProfit() * systemBetBTO.getBaseWeight();
                systemBetB.setBasesScore(basesScore);
                //计划基础得分（计划分值（利润额）*目标-部门分配基础权重）
                Double basesScorePlan = systemBetATO.getPlanProfit() * systemBetBTO.getBaseWeight();
                systemBetB.setBasesScorePlan(basesScorePlan);
                //实际基础得分（实际分值（利润额）*目标-部门分配基础权重）
                Double basesScorePractice = systemBetATO.getPracticeProfit() * systemBetBTO.getBaseWeight();
                systemBetB.setBasesScorePractice(basesScorePractice);
                //制约得分
                //判断是否含有“是”
                //Boolean scoreFlag = systemBetBTO.getSystemBetCTOS().stream().anyMatch(str -> str.getStandard().equals("是"));
                //if (scoreFlag) {
                //说明含有“是” ,给相对应体系
                //Double restrictScore = 0.0;
//                systemBetB.setRestrictScore();
//                systemBetB.setRestrictScore(systemBetBTO.getUnmetAllocation()* betScore);
                //}
                //体系目标总得分（目标制约得分+目标基础得分）
                systemBetB.setSystemTotalScore(systemBetBTO.getRestrictScore() + basesScore);
                //体系计划总得分（计划制约得分+计划基础得分）
                systemBetB.setSystemTotalScorePlan(systemBetBTO.getRestrictScorePlan() + basesScorePlan);
                //体系实际总得分（实际制约得分+实际基础得分）
                systemBetB.setSystemTotalScorePractice(systemBetBTO.getRestrictScorePractice() + basesScorePractice);

                systemBetB.setId(null);
                systemBetBSer.update(systemBetB);
                systemBetBS.add(systemBetB);

                //查询C表的数据并修改
                List<SystemBetCTO> systemBetCTOS = systemBetBTO.getSystemBetCTOS();
                if (systemBetCTOS != null) {
                    for (SystemBetCTO systemBetCTO : systemBetCTOS) {
                        SystemBetC systemBetC = BeanTransform.copyProperties(systemBetCTO, SystemBetC.class, true);
                        systemBetC.setSystemBetB(systemBetB);

                        //目标对赌得分（分值（利润额）*目标-部门分配对赌权重）
                        Double betScore = systemBetATO.getScoreProfit() * systemBetCTO.getBetWeight();
                        systemBetC.setBetScore(betScore);
                        //计划对赌得分（计划分值（利润额）*目标-部门分配对赌权重）
                        Double betScorePlan = systemBetATO.getPlanProfit() * systemBetCTO.getBetWeight();
                        systemBetC.setBetScorePlan(betScorePlan);
                        //实际对赌得分（实际分值（利润额）*目标-部门分配对赌权重）
                        Double betScorePractice = systemBetATO.getPracticeProfit() * systemBetCTO.getBetWeight();
                        systemBetC.setBetScorePractice(betScorePractice);
                        systemBetC.setId(null);
                        systemBetCSer.update(systemBetC);
                        systemBetCS.add(systemBetC);

                        //查询D表的数据并修改
                        List<SystemBetETO> systemBetETOS = systemBetCTO.getSystemBetETOS();
                        if (systemBetETOS != null) {
                            for (SystemBetETO systemBetETO : systemBetETOS) {
                                SystemBetD systemBetD = BeanTransform.copyProperties(systemBetETO, SystemBetD.class, true);
                                systemBetD.setSystemBetC(systemBetC);
                                if(systemBetB.getSystem().equals(systemBetD.getUnmetAllocationSystem())){
                                    throw new SerException("体系不能与未达标分配体系是同一个");
                                }
                                systemBetD.setId(null);
                                systemBetDSer.update(systemBetD);
                                systemBetDS.add(systemBetD);
                            }
                        }
                    }
                }
            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkAddIdentity();

        SystemBetD systemBetD = systemBetDSer.findById(id);
        if (systemBetD == null) {
            throw new SerException("该对象不存在");
        }
        systemBetDSer.remove(id);
        List<SystemBetC> cList = systemBetCSer.findAll();
        List<SystemBetB> bList = systemBetBSer.findAll();
        List<SystemBetA> aList = systemBetASer.findAll();
        Set<String> cids = new HashSet<>();
        Set<String> bids = new HashSet<>();
        Set<String> aids = new HashSet<>();

        for (SystemBetD systemBetD1 : systemBetDSer.findAll()) {
            cids.add(systemBetD1.getSystemBetC().getId());
        }
        for (SystemBetC c : cList) {
            if (!cids.contains(c.getId())) {
                systemBetCSer.remove(c.getId());
            }
        }
        for (SystemBetC c1 : systemBetCSer.findAll()) {
            bids.add(c1.getSystemBetB().getId());
        }
        for (SystemBetB b : bList) {
            if (!bids.contains(b.getId())) {
                systemBetBSer.remove(b.getId());
            }
        }
        for (SystemBetB b1 : systemBetBSer.findAll()) {
            aids.add(b1.getSystemBetA().getId());
        }
        for (SystemBetA a : aList) {
            if (!aids.contains(a.getId())) {
                systemBetASer.remove(a.getId());
            }
        }
    }

    @Override
    public List<String> getProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<SystemBetABO> systemBetABOS = super.findBySql("select distinct projectName from royalty_systembeta group by projectName order by projectName asc ", SystemBetABO.class, fields);

        List<String> projectNameList = systemBetABOS.stream().map(SystemBetABO::getProjectName)
                .filter(projectName -> (StringUtils.isNotBlank(projectName))).distinct().collect(Collectors.toList());


        return projectNameList;
    }

    @Override
    public List<String> system() throws SerException {
        String[] fields = new String[]{"system"};
        List<SystemBetBBO> systemBetBBOS = super.findBySql("select distinct system from royalty_systembetb group by system order by system asc ", SystemBetBBO.class, fields);

        List<String> systemList = systemBetBBOS.stream().map(SystemBetBBO::getSystem)
                .filter(system -> (StringUtils.isNotBlank(system))).distinct().collect(Collectors.toList());


        return systemList;

    }

    @Override
    public SystemBetABO getSystem(String projectName) throws SerException {
        SystemBetA a = new SystemBetA();
        if (StringUtils.isNotBlank(projectName)) {
            SystemBetADTO adto = new SystemBetADTO();
            adto.getConditions().add(Restrict.eq("projectName", projectName));
            a = systemBetASer.findOne(adto);
        }
        SystemBetABO bo = BeanTransform.copyProperties(a, SystemBetABO.class);
        return bo;
    }


    @Override
    public List<SystemBetABO> systemCollect(ProjectNameTO to) throws SerException {
        List<SystemBetABO> aboList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.betTime AS betTime,a.area as area,a.projectGroup as projectGroup, ");
        sb.append(" a.projectName as projectName,a.scoreProfit as scoreProfit, ");
        sb.append(" a.planProfit as planProfit,a.practiceProfit as practiceProfit, ");
        sb.append(" b.system AS system,b.baseWeight AS baseWeight,b.basesScore AS basesScore, ");
        sb.append(" b.basesScorePlan AS basesScorePlan,b.basesScorePractice AS basesScorePractice, ");
        sb.append(" b.restrictScore AS restrictScore,b.restrictScorePlan AS restrictScorePlan, ");
        sb.append(" b.restrictScorePractice AS restrictScorePractice,b.systemTotalScore AS systemTotalScore, ");
        sb.append(" b.systemTotalScorePlan AS systemTotalScorePlan,b.systemTotalScorePractice AS systemTotalScorePractice, ");
        sb.append(" c.betWeight AS betWeight,c.indexNum as indexNum,c.indexName AS indexName, ");
        sb.append(" c.confirmTargetValue as confirmTargetValue,c.actualTargetValue AS actualTargetValue, ");
        sb.append(" c.is_standard AS standard,c.betScore AS betScore,c.betScorePlan AS betScorePlan, ");
        sb.append(" c.betScorePractice AS betScorePractice, ");
        sb.append(" d.unmetAllocationSystem as unmetAllocationSystem, d.unmetAllocation as unmetAllocation ");
        sb.append(" FROM royalty_systembeta a,royalty_systembetb b, royalty_systembetc c,royalty_systembetd d ");
        sb.append(" WHERE a.projectName='" + to.getProjectName() + "' AND a.id=b.systemBetA_id AND b.id=c.systemBetB_id  AND c.id=d.systemBetC_id ");
        String[] feilds = new String[]{"betTime", "area", "projectGroup", "projectName", "scoreProfit", "planProfit",
                "practiceProfit", "system", "baseWeight", "basesScore", "basesScorePlan", "basesScorePractice",
                "restrictScore", "restrictScorePlan", "restrictScorePractice", "systemTotalScore", "systemTotalScorePlan",
                "systemTotalScorePractice", "betWeight", "indexNum", "indexName",
                "confirmTargetValue", "actualTargetValue", "standard", "betScore", "betScorePlan", "betScorePractice",
                "unmetAllocationSystem", "unmetAllocation"};

        List<SystemBetBO> systemBetBOS = super.findBySql(sb.toString(), SystemBetBO.class, feilds);
        double basesScore = 0.0;
        double basesScorePlan = 0.0;
        double basesScorePractice = 0.0;
        double restrictScore = 0.0;
        double restrictScorePlan = 0.0;
        double restrictScorePractice = 0.0;
        double systemTotalScore = 0.0;
        double systemTotalScorePlan = 0.0;
        double systemTotalScorePractice = 0.0;
        double betScore = 0.0;
        double betScorePlan = 0.0;
        double betScorePractice = 0.0;
        if (systemBetBOS != null) {
            basesScore = systemBetBOS.stream().filter(p -> p.getBasesScore() != null).mapToDouble(p -> p.getBasesScore()).sum();
            basesScorePlan = systemBetBOS.stream().filter(p -> p.getBasesScorePlan() != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
            basesScorePractice = systemBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            restrictScore = systemBetBOS.stream().filter(p -> p.getRestrictScore() != null).mapToDouble(p -> p.getRestrictScore()).sum();
            restrictScorePlan = systemBetBOS.stream().filter(p -> p.getRestrictScorePlan() != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
            restrictScorePractice = systemBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            systemTotalScore = systemBetBOS.stream().filter(p -> p.getSystemTotalScore() != null).mapToDouble(p -> p.getSystemTotalScore()).sum();
            systemTotalScorePlan = systemBetBOS.stream().filter(p -> p.getSystemTotalScorePlan() != null).mapToDouble(p -> p.getSystemTotalScorePlan()).sum();
            systemTotalScorePractice = systemBetBOS.stream().filter(p -> p.getSystemTotalScorePractice() != null).mapToDouble(p -> p.getSystemTotalScorePractice()).sum();
            betScore = systemBetBOS.stream().filter(p -> p.getBetScore() != null).mapToDouble(p -> p.getBetScore()).sum();
            betScorePlan = systemBetBOS.stream().filter(p -> p.getBetScorePlan() != null).mapToDouble(p -> p.getBetScorePlan()).sum();
            betScorePractice = systemBetBOS.stream().filter(p -> p.getBetScorePractice() != null).mapToDouble(p -> p.getBetScorePractice()).sum();

        }

        Set<String> projectNames = systemBetBOS.stream().map(SystemBetBO::getProjectName).collect(Collectors.toSet());
        SystemBetABO systemBetABO = new SystemBetABO();
        for (String name : projectNames) {
            List<SystemBetBO> betBOS = systemBetBOS.stream().filter(systemBetBO -> name.equals(systemBetBO.getProjectName())).collect(Collectors.toList());
            for (SystemBetBO systemBetBO : betBOS) {
                systemBetABO.setBetTime(systemBetBO.getBetTime());
                systemBetABO.setArea(systemBetBO.getArea());
                systemBetABO.setProjectName(name);
                systemBetABO.setProjectGroup(systemBetBO.getProjectGroup());
                systemBetABO.setScoreProfit(systemBetBO.getScoreProfit());
                systemBetABO.setPlanProfit(systemBetBO.getPlanProfit());
                systemBetABO.setPracticeProfit(systemBetBO.getPracticeProfit());
            }
            Set<String> systems = systemBetBOS.stream().map(systemBetBO -> systemBetBO.getSystem()).collect(Collectors.toSet());
            List<SystemBetBBO> systemBetBBOS = new ArrayList<>();
            for (String system : systems) {
                List<SystemBetBO> list = betBOS.stream().filter(systemBetBO -> system.equals(systemBetBO.getSystem())).collect(Collectors.toList());
                List<SystemBetBBO> bbos = BeanTransform.copyProperties(list, SystemBetBBO.class);
                systemBetBBOS.addAll(bbos);
            }
            TreeSet<SystemBetBBO> treeSetB = filter();
            for (SystemBetBBO s : systemBetBBOS) {
                treeSetB.add(s);
                Set<String> indexNums = systemBetBOS.stream().map(systemBetBO -> systemBetBO.getIndexNum()).collect(Collectors.toSet());
                List<SystemBetCBO> systemBetCBOS = new ArrayList<>();
                for (String indexNum : indexNums) {
                    List<SystemBetBO> betBOS1 = betBOS.stream().filter(systemBetBO -> indexNum.equals(systemBetBO.getIndexNum())).collect(Collectors.toList());
                    List<SystemBetCBO> cbos = BeanTransform.copyProperties(betBOS1, SystemBetCBO.class);
                    systemBetCBOS.addAll(cbos);
                }
                TreeSet<SystemBetCBO> treeSetC = filter();
                for (SystemBetCBO c : systemBetCBOS) {
                    treeSetC.add(c);
                    Set<String> unmetSystems = systemBetBOS.stream().map(systemBetBO -> systemBetBO.getUnmetAllocationSystem()).collect(Collectors.toSet());
                    List<SystemBetDBO> systemBetDBOS = new ArrayList<>();
                    for (String unmetSystem : unmetSystems) {
                        List<SystemBetBO> betBOList = betBOS.stream().filter(systemBetBO -> unmetSystem.equals(systemBetBO.getUnmetAllocationSystem())).collect(Collectors.toList());
                        List<SystemBetDBO> dbos = BeanTransform.copyProperties(betBOList, SystemBetDBO.class);
                        systemBetDBOS.addAll(dbos);
                    }
                    TreeSet<SystemBetDBO> treeSetD = filter();
                    for (SystemBetDBO d : systemBetDBOS) {
                        treeSetD.add(d);
                    }
                    systemBetDBOS.clear();
                    systemBetDBOS = new ArrayList<>(treeSetD);
                    c.setSystemBetDBOS(systemBetDBOS);

                }
                systemBetCBOS.clear();
                systemBetCBOS = new ArrayList<>(treeSetC);
                s.setSystemBetCBOS(systemBetCBOS);
            }
            systemBetBBOS.clear();
            systemBetBBOS = new ArrayList<>(treeSetB);
            systemBetABO.setSystemBetBBOS(systemBetBBOS);
            aboList.add(systemBetABO);

        }

        SystemBetABO abo = new SystemBetABO();
        abo.setArea("合计分值");
        List<SystemBetBBO> bbos = new ArrayList<>();
        SystemBetBBO bbo = new SystemBetBBO();
        bbo.setBasesScore(basesScore);
        bbo.setBasesScorePlan(basesScorePlan);
        bbo.setBasesScorePractice(basesScorePractice);
        bbo.setRestrictScore(restrictScore);
        bbo.setRestrictScorePlan(restrictScorePlan);
        bbo.setRestrictScorePractice(restrictScorePractice);
        bbo.setSystemTotalScore(systemTotalScore);
        bbo.setSystemTotalScorePlan(systemTotalScorePlan);
        bbo.setSystemTotalScorePractice(systemTotalScorePractice);
        bbos.add(bbo);
        abo.setSystemBetBBOS(bbos);
        List<SystemBetCBO> cbos = new ArrayList<>();
        SystemBetCBO cbo = new SystemBetCBO();
        cbo.setBetScore(betScore);
        cbo.setBetScorePlan(betScorePlan);
        cbo.setBetScorePractice(betScorePractice);
        cbos.add(cbo);
        bbo.setSystemBetCBOS(cbos);

        aboList.add(abo);
        return aboList;
    }


    private <T> TreeSet<T> filter() throws SerException {
        TreeSet<T> treeSet = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field[] field = o1.getClass().getDeclaredFields();//获取实体类的所有属性，返回field数组
                int num = 0;   //用于识别属性相同的个数
                int sum = 0;    //用于识别该对象除了集合的属性值个数
                for (Field f : field) {//遍历所有的属性
                    String type = f.getGenericType().toString();//获取属性的类型
                    if (type.indexOf("java.util.List") < 0) {
                        sum++;
                        String name = f.getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                        try {
                            Method m = o1.getClass().getMethod("get" + name);
                            Object value = m.invoke(o1);// 调用getter方法获取属性值
                            Method m1 = o2.getClass().getMethod("get" + name);
                            Object value1 = m1.invoke(o2);
                            if (value.equals(value1)) {    //判断该属性值是否相同
                                num++;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (num == sum) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return treeSet;
    }
}