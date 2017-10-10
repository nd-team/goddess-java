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
    private JobsBetFSer jobsBetFSer;
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
                                    if (listEBO != null) {
                                        for (JobsBetEBO jobsBetEBO : listEBO) {
                                            JobsBetFDTO fdto = new JobsBetFDTO();
                                            fdto.getConditions().add(Restrict.eq("jobsBetE.id", jobsBetEBO.getId()));
                                            List<JobsBetF> listF = jobsBetFSer.findByCis(fdto);
                                            List<JobsBetFBO> listFBO = BeanTransform.copyProperties(listF, JobsBetFBO.class);
                                            jobsBetEBO.setJobsBetFBOS(listFBO);
                                        }
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
                                        if (listEBO != null) {
                                            for (JobsBetEBO jobsBetEBO : listEBO) {
                                                JobsBetFDTO fdto = new JobsBetFDTO();
                                                fdto.getConditions().add(Restrict.eq("jobsBetE.id", jobsBetEBO.getId()));
                                                List<JobsBetF> listF = jobsBetFSer.findByCis(fdto);
                                                List<JobsBetFBO> listFBO = BeanTransform.copyProperties(listF, JobsBetFBO.class);
                                                jobsBetEBO.setJobsBetFBOS(listFBO);
                                            }
                                        }
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
    public void insert(JobsBetATO jobsBetATO) throws SerException {
        checkAddIdentity();
        JobsBetA jobsBetA = BeanTransform.copyProperties(jobsBetATO, JobsBetA.class, true);
        jobsBetASer.save(jobsBetA);

        List<JobsBetB> jobsBetBS = new ArrayList<>();
        List<JobsBetC> jobsBetCS = new ArrayList<>();
        List<JobsBetD> jobsBetDS = new ArrayList<>();
        List<JobsBetE> jobsBetES = new ArrayList<>();
        List<JobsBetF> jobsBetFS = new ArrayList<>();
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

                        jobsBetC = jobsBetCSer.save(jobsBetC);
                        jobsBetCS.add(jobsBetC);

                        List<JobsBetETO> jobsBetETOS = jobsBetCTO.getJobsBetETOS();
                        if (jobsBetETOS != null) {
                            for (JobsBetETO jobsBetETO : jobsBetETOS) {
                                JobsBetD jobsBetD = BeanTransform.copyProperties(jobsBetETO, JobsBetD.class, true);
                                jobsBetD.setJobsBetC(jobsBetC);
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
                                        " systemTotalScorePractice as systemTotalScorePractice FROM royalty_systembetb WHERE system='" + jobsBetB.getSystem() + "' ";
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
                                basesScore = systemTotalScore * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScore(basesScore);
                                //计划基础得分（体系计划总得分*目标-部门分配基础权重）
                                basesScorePlan = systemTotalScorePlan * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScorePlan(basesScorePlan);
                                //实际基础得分（体系实际总得分*目标-部门分配基础权重）
                                basesScorePractice = systemTotalScorePractice * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScorePractice(basesScorePractice);

                                //目标总得分（目标制约得分+目标基础得分）
                                Double totalScore = jobsBetD.getRestrictScore() + basesScore;
                                jobsBetD.setTotalScore(totalScore);
                                //计划总得分（计划制约得分+计划基础得分）
                                Double totalScorePlan = jobsBetD.getRestrictScorePlan() + basesScorePlan;
                                jobsBetD.setTotalScorePlan(totalScorePlan);
                                //实际总得分（实际制约得分+实际基础得分）
                                Double totalScorePractice = jobsBetD.getTotalScorePractice() + basesScorePractice;
                                jobsBetD.setTotalScorePractice(totalScorePractice);
                                jobsBetD = jobsBetDSer.save(jobsBetD);
                                jobsBetDS.add(jobsBetD);

                                List<JobsBetFTO> jobsBetFTOS = jobsBetETO.getJobsBetFTOS();
                                if (jobsBetFTOS != null) {
                                    for (JobsBetFTO jobsBetFTO : jobsBetFTOS) {
                                        JobsBetE jobsBetE = BeanTransform.copyProperties(jobsBetFTO, JobsBetE.class, true);
                                        jobsBetE.setJobsBetD(jobsBetD);
                                        jobsBetE = jobsBetESer.save(jobsBetE);
                                        jobsBetES.add(jobsBetE);
                                        List<JobsBetGTO> jobsBetGTOS = jobsBetFTO.getJobsBetGTOS();
                                        if (jobsBetGTOS != null) {
                                            for (JobsBetGTO jobsBetGTO : jobsBetGTOS) {
                                                JobsBetF jobsBetF = BeanTransform.copyProperties(jobsBetGTO, JobsBetF.class, true);
                                                jobsBetF.setJobsBetE(jobsBetE);
                                                if (jobsBetD.getJobs().equals(jobsBetF.getUnmetAllocationJobs())) {
                                                    throw new SerException("岗位不能和未达标分配岗位是同一个");
                                                }
                                                jobsBetF = jobsBetFSer.save(jobsBetF);
                                                jobsBetFS.add(jobsBetF);
                                            }
                                        }
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
        List<JobsBetF> jobsBetFS = new ArrayList<>();

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
                        //查询对应F表的数据，先删除
                        List<String> eIdList = eList.stream().map(JobsBetE::getId).collect(Collectors.toList());
                        String[] eids = new String[eIdList.size()];
                        eids = eIdList.toArray(eids);
                        JobsBetFDTO jobsBetFDTO = new JobsBetFDTO();
                        jobsBetFDTO.getConditions().add(Restrict.in("jobsBetE.id", eids));
                        List<JobsBetF> fList = jobsBetFSer.findByCis(jobsBetFDTO);
                        if (fList != null && fList.size() > 0) {
                            jobsBetFSer.remove(fList);
                        }
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
                        jobsBetC.setId(null);
                        jobsBetCSer.update(jobsBetC);
                        jobsBetCS.add(jobsBetC);

                        List<JobsBetETO> jobsBetETOS = jobsBetCTO.getJobsBetETOS();
                        if (jobsBetETOS != null) {
                            for (JobsBetETO jobsBetETO : jobsBetETOS) {
                                JobsBetD jobsBetD = BeanTransform.copyProperties(jobsBetETO, JobsBetD.class, true);
                                jobsBetD.setJobsBetC(jobsBetC);
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
                                        " systemTotalScorePractice as systemTotalScorePractice FROM royalty_systembetb WHERE system='" + jobsBetB.getSystem() + "' ";
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
                                basesScore = systemTotalScore * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScore(basesScore);
                                //计划基础得分（体系计划总得分*目标-部门分配基础权重）
                                basesScorePlan = systemTotalScorePlan * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScorePlan(basesScorePlan);
                                //实际基础得分（体系实际总得分*目标-部门分配基础权重）
                                basesScorePractice = systemTotalScorePractice * jobsBetD.getBaseWeight();
                                jobsBetD.setBasesScorePractice(basesScorePractice);

                                //目标总得分（目标制约得分+目标基础得分）
                                Double totalScore = jobsBetD.getRestrictScore() + basesScore;
                                jobsBetD.setTotalScore(totalScore);
                                //计划总得分（计划制约得分+计划基础得分）
                                Double totalScorePlan = jobsBetD.getRestrictScorePlan() + basesScorePlan;
                                jobsBetD.setTotalScorePlan(totalScorePlan);
                                //实际总得分（实际制约得分+实际基础得分）
                                Double totalScorePractice = jobsBetD.getTotalScorePractice() + basesScorePractice;
                                jobsBetD.setTotalScorePractice(totalScorePractice);
                                jobsBetD.setId(null);
                                jobsBetDSer.update(jobsBetD);
                                jobsBetDS.add(jobsBetD);

                                List<JobsBetFTO> jobsBetFTOS = jobsBetETO.getJobsBetFTOS();
                                if (jobsBetFTOS != null) {
                                    for (JobsBetFTO jobsBetFTO : jobsBetFTOS) {
                                        JobsBetE jobsBetE = BeanTransform.copyProperties(jobsBetFTO, JobsBetE.class, true);
                                        jobsBetE.setJobsBetD(jobsBetD);
                                        jobsBetE.setId(null);
                                        jobsBetESer.update(jobsBetE);
                                        jobsBetES.add(jobsBetE);
                                        List<JobsBetGTO> jobsBetGTOS = jobsBetFTO.getJobsBetGTOS();
                                        if (jobsBetGTOS != null) {
                                            for (JobsBetGTO jobsBetGTO : jobsBetGTOS) {
                                                JobsBetF jobsBetF = BeanTransform.copyProperties(jobsBetGTO, JobsBetF.class, true);
                                                jobsBetF.setJobsBetE(jobsBetE);
                                                jobsBetF.setId(null);
                                                if (jobsBetD.getJobs().equals(jobsBetF.getUnmetAllocationJobs())) {
                                                    throw new SerException("岗位不能和未达标分配岗位是同一个");
                                                }
                                                jobsBetFSer.update(jobsBetF);
                                                jobsBetFS.add(jobsBetF);
                                            }
                                        }
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
        JobsBetF jobsBetF = jobsBetFSer.findById(id);
        if (jobsBetF == null) {
            throw new SerException("该对象不存在");
        }
        jobsBetFSer.remove(id);
        List<JobsBetE> eList = jobsBetESer.findAll();
        List<JobsBetD> dList = jobsBetDSer.findAll();
        List<JobsBetC> cList = jobsBetCSer.findAll();
        List<JobsBetB> bList = jobsBetBSer.findAll();
        List<JobsBetA> aList = jobsBetASer.findAll();

        Set<String> eids = new HashSet<>();
        Set<String> dids = new HashSet<>();
        Set<String> cids = new HashSet<>();
        Set<String> bids = new HashSet<>();
        Set<String> aids = new HashSet<>();

        for (JobsBetF f : jobsBetFSer.findAll()) {
            eids.add(f.getJobsBetE().getId());
        }
        for (JobsBetE e : eList) {
            if (!eids.contains(e.getId())) {
                jobsBetESer.remove(e.getId());
            }
        }
        for (JobsBetE e1 : jobsBetESer.findAll()) {
            dids.add(e1.getJobsBetD().getId());
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
                jobsBetCSer.remove(c.getId());
            }
        }
        for (JobsBetC c1 : jobsBetCSer.findAll()) {
            bids.add(c1.getJobsBetB().getId());
        }
        for (JobsBetB b : bList) {
            if (!bids.contains(b.getId())) {
                jobsBetBSer.remove(b.getId());
            }
        }
        for (JobsBetB b1 : jobsBetBSer.findAll()) {
            aids.add(b1.getJobsBetA().getId());
        }
        for (JobsBetA a : aList) {
            if (!aids.contains(a.getId())) {
                jobsBetASer.remove(a.getId());
            }
        }


    }

    @Override
    public List<JobsBetABO> jobsCollect(ProjectNameTO to) throws SerException {
        List<JobsBetABO> jobsBetABOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.betTime AS betTime,a.area as area,a.projectGroup as projectGroup, ");
        sb.append(" a.projectName as projectName,a.scoreProfit as scoreProfit, ");
        sb.append(" a.planProfit as planProfit,a.practiceProfit as practiceProfit, ");
        sb.append(" b.system AS system,c.department as department,d.jobs AS jobs,d.people AS people, ");
        sb.append(" d.baseWeight AS baseWeight,d.basesScore AS basesScore, ");
        sb.append(" d.basesScorePlan AS basesScorePlan,d.basesScorePractice AS basesScorePractice, ");
        sb.append(" d.restrictScore AS restrictScore,d.restrictScorePlan AS restrictScorePlan, ");
        sb.append(" d.restrictScorePractice AS restrictScorePractice,d.totalScore AS totalScore, ");
        sb.append(" d.totalScorePlan AS totalScorePlan,d.totalScorePractice AS totalScorePractice, ");
        sb.append(" e.betWeight AS betWeight,e.indexNum as indexNum,e.indexName AS indexName, ");
        sb.append(" e.confirmTargetValue as confirmTargetValue,e.actualTargetValue AS actualTargetValue, ");
        sb.append(" e.is_standard AS standard,e.betScore AS betScore,e.betScorePlan AS betScorePlan, ");
        sb.append(" e.betScorePractice AS betScorePractice, ");
        sb.append(" f.unmetAllocationJobs as unmetAllocationJobs, f.unmetAllocation as unmetAllocation ");
        sb.append(" FROM royalty_jobsbeta a,royalty_jobsbetb b,  royalty_jobsbetc c,royalty_jobsbetd d, ");
        sb.append(" royalty_jobsbete e,royalty_jobsbetf f ");
        sb.append(" WHERE a.projectName='" + to.getProjectName() + "' AND a.id=b.jobsBetA_id AND b.id=c.jobsBetB_id AND c.id=d.jobsBetC_id ");
        sb.append(" AND d.id=e.jobsBetD_id AND e.id=f.jobsBetE_id ");
        String[] feilds = new String[]{"betTime", "area", "projectGroup", "projectName", "scoreProfit", "planProfit",
                "practiceProfit", "system", "department", "jobs", "people", "baseWeight", "basesScore", "basesScorePlan", "basesScorePractice",
                "restrictScore", "restrictScorePlan", "restrictScorePractice", "totalScore", "totalScorePlan",
                "totalScorePractice", "betWeight", "indexNum", "indexName",
                "confirmTargetValue", "actualTargetValue", "standard", "betScore", "betScorePlan", "betScorePractice",
                "unmetAllocationJobs", "unmetAllocation"};
        List<JobsBetBO> jobsBetBOS = super.findBySql(sb.toString(), JobsBetBO.class, feilds);
        double basesScore = 0.0;
        double basesScorePlan = 0.0;
        double basesScorePractice = 0.0;
        double restrictScore = 0.0;
        double restrictScorePlan = 0.0;
        double restrictScorePractice = 0.0;
        double totalScore = 0.0;
        double totalScorePlan = 0.0;
        double totalScorePractice = 0.0;
        double betScore = 0.0;
        double betScorePlan = 0.0;
        double betScorePractice = 0.0;
        if (jobsBetBOS != null) {
            basesScore = jobsBetBOS.stream().filter(p -> p.getBasesScore() != null).mapToDouble(p -> p.getBasesScore()).sum();
            basesScorePlan = jobsBetBOS.stream().filter(p -> p.getBasesScorePlan() != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
            basesScorePractice = jobsBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            restrictScore = jobsBetBOS.stream().filter(p -> p.getRestrictScore() != null).mapToDouble(p -> p.getRestrictScore()).sum();
            restrictScorePlan = jobsBetBOS.stream().filter(p -> p.getRestrictScorePlan() != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
            restrictScorePractice = jobsBetBOS.stream().filter(p -> p.getBasesScorePractice() != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
            totalScore = jobsBetBOS.stream().filter(p -> p.getTotalScore() != null).mapToDouble(p -> p.getTotalScore()).sum();
            totalScorePlan = jobsBetBOS.stream().filter(p -> p.getTotalScorePlan() != null).mapToDouble(p -> p.getTotalScorePlan()).sum();
            totalScorePractice = jobsBetBOS.stream().filter(p -> p.getTotalScorePractice() != null).mapToDouble(p -> p.getTotalScorePractice()).sum();
            betScore = jobsBetBOS.stream().filter(p -> p.getBetScore() != null).mapToDouble(p -> p.getBetScore()).sum();
            betScorePlan = jobsBetBOS.stream().filter(p -> p.getBetScorePlan() != null).mapToDouble(p -> p.getBetScorePlan()).sum();
            betScorePractice = jobsBetBOS.stream().filter(p -> p.getBetScorePractice() != null).mapToDouble(p -> p.getBetScorePractice()).sum();

        }
        Set<String> projectNames = jobsBetBOS.stream().map(JobsBetBO::getProjectName).collect(Collectors.toSet());
        JobsBetABO jobsBetABO = new JobsBetABO();
        for (String projectName : projectNames) {
            List<JobsBetBO> betBOS = jobsBetBOS.stream().filter(jobsBetBO -> projectName.equals(jobsBetBO.getProjectName())).collect(Collectors.toList());
            for (JobsBetBO jobsBetBO : betBOS) {
                jobsBetABO.setBetTime(jobsBetBO.getBetTime());
                jobsBetABO.setArea(jobsBetBO.getArea());
                jobsBetABO.setProjectName(projectName);
                jobsBetABO.setProjectGroup(jobsBetBO.getProjectGroup());
                jobsBetABO.setScoreProfit(jobsBetBO.getScoreProfit());
                jobsBetABO.setPlanProfit(jobsBetBO.getPlanProfit());
                jobsBetABO.setPracticeProfit(jobsBetBO.getPracticeProfit());
            }
            Set<String> systems = jobsBetBOS.stream().map(jobsBetBO -> jobsBetBO.getSystem()).collect(Collectors.toSet());
            List<JobsBetBBO> jobsBetBBOS = new ArrayList<>();
            for (String system : systems) {
                List<JobsBetBO> list = betBOS.stream().filter(jobsBetBO -> system.equals(jobsBetBO.getSystem())).collect(Collectors.toList());
                List<JobsBetBBO> bbos = BeanTransform.copyProperties(list, JobsBetBBO.class);
                jobsBetBBOS.addAll(bbos);
            }
            TreeSet<JobsBetBBO> treeSetB = filter();
            for (JobsBetBBO b : jobsBetBBOS) {
                treeSetB.add(b);
                Set<String> departments = jobsBetBOS.stream().map(jobsBetBO -> jobsBetBO.getDepartment()).collect(Collectors.toSet());
                List<JobsBetCBO> jobsBetCBOS = new ArrayList<>();
                for (String department : departments) {
                    List<JobsBetBO> list = betBOS.stream().filter(jobsBetBO -> department.equals(jobsBetBO.getDepartment())).collect(Collectors.toList());
                    List<JobsBetCBO> cbos = BeanTransform.copyProperties(list, JobsBetCBO.class);
                    jobsBetCBOS.addAll(cbos);
                }
                TreeSet<JobsBetCBO> treeSetC = filter();
                for (JobsBetCBO c : jobsBetCBOS) {
                    treeSetC.add(c);
                    Set<String> jobs = jobsBetBOS.stream().map(jobsBetBO -> jobsBetBO.getJobs()).collect(Collectors.toSet());
                    List<JobsBetDBO> jobsBetDBOS = new ArrayList<>();
                    for (String job : jobs) {
                        List<JobsBetBO> list = jobsBetBOS.stream().filter(jobsBetBO -> job.equals(jobsBetBO.getJobs())).collect(Collectors.toList());
                        List<JobsBetDBO> dbos = BeanTransform.copyProperties(list, JobsBetDBO.class);
                        jobsBetDBOS.addAll(dbos);
                    }
                    TreeSet<JobsBetDBO> treeSetD = filter();
                    for (JobsBetDBO d : jobsBetDBOS) {
                        treeSetD.add(d);
                        Set<String> indexNums = jobsBetBOS.stream().map(jobsBetBO -> jobsBetBO.getIndexNum()).collect(Collectors.toSet());
                        List<JobsBetEBO> jobsBetEBOS = new ArrayList<>();
                        for (String indexNum : indexNums) {
                            List<JobsBetBO> list = jobsBetBOS.stream().filter(jobsBetBO -> indexNum.equals(jobsBetBO.getIndexNum())).collect(Collectors.toList());
                            List<JobsBetEBO> ebos = BeanTransform.copyProperties(list, JobsBetEBO.class);
                            jobsBetEBOS.addAll(ebos);
                        }
                        TreeSet<JobsBetEBO> treeSetE = filter();
                        for (JobsBetEBO e : jobsBetEBOS) {
                            treeSetE.add(e);
                            Set<String> unmetJobs = jobsBetBOS.stream().map(jobsBetBO -> jobsBetBO.getUnmetAllocationJobs()).collect(Collectors.toSet());
                            List<JobsBetFBO> jobsBetFBOS = new ArrayList<>();
                            for (String unmetJob : unmetJobs) {
                                List<JobsBetBO> list = jobsBetBOS.stream().filter(jobsBetBO -> unmetJob.equals(jobsBetBO.getUnmetAllocationJobs())).collect(Collectors.toList());
                                List<JobsBetFBO> fbos = BeanTransform.copyProperties(list, JobsBetFBO.class);
                                jobsBetFBOS.addAll(fbos);
                            }
                            TreeSet<JobsBetFBO> treeSetF = filter();
                            jobsBetFBOS.clear();
                            jobsBetFBOS = new ArrayList<>(treeSetF);
                            e.setJobsBetFBOS(jobsBetFBOS);
                        }
                        jobsBetEBOS.clear();
                        jobsBetEBOS = new ArrayList<>(treeSetE);
                        d.setJobsBetEBOS(jobsBetEBOS);
                    }
                    jobsBetDBOS.clear();
                    jobsBetDBOS = new ArrayList<>(treeSetD);
                    c.setJobsBetDBOS(jobsBetDBOS);
                }
                jobsBetCBOS.clear();
                jobsBetCBOS = new ArrayList<>(treeSetC);
                b.setJobsBetCBOS(jobsBetCBOS);
            }
            jobsBetBBOS.clear();
            jobsBetBBOS = new ArrayList<>(treeSetB);
            jobsBetABO.setJobsBetBBOS(jobsBetBBOS);
        }
        jobsBetABOS.add(jobsBetABO);
        JobsBetABO abo = new JobsBetABO();
        abo.setArea("合计分值");
        List<JobsBetBBO> bboList = new ArrayList<>();
        JobsBetBBO bbo = new JobsBetBBO();
        bboList.add(bbo);
        abo.setJobsBetBBOS(bboList);
        List<JobsBetCBO> cboList = new ArrayList<>();
        JobsBetCBO cbo = new JobsBetCBO();
        cboList.add(cbo);
        bbo.setJobsBetCBOS(cboList);
        List<JobsBetDBO> dboList = new ArrayList<>();
        JobsBetDBO dbo = new JobsBetDBO();
        dbo.setBasesScore(basesScore);
        dbo.setBasesScorePlan(basesScorePlan);
        dbo.setBasesScorePractice(basesScorePractice);
        dbo.setRestrictScore(restrictScore);
        dbo.setRestrictScorePlan(restrictScorePlan);
        dbo.setRestrictScorePractice(restrictScorePractice);
        dbo.setTotalScore(totalScore);
        dbo.setTotalScorePlan(totalScorePlan);
        dbo.setTotalScorePractice(totalScorePractice);
        dboList.add(dbo);
        cbo.setJobsBetDBOS(dboList);
        List<JobsBetEBO> eboList = new ArrayList<>();
        JobsBetEBO ebo = new JobsBetEBO();
        ebo.setBetScore(betScore);
        ebo.setBetScorePlan(betScorePlan);
        ebo.setBetScorePractice(betScorePractice);
        eboList.add(ebo);
        dbo.setJobsBetEBOS(eboList);
        jobsBetABOS.add(abo);
        return jobsBetABOS;
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

    @Override
    public Set<String> projectName() throws SerException {
        Set<String> set = new HashSet<>();
        List<JobsBetA> list = jobsBetASer.findAll();
        for (JobsBetA a : list) {
            set.add(a.getProjectName());
        }
        return set;
    }

    @Override
    public List<ManageCommissionBO> collect(CollectTO to) throws SerException {
        List<ManageCommissionBO> manageCommissionBOS = new ArrayList<>();
        SystemBetADTO systemBetADTO = new SystemBetADTO();
        if (null != to.getProjectName()) {
            systemBetADTO.getConditions().add(Restrict.in("projectName", to.getProjectName()));
        }
        List<SystemBetABO> systemBetABOS = system(systemBetADTO);
        List<SystemBetBBO> systemBetBBOS = null;
        List<SystemBetCBO> systemBetCBOS = null;
        for (SystemBetABO systemBetABO : systemBetABOS) {
            SystemBetBDTO systemBetBDTO = new SystemBetBDTO();
            systemBetBDTO.getConditions().add(Restrict.eq("systemBetA.id", systemBetABO.getId()));
            List<SystemBetB> systemBetBS = systemBetBSer.findByCis(systemBetBDTO);
            systemBetBBOS = BeanTransform.copyProperties(systemBetBS, SystemBetBBO.class);
            for (SystemBetBBO systemBetBBO : systemBetBBOS) {
                SystemBetCDTO systemBetCDTO = new SystemBetCDTO();
                systemBetCDTO.getConditions().add(Restrict.eq("systemBetB.id", systemBetBBO.getId()));
                List<SystemBetC> systemBetCS = systemBetCSer.findByCis(systemBetCDTO);
                systemBetCBOS = BeanTransform.copyProperties(systemBetCS, SystemBetCBO.class);
            }
        }
        Set<String> systems = systemBetBBOS.stream().map(SystemBetBBO::getSystem).collect(Collectors.toSet());
        manageCommissionBOS = BeanTransform.copyProperties(systemBetABOS, ManageCommissionBO.class);
        List<DepartmentBetCBO> departmentBetCBOS = null;
        List<DepartmentBetDBO> departmentBetDBOS = null;
        List<JobsBetDBO> jobsBetDBOS = null;
        List<JobsBetEBO> jobsBetEBOS = null;
        for (ManageCommissionBO bo : manageCommissionBOS) {
            DepartmentBetADTO departmentBetADTO = new DepartmentBetADTO();
            List<DepartmentBetABO> departmentBetABOS = department(departmentBetADTO);
            for (DepartmentBetABO departmentBetABO : departmentBetABOS) {
                DepartmentBetBDTO departmentBetBDTO = new DepartmentBetBDTO();
                departmentBetBDTO.getConditions().add(Restrict.eq("departmentBetA.id", departmentBetABO.getId()));
                List<DepartmentBetB> listB = departmentBetBSer.findByCis(departmentBetBDTO);
                List<DepartmentBetBBO> listBBO = BeanTransform.copyProperties(listB, DepartmentBetBBO.class);
                for (DepartmentBetBBO departmentBetBBO : listBBO) {
                    if (systems.contains(departmentBetBBO.getSystem())) {
                        bo.setDepartmentBetBBOS(listBBO);
                        DepartmentBetCDTO cdto = new DepartmentBetCDTO();
                        cdto.getConditions().add(Restrict.eq("departmentBetB.id", departmentBetBBO.getId()));
                        List<DepartmentBetC> listC = departmentBetCSer.findByCis(cdto);
                        departmentBetCBOS = BeanTransform.copyProperties(listC, DepartmentBetCBO.class);
                        departmentBetBBO.setDepartmentBetCBOS(departmentBetCBOS);
                        for (DepartmentBetCBO departmentBetCBO : departmentBetCBOS) {
                            DepartmentBetDDTO ddto = new DepartmentBetDDTO();
                            ddto.getConditions().add(Restrict.eq("departmentBetC.id", departmentBetCBO.getId()));
                            List<DepartmentBetD> listD = departmentBetDSer.findByCis(ddto);
                            departmentBetDBOS = BeanTransform.copyProperties(listD, DepartmentBetDBO.class);
                            departmentBetCBO.setDepartmentBetDBOS(departmentBetDBOS);
                            if (departmentBetDBOS != null) {
                                for (DepartmentBetDBO departmentBetDBO : departmentBetDBOS) {
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
            JobsBetADTO jobsBetADTO = new JobsBetADTO();
            List<JobsBetABO> jobsBetABOS = job(jobsBetADTO);
            for (JobsBetABO jobsBetABO : jobsBetABOS) {
                JobsBetBDTO jobsBetBDTO = new JobsBetBDTO();
                jobsBetBDTO.getConditions().add(Restrict.eq("jobsBetA.id", jobsBetABO.getId()));
                List<JobsBetB> listB = jobsBetBSer.findByCis(jobsBetBDTO);
                List<JobsBetBBO> listBBO = BeanTransform.copyProperties(listB, JobsBetBBO.class);
                for (JobsBetBBO jobsBetBBO : listBBO) {
                    if (systems.contains(jobsBetBBO.getSystem())) {
                        bo.setJobsBetBBOS(listBBO);
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
                                jobsBetDBOS = BeanTransform.copyProperties(listD, JobsBetDBO.class);
                                jobsBetCBO.setJobsBetDBOS(jobsBetDBOS);
                                if (jobsBetDBOS != null) {
                                    for (JobsBetDBO jobsBetDBO : jobsBetDBOS) {
                                        JobsBetEDTO edto = new JobsBetEDTO();
                                        edto.getConditions().add(Restrict.eq("jobsBetD.id", jobsBetDBO.getId()));
                                        List<JobsBetE> listE = jobsBetESer.findByCis(edto);
                                        jobsBetEBOS = BeanTransform.copyProperties(listE, JobsBetEBO.class);
                                        jobsBetDBO.setJobsBetEBOS(jobsBetEBOS);
                                        if (jobsBetEBOS != null) {
                                            for (JobsBetEBO jobsBetEBO : jobsBetEBOS) {
                                                JobsBetFDTO fdto = new JobsBetFDTO();
                                                fdto.getConditions().add(Restrict.eq("jobsBetE.id", jobsBetEBO.getId()));
                                                List<JobsBetF> listF = jobsBetFSer.findByCis(fdto);
                                                List<JobsBetFBO> listFBO = BeanTransform.copyProperties(listF, JobsBetFBO.class);
                                                jobsBetEBO.setJobsBetFBOS(listFBO);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //体系间对赌表目标基础得分
        Double basesScoreSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScore()).sum();
        //体系间对赌表计划基础得分
        Double basesScorePlanSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
        //体系间对赌表实际基础得分
        Double basesScorePracticeSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
        //体系间对赌表目标制约得分
        Double restrictScoreSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScore()).sum();
        //体系间对赌表计划制约得分
        Double restrictScorePlanSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
        //体系间对赌表实际制约得分
        Double restrictScorePracticeSystem = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePractice()).sum();
        //体系间对赌表 体系目标总得分
        Double systemTotalScore = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getSystemTotalScore()).sum();
        //体系间对赌表体系计划总得分
        Double systemTotalScorePlan = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getSystemTotalScorePlan()).sum();
        //体系间对赌表体系实际总得分
        Double systemTotalScorePractice = systemBetBBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getSystemTotalScorePractice()).sum();
        //体系间对赌表目标对赌得分
        Double betScoreSystem = systemBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScore()).sum();
        //体系间对赌表计划对赌得分
        Double betScorePlanSystem = systemBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePlan()).sum();
        //体系间对赌表实际对赌得分
        Double betScorePracticeSystem = systemBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePractice()).sum();
        //部门间对赌表目标基础得分
        Double basesScoreDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScore()).sum();
        //部门间对赌表计划基础得分
        Double basesScorePlanDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
        //部门间对赌表实际基础得分
        Double basesScorePracticeDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
        //部门间对赌表目标制约得分
        Double restrictScoreDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScore()).sum();
        //部门间对赌表计划制约得分
        Double restrictScorePlanDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
        //部门间对赌表实际制约得分
        Double restrictScorePracticeDepartment = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePractice()).sum();
        //部门间对赌表部门目标总得分
        Double departmentTotalScore = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getDepartmentTotalScore()).sum();
        //部门间对赌表部门计划总得分
        Double departmentTotalScorePlan = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getDepartmentTotalScorePlan()).sum();
        //部门间对赌表部门实际总得分
        Double departmentTotalScorePractice = departmentBetCBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getDepartmentTotalScorePractice()).sum();
        //部门间对赌表目标对赌得分
         Double betScoreDepartment= departmentBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScore()).sum();
        //部门间对赌表计划对赌得分
         Double betScorePlanDepartment= departmentBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePlan()).sum();
        //部门间对赌表实际对赌得分
         Double betScorePracticeDepartment = departmentBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePractice()).sum();
         //岗位间对赌表目标基础得分
         Double basesScoreJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScore()).sum();
         //岗位间对赌表计划基础得分
         Double basesScorePlanJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePlan()).sum();
         //岗位间对赌表实际基础得分
         Double basesScorePracticeJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBasesScorePractice()).sum();
         //岗位间对赌表目标制约得分
         Double restrictScoreJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScore()).sum();
         //岗位间对赌表 计划制约得分
         Double restrictScorePlanJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePlan()).sum();
         //岗位间对赌表实际制约得分
         Double restrictScorePracticeJob= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getRestrictScorePractice()).sum();
         //岗位间对赌表目标总得分
         Double totalScore= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getTotalScore()).sum();
         //岗位间对赌表计划总得分
         Double totalScorePlan= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getTotalScorePlan()).sum();
         //岗位间对赌表实际总得分
         Double totalScorePractice= jobsBetDBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getTotalScorePractice()).sum();
         //岗位间对赌表目标对赌得分
         Double betScoreJob= jobsBetEBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScore()).sum();
         //岗位间对赌表计划对赌得分
         Double betScorePlanJob= jobsBetEBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePlan()).sum();
         //岗位间对赌表实际对赌得分
         Double betScorePracticeJob= jobsBetEBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getBetScorePractice()).sum();
         ManageCommissionBO bo = new ManageCommissionBO();
        bo.setProjectName("合计");
        //体系
        List<SystemBetBBO> systemBetBBOS1 = new ArrayList<>();
        SystemBetBBO bbo = new SystemBetBBO();
        bbo.setBasesScore(basesScoreSystem);
        bbo.setBasesScorePlan(basesScorePlanSystem);
        bbo.setBasesScorePractice(basesScorePracticeSystem);
        bbo.setRestrictScore(restrictScoreSystem);
        bbo.setRestrictScorePlan(restrictScorePlanSystem);
        bbo.setRestrictScorePractice(restrictScorePracticeSystem);
        bbo.setSystemTotalScore(systemTotalScore);
        bbo.setSystemTotalScorePlan(systemTotalScorePlan);
        bbo.setSystemTotalScorePractice(systemTotalScorePractice);
        List<SystemBetCBO> systemBetCBOS1 = new ArrayList<>();
        SystemBetCBO cbo = new SystemBetCBO();
        cbo.setBetScore(betScoreSystem);
        cbo.setBetScorePlan(betScorePlanSystem);
        cbo.setBetScorePractice(betScorePracticeSystem);
        systemBetCBOS1.add(cbo);
        bbo.setSystemBetCBOS(systemBetCBOS1);
        systemBetBBOS1.add(bbo);
        bo.setSystemBetBBOS(systemBetBBOS1);
        //部门
        List<DepartmentBetABO> departmentBetABOS = new ArrayList<>();
        DepartmentBetABO departmentBetABO = new DepartmentBetABO();
        departmentBetABOS.add(departmentBetABO);
        List<DepartmentBetBBO> departmentBetBBOS = new ArrayList<>();
        DepartmentBetBBO departmentBetBBO = new DepartmentBetBBO();
        departmentBetABO.setDepartmentBetBBOS(departmentBetBBOS);
        departmentBetBBOS.add(departmentBetBBO);
        List<DepartmentBetCBO> departmentBetCBOS1 = new ArrayList<>();
        DepartmentBetCBO departmentBetCBO = new DepartmentBetCBO();
        departmentBetCBO.setBasesScore(basesScoreDepartment);
        departmentBetCBO.setBasesScorePlan(basesScorePlanDepartment);
        departmentBetCBO.setBasesScorePractice(basesScorePracticeDepartment);
        departmentBetCBO.setRestrictScore(restrictScoreDepartment);
        departmentBetCBO.setRestrictScorePlan(restrictScorePlanDepartment);
        departmentBetCBO.setRestrictScorePractice(restrictScorePracticeDepartment);
        departmentBetCBO.setDepartmentTotalScore(departmentTotalScore);
        departmentBetCBO.setDepartmentTotalScorePlan(departmentTotalScorePlan);
        departmentBetCBO.setDepartmentTotalScorePractice(departmentTotalScorePractice);
        departmentBetCBOS1.add(departmentBetCBO);
        departmentBetBBO.setDepartmentBetCBOS(departmentBetCBOS1);
        List<DepartmentBetDBO> departmentBetDBOS1 = new ArrayList<>();
        DepartmentBetDBO departmentBetDBO = new DepartmentBetDBO();
        departmentBetDBO.setBetScore(betScoreDepartment);
        departmentBetDBO.setBetScorePlan(betScorePlanDepartment);
        departmentBetDBO.setBetScorePractice(betScorePracticeDepartment);
        departmentBetCBO.setDepartmentBetDBOS(departmentBetDBOS1);
        departmentBetDBOS1.add(departmentBetDBO);
        bo.setDepartmentBetBBOS(departmentBetBBOS);
        //岗位
        List<JobsBetABO> jobsBetABOS = new ArrayList<>();
        JobsBetABO jobsBetABO = new JobsBetABO();
        jobsBetABOS.add(jobsBetABO);
        List<JobsBetBBO> jobsBetBBOS = new ArrayList<>();
        JobsBetBBO jobsBetBBO = new JobsBetBBO();
        jobsBetBBOS.add(jobsBetBBO);
        jobsBetABO.setJobsBetBBOS(jobsBetBBOS);
        List<JobsBetCBO> jobsBetCBOS = new ArrayList<>();
        JobsBetCBO jobsBetCBO = new JobsBetCBO();
        jobsBetCBOS.add(jobsBetCBO);
        jobsBetBBO.setJobsBetCBOS(jobsBetCBOS);
        List<JobsBetDBO> jobsBetDBOS1 = new ArrayList<>();
        JobsBetDBO jobsBetDBO = new JobsBetDBO();
        jobsBetDBO.setBasesScore(basesScoreJob);
        jobsBetDBO.setBasesScorePlan(basesScorePlanJob);
        jobsBetDBO.setBasesScorePractice(basesScorePracticeJob);
        jobsBetDBO.setRestrictScore(restrictScoreJob);
        jobsBetDBO.setRestrictScorePlan(restrictScorePlanJob);
        jobsBetDBO.setRestrictScorePractice(restrictScorePracticeJob);
        jobsBetDBO.setTotalScore(totalScore);
        jobsBetDBO.setTotalScorePlan(totalScorePlan);
        jobsBetDBO.setTotalScorePractice(totalScorePractice);
        jobsBetDBOS1.add(jobsBetDBO);
        jobsBetCBO.setJobsBetDBOS(jobsBetDBOS1);
        List<JobsBetEBO> jobsBetEBOS1 = new ArrayList<>();
        JobsBetEBO jobsBetEBO = new JobsBetEBO();
        jobsBetEBO.setBetScore(betScoreJob);
        jobsBetEBO.setBetScorePlan(betScorePlanJob);
        jobsBetEBO.setBetScorePractice(betScorePracticeJob);
        jobsBetEBOS1.add(jobsBetEBO);
        jobsBetDBO.setJobsBetEBOS(jobsBetEBOS1);
        bo.setJobsBetBBOS(jobsBetBBOS);
        manageCommissionBOS.add(bo);

        return manageCommissionBOS;
    }

    private List<SystemBetABO> system(SystemBetADTO adto) throws SerException {
        List<SystemBetA> listA = systemBetASer.findByCis(adto);
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

    private List<DepartmentBetABO> department(DepartmentBetADTO adto) throws SerException {
        List<DepartmentBetA> listA = departmentBetASer.findByCis(adto);
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

    private List<JobsBetABO> job(JobsBetADTO adto) throws SerException {
        List<JobsBetA> listA = jobsBetASer.findByCis(adto);
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
                                        if (listEBO != null) {
                                            for (JobsBetEBO jobsBetEBO : listEBO) {
                                                JobsBetFDTO fdto = new JobsBetFDTO();
                                                fdto.getConditions().add(Restrict.eq("jobsBetE.id", jobsBetEBO.getId()));
                                                List<JobsBetF> listF = jobsBetFSer.findByCis(fdto);
                                                List<JobsBetFBO> listFBO = BeanTransform.copyProperties(listF, JobsBetFBO.class);
                                                jobsBetEBO.setJobsBetFBOS(listFBO);
                                            }
                                        }
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

    @Override
    public List<String> getProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<SystemBetABO> systemBetABOS = super.findBySql("SELECT a.projectName AS projectName FROM royalty_systembeta a,royalty_departmentbeta b,royalty_jobsbeta c WHERE a.projectName=b.projectName and a.projectName=c.projectName GROUP BY a.projectName ", SystemBetABO.class, fields);

        List<String> projectNameList = systemBetABOS.stream().map(SystemBetABO::getProjectName)
                .filter(projectName -> (StringUtils.isNotBlank(projectName))).distinct().collect(Collectors.toList());


        return projectNameList;
    }
}