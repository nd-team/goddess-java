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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门间对赌表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class DepartmentBetSerImpl extends ServiceImpl<DepartmentBet, DepartmentBetDTO> implements DepartmentBetSer {
    @Autowired
    private DepartmentBetASer departmentBetASer;
    @Autowired
    private DepartmentBetBSer departmentBetBSer;
    @Autowired
    private SystemBetBSer systemBetBSer;
    @Autowired
    private DepartmentBetCSer departmentBetCSer;
    @Autowired
    private DepartmentBetDSer departmentBetDSer;
    @Autowired
    private DepartmentBetESer departmentBetESer;
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
    public Long count(DepartmentBetDDTO dto) throws SerException {
        Long count = departmentBetDSer.count(dto);
        return count;
    }

    @Override
    public DepartmentBetABO getOne(String id) throws SerException {
        DepartmentBetA departmentBetA = departmentBetASer.findById(id);
        DepartmentBetABO listABO = BeanTransform.copyProperties(departmentBetA, DepartmentBetABO.class);

        if (listABO != null) {
            DepartmentBetBDTO bdto = new DepartmentBetBDTO();
            bdto.getConditions().add(Restrict.eq("departmentBetA.id", listABO.getId()));
            List<DepartmentBetB> listB = departmentBetBSer.findByCis(bdto);
            List<DepartmentBetBBO> listBBO = BeanTransform.copyProperties(listB, DepartmentBetBBO.class);
            listABO.setDepartmentBetBBOS(listBBO);
            if (listBBO != null) {
                for (DepartmentBetBBO departmentBetBBO : listBBO) {
                    DepartmentBetCDTO cdto = new DepartmentBetCDTO();
                    cdto.getConditions().add(Restrict.eq("departmentBetB.id", departmentBetBBO.getId()));
                    List<DepartmentBetC> listC = departmentBetCSer.findByCis(cdto);
                    List<DepartmentBetCBO> listCBO = BeanTransform.copyProperties(listC, DepartmentBetCBO.class);
                    departmentBetBBO.setDepartmentBetCBOS(listCBO);
                    if (listCBO != null) {
                        for (DepartmentBetCBO departmentBetCBO : listCBO) {
                            DepartmentBetDDTO ddto = new DepartmentBetDDTO();
                            ddto.getConditions().add(Restrict.eq("departmentBetC.id", departmentBetCBO.getId()));
                            List<DepartmentBetD> listD = departmentBetDSer.findByCis(ddto);
                            List<DepartmentBetDBO> listDBO = BeanTransform.copyProperties(listD, DepartmentBetDBO.class);
                            departmentBetCBO.setDepartmentBetDBOS(listDBO);
                            if (listDBO != null) {
                                for (DepartmentBetDBO departmentBetDBO : listDBO) {
                                    DepartmentBetEDTO edto = new DepartmentBetEDTO();
                                    edto.getConditions().add(Restrict.eq("departmentBetD.id", departmentBetDBO.getId()));
                                    List<DepartmentBetE> listE = departmentBetESer.findByCis(edto);
                                    List<DepartmentBetEBO> listEBO = BeanTransform.copyProperties(listE, DepartmentBetEBO.class);
                                    departmentBetDBO.setDepartmentBetEBOS(listEBO);
                                }
                            }
                        }
                    }
                }
            }
        }
        return listABO;
    }

    @Override
    public List<DepartmentBetABO> list(DepartmentBetADTO dto) throws SerException {
        checkSeeIdentity();
        List<DepartmentBetA> listA = departmentBetASer.findByCis(dto);
        List<DepartmentBetABO> listABO = BeanTransform.copyProperties(listA, DepartmentBetABO.class);
        if (listABO != null) {
            for (DepartmentBetABO departmentBetABO : listABO) {
                DepartmentBetBDTO bdto = new DepartmentBetBDTO();
                bdto.getConditions().add(Restrict.eq("departmentBetA.id", departmentBetABO.getId()));
                List<DepartmentBetB> listB = departmentBetBSer.findByCis(bdto);
                List<DepartmentBetBBO> listBBO = BeanTransform.copyProperties(listB, DepartmentBetBBO.class);
                departmentBetABO.setDepartmentBetBBOS(listBBO);
                if (listBBO != null) {
                    for (DepartmentBetBBO departmentBetBBO : listBBO) {
                        DepartmentBetCDTO cdto = new DepartmentBetCDTO();
                        cdto.getConditions().add(Restrict.eq("departmentBetB.id", departmentBetBBO.getId()));
                        List<DepartmentBetC> listC = departmentBetCSer.findByCis(cdto);
                        List<DepartmentBetCBO> listCBO = BeanTransform.copyProperties(listC, DepartmentBetCBO.class);
                        departmentBetBBO.setDepartmentBetCBOS(listCBO);
                        if (listCBO != null) {
                            for (DepartmentBetCBO departmentBetCBO : listCBO) {
                                DepartmentBetDDTO ddto = new DepartmentBetDDTO();
                                ddto.getConditions().add(Restrict.eq("departmentBetC.id", departmentBetCBO.getId()));
                                List<DepartmentBetD> listD = departmentBetDSer.findByCis(ddto);
                                List<DepartmentBetDBO> listDBO = BeanTransform.copyProperties(listD, DepartmentBetDBO.class);
                                departmentBetCBO.setDepartmentBetDBOS(listDBO);
                                if (listDBO != null) {
                                    for (DepartmentBetDBO departmentBetDBO : listDBO) {
                                        DepartmentBetEDTO edto = new DepartmentBetEDTO();
                                        edto.getConditions().add(Restrict.eq("departmentBetD.id", departmentBetDBO.getId()));
                                        List<DepartmentBetE> listE = departmentBetESer.findByCis(edto);
                                        List<DepartmentBetEBO> listEBO = BeanTransform.copyProperties(listE, DepartmentBetEBO.class);
                                        departmentBetDBO.setDepartmentBetEBOS(listEBO);
                                    }
                                }
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
    public void insert(DepartmentBetATO departmentBetATO) throws SerException {
        checkAddIdentity();
        DepartmentBetA departmentBetA = BeanTransform.copyProperties(departmentBetATO, DepartmentBetA.class, true);
        departmentBetASer.save(departmentBetA);

        List<DepartmentBetB> departmentBetBS = new ArrayList<>();
        List<DepartmentBetC> departmentBetCS = new ArrayList<>();
        List<DepartmentBetD> departmentBetDS = new ArrayList<>();
        List<DepartmentBetE> departmentBetES = new ArrayList<>();
        //B表
        List<DepartmentBetBTO> departmentBetBTOS = departmentBetATO.getDepartmentBetBTOS();
        if (departmentBetBTOS != null) {
            for (DepartmentBetBTO departmentBetBTO : departmentBetBTOS) {
                DepartmentBetB departmentBetB = BeanTransform.copyProperties(departmentBetBTO, DepartmentBetB.class, true);
                departmentBetB.setDepartmentBetA(departmentBetA);
                departmentBetB = departmentBetBSer.save(departmentBetB);
                departmentBetBS.add(departmentBetB);
                //C表
                List<DepartmentBetCTO> departmentBetCTOS = departmentBetBTO.getDepartmentBetCTOS();
                if (departmentBetCTOS != null) {
                    for (DepartmentBetCTO departmentBetCTO : departmentBetCTOS) {
                        DepartmentBetC departmentBetC = BeanTransform.copyProperties(departmentBetCTO, DepartmentBetC.class, true);
                        departmentBetC.setDepartmentBetB(departmentBetB);
                        //体系目标总得分
                        double systemTotalScore = 0;
                        //体系计划总得分
                        double systemTotalScorePlan = 0;
                        //体系实际总得分
                        double systemTotalScorePractice = 0;
                        //目标基础得分
                        double basesScore = 0.0;
                        //计划基础得分
                        double basesScorePlan = 0.0;
                        //实际基础得分
                        double basesScorePractice = 0.0;
                        String[] fields = new String[]{"systemTotalScore", "systemTotalScorePlan", "systemTotalScorePractice"};
                        String sql = "SELECT systemTotalScore AS systemTotalScore,systemTotalScorePlan AS systemTotalScorePlan, " +
                                " systemTotalScorePractice as systemTotalScorePractice FROM royalty_systembetb WHERE system='" + departmentBetB.getSystem() + "' ";
                        List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                        if (systemBetBS != null && !systemBetBS.isEmpty()) {
                            if (null != systemBetBS.get(0).getSystemTotalScore()) {
                                systemTotalScore = systemBetBS.get(0).getSystemTotalScore();
                            }
                            if (null != systemBetBS.get(0).getSystemTotalScorePlan()) {
                                systemTotalScorePlan = systemBetBS.get(0).getSystemTotalScorePlan();
                            }
                            if (null != systemBetBS.get(0).getSystemTotalScorePractice()) {
                                systemTotalScorePractice = systemBetBS.get(0).getSystemTotalScorePractice();
                            }

                        }
                        //目标基础得分（体系目标总得分*目标-部门分配基础权重）
                        basesScore = systemTotalScore * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScore(basesScore);
                        //计划基础得分（体系计划总得分*目标-部门分配基础权重）
                        basesScorePlan = systemTotalScorePlan * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScorePlan(basesScorePlan);
                        //实际基础得分（体系实际总得分*目标-部门分配基础权重）
                        basesScorePractice = systemTotalScorePractice * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScorePractice(basesScorePractice);

                        //部门目标总得分（目标制约得分+目标基础得分）
                        Double departmentTotalScore = departmentBetC.getRestrictScore() + basesScore;
                        departmentBetC.setDepartmentTotalScore(departmentTotalScore);
                        //部门计划总得分（计划制约得分+计划基础得分）
                        Double departmentTotalScorePlan = departmentBetC.getRestrictScorePlan() + basesScorePlan;
                        departmentBetC.setDepartmentTotalScorePlan(departmentTotalScorePlan);
                        //部门实际总得分（实际制约得分+实际基础得分）
                        Double departmentTotalScorePractice = departmentBetC.getRestrictScorePractice() + basesScorePractice;
                        departmentBetC.setDepartmentTotalScorePractice(departmentTotalScorePractice);

                        departmentBetC = departmentBetCSer.save(departmentBetC);
                        departmentBetCS.add(departmentBetC);
                        //D表
                        List<DepartmentBetETO> departmentBetETOS = departmentBetCTO.getDepartmentBetETOS();
                        if (departmentBetETOS != null) {
                            for (DepartmentBetETO departmentBetETO : departmentBetETOS) {
                                DepartmentBetD departmentBetD = BeanTransform.copyProperties(departmentBetETO, DepartmentBetD.class, true);
                                departmentBetD.setDepartmentBetC(departmentBetC);
                                //目标对赌得分（体系目标总得分*目标-部门分配对赌权重）
                                Double betScore = systemTotalScore * departmentBetD.getBetWeight();
                                departmentBetD.setBetScore(betScore);
                                //计划对赌得分（体系计划总得分*目标-部门分配对赌权重）
                                Double betScorePlan = systemTotalScorePlan * departmentBetD.getBetWeight();
                                departmentBetD.setBetScorePlan(betScorePlan);
                                //实际对赌得分（体系实际总得分*目标-部门分配对赌权重）
                                Double betScorePractice = systemTotalScorePractice * departmentBetD.getBetWeight();
                                departmentBetD.setBetScorePractice(betScorePractice);
                                departmentBetD = departmentBetDSer.save(departmentBetD);
                                departmentBetDS.add(departmentBetD);
                                //E表
                                List<DepartmentBetFTO> departmentBetFTOS = departmentBetETO.getDepartmentBetFTOS();
                                if (departmentBetFTOS != null) {
                                    for (DepartmentBetFTO departmentBetFTO : departmentBetFTOS) {
                                        DepartmentBetE departmentBetE = BeanTransform.copyProperties(departmentBetFTO, DepartmentBetE.class, true);
                                        departmentBetE.setDepartmentBetD(departmentBetD);
                                        if (departmentBetC.getDepartment().equals(departmentBetE.getUnmetAllocationDepartment())) {
                                            throw new SerException("部门和未达标分配部门不能是同一个");
                                        }
                                        departmentBetE = departmentBetESer.save(departmentBetE);
                                        departmentBetES.add(departmentBetE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(DepartmentBetATO departmentBetATO) throws SerException {
        checkAddIdentity();
        DepartmentBetA departmentBetA = departmentBetASer.findById(departmentBetATO.getId());
        BeanTransform.copyProperties(departmentBetATO, departmentBetA, true);
        departmentBetASer.update(departmentBetA);

        //先删掉B表对应数据
        DepartmentBetBDTO departmentBetBDTO = new DepartmentBetBDTO();
        departmentBetBDTO.getConditions().add(Restrict.eq("departmentBetA.id", departmentBetA.getId()));
        List<DepartmentBetB> bList = departmentBetBSer.findByCis(departmentBetBDTO);
        if (bList != null && bList.size() > 0) {
            //查询对应C表的数据，先删除
            List<String> bIdList = bList.stream().map(DepartmentBetB::getId).collect(Collectors.toList());
            String[] bids = new String[bIdList.size()];
            bids = bIdList.toArray(bids);
            DepartmentBetCDTO departmentBetCDTO = new DepartmentBetCDTO();
            departmentBetCDTO.getConditions().add(Restrict.in("departmentBetB.id", bids));
            List<DepartmentBetC> cList = departmentBetCSer.findByCis(departmentBetCDTO);
            if (cList != null && cList.size() > 0) {
                //查询对应D表的数据，先删除
                List<String> cIdList = cList.stream().map(DepartmentBetC::getId).collect(Collectors.toList());
                String[] cids = new String[cIdList.size()];
                cids = cIdList.toArray(cids);
                DepartmentBetDDTO departmentBetDDTO = new DepartmentBetDDTO();
                departmentBetDDTO.getConditions().add(Restrict.in("departmentBetC.id", cids));
                List<DepartmentBetD> dList = departmentBetDSer.findByCis(departmentBetDDTO);
                if (dList != null && dList.size() > 0) {
                    //查询对应E表的数据，先删除
                    List<String> dIdList = dList.stream().map(DepartmentBetD::getId).collect(Collectors.toList());
                    String[] dids = new String[dIdList.size()];
                    dids = dIdList.toArray(dids);
                    DepartmentBetEDTO departmentBetEDTO = new DepartmentBetEDTO();
                    departmentBetEDTO.getConditions().add(Restrict.in("departmentBetD.id", dids));
                    List<DepartmentBetE> eList = departmentBetESer.findByCis(departmentBetEDTO);
                    if (eList != null && eList.size() > 0) {
                        departmentBetESer.remove(eList);
                    }
                    departmentBetDSer.remove(dList);
                }
                departmentBetCSer.remove(cList);
            }
            departmentBetBSer.remove(bList);
        }
        //B表
        List<DepartmentBetB> departmentBetBS = new ArrayList<>();
        List<DepartmentBetBTO> departmentBetBTOS = departmentBetATO.getDepartmentBetBTOS();
        if (departmentBetBTOS != null) {
            for (DepartmentBetBTO departmentBetBTO : departmentBetBTOS) {
                DepartmentBetB departmentBetB = BeanTransform.copyProperties(departmentBetBTO, DepartmentBetB.class, true);
                departmentBetB.setDepartmentBetA(departmentBetA);
                departmentBetB.setId(null);
                departmentBetBSer.update(departmentBetB);
                departmentBetBS.add(departmentBetB);
                //C表
                List<DepartmentBetC> departmentBetCS = new ArrayList<>();
                List<DepartmentBetCTO> departmentBetCTOS = departmentBetBTO.getDepartmentBetCTOS();
                if (departmentBetCTOS != null) {
                    for (DepartmentBetCTO departmentBetCTO : departmentBetCTOS) {
                        DepartmentBetC departmentBetC = BeanTransform.copyProperties(departmentBetCTO, DepartmentBetC.class, true);
                        departmentBetC.setDepartmentBetB(departmentBetB);
                        //体系目标总得分
                        double systemTotalScore = 0;
                        //体系计划总得分
                        double systemTotalScorePlan = 0;
                        //体系实际总得分
                        double systemTotalScorePractice = 0;
                        //目标基础得分
                        double basesScore = 0.0;
                        //计划基础得分
                        double basesScorePlan = 0.0;
                        //实际基础得分
                        double basesScorePractice = 0.0;
                        String[] fields = new String[]{"systemTotalScore", "systemTotalScorePlan", "systemTotalScorePractice"};
                        String sql = "SELECT systemTotalScore AS systemTotalScore,systemTotalScorePlan AS systemTotalScorePlan, " +
                                " systemTotalScorePractice as systemTotalScorePractice FROM royalty_systembetb WHERE system='" + departmentBetB.getSystem() + "' ";
                        List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                        if (systemBetBS != null && !systemBetBS.isEmpty()) {
                            if (null != systemBetBS.get(0).getSystemTotalScore()) {
                                systemTotalScore = systemBetBS.get(0).getSystemTotalScore();
                            }
                            if (null != systemBetBS.get(0).getSystemTotalScorePlan()) {
                                systemTotalScorePlan = systemBetBS.get(0).getSystemTotalScorePlan();
                            }
                            if (null != systemBetBS.get(0).getSystemTotalScorePractice()) {
                                systemTotalScorePractice = systemBetBS.get(0).getSystemTotalScorePractice();
                            }

                        }
                        //目标基础得分（体系目标总得分*目标-部门分配基础权重）
                        basesScore = systemTotalScore * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScore(basesScore);
                        //计划基础得分（体系计划总得分*目标-部门分配基础权重）
                        basesScorePlan = systemTotalScorePlan * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScorePlan(basesScorePlan);
                        //实际基础得分（体系实际总得分*目标-部门分配基础权重）
                        basesScorePractice = systemTotalScorePractice * departmentBetC.getBaseWeight();
                        departmentBetC.setBasesScorePractice(basesScorePractice);

                        //部门目标总得分（目标制约得分+目标基础得分）
                        Double departmentTotalScore = departmentBetC.getRestrictScore() + basesScore;
                        departmentBetC.setDepartmentTotalScore(departmentTotalScore);
                        //部门计划总得分（计划制约得分+计划基础得分）
                        Double departmentTotalScorePlan = departmentBetC.getRestrictScorePlan() + basesScorePlan;
                        departmentBetC.setDepartmentTotalScorePlan(departmentTotalScorePlan);
                        //部门实际总得分（实际制约得分+实际基础得分）
                        Double departmentTotalScorePractice = departmentBetC.getRestrictScorePractice() + basesScorePractice;
                        departmentBetC.setDepartmentTotalScorePractice(departmentTotalScorePractice);
                        departmentBetC.setId(null);
                        departmentBetCSer.update(departmentBetC);
                        departmentBetCS.add(departmentBetC);

                        //D表
                        List<DepartmentBetD> departmentBetDS = new ArrayList<>();
                        List<DepartmentBetETO> departmentBetETOS = departmentBetCTO.getDepartmentBetETOS();
                        if (departmentBetETOS != null) {
                            for (DepartmentBetETO departmentBetETO : departmentBetETOS) {
                                DepartmentBetD departmentBetD = BeanTransform.copyProperties(departmentBetETO, DepartmentBetD.class, true);
                                departmentBetD.setDepartmentBetC(departmentBetC);
                                //目标对赌得分（体系目标总得分*目标-部门分配对赌权重）
                                Double betScore = systemTotalScore * departmentBetD.getBetWeight();
                                departmentBetD.setBetScore(betScore);
                                //计划对赌得分（体系计划总得分*目标-部门分配对赌权重）
                                Double betScorePlan = systemTotalScorePlan * departmentBetD.getBetWeight();
                                departmentBetD.setBetScorePlan(betScorePlan);
                                //实际对赌得分（体系实际总得分*目标-部门分配对赌权重）
                                Double betScorePractice = systemTotalScorePractice * departmentBetD.getBetWeight();
                                departmentBetD.setBetScorePractice(betScorePractice);
                                departmentBetD.setId(null);
                                departmentBetDSer.update(departmentBetD);
                                departmentBetDS.add(departmentBetD);
                                //E表
                                List<DepartmentBetE> departmentBetES = new ArrayList<>();
                                List<DepartmentBetFTO> departmentBetFTOS = departmentBetETO.getDepartmentBetFTOS();
                                if (departmentBetFTOS != null) {
                                    for (DepartmentBetFTO departmentBetFTO : departmentBetFTOS) {
                                        DepartmentBetE departmentBetE = BeanTransform.copyProperties(departmentBetFTO, DepartmentBetE.class, true);
                                        departmentBetE.setDepartmentBetD(departmentBetD);
                                        departmentBetE.setId(null);
                                        if (departmentBetC.getDepartment().equals(departmentBetE.getUnmetAllocationDepartment())) {
                                            throw new SerException("部门和未达标分配部门不能是同一个");
                                        }
                                        departmentBetESer.update(departmentBetE);
                                        departmentBetES.add(departmentBetE);
                                    }
                                }
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
        DepartmentBetE departmentBetE = departmentBetESer.findById(id);

        if (departmentBetE == null) {
            throw new SerException("该对象不存在");
        }
        departmentBetESer.remove(id);
        List<DepartmentBetD> dList = departmentBetDSer.findAll();
        List<DepartmentBetC> cList = departmentBetCSer.findAll();
        List<DepartmentBetB> bList = departmentBetBSer.findAll();
        List<DepartmentBetA> aList = departmentBetASer.findAll();

        Set<String> dids = new HashSet<>();
        Set<String> cids = new HashSet<>();
        Set<String> bids = new HashSet<>();
        Set<String> aids = new HashSet<>();
        for (DepartmentBetE e : departmentBetESer.findAll()) {
            dids.add(e.getDepartmentBetD().getId());
        }
        for (DepartmentBetD d : dList) {
            if (!dids.contains(d.getId())) {
                departmentBetDSer.remove(d.getId());
            }
        }
        for (DepartmentBetD d1 : departmentBetDSer.findAll()) {
            cids.add(d1.getDepartmentBetC().getId());
        }
        for (DepartmentBetC c : cList) {
            if (!cids.contains(c.getId())) {
                departmentBetCSer.remove(c.getId());
            }
        }
        for (DepartmentBetC c1 : departmentBetCSer.findAll()) {
            bids.add(c1.getDepartmentBetB().getId());
        }
        for (DepartmentBetB b : bList) {
            if (!bids.contains(b.getId())) {
                departmentBetBSer.remove(b.getId());
            }
        }
        for (DepartmentBetB b1 : departmentBetBSer.findAll()) {
            aids.add(b1.getDepartmentBetA().getId());
        }
        for (DepartmentBetA a : aList) {
            if (!aids.contains(a.getId())) {
                departmentBetASer.remove(a.getId());
            }
        }

    }

    @Override
    public List<DepartmentBetABO> departmentCollect(ProjectNameTO to) throws SerException {
        List<DepartmentBetABO> departmentBetABOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("  SELECT a.betTime AS betTime,a.area as area,a.projectGroup as projectGroup, ");
        sb.append(" a.projectName as projectName,a.scoreProfit as scoreProfit, ");
        sb.append(" a.planProfit as planProfit,a.practiceProfit as practiceProfit, ");
        sb.append(" b.system AS system,c.department as department,c.baseWeight AS baseWeight,c.basesScore AS basesScore, ");
        sb.append(" c.basesScorePlan AS basesScorePlan,c.basesScorePractice AS basesScorePractice, ");
        sb.append(" c.restrictScore AS restrictScore,c.restrictScorePlan AS restrictScorePlan, ");
        sb.append(" c.restrictScorePractice AS restrictScorePractice,c.departmentTotalScore AS departmentTotalScore, ");
        sb.append(" c.departmentTotalScorePlan AS departmentTotalScorePlan,c.departmentTotalScorePractice AS departmentTotalScorePractice, ");
        sb.append(" d.betWeight AS betWeight,d.indexNum as indexNum,d.indexName AS indexName, ");
        sb.append("  d.confirmTargetValue as confirmTargetValue,d.actualTargetValue AS actualTargetValue, ");
        sb.append(" d.is_standard AS standard,d.betScore AS betScore,d.betScorePlan AS betScorePlan, ");
        sb.append(" d.betScorePractice AS betScorePractice, ");
        sb.append(" e.unmetAllocationDepartment as unmetAllocationDepartment, e.unmetAllocation as unmetAllocation ");
        sb.append(" FROM royalty_departmentbeta a,royalty_departmentbetb b,  royalty_departmentbetc c,royalty_departmentbetd d, ");
        sb.append(" royalty_departmentbete e ");
        sb.append(" WHERE a.projectName='" + to.getProjectName() + "' AND a.id=b.departmentBetA_id AND b.id=c.departmentBetB_id  AND c.id=d.departmentBetC_id ");
        sb.append(" AND d.id=e.departmentBetD_id ");
        String[] feilds = new String[]{"betTime", "area", "projectGroup", "projectName", "scoreProfit", "planProfit",
                "practiceProfit", "system", "department", "baseWeight", "basesScore", "basesScorePlan", "basesScorePractice",
                "restrictScore", "restrictScorePlan", "restrictScorePractice", "departmentTotalScore", "departmentTotalScorePlan",
                "departmentTotalScorePractice", "betWeight", "indexNum", "indexName",
                "confirmTargetValue", "actualTargetValue", "standard", "betScore", "betScorePlan", "betScorePractice",
                "unmetAllocationDepartment", "unmetAllocation"};
        List<DepartmentBetBO> departmentBetBOS = super.findBySql(sb.toString(), DepartmentBetBO.class, feilds);
        double basesScore = 0.0;
        double basesScorePlan = 0.0;
        double basesScorePractice = 0.0;
        double restrictScore = 0.0;
        double restrictScorePlan = 0.0;
        double restrictScorePractice = 0.0;
        double departmentTotalScore = 0.0;
        double departmentTotalScorePlan = 0.0;
        double departmentTotalScorePractice = 0.0;
        double betScore = 0.0;
        double betScorePlan = 0.0;
        double betScorePractice = 0.0;
        if (departmentBetBOS != null) {
            basesScore = departmentBetBOS.stream().filter(p -> p.getBasesScore() != null).mapToDouble(p -> p.getBasesScore()).sum();
            basesScorePlan = departmentBetBOS.stream().filter(p -> p.getBasesScorePlan() != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
            basesScorePractice = departmentBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            restrictScore = departmentBetBOS.stream().filter(p -> p.getRestrictScore() != null).mapToDouble(p -> p.getRestrictScore()).sum();
            restrictScorePlan = departmentBetBOS.stream().filter(p -> p.getRestrictScorePlan() != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
            restrictScorePractice = departmentBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            departmentTotalScore = departmentBetBOS.stream().filter(p -> p.getDepartmentTotalScore() != null).mapToDouble(p -> p.getDepartmentTotalScore()).sum();
            departmentTotalScorePlan = departmentBetBOS.stream().filter(p -> p.getDepartmentTotalScorePlan() != null).mapToDouble(p -> p.getDepartmentTotalScorePlan()).sum();
            departmentTotalScorePractice = departmentBetBOS.stream().filter(p -> p.getDepartmentTotalScorePractice() != null).mapToDouble(p -> p.getDepartmentTotalScorePractice()).sum();
            betScore = departmentBetBOS.stream().filter(p -> p.getBetScore() != null).mapToDouble(p -> p.getBetScore()).sum();
            betScorePlan = departmentBetBOS.stream().filter(p -> p.getBetScorePlan() != null).mapToDouble(p -> p.getBetScorePlan()).sum();
            betScorePractice = departmentBetBOS.stream().filter(p -> p.getBetScorePractice() != null).mapToDouble(p -> p.getBetScorePractice()).sum();

        }
        Set<String> projectNames = departmentBetBOS.stream().map(DepartmentBetBO::getProjectName).collect(Collectors.toSet());
        DepartmentBetABO departmentBetABO = new DepartmentBetABO();
        for (String projectName : projectNames) {
            List<DepartmentBetBO> betBOS = departmentBetBOS.stream().filter(departmentBetBO -> projectName.equals(departmentBetBO.getProjectName())).collect(Collectors.toList());
            for (DepartmentBetBO departmentBetBO : betBOS) {
                departmentBetABO.setBetTime(departmentBetBO.getBetTime());
                departmentBetABO.setArea(departmentBetBO.getArea());
                departmentBetABO.setProjectName(projectName);
                departmentBetABO.setProjectGroup(departmentBetBO.getProjectGroup());
                departmentBetABO.setScoreProfit(departmentBetBO.getScoreProfit());
                departmentBetABO.setPlanProfit(departmentBetBO.getPlanProfit());
                departmentBetABO.setPracticeProfit(departmentBetBO.getPracticeProfit());
            }
            Set<String> systems = departmentBetBOS.stream().map(departmentBetBO -> departmentBetBO.getSystem()).collect(Collectors.toSet());
            List<DepartmentBetBBO> departmentBetBBOS = new ArrayList<>();
            for (String system : systems) {
                List<DepartmentBetBO> list = betBOS.stream().filter(departmentBetBO -> system.equals(departmentBetBO.getSystem())).collect(Collectors.toList());
                List<DepartmentBetBBO> bbos = BeanTransform.copyProperties(list, DepartmentBetBBO.class);
                departmentBetBBOS.addAll(bbos);
            }
            TreeSet<DepartmentBetBBO> treeSetB = filter();
            for (DepartmentBetBBO b : departmentBetBBOS) {
                treeSetB.add(b);
                Set<String> departments = departmentBetBOS.stream().map(departmentBetBO -> departmentBetBO.getDepartment()).collect(Collectors.toSet());
                List<DepartmentBetCBO> departmentBetCBOS = new ArrayList<>();
                for (String department : departments) {
                    List<DepartmentBetBO> list = betBOS.stream().filter(departmentBetBO -> department.equals(departmentBetBO.getDepartment())).collect(Collectors.toList());
                    List<DepartmentBetCBO> cbos = BeanTransform.copyProperties(list, DepartmentBetCBO.class);
                    departmentBetCBOS.addAll(cbos);
                }

                TreeSet<DepartmentBetCBO> treeSetC = filter();
                for (DepartmentBetCBO c : departmentBetCBOS) {
                    treeSetC.add(c);
                    Set<String> indexNums = departmentBetBOS.stream().map(departmentBetBO -> departmentBetBO.getIndexNum()).collect(Collectors.toSet());
                    List<DepartmentBetDBO> departmentBetDBOS = new ArrayList<>();
                    for (String indexNum : indexNums) {
                        List<DepartmentBetBO> list = betBOS.stream().filter(departmentBetBO -> indexNum.equals(departmentBetBO.getIndexNum())).collect(Collectors.toList());
                        List<DepartmentBetDBO> dbos = BeanTransform.copyProperties(list, DepartmentBetDBO.class);
                        departmentBetDBOS.addAll(dbos);
                    }
                    TreeSet<DepartmentBetDBO> treeSetD = filter();
                    for (DepartmentBetDBO d : departmentBetDBOS) {
                        treeSetD.add(d);
                        Set<String> unmetDepartments = departmentBetBOS.stream().map(departmentBetBO -> departmentBetBO.getUnmetAllocationDepartment()).collect(Collectors.toSet());
                        List<DepartmentBetEBO> departmentBetEBOS = new ArrayList<>();
                        for (String unmetDepartment : unmetDepartments) {
                            List<DepartmentBetBO> list = departmentBetBOS.stream().filter(departmentBetBO -> unmetDepartment.equals(departmentBetBO.getUnmetAllocationDepartment())).collect(Collectors.toList());
                            List<DepartmentBetEBO> ebos = BeanTransform.copyProperties(list, DepartmentBetEBO.class);
                            departmentBetEBOS.addAll(ebos);
                        }
                        TreeSet<DepartmentBetEBO> treeSetE = filter();
                        departmentBetEBOS.clear();
                        departmentBetEBOS = new ArrayList<>(treeSetE);
                        d.setDepartmentBetEBOS(departmentBetEBOS);
                    }
                    departmentBetDBOS.clear();
                    departmentBetDBOS = new ArrayList<>(treeSetD);
                    c.setDepartmentBetDBOS(departmentBetDBOS);

                }
                departmentBetCBOS.clear();
                departmentBetCBOS = new ArrayList<>(treeSetC);
                b.setDepartmentBetCBOS(departmentBetCBOS);
            }
            departmentBetBBOS.clear();
            departmentBetBBOS = new ArrayList<>(treeSetB);
            departmentBetABO.setDepartmentBetBBOS(departmentBetBBOS);
            departmentBetABOS.add(departmentBetABO);
        }
        DepartmentBetABO abo = new DepartmentBetABO();
        abo.setArea("合计分值");
        List<DepartmentBetBBO> bboList = new ArrayList<>();
        DepartmentBetBBO bbo = new DepartmentBetBBO();
        abo.setDepartmentBetBBOS(bboList);
        List<DepartmentBetCBO> cboList = new ArrayList<>();
        DepartmentBetCBO cbo = new DepartmentBetCBO();
        cbo.setBasesScore(basesScore);
        cbo.setBasesScorePlan(basesScorePlan);
        cbo.setBasesScorePractice(basesScorePractice);
        cbo.setRestrictScore(restrictScore);
        cbo.setRestrictScorePlan(restrictScorePlan);
        cbo.setRestrictScorePractice(restrictScorePractice);
        cbo.setDepartmentTotalScore(departmentTotalScore);
        cbo.setDepartmentTotalScorePlan(departmentTotalScorePlan);
        cbo.setDepartmentTotalScorePractice(departmentTotalScorePractice);
        cboList.add(cbo);
        bbo.setDepartmentBetCBOS(cboList);
        List<DepartmentBetDBO> dboList = new ArrayList<>();
        DepartmentBetDBO dbo = new DepartmentBetDBO();
        dbo.setBetScore(betScore);
        dbo.setBetScorePlan(betScorePlan);
        dbo.setBetScorePractice(betScorePractice);
        dboList.add(dbo);
        cbo.setDepartmentBetDBOS(dboList);

        departmentBetABOS.add(abo);
        return departmentBetABOS;
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