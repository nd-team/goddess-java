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
import org.springframework.beans.BeanUtils;
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
                                                if(jobsBetD.getJobs().equals(jobsBetF.getUnmetAllocationJobs())){
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
                                                if(jobsBetD.getJobs().equals(jobsBetF.getUnmetAllocationJobs())){
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
        abo.setJobsBetBBOS(bboList);
        List<JobsBetCBO> cboList = new ArrayList<>();
        JobsBetCBO cbo = new JobsBetCBO();
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
                String[] feilds = new String[]{"system"};
                String sql = "SELECT system AS system FROM royalty_systembetb WHERE systemBetA_id='" + id + "'";
                departmentBetBBOS = super.findBySql(sql, DepartmentBetBBO.class, feilds);
            }
            Set<String> departments = new HashSet<>();
            if (departmentBetBBOS != null && !departmentBetBBOS.isEmpty()) {
                for (DepartmentBetBBO departmentBetBBO : departmentBetBBOS) {
//                    departments.add(departmentBetBBO.getDepartment());

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
//                                ManageCommissionBO bo = new ManageCommissionBO();
//                                bo.setProjectGroup(jobsBetB.getDepartment());
//                                bo.setJobs(jobsBetC.getJobs());
//                                bo.setJobsBaseWeight(jobsBetC.getBaseWeight());
//                                bo.setJobsBasesScore(jobsBetC.getBasesScore());
//                                bo.setJobsRestrictScore(jobsBetC.getRestrictScore());
//                                bo.setJobsTotalScore(jobsBetC.getDepartmentTotalScore());
//                                bo.setJobsBetWeight(jobsBetD.getBetWeight());
//                                bo.setJobsIndexNum(jobsBetD.getIndexNum());
//                                bo.setJobsIndexName(jobsBetD.getIndexName());
//                                bo.setJobsConfirmTargetValue(jobsBetD.getConfirmTargetValue());
//                                bo.setJobsStandard(jobsBetD.getStandard());
//                                bo.setJobsBetScore(jobsBetD.getBetScore());
//                                bo.setUnmetAllocationJobs(jobsBetE.getUnmetAllocationJobs());
//                                bo.setJobsUnmetAllocation(jobsBetE.getUnmetAllocation());
//                                list.add(bo);
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
//                                ManageCommissionBO bo = new ManageCommissionBO();
//                                bo.setDepartment(departmentBetB.getDepartment());
//                                bo.setDepartmentBaseWeight(departmentBetB.getBaseWeight());
//                                bo.setDepartmentBasesScore(departmentBetB.getBasesScore());
//                                bo.setDepartmentRestrictScore(departmentBetB.getRestrictScore());
//                                bo.setDepartmentTotalScore(departmentBetB.getDepartmentTotalScore());
//                                bo.setDepartmentBetWeight(departmentBetC.getBetWeight());
//                                bo.setDepartmentIndexNum(departmentBetC.getIndexNum());
//                                bo.setDepartmentIndexName(departmentBetC.getIndexName());
//                                bo.setDepartmentConfirmTargetValue(departmentBetC.getConfirmTargetValue());
//                                bo.setDepartmentStandard(departmentBetC.getStandard());
//                                bo.setDepartmentBetScore(departmentBetC.getBetScore());
//                                bo.setUnmetAllocationDepartment(departmentBetD.getUnmetAllocationDepartment());
//                                bo.setDepartmentUnmetAllocation(departmentBetD.getUnmetAllocation());
//                                list.add(bo);
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
//                            bo.setScore(systemBetA.getScore());
                            bo.setSystem(systemBetB.getSystem());
                            bo.setSystemBaseWeight(systemBetB.getBaseWeight());
                            bo.setSystemBasesScore(systemBetB.getBasesScore());
                            bo.setSystemRestrictScore(systemBetB.getRestrictScore());
//                            bo.setSystemTotalScore(systemBetB.getDepartmentTotalScore());
                            bo.setSystemBetWeight(systemBetC.getBetWeight());
                            bo.setSystemIndexNum(systemBetC.getIndexNum());
                            bo.setSystemIndexName(systemBetC.getIndexName());
                            bo.setSystemConfirmTargetValue(systemBetC.getConfirmTargetValue());
                            bo.setSystemStandard(systemBetC.getStandard());
                            bo.setSystemBetScore(systemBetC.getBetScore());
                            bo.setUnmetAllocationSystem(systemBetD.getUnmetAllocationSystem());
                            bo.setSystemUnmetAllocation(systemBetD.getUnmetAllocation());
//                            bo.setSystemDepartment(systemBetB.getDepartment());
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
        ManageCommissionBO totalBO = null;
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
            jobsTotalScore = list.stream().filter(p -> p.getTotalScore() != null).mapToDouble(p -> p.getTotalScore()).sum();


            totalBO = new ManageCommissionBO("合计分值", systemBasesScore, systemBetScore, systemRestrictScore, systemTotalScore,
                    departmentBasesScore, departmentBetScore, departmentRestrictScore, departmentTotalScore,
                    jobsBasesScore, jobsBetScore, jobsRestrictScore, jobsTotalScore);

        }
        List<ManageCommissionBO> bos = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getDepartment() != null && list.get(j).getSystem() != null) {

                    if (list.get(i).getDepartment().equals(list.get(j).getSystem())) {
                        ManageCommissionBO bo = list.get(i);
                        ManageCommissionBO naBo = list.get(j);
//                        bo.setProjectName(naBo.getProjectName());
//                        bo.setScore(naBo.getScore());
//                        bo.setSystem(naBo.getSystem());
//                        bo.setSystemBaseWeight(naBo.getSystemBaseWeight());
//                        bo.setSystemBetWeight(naBo.getSystemBetWeight());
//                        bo.setSystemIndexNum(naBo.getSystemIndexNum());
//                        bo.setSystemIndexName(naBo.getSystemIndexName());
//                        bo.setSystemConfirmTargetValue(naBo.getSystemConfirmTargetValue());
//                        bo.setSystemStandard(naBo.getSystemStandard());
//                        bo.setSystemBasesScore(naBo.getSystemBasesScore());
//                        bo.setSystemBetScore(naBo.getSystemBetScore());
//                        bo.setUnmetAllocationSystem(naBo.getUnmetAllocationSystem());
//                        bo.setSystemUnmetAllocation(naBo.getSystemUnmetAllocation());
//                        bo.setSystemRestrictScore(naBo.getSystemRestrictScore());
//                        bo.setSystemTotalScore(naBo.getSystemTotalScore());
                        BeanUtils.copyProperties(naBo, bo, "department", "departmentBaseWeight", "departmentBetWeight",
                                "departmentIndexNum", "departmentIndexName", "departmentConfirmTargetValue",
                                "departmentStandard", "departmentBasesScore", "departmentBetScore",
                                "unmetAllocationDepartment", "departmentUnmetAllocation", "departmentRestrictScore",
                                "departmentTotalScore");
                        bos.add(bo);
                    }
                }
            }
        }
        for (int i = 0; i < bos.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (bos.get(i).getDepartment() != null && list.get(j).getProjectGroup() != null) {

                    if (bos.get(i).getDepartment().equals(list.get(j).getProjectGroup())) {
                        ManageCommissionBO bo = bos.get(i);
                        ManageCommissionBO naBo = list.get(j);
                        BeanUtils.copyProperties(naBo, bo, "projectName", "score", "system",
                                "systemBaseWeight", "systemBetWeight", "systemIndexNum", "systemIndexName",
                                "systemConfirmTargetValue", "systemStandard", "systemBasesScore", "systemBetScore",
                                "unmetAllocationSystem", "systemUnmetAllocation", "systemRestrictScore",
                                "systemTotalScore", "department", "departmentBaseWeight", "departmentBetWeight",
                                "departmentIndexNum", "departmentIndexName", "departmentConfirmTargetValue",
                                "departmentStandard", "departmentBasesScore", "departmentBetScore",
                                "unmetAllocationDepartment", "departmentUnmetAllocation", "departmentRestrictScore",
                                "departmentTotalScore");
                    }
                }
            }
        }
        if (null != totalBO) {
            bos.add(totalBO);
        }
        return bos;
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