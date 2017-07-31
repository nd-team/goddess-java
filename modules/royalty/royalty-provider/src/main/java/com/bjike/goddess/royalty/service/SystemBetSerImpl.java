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

import java.util.ArrayList;
import java.util.List;
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
        return BeanTransform.copyProperties(systemBetA, SystemBetABO.class);
    }


    @Override
    public List<SystemBetBO> list(SystemBetDTO dto) throws SerException {
        checkSeeIdentity();
        SystemBetADTO dtoA = new SystemBetADTO();
        List<SystemBetA> listA = systemBetASer.findByCis(dtoA);
        List<SystemBetBO> systemBetBOS = new ArrayList<>(listA.size());
        for (SystemBetA systemBetA : listA) {
            SystemBetBO betBO = new SystemBetBO();
            betBO.setSystemBetABO(BeanTransform.copyProperties(systemBetA, SystemBetABO.class));
            SystemBetBDTO dtoB = new SystemBetBDTO();
            List<SystemBetB> listB = systemBetBSer.findByCis(dtoB);
            List<SystemBetBBO> bboList = BeanTransform.copyProperties(listB, SystemBetBBO.class);
            betBO.getSystemBetABO().setSystemBetBBOS(bboList);

            for (SystemBetBBO systemBetBBO : bboList) {
                SystemBetCDTO dtoC = new SystemBetCDTO();
                List<SystemBetC> listC = systemBetCSer.findByCis(dtoC);
                List<SystemBetCBO> cboList = BeanTransform.copyProperties(listC, SystemBetCBO.class);
                systemBetBBO.setSystemBetCBOS(cboList);

                for (SystemBetCBO systemBetCBO : cboList) {
                    SystemBetDDTO dtoD = new SystemBetDDTO();
                    List<SystemBetD> listD = systemBetDSer.findByCis(dtoD);
                    List<SystemBetDBO> dboList = BeanTransform.copyProperties(listD, SystemBetDBO.class);
                    systemBetCBO.setSystemBetDBOS(dboList);
                }
            }
            systemBetBOS.add(betBO);
        }
        return systemBetBOS;
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

            //基础得分（分值*目标-部门分配基础权重）
            Double basesScore = systemBetATO.getScore() * systemBetBTO.getBaseWeight();
            systemBetB.setBasesScore(basesScore);
            //制约得分
            //判断是否含有“是”
            //Boolean scoreFlag = systemBetBTO.getSystemBetCTOS().stream().anyMatch(str -> str.getStandard().equals("是"));
            //if (scoreFlag) {
            //说明含有“是” ,给相对应体系
            //Double restrictScore = 0.0;
//                systemBetB.setRestrictScore();
//                systemBetB.setRestrictScore(systemBetBTO.getUnmetAllocation()* betScore);
            //}
            //部门总得分（制约得分+基础得分）
            systemBetB.setDepartmentTotalScore(systemBetBTO.getRestrictScore() + basesScore);

            systemBetB = systemBetBSer.save(systemBetB);
            systemBetBS.add(systemBetB);
            List<SystemBetCTO> systemBetCTOS = systemBetBTO.getSystemBetCTOS();
            if (null != systemBetBTOS) {
                for (SystemBetCTO systemBetCTO : systemBetCTOS) {
                    SystemBetC systemBetC = BeanTransform.copyProperties(systemBetCTO, SystemBetC.class, true);
                    systemBetC.setSystemBetB(systemBetB);


                    //对赌得分（分值*目标-部门分配对赌权重）
                    Double betScore = systemBetATO.getScore() * systemBetCTO.getBetWeight();
                    systemBetC.setBetScore(betScore);
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
                //基础得分（分值*目标-部门分配基础权重）
                Double basesScore = systemBetATO.getScore() * systemBetBTO.getBaseWeight();
                systemBetB.setBasesScore(basesScore);

                systemBetBSer.update(systemBetB);
                systemBetBS.add(systemBetB);

                //查询C表的数据并修改
                List<SystemBetCTO> systemBetCTOS = systemBetBTO.getSystemBetCTOS();
                if (systemBetCTOS != null) {
                    for (SystemBetCTO systemBetCTO : systemBetCTOS) {
                        SystemBetC systemBetC = BeanTransform.copyProperties(systemBetCTO, SystemBetC.class, true);
                        systemBetC.setSystemBetB(systemBetB);

                        //对赌得分（分值*目标-部门分配对赌权重）
                        Double betScore = systemBetATO.getScore() * systemBetCTO.getBetWeight();
                        systemBetC.setBetScore(betScore);

                        systemBetCSer.update(systemBetC);
                        systemBetCS.add(systemBetC);

                        //查询D表的数据并修改
                        List<SystemBetETO> systemBetETOS = systemBetCTO.getSystemBetETOS();
                        if (systemBetETOS != null) {
                            for (SystemBetETO systemBetETO : systemBetETOS) {
                                SystemBetD systemBetD = BeanTransform.copyProperties(systemBetETO, SystemBetD.class, true);
                                systemBetD.setSystemBetC(systemBetC);
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
        systemBetDSer.remove(id);
//        SystemBetADTO systemBetADTO = new SystemBetADTO();
//        systemBetADTO.getConditions().add(Restrict.eq("id", id));
//        List<SystemBetA> aList = systemBetASer.findByCis(systemBetADTO);
//        if (aList != null && aList.size() > 0) {
//            List<String> aIdList = aList.stream().map(SystemBetA::getId).collect(Collectors.toList());
//            String[] aids = new String[aIdList.size()];
//            aids = aIdList.toArray(aids);
//            //查询B表对应的数据
//            SystemBetBDTO systemBetBDTO = new SystemBetBDTO();
//            systemBetBDTO.getConditions().add(Restrict.eq("systemBetA.id", aids));
//            List<SystemBetB> bList = systemBetBSer.findByCis(systemBetBDTO);
//            if (bList != null && bList.size() > 0) {
//                //查询对应C表的数据
//                List<String> bIdList = bList.stream().map(SystemBetB::getId).collect(Collectors.toList());
//                String[] bids = new String[bIdList.size()];
//                bids = bIdList.toArray(bids);
//                SystemBetCDTO systemBetCDTO = new SystemBetCDTO();
//                systemBetCDTO.getConditions().add(Restrict.in("systemBetB.id", bids));
//                List<SystemBetC> cList = systemBetCSer.findByCis(systemBetCDTO);
//                if (cList != null && cList.size() > 0) {
//                    //查询对应D表的数据，先删除
//                    List<String> cIdList = cList.stream().map(SystemBetC::getId).collect(Collectors.toList());
//                    String[] cids = new String[cIdList.size()];
//                    cids = cIdList.toArray(cids);
//                    SystemBetDDTO systemBetDDTO = new SystemBetDDTO();
//                    systemBetDDTO.getConditions().add(Restrict.in("systemBetC.id", cids));
//                    List<SystemBetD> dList = systemBetDSer.findByCis(systemBetDDTO);
//                    if (dList != null && dList.size() > 0) {
//                        systemBetDSer.remove(dList);
//                    }
//                    systemBetCSer.remove(cList);
//                }
//
//                systemBetBSer.remove(bList);
//            }
//            systemBetASer.remove(id);
//
//        }
    }

    @Override
    public List<String> getProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<SystemBetABO> systemBetABOS = super.findBySql("select distinct projectName from royalty_departmentbeta group by projectName order by projectName asc ", SystemBetABO.class, fields);

        List<String> projectNameList = systemBetABOS.stream().map(SystemBetABO::getProjectName)
                .filter(projectName -> (StringUtils.isNotBlank(projectName))).distinct().collect(Collectors.toList());


        return projectNameList;
    }

    @Override
    public List<String> getDepartment() throws SerException {
        String[] fields = new String[]{"department"};
        List<SystemBetBBO> systemBetBBOS = super.findBySql("select distinct department from royalty_departmentbetb group by department order by department asc ", SystemBetBBO.class, fields);

        List<String> departmentList = systemBetBBOS.stream().map(SystemBetBBO::getDepartment)
                .filter(department -> (StringUtils.isNotBlank(department))).distinct().collect(Collectors.toList());


        return departmentList;

    }
}