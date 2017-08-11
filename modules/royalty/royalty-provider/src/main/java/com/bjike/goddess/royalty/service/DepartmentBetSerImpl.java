package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.royalty.bo.DepartmentBetABO;
import com.bjike.goddess.royalty.bo.DepartmentBetBBO;
import com.bjike.goddess.royalty.bo.DepartmentBetCBO;
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

import java.util.ArrayList;
import java.util.List;
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
    private SystemBetSer systemBetSer;
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
        return BeanTransform.copyProperties(departmentBetA, DepartmentBetABO.class);
    }

    @Override
    public List<DepartmentBetABO> list(DepartmentBetADTO dto) throws SerException {
        checkSeeIdentity();
        List<DepartmentBetA> listA = departmentBetASer.findByCis(dto);
        List<DepartmentBetABO> listABO = BeanTransform.copyProperties(listA, DepartmentBetABO.class);
        if (listABO != null) {
            for (DepartmentBetABO departmentBetABO : listABO) {
                DepartmentBetBDTO bdto = new DepartmentBetBDTO();
                bdto.getConditions().add(Restrict.eq("departmentBetA.id",departmentBetABO.getId()));
                List<DepartmentBetB> listB = departmentBetBSer.findByCis(bdto);
                List<DepartmentBetBBO> listBBO = BeanTransform.copyProperties(listB,DepartmentBetBBO.class);
                departmentBetABO.setDepartmentBetBBOS(listBBO);
                if(listBBO!=null){
                    for(DepartmentBetBBO departmentBetBBO:listBBO) {
                        DepartmentBetCDTO cdto = new DepartmentBetCDTO();
                        cdto.getConditions().add(Restrict.eq("departmentBetB.id",departmentBetBBO.getId()));
                        List<DepartmentBetC> listC = departmentBetCSer.findByCis(cdto);
                        List<DepartmentBetCBO> listCBO = BeanTransform.copyProperties(listC,DepartmentBetCBO.class);
                        departmentBetBBO.setDepartmentBetCBOS(listCBO);
                    }
                }
            }
        }
        return listABO;
//        DepartmentBetADTO adto = new DepartmentBetADTO();
//        List<DepartmentBetA> listA = departmentBetASer.findByCis(adto);
//        List<DepartmentBetBO> departmentBetBOS = new ArrayList<>(listA.size());
//
//        for (DepartmentBetA departmentBetA : listA) {
//            DepartmentBetBO betBO = new DepartmentBetBO();
//            betBO.setDepartmentBetABO(BeanTransform.copyProperties(departmentBetA, DepartmentBetABO.class));
//            DepartmentBetBDTO bdto = new DepartmentBetBDTO();
//            List<DepartmentBetB> listB = departmentBetBSer.findByCis(bdto);
//            List<DepartmentBetBBO> bboList = BeanTransform.copyProperties(listB, DepartmentBetBBO.class);
//            betBO.getDepartmentBetABO().setDepartmentBetBBOS(bboList);
//
//            for (DepartmentBetBBO departmentBetBBO : bboList) {
//                DepartmentBetCDTO dtoC = new DepartmentBetCDTO();
//                List<DepartmentBetC> listC = departmentBetCSer.findByCis(dtoC);
//                List<DepartmentBetCBO> cboList = BeanTransform.copyProperties(listC, DepartmentBetCBO.class);
//                departmentBetBBO.setDepartmentBetCBOS(cboList);
//
//                for (DepartmentBetCBO departmentBetCBO : cboList) {
//                    DepartmentBetDDTO dtoD = new DepartmentBetDDTO();
//                    List<DepartmentBetD> listD = departmentBetDSer.findByCis(dtoD);
//                    List<DepartmentBetDBO> dboList = BeanTransform.copyProperties(listD, DepartmentBetDBO.class);
//                    departmentBetCBO.setDepartmentBetDBOS(dboList);
//                }
//            }
//            departmentBetBOS.add(betBO);
//        }
//
//        return departmentBetBOS;
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
        //B表
        List<DepartmentBetBTO> departmentBetBTOS = departmentBetATO.getDepartmentBetBTOS();
        if (departmentBetBTOS != null) {
            for (DepartmentBetBTO departmentBetBTO : departmentBetBTOS) {
                DepartmentBetB departmentBetB = BeanTransform.copyProperties(departmentBetBTO, DepartmentBetB.class, true);
                departmentBetB.setDepartmentBetA(departmentBetA);
                //基础得分（部门总得分*目标-部门分配基础权重）
                double departmentTotalScore = 0;
                double basesScore = 0.0;
                String[] fields = new String[]{"departmentTotalScore"};
                String sql = "SELECT departmentTotalScore AS departmentTotalScore FROM royalty_systembetb WHERE department='" + departmentBetB.getDepartment() + "'";
                List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                if (systemBetBS != null && !systemBetBS.isEmpty()) {
                    departmentTotalScore = systemBetBS.get(0).getDepartmentTotalScore();
                }
                if (departmentTotalScore != 0) {
                    basesScore = departmentTotalScore * departmentBetB.getBaseWeight();
                    departmentBetB.setBasesScore(basesScore);
                }

                //部门总得分
                Double totalScore = departmentBetB.getRestrictScore() + basesScore;
                departmentBetB.setDepartmentTotalScore(totalScore);

                departmentBetB = departmentBetBSer.save(departmentBetB);
                departmentBetBS.add(departmentBetB);
                //C表
                List<DepartmentBetCTO> departmentBetCTOS = departmentBetBTO.getDepartmentBetCTOS();
                if (departmentBetCTOS != null) {
                    for (DepartmentBetCTO departmentBetCTO : departmentBetCTOS) {
                        DepartmentBetC departmentBetC = BeanTransform.copyProperties(departmentBetCTO, DepartmentBetC.class, true);
                        departmentBetC.setDepartmentBetB(departmentBetB);
                        //对赌得分
                        if (departmentTotalScore != 0) {
                            double betScore = departmentTotalScore * departmentBetC.getBetWeight();
                            departmentBetC.setBetScore(betScore);
                        }

                        departmentBetC = departmentBetCSer.save(departmentBetC);
                        departmentBetCS.add(departmentBetC);
                        //D表
                        List<DepartmentBetETO> departmentBetETOS = departmentBetCTO.getDepartmentBetETOS();
                        if (departmentBetETOS != null) {
                            for (DepartmentBetETO departmentBetETO : departmentBetETOS) {
                                DepartmentBetD departmentBetD = BeanTransform.copyProperties(departmentBetETO, DepartmentBetD.class, true);
                                departmentBetD.setDepartmentBetC(departmentBetC);
                                departmentBetD = departmentBetDSer.save(departmentBetD);
                                departmentBetDS.add(departmentBetD);
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
                //基础得分（部门总得分*目标-部门分配基础权重）
                double departmentTotalScore = 0;
                double basesScore = 0.0;
                String[] fields = new String[]{"departmentTotalScore"};
                String sql = "SELECT departmentTotalScore AS departmentTotalScore FROM royalty_systembetb WHERE department='" + departmentBetB.getDepartment() + "'";
                List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                if (systemBetBS != null && !systemBetBS.isEmpty()) {
                    departmentTotalScore = systemBetBS.get(0).getDepartmentTotalScore();
                }
                if (departmentTotalScore != 0) {
                    basesScore = departmentTotalScore * departmentBetB.getBaseWeight();
                    departmentBetB.setBasesScore(basesScore);
                }

                //部门总得分
                Double totalScore = departmentBetB.getRestrictScore() + basesScore;
                departmentBetB.setDepartmentTotalScore(totalScore);

                departmentBetB.setDepartmentBetA(departmentBetA);
                departmentBetBSer.update(departmentBetB);
                departmentBetBS.add(departmentBetB);
                //C表
                List<DepartmentBetC> departmentBetCS = new ArrayList<>();
                List<DepartmentBetCTO> departmentBetCTOS = departmentBetBTO.getDepartmentBetCTOS();
                if (departmentBetCTOS != null) {
                    for (DepartmentBetCTO departmentBetCTO : departmentBetCTOS) {
                        DepartmentBetC departmentBetC = BeanTransform.copyProperties(departmentBetCTO, DepartmentBetC.class, true);
                        departmentBetC.setDepartmentBetB(departmentBetB);

                        //对赌得分
                        if (departmentTotalScore != 0) {
                            double betScore = departmentTotalScore * departmentBetC.getBetWeight();
                            departmentBetC.setBetScore(betScore);
                        }

                        departmentBetCSer.update(departmentBetC);
                        departmentBetCS.add(departmentBetC);
                        //D表
                        List<DepartmentBetD> departmentBetDS = new ArrayList<>();
                        List<DepartmentBetETO> departmentBetETOS = departmentBetCTO.getDepartmentBetETOS();

                        if (departmentBetETOS != null) {
                            for (DepartmentBetETO departmentBetETO : departmentBetETOS) {
                                DepartmentBetD departmentBetD = BeanTransform.copyProperties(departmentBetETO, DepartmentBetD.class, true);
                                departmentBetD.setDepartmentBetC(departmentBetC);
                                departmentBetDSer.update(departmentBetD);
                                departmentBetDS.add(departmentBetD);
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
        DepartmentBetADTO departmentBetADTO = new DepartmentBetADTO();
        departmentBetADTO.getConditions().add(Restrict.eq("id", id));
        List<DepartmentBetA> aList = departmentBetASer.findByCis(departmentBetADTO);
        if (aList != null && aList.size() > 0) {

            List<String> aIdList = aList.stream().map(DepartmentBetA::getId).collect(Collectors.toList());
            String[] aids = new String[aIdList.size()];
            aids = aIdList.toArray(aids);
            //查询B表对应的数据
            DepartmentBetBDTO departmentBetBDTO = new DepartmentBetBDTO();
            departmentBetBDTO.getConditions().add(Restrict.eq("departmentBetA.id", aids));
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
                        departmentBetDSer.remove(dList);
                    }
                    departmentBetCSer.remove(cList);
                }

                departmentBetBSer.remove(bList);
            }
        }
    }
    //基础得分（部门总得分*目标-部门分配基础权重）
//            SystemBetBDTO dto = new SystemBetBDTO();
//            dto.getConditions().add(Restrict.eq("departmentTotalScore",dto.getDepartmentTotalScore()));
//            List<SystemBetB> systemBetBS = systemBetBSer.findByCis(dto);
//            Double departmentTotalScore = 0.0;
//            for(SystemBetB systemBetB:systemBetBS){
//                departmentTotalScore =  systemBetB.getDepartmentTotalScore();
//            }
//            Double basesScore = departmentTotalScore*departmentBetATO.getBaseWeight();
    //对赌得分（部门总得分*目标-部门分配对赌权重）
    //制约得分
    //部门总得分（制约得分+基础得分）
}