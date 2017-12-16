package com.bjike.goddess.businesscommission.service;

import com.bjike.goddess.businesscommission.bo.QuotaBO;
import com.bjike.goddess.businesscommission.bo.QuotaCollectBO;
import com.bjike.goddess.businesscommission.dto.QuotaDTO;
import com.bjike.goddess.businesscommission.entity.Proportion;
import com.bjike.goddess.businesscommission.entity.ProportionRatio;
import com.bjike.goddess.businesscommission.entity.Quota;
import com.bjike.goddess.businesscommission.enums.GuideAddrStatus;
import com.bjike.goddess.businesscommission.excel.QuotaExcele;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.QuotaTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.projectroyalty.api.WeightalsAPI;
import com.bjike.goddess.projectroyalty.bo.CollectBO;
import com.bjike.goddess.projectroyalty.enums.Type;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务提成权重分配表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businesscommissionSerCache")
@Service
public class QuotaSerImpl extends ServiceImpl<Quota, QuotaDTO> implements QuotaSer {

    @Autowired
    private WeightalsAPI weightalsAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ProportionSer proportionSer;
    @Autowired
    private ProportionRatioSer proportionRatioSer;

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<QuotaCollectBO> dayCollect(String day) throws SerException {
        String startTime = "";
        String endTime = "";
        if (StringUtils.isNotBlank(day)) {
            startTime = day;
            endTime = getSpecifiedDayAfter(day);
        }
        //从项目提成中获取地区,项目组,已立项,已确定提成分配比例数
        List<CollectBO> collectBOs = weightalsAPI.dayCollect(day);
        return returnResult(collectBOs, startTime, endTime);
    }

    @Override
    public List<QuotaCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];
        //从项目提成中获取地区,项目组,已立项,已确定提成分配比例数
        List<CollectBO> collectBOs = weightalsAPI.weekCollect(year, month, week);
        return returnResult(collectBOs, startTime, endTime);
    }

    @Override
    public List<QuotaCollectBO> monthCollect(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        //从项目提成中获取地区,项目组,已立项,已确定提成分配比例数
        List<CollectBO> collectBOs = weightalsAPI.monthCollect(month);
        return returnResult(collectBOs, startTime, endTime);
    }

    @Override
    public List<QuotaCollectBO> totalCollect() throws SerException {
        String startTime = "";
        String endTime = "";
        //从项目提成中获取地区,项目组,已立项,已确定提成分配比例数
        List<CollectBO> collectBOs = weightalsAPI.totalCollect();
        return returnResult(collectBOs, startTime, endTime);
    }

    @Override
    public List<String> listInnerProject() throws SerException {
        List<String> list = new ArrayList<>(0);
        List<Proportion> proportions = proportionSer.findAll();
        if (null != proportions && proportions.size() > 0) {
            list = proportions.stream().map(Proportion::getProjectName).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public void addQuota(QuotaTO to) throws SerException {
        Quota entity = BeanTransform.copyProperties(to, Quota.class, true);
        entity = getData(entity);
        super.save(entity);
    }

    @Override
    public void editQuota(QuotaTO to) throws SerException {
        Quota entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        BeanTransform.copyProperties(to, entity,true);
        entity = getData(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void deleteQuota(String id) throws SerException {
        Quota entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        super.remove(entity);
    }

    @Override
    public Long countQuota(QuotaDTO dto) throws SerException {
        searchCondition(dto);
        return super.count(dto);
    }

    @Override
    public QuotaBO getOneById(String id) throws SerException {
        Quota entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        QuotaBO quotaBO = BeanTransform.copyProperties(entity, QuotaBO.class, false);
        return quotaBO;
    }

    @Override
    public List<QuotaBO> listQuota(QuotaDTO dto) throws SerException {
//        searchCondition(dto);
//        List<Quota> quotas = super.findByPage(dto);
        List<Quota> quotas1 = super.findByCis(dto, true);
        List<QuotaBO> quotaBOList = BeanTransform.copyProperties(quotas1, QuotaBO.class);
        return quotaBOList;
    }

    @Override
    public byte[] exportExcel(QuotaDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }

        List<Quota> list = super.findByCis(dto);

        List<QuotaExcele> quotaExports = new ArrayList<>(0);
        list.stream().forEach(str -> {
            QuotaExcele excel = BeanTransform.copyProperties(str, QuotaExcele.class);
            quotaExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(quotaExports, excel);
        return bytes;
    }

    private Quota getData(Quota entity) throws SerException {
        entity.setAimAmount(weightalsAPI.findAimAmount(entity.getProjectName(), Type.AIM));
        entity.setPlanAmount(weightalsAPI.findAimAmount(entity.getProjectName(), Type.PLAN));
        entity.setActualAmount(weightalsAPI.findAimAmount(entity.getProjectName(), Type.ACTUAL));

        ProportionRatio proportionRatio = getRatio(entity);
        //获得占比
        if (null != proportionRatio) {
            entity.setMessageProportion(proportionRatio.getMessageProportion());
            entity.setBusinessProportion(proportionRatio.getBusinessProportion());
            entity.setTalkProportion(proportionRatio.getTalkProportion());
            entity.setMaintainProportion(proportionRatio.getMaintainProportion());
        } else {
            throw new SerException("本表的项目名称在业务提成分配比例表中无对应的数据");
        }

        //计算占额
        entity.setProvideAccount(entity.getMessageProportion() * entity.getActualAmount());
        entity.setContractAccount(entity.getBusinessProportion() * entity.getActualAmount());
        entity.setNegotiationAccount(entity.getTalkProportion() * entity.getActualAmount());
        entity.setMaintenanceAccount(entity.getMaintainProportion() * entity.getActualAmount());

        entity.setSurplusProportion(100 - entity.getTalkProportion() - entity.getBusinessProportion() - entity.getMaintainProportion() - entity.getMessageProportion());
        entity.setRemainingAccount(entity.getSurplusProportion() * entity.getActualAmount());
        entity.setRatio(entity.getSurplusProportion() + entity.getMaintainProportion() + entity.getMessageProportion() + entity.getBusinessProportion() + entity.getTalkProportion());

        return entity;
    }

    private void searchCondition(QuotaDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        if (dto.getActualAmount() != null) {
            dto.getConditions().add(Restrict.eq("actualAmount", dto.getActualAmount()));
        }
        if (dto.getAimAmount() != null) {
            dto.getConditions().add(Restrict.eq("aimAmount", dto.getAimAmount()));
        }
        if (dto.getPlanAmount() != null) {
            dto.getConditions().add(Restrict.eq("planAmount", dto.getPlanAmount()));
        }
    }

    //判断是否是市场专业人员
    private Boolean isMarker(String name) throws SerException {
        String userId = "";
        UserBO userBO = userAPI.findByUsername(name);
        if (null != userBO) {
            userId = userBO.getId();
        }
        return positionDetailUserAPI.isMarker(userId);
    }

    //获得占比计算
    private ProportionRatio getRatio(Quota entity) throws SerException {
        //是否一手包揽
        Boolean isAll = false;
        Boolean tar = false;
        String name = entity.getInformationProvide();
        if (name.equals(entity.getBusinessContracting()) && name.equals(entity.getBusinessNegotiation()) && name.equals(entity.getMaintenance())) {
            isAll = true;
        }

        List<Proportion> proportions = proportionSer.findAll();
        List<ProportionRatio> proportionRatios = proportionRatioSer.findAll();
        ProportionRatio proportionRatio = new ProportionRatio();
        List<String> ids = new ArrayList<>(0);
        if (isAll) {
            if (null != proportions && proportions.size() > 0) {
                ids = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                if (null != ids && ids.size() > 0) {
                    final String id = ids.get(0);
                    proportionRatios = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "一手包揽".equals(obj.getFactors())).collect(Collectors.toList());
                    if (null != proportionRatios && proportionRatios.size() > 0) {
                        proportionRatio = proportionRatios.get(0);
                    }
                }

            }
        } else {
            tar = isMarker(entity.getInformationProvide());
            if (tar) {
                List<ProportionRatio> proportionRatios1 = new ArrayList<>(0);
                List<String> ids1 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids1 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids1 && ids1.size() > 0) {
                        final String id = ids1.get(0);
                        proportionRatios1 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "市场专业人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios1 && proportionRatios1.size() > 0) {
                            proportionRatio.setMessageProportion(proportionRatios1.get(0).getMessageProportion());
                        }
                    }

                }
            } else {
                List<ProportionRatio> proportionRatios1 = new ArrayList<>(0);
                List<String> ids1 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids1 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids1 && ids1.size() > 0) {
                        final String id = ids1.get(0);
                        proportionRatios1 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "非市场人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios1 && proportionRatios1.size() > 0) {
                            proportionRatio.setMessageProportion(proportionRatios1.get(0).getMessageProportion());
                        }
                    }

                }
            }
            tar = isMarker(entity.getBusinessContracting());
            if (tar) {
                List<ProportionRatio> proportionRatios2 = new ArrayList<>(0);
                List<String> ids2 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids2 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids2 && ids2.size() > 0) {
                        final String id = ids2.get(0);
                        proportionRatios2 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "市场专业人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios2 && proportionRatios2.size() > 0) {
                            proportionRatio.setBusinessProportion(proportionRatios2.get(0).getBusinessProportion());
                        }
                    }

                }
            } else {
                List<ProportionRatio> proportionRatios2 = new ArrayList<>(0);
                List<String> ids2 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids2 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids2 && ids2.size() > 0) {
                        final String id = ids2.get(0);
                        proportionRatios2 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "非市场人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios2 && proportionRatios2.size() > 0) {
                            proportionRatio.setBusinessProportion(proportionRatios2.get(0).getBusinessProportion());
                        }
                    }

                }
            }

            tar = isMarker(entity.getBusinessNegotiation());
            if (tar) {
                List<ProportionRatio> proportionRatios3 = new ArrayList<>(0);
                List<String> ids3 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids3 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids3 && ids3.size() > 0) {
                        final String id = ids3.get(0);
                        proportionRatios3 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "市场专业人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios3 && proportionRatios3.size() > 0) {
                            proportionRatio.setTalkProportion(proportionRatios3.get(0).getTalkProportion());
                        }
                    }
                }
            } else {
                List<ProportionRatio> proportionRatios3 = new ArrayList<>(0);
                List<String> ids3 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids3 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids3 && ids3.size() > 0) {
                        final String id = ids3.get(0);
                        proportionRatios3 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "非市场人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios3 && proportionRatios3.size() > 0) {
                            proportionRatio.setTalkProportion(proportionRatios3.get(0).getTalkProportion());
                        }
                    }
                }
            }

            tar = isMarker(entity.getMaintenance());
            if (tar) {
                List<ProportionRatio> proportionRatios4 = new ArrayList<>(0);
                List<String> ids4 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids4 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids4 && ids4.size() > 0) {
                        final String id = ids4.get(0);
                        proportionRatios4 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "市场人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios4 && proportionRatios4.size() > 0) {
                            proportionRatio.setMaintainProportion(proportionRatios4.get(0).getMaintainProportion());
                        }
                    }
                }
            } else {
                List<ProportionRatio> proportionRatios4 = new ArrayList<>(0);
                List<String> ids4 = new ArrayList<>(0);
                if (null != proportions && proportions.size() > 0) {
                    ids4 = proportions.stream().filter(obj -> entity.getProjectName().equals(obj.getProjectName())).map(Proportion::getId).collect(Collectors.toList());
                    if (null != ids4 && ids4.size() > 0) {
                        final String id = ids4.get(0);
                        proportionRatios4 = proportionRatios.stream().filter(obj -> id.equals(obj.getProportionId()) && "非市场人员".equals(obj.getFactors())).collect(Collectors.toList());
                        if (null != proportionRatios4 && proportionRatios4.size() > 0) {
                            proportionRatio.setMaintainProportion(proportionRatios4.get(0).getMaintainProportion());
                        }
                    }
                }
            }
        }
        return proportionRatio;
    }

    //获取指定日期的后一天
    public String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    private List<QuotaCollectBO> returnResult(List<CollectBO> collectBOs, String startTime, String endTime) throws SerException {

        List<QuotaCollectBO> quotaCollectBOList = new ArrayList<>(0);
        for (CollectBO collectBO : collectBOs) {
            QuotaCollectBO quotaCollectBO = new QuotaCollectBO();

            quotaCollectBO.setArea(collectBO.getArea());
            quotaCollectBO.setDepartment(collectBO.getDepartment());
            quotaCollectBO.setBuild(collectBO.getBuild());
            quotaCollectBO.setNum(Integer.valueOf(collectBO.getNum().toString()));

            String area = collectBO.getArea();
            String department = collectBO.getDepartment();
            String[] fields = new String[]{"ratioNum"};
            StringBuilder sql = new StringBuilder(" SELECT count(a.time1) as ratioNum FROM businesscommission_proportion a ");
            sql.append(" WHERE a.area = '" + area + "' and a.department = '" + department + "' ");
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql.append(" and a.time1 BETWEEN '" + startTime + "' AND '" + endTime + "' ");
            }
            List<QuotaCollectBO> quotaCollectBOs = super.findBySql(sql.toString(), QuotaCollectBO.class, fields);
            if (null != quotaCollectBOs && quotaCollectBOs.size() > 0) {
                quotaCollectBO.setRatioNum(quotaCollectBOs.get(0).getRatioNum());
            } else {
                quotaCollectBO.setRatioNum(0);
            }
            // TODO: 17-9-27 已完工项目
            // TODO: 17-9-27 已回款项目
            //相同地区和项目组的目标业务提成额总和
            String[] fildes = new String[]{"aimAmount", "planAmount", "actualAmount", "remainingAccount"};
            StringBuilder sql1 = new StringBuilder(" select ifnull(sum(aimAmount),0) as aimAmount, ");
            sql1.append(" ifnull(sum(planAmount),0) as planAmount, ");
            sql1.append(" ifnull(sum(actualAmount),0) as actualAmount, ");
            sql1.append(" ifnull(sum(remainingAccount),0) as remainingAccount ");
            sql1.append(" from businesscommission_quota a, ");
            sql1.append(" businesscommission_proportion b ");
            sql1.append(" WHERE a.area = '" + area + "' and a.department = '" + department + "' ");
            sql1.append(" and a.projectName=b.projectName ");
            if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
                sql1.append(" and b.time1 BETWEEN '" + startTime + "' AND '" + endTime + "' ");
            }
//            List<Object> objectList = super.
            List<Object> objectList = proportionRatioSer.findBySql(sql1.toString());
//            if (null != quotaCollectBOs1 && quotaCollectBOs1.size() > 0) {
            Object[] objects = (Object[]) objectList.get(0);
                quotaCollectBO.setAimAmount(Double.parseDouble(String.valueOf(objects[0])));
                quotaCollectBO.setPlanAmount(Double.parseDouble(String.valueOf(objects[1])));
                quotaCollectBO.setActualAmount(Double.parseDouble(String.valueOf(objects[2])));
                quotaCollectBO.setRemainingAccount(Double.parseDouble(String.valueOf(objects[3])));
                quotaCollectBO.setDifferenceAmount(quotaCollectBO.getActualAmount() - quotaCollectBO.getPlanAmount());
//            } else {
//                quotaCollectBO.setAimAmount(0d);
//                quotaCollectBO.setPlanAmount(0d);
//                quotaCollectBO.setActualAmount(0d);
//                quotaCollectBO.setRemainingAccount(0d);
//                quotaCollectBO.setDifferenceAmount(quotaCollectBO.getActualAmount() - quotaCollectBO.getPlanAmount());
//            }
            quotaCollectBOList.add(quotaCollectBO);
        }
        return quotaCollectBOList;
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }
}