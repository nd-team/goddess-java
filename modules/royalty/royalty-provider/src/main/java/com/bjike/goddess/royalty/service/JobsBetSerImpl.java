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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 岗位间对赌表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class JobsBetSerImpl extends ServiceImpl<JobsBet, JobsBetDTO> implements JobsBetSer {
    @Autowired
    private JobsBetASer jobsBetASer;
    @Autowired
    private JobsBetBSer jobsBetBSer;
    @Autowired
    private JobsBetCSer jobsBetCSer;
    @Autowired
    private JobsBetDSer jobsBetDSer;
    @Autowired
    private JobsBetESer jobsBetESer;
    @Autowired
    private SystemBetBSer systemBetBSer;
    @Autowired
    private SystemBetASer systemBetASer;
    @Autowired
    private SystemBetCSer systemBetCSer;
    @Autowired
    private SystemBetDSer systemBetDSer;
    @Autowired
    private DepartmentBetASer departmentBetASer;
    @Autowired
    private DepartmentBetBSer departmentBetBSer;
    @Autowired
    private DepartmentBetCSer departmentBetCSer;
    @Autowired
    private DepartmentBetDSer departmentBetDSer;
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
    public Long count(JobsBetEDTO dto) throws SerException {
        Long count = jobsBetESer.count(dto);
        return count;
    }

    @Override
    public JobsBetABO getOne(String id) throws SerException {
        JobsBetA jobsBetA = jobsBetASer.findById(id);
        JobsBetABO listABO = BeanTransform.copyProperties(jobsBetA, JobsBetABO.class);
        if (listABO != null) {
            JobsBetBDTO bdto = new JobsBetBDTO();
            bdto.getConditions().add(Restrict.eq("jobsBetA.id", listABO.getId()));
            List<JobsBetB> listB = jobsBetBSer.findByCis(bdto);
            List<JobsBetBBO> listBBO = BeanTransform.copyProperties(listB, JobsBetBBO.class);
            listABO.setJobsBetBBOS(listBBO);
            if (listBBO != null) {
                for (JobsBetBBO jobsBetBBO : listBBO) {
                    JobsBetCDTO cdto = new JobsBetCDTO();
                    cdto.getConditions().add(Restrict.eq("jobsBetB.id", jobsBetBBO.getId()));
                    List<JobsBetC> listC = jobsBetCSer.findByCis(cdto);
                    List<JobsBetCBO> listCBO = BeanTransform.copyProperties(listC, JobsBetCBO.class);
                    jobsBetBBO.setJobsBetCBOS(listCBO);
                    if (listCBO != null) {
                        for (JobsBetCBO jobsBetCBO : listCBO) {
                            JobsBetDDTO ddto = new JobsBetDDTO();
                            ddto.getConditions().add(Restrict.eq("jobsBetC.id", jobsBetCBO.getId()));
                            List<JobsBetD> listD = jobsBetDSer.findByCis(ddto);
                            List<JobsBetDBO> listDBO = BeanTransform.copyProperties(listD, JobsBetDBO.class);
                            jobsBetCBO.setJobsBetDBOS(listDBO);
                            if (listDBO != null) {
                                for (JobsBetDBO jobsBetDBO : listDBO) {
                                    JobsBetEDTO edto = new JobsBetEDTO();
                                    edto.getConditions().add(Restrict.eq("jobsBetD.id", jobsBetDBO.getId()));
                                    List<JobsBetE> listE = jobsBetESer.findByCis(edto);
                                    List<JobsBetEBO> listEBO = BeanTransform.copyProperties(listE, JobsBetEBO.class);
                                    jobsBetDBO.setJobsBetEBOS(listEBO);
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
    public List<JobsBetABO> list(JobsBetADTO dto) throws SerException {
        checkSeeIdentity();
        List<JobsBetA> listA = jobsBetASer.findByCis(dto);
        List<JobsBetABO> listABO = BeanTransform.copyProperties(listA, JobsBetABO.class);
        if (listABO != null) {
            for (JobsBetABO jobsBetABO : listABO) {
                JobsBetBDTO bdto = new JobsBetBDTO();
                bdto.getConditions().add(Restrict.eq("jobsBetA.id", jobsBetABO.getId()));
                List<JobsBetB> listB = jobsBetBSer.findByCis(bdto);
                List<JobsBetBBO> listBBO = BeanTransform.copyProperties(listB, JobsBetBBO.class);
                jobsBetABO.setJobsBetBBOS(listBBO);
                if (listBBO != null) {
                    for (JobsBetBBO jobsBetBBO : listBBO) {
                        JobsBetCDTO cdto = new JobsBetCDTO();
                        cdto.getConditions().add(Restrict.eq("jobsBetB.id", jobsBetBBO.getId()));
                        List<JobsBetC> listC = jobsBetCSer.findByCis(cdto);
                        List<JobsBetCBO> listCBO = BeanTransform.copyProperties(listC, JobsBetCBO.class);
                        jobsBetBBO.setJobsBetCBOS(listCBO);
                        if (listCBO != null) {
                            for (JobsBetCBO jobsBetCBO : listCBO) {
                                JobsBetDDTO ddto = new JobsBetDDTO();
                                ddto.getConditions().add(Restrict.eq("jobsBetC.id", jobsBetCBO.getId()));
                                List<JobsBetD> listD = jobsBetDSer.findByCis(ddto);
                                List<JobsBetDBO> listDBO = BeanTransform.copyProperties(listD, JobsBetDBO.class);
                                jobsBetCBO.setJobsBetDBOS(listDBO);
                                if (listDBO != null) {
                                    for (JobsBetDBO jobsBetDBO : listDBO) {
                                        JobsBetEDTO edto = new JobsBetEDTO();
                                        edto.getConditions().add(Restrict.eq("jobsBetD.id", jobsBetDBO.getId()));
                                        List<JobsBetE> listE = jobsBetESer.findByCis(edto);
                                        List<JobsBetEBO> listEBO = BeanTransform.copyProperties(listE, JobsBetEBO.class);
                                        jobsBetDBO.setJobsBetEBOS(listEBO);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return listABO;
//        JobsBetADTO adto = new JobsBetADTO();
//        List<JobsBetA> listA = jobsBetASer.findByCis(adto);
//        List<JobsBetBO> jobsBetBOS = new ArrayList<>(listA.size());
//        for (JobsBetA jobsBetA : listA) {
//            JobsBetBO betBO = new JobsBetBO();
//            betBO.setJobsBetABO(BeanTransform.copyProperties(jobsBetA, JobsBetABO.class));
//            JobsBetBDTO bdto = new JobsBetBDTO();
//            List<JobsBetB> listB = jobsBetBSer.findByCis(bdto);
//            List<JobsBetBBO> bboList = BeanTransform.copyProperties(listB, JobsBetBBO.class);
//            betBO.getJobsBetABO().setJobsBetBBOS(bboList);
//
//            for (JobsBetBBO jobsBetBBO : bboList) {
//                JobsBetCDTO dtoC = new JobsBetCDTO();
//                List<JobsBetC> listC = jobsBetCSer.findByCis(dtoC);
//                List<JobsBetCBO> cboList = BeanTransform.copyProperties(listC, JobsBetCBO.class);
//                jobsBetBBO.setJobsBetCBOS(cboList);
//
//                for (JobsBetCBO jobsBetCBO : cboList) {
//                    JobsBetDDTO dtoD = new JobsBetDDTO();
//                    List<JobsBetD> listD = jobsBetDSer.findByCis(dtoD);
//                    List<JobsBetDBO> dboList = BeanTransform.copyProperties(listD, JobsBetDBO.class);
//                    jobsBetCBO.setJobsBetDBOS(dboList);
//
//                    for (JobsBetDBO jobsBetDBO : dboList) {
//                        JobsBetEDTO dtoE = new JobsBetEDTO();
//                        List<JobsBetE> listE = jobsBetESer.findByCis(dtoE);
//                        List<JobsBetEBO> eboList = BeanTransform.copyProperties(listE, JobsBetEBO.class);
//                        jobsBetDBO.setJobsBetEBOS(eboList);
//                    }
//                }
//                jobsBetBOS.add(betBO);
//            }
//        }

//        return jobsBetBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insert(JobsBetATO jobsBetATO) throws SerException {
        checkAddIdentity();
        JobsBetA jobsBetA = BeanTransform.copyProperties(jobsBetATO, JobsBetA.class, true);
        jobsBetASer.save(jobsBetA);

        List<JobsBetB> jobsBetBS = new ArrayList<>();
        List<JobsBetC> jobsBetCS = new ArrayList<>();
        List<JobsBetD> jobsBetDS = new ArrayList<>();
        List<JobsBetE> jobsBetES = new ArrayList<>();
        List<JobsBetBTO> jobsBetBTOS = jobsBetATO.getJobsBetBTOS();
        if (jobsBetBTOS != null) {
            for (JobsBetBTO jobsBetBTO : jobsBetBTOS) {
                JobsBetB jobsBetB = BeanTransform.copyProperties(jobsBetBTO, JobsBetB.class, true);
                jobsBetB.setJobsBetA(jobsBetA);
                jobsBetB = jobsBetBSer.save(jobsBetB);
                jobsBetBS.add(jobsBetB);

                List<JobsBetCTO> jobsBetCTOS = jobsBetBTO.getJobsBetCTOS();
                if (jobsBetCTOS != null) {
                    for (JobsBetCTO jobsBetCTO : jobsBetCTOS) {
                        JobsBetC jobsBetC = BeanTransform.copyProperties(jobsBetCTO, JobsBetC.class, true);
                        jobsBetC.setJobsBetB(jobsBetB);

                        //基础得分（部门总得分*目标-部门分配基础权重）
                        double departmentTotalScore = 0;
                        double basesScore = 0.0;
                        String[] fields = new String[]{"departmentTotalScore"};
                        String sql = "SELECT departmentTotalScore AS departmentTotalScore FROM royalty_systembetb WHERE department='" + jobsBetB.getDepartment() + "'";
                        List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                        if (systemBetBS != null && !systemBetBS.isEmpty()) {
                            departmentTotalScore = systemBetBS.get(0).getDepartmentTotalScore();
                        }
                        if (departmentTotalScore != 0) {
                            basesScore = departmentTotalScore * jobsBetC.getBaseWeight();
                            jobsBetC.setBasesScore(basesScore);
                        }
                        //部门总得分
                        double totalScore = jobsBetC.getRestrictScore() + basesScore;
                        jobsBetC.setDepartmentTotalScore(totalScore);

                        jobsBetC = jobsBetCSer.save(jobsBetC);
                        jobsBetCS.add(jobsBetC);

                        List<JobsBetETO> jobsBetETOS = jobsBetCTO.getJobsBetETOS();
                        if (jobsBetETOS != null) {
                            for (JobsBetETO jobsBetETO : jobsBetETOS) {
                                JobsBetD jobsBetD = BeanTransform.copyProperties(jobsBetETO, JobsBetD.class, true);
                                jobsBetD.setJobsBetC(jobsBetC);

                                //对赌得分
                                if (departmentTotalScore != 0) {
                                    double betScore = departmentTotalScore * jobsBetD.getBetWeight();
                                    jobsBetD.setBetScore(betScore);
                                }

                                jobsBetD = jobsBetDSer.save(jobsBetD);
                                jobsBetDS.add(jobsBetD);

                                List<JobsBetFTO> jobsBetFTOS = jobsBetETO.getJobsBetFTOS();
                                if (jobsBetFTOS != null) {
                                    for (JobsBetFTO jobsBetFTO : jobsBetFTOS) {
                                        JobsBetE jobsBetE = BeanTransform.copyProperties(jobsBetFTO, JobsBetE.class, true);
                                        jobsBetE.setJobsBetD(jobsBetD);
                                        jobsBetE = jobsBetESer.save(jobsBetE);
                                        jobsBetES.add(jobsBetE);
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
    public void edit(JobsBetATO jobsBetATO) throws SerException {
        checkAddIdentity();
        JobsBetA jobsBetA = jobsBetASer.findById(jobsBetATO.getId());
        BeanTransform.copyProperties(jobsBetATO, jobsBetA, true);
        jobsBetASer.update(jobsBetA);

        List<JobsBetB> jobsBetBS = new ArrayList<>();
        List<JobsBetC> jobsBetCS = new ArrayList<>();
        List<JobsBetD> jobsBetDS = new ArrayList<>();
        List<JobsBetE> jobsBetES = new ArrayList<>();

        //先删掉B表对应数据
        JobsBetBDTO jobsBetBDTO = new JobsBetBDTO();
        jobsBetBDTO.getConditions().add(Restrict.eq("jobsBetA.id", jobsBetA.getId()));
        List<JobsBetB> bList = jobsBetBSer.findByCis(jobsBetBDTO);
        if (bList != null && bList.size() > 0) {
            //查询对应C表的数据，先删除
            List<String> bIdList = bList.stream().map(JobsBetB::getId).collect(Collectors.toList());
            String[] bids = new String[bIdList.size()];
            bids = bIdList.toArray(bids);
            JobsBetCDTO jobsBetCDTO = new JobsBetCDTO();
            jobsBetCDTO.getConditions().add(Restrict.eq("jobsBetB.id", bids));
            List<JobsBetC> cList = jobsBetCSer.findByCis(jobsBetCDTO);
            if (cList != null && cList.size() > 0) {
                //查询对应D表的数据，先删除
                List<String> cIdList = cList.stream().map(JobsBetC::getId).collect(Collectors.toList());
                String[] cids = new String[cIdList.size()];
                cids = cIdList.toArray(cids);
                JobsBetDDTO jobsBetDDTO = new JobsBetDDTO();
                jobsBetDDTO.getConditions().add(Restrict.in("jobsBetC.id", cids));
                List<JobsBetD> dList = jobsBetDSer.findByCis(jobsBetDDTO);
                if (dList != null && dList.size() > 0) {
                    //查询对应E表的数据，先删除
                    List<String> dIdList = dList.stream().map(JobsBetD::getId).collect(Collectors.toList());
                    String[] dids = new String[dIdList.size()];
                    dids = dIdList.toArray(dids);
                    JobsBetEDTO jobsBetEDTO = new JobsBetEDTO();
                    jobsBetEDTO.getConditions().add(Restrict.in("jobsBetD.id", dids));
                    List<JobsBetE> eList = jobsBetESer.findByCis(jobsBetEDTO);
                    if (eList != null && eList.size() > 0) {
                        jobsBetESer.remove(eList);
                    }
                    jobsBetDSer.remove(dList);
                }
                jobsBetCSer.remove(cList);
            }
            jobsBetBSer.remove(bList);
        }
        List<JobsBetBTO> jobsBetBTOS = jobsBetATO.getJobsBetBTOS();

        if (jobsBetBTOS != null) {
            for (JobsBetBTO jobsBetBTO : jobsBetBTOS) {
                JobsBetB jobsBetB = BeanTransform.copyProperties(jobsBetBTO, JobsBetB.class, true);
                jobsBetB.setJobsBetA(jobsBetA);

                jobsBetB.setId(null);
                jobsBetBSer.update(jobsBetB);
                jobsBetBS.add(jobsBetB);

                List<JobsBetCTO> jobsBetCTOS = jobsBetBTO.getJobsBetCTOS();
                if (jobsBetCTOS != null) {
                    for (JobsBetCTO jobsBetCTO : jobsBetCTOS) {
                        JobsBetC jobsBetC = BeanTransform.copyProperties(jobsBetCTO, JobsBetC.class, true);
                        jobsBetC.setJobsBetB(jobsBetB);

                        //基础得分（部门总得分*目标-部门分配基础权重）
                        double departmentTotalScore = 0;
                        double basesScore = 0.0;
                        String[] fields = new String[]{"departmentTotalScore"};
                        String sql = "SELECT departmentTotalScore AS departmentTotalScore FROM royalty_systembetb WHERE department='" + jobsBetB.getDepartment() + "'";
                        List<SystemBetB> systemBetBS = systemBetBSer.findBySql(sql, SystemBetB.class, fields);
                        if (systemBetBS != null && !systemBetBS.isEmpty()) {
                            departmentTotalScore = systemBetBS.get(0).getDepartmentTotalScore();
                        }
                        if (departmentTotalScore != 0) {
                            basesScore = departmentTotalScore * jobsBetC.getBaseWeight();
                            jobsBetC.setBasesScore(basesScore);
                        }
                        //部门总得分
                        double totalScore = jobsBetC.getRestrictScore() + basesScore;
                        jobsBetC.setDepartmentTotalScore(totalScore);

                        jobsBetCSer.update(jobsBetC);
                        jobsBetCS.add(jobsBetC);

                        List<JobsBetETO> jobsBetETOS = jobsBetCTO.getJobsBetETOS();
                        if (jobsBetETOS != null) {
                            for (JobsBetETO jobsBetETO : jobsBetETOS) {
                                JobsBetD jobsBetD = BeanTransform.copyProperties(jobsBetETO, JobsBetD.class, true);
                                jobsBetD.setJobsBetC(jobsBetC);

                                //对赌得分
                                if (departmentTotalScore != 0) {
                                    double betScore = departmentTotalScore * jobsBetD.getBetWeight();
                                    jobsBetD.setBetScore(betScore);
                                }

                                jobsBetDSer.update(jobsBetD);
                                jobsBetDS.add(jobsBetD);

                                List<JobsBetFTO> jobsBetFTOS = jobsBetETO.getJobsBetFTOS();
                                if (jobsBetFTOS != null) {
                                    for (JobsBetFTO jobsBetFTO : jobsBetFTOS) {
                                        JobsBetE jobsBetE = BeanTransform.copyProperties(jobsBetFTO, JobsBetE.class, true);
                                        jobsBetE.setJobsBetD(jobsBetD);
                                        jobsBetESer.update(jobsBetE);
                                        jobsBetES.add(jobsBetE);
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
        JobsBetE jobsBetE = jobsBetESer.findById(id);
        if (jobsBetE == null) {
            throw new SerException("该对象不存在");
        }
        jobsBetESer.remove(id);
        List<JobsBetD> dList = jobsBetDSer.findAll();
        List<JobsBetC> cList = jobsBetCSer.findAll();
        List<JobsBetB> bList = jobsBetBSer.findAll();
        List<JobsBetA> aList = jobsBetASer.findAll();

        Set<String> dids = new HashSet<>();
        Set<String> cids = new HashSet<>();
        Set<String> bids = new HashSet<>();
        Set<String> aids = new HashSet<>();

        for (JobsBetE e : jobsBetESer.findAll()) {
            dids.add(e.getJobsBetD().getId());
        }
        for (JobsBetD d : dList) {
            if (!dids.contains(d.getId())) {
                jobsBetDSer.remove(d.getId());
            }
        }
        for (JobsBetD d1 : jobsBetDSer.findAll()) {
            cids.add(d1.getJobsBetC().getId());
        }
        for (JobsBetC c : cList) {
            if (!cids.contains(c.getId())) {
                jobsBetCSer.remove(id);
            }
        }
        for (JobsBetC c1 : jobsBetCSer.findAll()) {
            bids.add(c1.getJobsBetB().getId());
        }
        for (JobsBetB b : bList) {
            if (!bids.contains(b.getId())) {
                jobsBetBSer.remove(id);
            }
        }
        for (JobsBetB b1 : jobsBetBSer.findAll()) {
            aids.add(b1.getJobsBetA().getId());
        }
        for (JobsBetA a : aList) {
            if (!aids.contains(a.getId())) {
                jobsBetASer.remove(id);
            }
        }


    }

    @Override
    public List<ManageCommissionBO> collect(CollectTO to) throws SerException {
        SystemBetADTO systemBetADTO = new SystemBetADTO();
        if (null != to.getProjectName()) {
            systemBetADTO.getConditions().add(Restrict.in("projectName", to.getProjectName()));
        }

        return manageCollect(systemBetADTO);
    }

    public List<ManageCommissionBO> manageCollect(SystemBetADTO systemBetADTO) throws SerException {
        String departmentBetBId = null;
        List<SystemBetA> systemBetAS = systemBetASer.findByCis(systemBetADTO);
        List<ManageCommissionBO> list = new ArrayList<>();
        for (SystemBetA systemBetA : systemBetAS) {
            SystemBetBDTO systemBetBDTO = new SystemBetBDTO();
            systemBetBDTO.getConditions().add(Restrict.eq("systemBetA.id", systemBetA.getId()));
            String[] ids = new String[]{systemBetA.getId()};
            List<DepartmentBetBBO> departmentBetBBOS = null;
            for (String id : ids) {
                String[] feilds = new String[]{"department"};
                String sql = "SELECT department AS department FROM royalty_systembetb WHERE systemBetA_id='" + id + "'";
                departmentBetBBOS = super.findBySql(sql, DepartmentBetBBO.class, feilds);
            }
            Set<String> departments = new HashSet<>();
            if (departmentBetBBOS != null && !departmentBetBBOS.isEmpty()) {
                for (DepartmentBetBBO departmentBetBBO : departmentBetBBOS) {
                    departments.add(departmentBetBBO.getDepartment());

                }
            }
            systemBetBDTO.getConditions().add(Restrict.in("department", departments));
            for (String s : departments) {
                DepartmentBetBDTO departmentBetBDTO = new DepartmentBetBDTO();
                departmentBetBDTO.getConditions().add(Restrict.eq("department", s));

                JobsBetBDTO jobsBetBDTO = new JobsBetBDTO();
                jobsBetBDTO.getConditions().add(Restrict.eq("department", s));
                List<JobsBetB> jobsBetBS = jobsBetBSer.findByCis(jobsBetBDTO);
                for (JobsBetB jobsBetB : jobsBetBS) {

                    JobsBetCDTO jobsBetCDTO = new JobsBetCDTO();
                    jobsBetCDTO.getConditions().add(Restrict.eq("jobsBetB.id", jobsBetB.getId()));
                    List<JobsBetC> jobsBetCS = jobsBetCSer.findByCis(jobsBetCDTO);
                    for (JobsBetC jobsBetC : jobsBetCS) {

                        JobsBetDDTO jobsBetDDTO = new JobsBetDDTO();
                        jobsBetDDTO.getConditions().add(Restrict.eq("jobsBetC.id", jobsBetC.getId()));
                        List<JobsBetD> jobsBetDS = jobsBetDSer.findByCis(jobsBetDDTO);
                        for (JobsBetD jobsBetD : jobsBetDS) {

                            JobsBetEDTO jobsBetEDTO = new JobsBetEDTO();
                            jobsBetEDTO.getConditions().add(Restrict.eq("jobsBetD.id", jobsBetD.getId()));
                            List<JobsBetE> jobsBetES = jobsBetESer.findByCis(jobsBetEDTO);
                            for (JobsBetE jobsBetE : jobsBetES) {
                                ManageCommissionBO bo = new ManageCommissionBO();
                                bo.setProjectGroup(jobsBetB.getDepartment());
                                bo.setJobs(jobsBetC.getJobs());
                                bo.setJobsBaseWeight(jobsBetC.getBaseWeight());
                                bo.setJobsBasesScore(jobsBetC.getBasesScore());
                                bo.setJobsRestrictScore(jobsBetC.getRestrictScore());
                                bo.setJobsTotalScore(jobsBetC.getDepartmentTotalScore());
                                bo.setJobsBetWeight(jobsBetD.getBetWeight());
                                bo.setJobsIndexNum(jobsBetD.getIndexNum());
                                bo.setJobsIndexName(jobsBetD.getIndexName());
                                bo.setJobsConfirmTargetValue(jobsBetD.getConfirmTargetValue());
                                bo.setJobsStandard(jobsBetD.getStandard());
                                bo.setJobsBetScore(jobsBetD.getBetScore());
                                bo.setUnmetAllocationJobs(jobsBetE.getUnmetAllocationJobs());
                                bo.setJobsUnmetAllocation(jobsBetE.getUnmetAllocation());
                                list.add(bo);
                            }

                        }

                    }

                }

                List<DepartmentBetB> departmentBetBS = departmentBetBSer.findByCis(departmentBetBDTO);
                if (departmentBetBS != null && !departmentBetBS.isEmpty()) {
                    departmentBetBId = departmentBetBS.get(0).getId();
                    for (DepartmentBetB departmentBetB : departmentBetBS) {

                        DepartmentBetCDTO departmentBetCDTO = new DepartmentBetCDTO();
                        departmentBetCDTO.getConditions().add(Restrict.eq("departmentBetB.id", departmentBetBId));
                        List<DepartmentBetC> departmentBetCS = departmentBetCSer.findByCis(departmentBetCDTO);
                        for (DepartmentBetC departmentBetC : departmentBetCS) {

                            DepartmentBetDDTO departmentBetDDTO = new DepartmentBetDDTO();
                            departmentBetDDTO.getConditions().add(Restrict.eq("departmentBetC.id", departmentBetC.getId()));
                            List<DepartmentBetD> departmentBetDS = departmentBetDSer.findByCis(departmentBetDDTO);
                            for (DepartmentBetD departmentBetD : departmentBetDS) {
                                ManageCommissionBO bo = new ManageCommissionBO();
                                bo.setDepartment(departmentBetB.getDepartment());
                                bo.setDepartmentBaseWeight(departmentBetB.getBaseWeight());
                                bo.setDepartmentBasesScore(departmentBetB.getBasesScore());
                                bo.setDepartmentRestrictScore(departmentBetB.getRestrictScore());
                                bo.setDepartmentTotalScore(departmentBetB.getDepartmentTotalScore());
                                bo.setDepartmentBetWeight(departmentBetC.getBetWeight());
                                bo.setDepartmentIndexNum(departmentBetC.getIndexNum());
                                bo.setDepartmentIndexName(departmentBetC.getIndexName());
                                bo.setDepartmentConfirmTargetValue(departmentBetC.getConfirmTargetValue());
                                bo.setDepartmentStandard(departmentBetC.getStandard());
                                bo.setDepartmentBetScore(departmentBetC.getBetScore());
                                bo.setUnmetAllocationDepartment(departmentBetD.getUnmetAllocationDepartment());
                                bo.setDepartmentUnmetAllocation(departmentBetD.getUnmetAllocation());
                                list.add(bo);
                            }

                        }
                    }


                }
                List<SystemBetB> systemBetBS = systemBetBSer.findByCis(systemBetBDTO);

                for (SystemBetB systemBetB : systemBetBS) {

                    SystemBetCDTO systemBetCDTO = new SystemBetCDTO();
                    systemBetCDTO.getConditions().add(Restrict.eq("systemBetB.id", systemBetB.getId()));
                    List<SystemBetC> systemBetCS = systemBetCSer.findByCis(systemBetCDTO);
                    for (SystemBetC systemBetC : systemBetCS) {

                        SystemBetDDTO systemBetDDTO = new SystemBetDDTO();
                        systemBetDDTO.getConditions().add(Restrict.eq("systemBetC.id", systemBetC.getId()));
                        List<SystemBetD> systemBetDS = systemBetDSer.findByCis(systemBetDDTO);
                        for (SystemBetD systemBetD : systemBetDS) {

//                            DepartmentBetBDTO departmentBetBDTO = new DepartmentBetBDTO();
//                            departmentBetBDTO.getConditions().add(Restrict.eq("departmentBet", departmentBetA.getId()));
//                            List<DepartmentBetB> departmentBetBS = departmentBetBSer.findByCis(departmentBetBDTO);
//
//                            for (DepartmentBetB departmentBetB : departmentBetBS) {


                            ManageCommissionBO bo = new ManageCommissionBO();
                            bo.setProjectName(systemBetA.getProjectName());
                            bo.setScore(systemBetA.getScore());
                            bo.setSystem(systemBetB.getSystem());
                            bo.setSystemBaseWeight(systemBetB.getBaseWeight());
                            bo.setSystemBasesScore(systemBetB.getBasesScore());
                            bo.setSystemRestrictScore(systemBetB.getRestrictScore());
                            bo.setSystemTotalScore(systemBetB.getDepartmentTotalScore());
                            bo.setSystemBetWeight(systemBetC.getBetWeight());
                            bo.setSystemIndexNum(systemBetC.getIndexNum());
                            bo.setSystemIndexName(systemBetC.getIndexName());
                            bo.setSystemConfirmTargetValue(systemBetC.getConfirmTargetValue());
                            bo.setSystemStandard(systemBetC.getStandard());
                            bo.setSystemBetScore(systemBetC.getBetScore());
                            bo.setUnmetAllocationSystem(systemBetD.getUnmetAllocationSystem());
                            bo.setSystemUnmetAllocation(systemBetD.getUnmetAllocation());
//                                bo.setDepartment(departmentBetB.getDepartment());
//                                bo.setDepartmentBaseWeight(departmentBetB.getBaseWeight());
//                                bo.setDepartmentBasesScore(departmentBetB.getBasesScore());
//                                bo.setDepartmentRestrictScore(departmentBetB.getRestrictScore());
//                                bo.setDepartmentTotalScore(departmentBetB.getDepartmentTotalScore());

                            list.add(bo);
                        }

                    }


                }

            }

        }

        Double systemBasesScore = 0.0; //基础得分
        Double systemBetScore = 0.0; //对赌得分
        Double systemRestrictScore = 0.0; //制约得分
        Double systemTotalScore = 0.0;//部门总得分
        Double departmentBasesScore = 0.0; //基础得分
        Double departmentBetScore = 0.0; //对赌得分
        Double departmentRestrictScore = 0.0; //制约得分
        Double departmentTotalScore = 0.0;//部门总得分
        Double jobsBasesScore = 0.0; //基础得分
        Double jobsBetScore = 0.0; //对赌得分
        Double jobsRestrictScore = 0.0; //制约得分
        Double jobsTotalScore = 0.0;//部门总得分
        if (list != null) {
            systemBasesScore = list.stream().filter(p -> p.getSystemBasesScore() != null).mapToDouble(p -> p.getSystemBasesScore()).sum();
            systemBetScore = list.stream().filter(p -> p.getSystemBetScore() != null).mapToDouble(p -> p.getSystemBetScore()).sum();
            systemRestrictScore = list.stream().filter(p -> p.getSystemRestrictScore() != null).mapToDouble(p -> p.getSystemRestrictScore()).sum();
            systemTotalScore = list.stream().filter(p -> p.getSystemTotalScore() != null).mapToDouble(p -> p.getSystemTotalScore()).sum();

            departmentBasesScore = list.stream().filter(p -> p.getDepartmentBasesScore() != null).mapToDouble(p -> p.getDepartmentBasesScore()).sum();
            departmentBetScore = list.stream().filter(p -> p.getDepartmentBetScore() != null).mapToDouble(p -> p.getDepartmentBetScore()).sum();
            departmentRestrictScore = list.stream().filter(p -> p.getDepartmentRestrictScore() != null).mapToDouble(p -> p.getDepartmentRestrictScore()).sum();
            departmentTotalScore = list.stream().filter(p -> p.getDepartmentTotalScore() != null).mapToDouble(p -> p.getDepartmentTotalScore()).sum();

            jobsBasesScore = list.stream().filter(p -> p.getJobsBasesScore() != null).mapToDouble(p -> p.getJobsBasesScore()).sum();
            jobsBetScore = list.stream().filter(p -> p.getJobsBetScore() != null).mapToDouble(p -> p.getJobsBetScore()).sum();
            jobsRestrictScore = list.stream().filter(p -> p.getJobsRestrictScore() != null).mapToDouble(p -> p.getJobsRestrictScore()).sum();
            jobsTotalScore = list.stream().filter(p -> p.getJobsTotalScore() != null).mapToDouble(p -> p.getJobsTotalScore()).sum();


            ManageCommissionBO totalBO = new ManageCommissionBO("合计分值", systemBasesScore, systemBetScore, systemRestrictScore, systemTotalScore,
                    departmentBasesScore, departmentBetScore, departmentRestrictScore, departmentTotalScore,
                    jobsBasesScore, jobsBetScore, jobsRestrictScore, jobsTotalScore);
            list.add(totalBO);
        }

        return list;
    }
}