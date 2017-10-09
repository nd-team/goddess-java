package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.bo.RoyaltyCollectBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;
import com.bjike.goddess.royalty.enums.GuideAddrStatus;
import com.bjike.goddess.royalty.excel.SonPermissionObject;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.to.RoyaltyCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指标库业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class IndexLibrarySerImpl extends ServiceImpl<IndexLibrary, IndexLibraryDTO> implements IndexLibrarySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SystemBetSer systemBetSer;
    @Autowired
    private DepartmentBetSer departmentBetSer;
    @Autowired
    private JobsBetSer jobsBetSer;

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
        obj.setName("indexlibrary");
        obj.setDescribesion("指标库");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeSystembet = systemBetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("systembet");
        obj.setDescribesion("体系间对赌表");
        if (flagSeeSystembet) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeDepartmentbet = systemBetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("departmentbet");
        obj.setDescribesion("部门间对赌表");
        if (flagSeeDepartmentbet) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeJobsbet = systemBetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("jobsbet");
        obj.setDescribesion("岗位间对赌表");
        if (flagSeeJobsbet) {
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
    public Long count(IndexLibraryDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public IndexLibraryBO getOne(String id) throws SerException {
        IndexLibrary indexLibrary = super.findById(id);
        return BeanTransform.copyProperties(indexLibrary, IndexLibraryBO.class);
    }

    @Override
    public List<IndexLibraryBO> list(IndexLibraryDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<IndexLibrary> indexLibraries = super.findByPage(dto);
        List<IndexLibraryBO> indexLibraryBOS = BeanTransform.copyProperties(indexLibraries, IndexLibraryBO.class);
        return indexLibraryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndexLibraryBO insert(IndexLibraryTO indexLibraryTO) throws SerException {
        checkAddIdentity();
        IndexLibrary indexLibrary = BeanTransform.copyProperties(indexLibraryTO, IndexLibrary.class, true);
        indexLibrary.setCreateTime(LocalDateTime.now());
        super.save(indexLibrary);
        return BeanTransform.copyProperties(indexLibrary, IndexLibraryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndexLibraryBO edit(IndexLibraryTO indexLibraryTO) throws SerException {
        checkAddIdentity();
        IndexLibrary indexLibrary = super.findById(indexLibraryTO.getId());
        BeanTransform.copyProperties(indexLibraryTO, indexLibrary, true);
        indexLibrary.setModifyTime(LocalDateTime.now());
        super.update(indexLibrary);
        return BeanTransform.copyProperties(indexLibrary, IndexLibraryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<String> getIndexNum() throws SerException {
        String[] fields = new String[]{"indexNum"};
        List<IndexLibraryBO> indexLibraryBOS = super.findBySql("select distinct indexNum from royalty_indexlibrary group by indexNum order by indexNum asc ", IndexLibraryBO.class, fields);

        List<String> indexNumList = indexLibraryBOS.stream().map(IndexLibraryBO::getIndexNum)
                .filter(indexNum -> (StringUtils.isNotBlank(indexNum))).distinct().collect(Collectors.toList());


        return indexNumList;
    }

    @Override
    public List<String> getIndexName() throws SerException {
        String[] fields = new String[]{"indexName"};
        List<IndexLibraryBO> indexLibraryBOS = super.findBySql("select distinct indexName from royalty_indexlibrary group by indexName order by indexName asc ", IndexLibraryBO.class, fields);

        List<String> indexNameList = indexLibraryBOS.stream().map(IndexLibraryBO::getIndexName)
                .filter(indexName -> (StringUtils.isNotBlank(indexName))).distinct().collect(Collectors.toList());


        return indexNameList;
    }

    @Override
    public IndexLibraryBO getIndexLibrary(String indexNum) throws SerException {
        if (StringUtils.isNotBlank(indexNum)) {
            IndexLibraryDTO dto = new IndexLibraryDTO();
            dto.getConditions().add(Restrict.eq("indexNum", indexNum));
            IndexLibrary indexLibrary = super.findOne(dto);
            IndexLibraryBO bo = BeanTransform.copyProperties(indexLibrary, IndexLibraryBO.class);
            return bo;
        }
        return null;
    }

    @Override
    public List<RoyaltyCollectBO> dayRoyalty(RoyaltyCollectTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time = LocalDate.now();
        }
        List<RoyaltyCollectBO> boList = new ArrayList<>();
        String sql = " SELECT a.area AS area,c.department AS department, " +
                " count(c.department) AS  bet ,sum(a.practiceProfit) AS practiceProfit " +
                " FROM royalty_jobsbeta a,royalty_jobsbetc c " +
                " WHERE betTime = '" + time + "' " +
                " GROUP BY area,department ";
        String[] fields = new String[]{"area", "department", "bet", "practiceProfit"};

        List<RoyaltyCollectBO> royaltyCollectBOS = super.findBySql(sql, RoyaltyCollectBO.class, fields);

        for (RoyaltyCollectBO bo : royaltyCollectBOS) {
            String[] total = new String[]{"resourceProfit"};
            sql = " SELECT  d.totalScorePractice as resourceProfit  FROM royalty_jobsbetc c,royalty_jobsbetd d " +
                    "WHERE c.department='" + bo.getDepartment() + "' ";
            List<RoyaltyCollectBO> collectBOS = super.findBySql(sql, RoyaltyCollectBO.class, total);
            for (RoyaltyCollectBO collectBO : collectBOS) {

                if (bo.getDepartment().equals("综合资源部")) {
                    bo.setResourceProfit(collectBO.getResourceProfit());
                } else {
                    bo.setResourceProfit(0.0);
                }
                if (bo.getDepartment().equals("财务发展部")) {
                    bo.setAccountProfit(collectBO.getResourceProfit());
                } else {
                    bo.setAccountProfit(0.0);
                }
            }
            //todo 以下表头需求方只说了大的模块，没有具体到哪个模块哪张表，要等到时候再问需求方
            //todo 已立项 （商务合同）
            //todo 已确定提成分配比例项目数 (项目提成)
            //TODO 计划利润额 (项目提成)
            //todo 实际分值（利润额） (项目提成)
            //todo 实际分值（利润额）-计划利润额
            //todo 已完工项目  （商务合同）
            //todo 已回款项目 （回款）
            boList.add(bo);
        }

        return boList;
    }

    @Override
    public List<RoyaltyCollectBO> weekRoyalty(RoyaltyCollectTO to) throws SerException {
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        List<RoyaltyCollectBO> boList = new ArrayList<>();
        String sql = " SELECT a.area AS area,c.department AS department, " +
                " count(c.department) AS  bet ,sum(a.practiceProfit) AS practiceProfit " +
                " FROM royalty_jobsbeta a,royalty_jobsbetc c " +
                " WHERE betTime between '" + start + "' and '" + end + "' " +
                " GROUP BY area,department ";
        String[] fields = new String[]{"area", "department", "bet", "practiceProfit"};

        List<RoyaltyCollectBO> royaltyCollectBOS = super.findBySql(sql, RoyaltyCollectBO.class, fields);

        for (RoyaltyCollectBO bo : royaltyCollectBOS) {
            String[] total = new String[]{"resourceProfit"};
            sql = " SELECT  d.totalScorePractice as resourceProfit  FROM royalty_jobsbetc c,royalty_jobsbetd d " +
                    "WHERE c.department='" + bo.getDepartment() + "' ";
            List<RoyaltyCollectBO> collectBOS = super.findBySql(sql, RoyaltyCollectBO.class, total);
            for (RoyaltyCollectBO collectBO : collectBOS) {

                if (bo.getDepartment().equals("综合资源部")) {
                    bo.setResourceProfit(collectBO.getResourceProfit());
                } else {
                    bo.setResourceProfit(0.0);
                }
                if (bo.getDepartment().equals("财务发展部")) {
                    bo.setAccountProfit(collectBO.getResourceProfit());
                } else {
                    bo.setAccountProfit(0.0);
                }
            }
            boList.add(bo);
            //todo 以下表头需求方只说了大的模块，没有具体到哪个模块哪张表，要等到时候再问需求方
            //todo 已立项 （商务合同）
            //todo 已确定提成分配比例项目数 (项目提成)
            //TODO 计划利润额 (项目提成)
            //todo 实际分值（利润额） (项目提成)
            //todo 实际分值（利润额）-计划利润额
            //todo 已完工项目  （商务合同）
            //todo 已回款项目 （回款）
        }

        return boList;
    }

    @Override
    public List<RoyaltyCollectBO> monthRoyalty(RoyaltyCollectTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        List<RoyaltyCollectBO> boList = new ArrayList<>();
        String sql = " SELECT a.area AS area,c.department AS department, " +
                " count(c.department) AS  bet ,sum(a.practiceProfit) AS practiceProfit " +
                " FROM royalty_jobsbeta a,royalty_jobsbetc c " +
                " WHERE year(betTime) = '" + year + "' and month(betTime) = '" + month + "' " +
                " GROUP BY area,department ";
        String[] fields = new String[]{"area", "department", "bet", "practiceProfit"};

        List<RoyaltyCollectBO> royaltyCollectBOS = super.findBySql(sql, RoyaltyCollectBO.class, fields);

        for (RoyaltyCollectBO bo : royaltyCollectBOS) {
            String[] total = new String[]{"resourceProfit"};
            sql = " SELECT  d.totalScorePractice as resourceProfit  FROM royalty_jobsbetc c,royalty_jobsbetd d " +
                    "WHERE c.department='" + bo.getDepartment() + "' ";
            List<RoyaltyCollectBO> collectBOS = super.findBySql(sql, RoyaltyCollectBO.class, total);
            for (RoyaltyCollectBO collectBO : collectBOS) {

                if (bo.getDepartment().equals("综合资源部")) {
                    bo.setResourceProfit(collectBO.getResourceProfit());
                } else {
                    bo.setResourceProfit(0.0);
                }
                if (bo.getDepartment().equals("财务发展部")) {
                    bo.setAccountProfit(collectBO.getResourceProfit());
                } else {
                    bo.setAccountProfit(0.0);
                }
            }
            boList.add(bo);
            //todo 以下表头需求方只说了大的模块，没有具体到哪个模块哪张表，要等到时候再问需求方
            //todo 已立项 （商务合同）
            //todo 已确定提成分配比例项目数 (项目提成)
            //TODO 计划利润额 (项目提成)
            //todo 实际分值（利润额） (项目提成)
            //todo 实际分值（利润额）-计划利润额
            //todo 已完工项目  （商务合同）
            //todo 已回款项目 （回款）
        }

        return boList;
    }

    @Override
    public List<RoyaltyCollectBO> totalRoyalty(RoyaltyCollectTO to) throws SerException {
        LocalDate end = null;
        if (to.getTime() != null) {
            end = DateUtil.parseDate(to.getTime());
        } else {
            end = LocalDate.now();
        }
        List<RoyaltyCollectBO> boList = new ArrayList<>();
        String sql = " SELECT a.area AS area,c.department AS department, " +
                " count(c.department) AS  bet ,sum(a.practiceProfit) AS practiceProfit " +
                " FROM royalty_jobsbeta a,royalty_jobsbetc c " +
                " WHERE betTime <= '" + end + "' " +
                " GROUP BY area,department ";
        String[] fields = new String[]{"area", "department", "bet", "practiceProfit"};

        List<RoyaltyCollectBO> royaltyCollectBOS = super.findBySql(sql, RoyaltyCollectBO.class, fields);

        for (RoyaltyCollectBO bo : royaltyCollectBOS) {
            String[] total = new String[]{"resourceProfit"};
            sql = " SELECT  d.totalScorePractice as resourceProfit  FROM royalty_jobsbetc c,royalty_jobsbetd d " +
                    "WHERE c.department='" + bo.getDepartment() + "' ";
            List<RoyaltyCollectBO> collectBOS = super.findBySql(sql, RoyaltyCollectBO.class, total);
            for (RoyaltyCollectBO collectBO : collectBOS) {

                if (bo.getDepartment().equals("综合资源部")) {
                    bo.setResourceProfit(collectBO.getResourceProfit());
                } else {
                    bo.setResourceProfit(0.0);
                }
                if (bo.getDepartment().equals("财务发展部")) {
                    bo.setAccountProfit(collectBO.getResourceProfit());
                } else {
                    bo.setAccountProfit(0.0);
                }
            }
            boList.add(bo);
            //todo 以下表头需求方只说了大的模块，没有具体到哪个模块哪张表，要等到时候再问需求方
            //todo 已立项 （商务合同）
            //todo 已确定提成分配比例项目数 (项目提成)
            //TODO 计划利润额 (项目提成)
            //todo 实际分值（利润额） (项目提成)
            //todo 实际分值（利润额）-计划利润额
            //todo 已完工项目  （商务合同）
            //todo 已回款项目 （回款）
        }

        return boList;
    }
}