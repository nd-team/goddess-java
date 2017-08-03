package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.entity.YearIndexSet;
import com.bjike.goddess.balancecard.enums.GuideAddrStatus;
import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.excel.DepartYearIndexSetExcel;
import com.bjike.goddess.balancecard.to.DepartMonSerperateTO;
import com.bjike.goddess.balancecard.to.DepartYearIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门年度指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:28 ]
 * @Description: [ 部门年度指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class DepartYearIndexSetSerImpl extends ServiceImpl<DepartYearIndexSet, DepartYearIndexSetDTO> implements DepartYearIndexSetSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DepartMonIndexSetSer departMonIndexSetSer;
    @Autowired
    private PositionIndexSetSer positionIndexSetSer;
    @Autowired
    private YearIndexSetSer yearIndexSetSer;

    @Autowired
    private BalancecardPermissionSer cusPermissionSer;

    @Autowired
    private UserDetailAPI userDetailAPI;

    /**
     * 核对是否为管理层权限（部门级别）
     */
    private Boolean checkManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                RpcTransmit.transmitUserToken(userToken);
                return false;
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        return true;
    }


    @Override
    public Boolean sonPermission() throws SerException {
            return true;
    }
    //功能导航权限
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = true;
                break;
            case ADD:
                flag = true;
                break;
            case EDIT:
                flag = true;
                break;
            case AUDIT:
                flag = true;
                break;
            case DELETE:
                flag = true;
                break;
            case COLLECT:
                flag = true;
                break;
            case IMPORT:
                flag = true;
                break;
            case EXPORT:
                flag = true;
                break;
            case UPLOAD:
                flag = true;
                break;
            case DOWNLOAD:
                flag = true;
                break;
            case SEE:
                flag = true;
                break;
            case SEEFILE:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getIndexName())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("indexName", departYearIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getYear())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("year", departYearIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getDepartment())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("department", departYearIndexSetDTO.getDepartment()));
        }
        Long count = super.count(departYearIndexSetDTO);
        return count;
    }

    @Override
    public DepartYearIndexSetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DepartYearIndexSet temp = super.findById(id);
        DepartYearIndexSetBO bo = BeanTransform.copyProperties(temp, DepartYearIndexSetBO.class);
        return bo;
    }

    /**
     * 加入部门查看权限,管理层和决策层能查看所有,执行层只能查看自己的部门
     *
     * @param departYearIndexSetDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<DepartYearIndexSetBO> listDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        //先看是不是可以查看自己的部门指标
        //如果可以看（1.管理层和决策层权限，那么就可以查看列表所有数据
        // 2.只能查看自己部门的）
        if (checkManageIdentity()) {
            if (StringUtils.isNotBlank(departYearIndexSetDTO.getDepartment())) {
                departYearIndexSetDTO.getConditions().add(Restrict.like("department", departYearIndexSetDTO.getDepartment()));
            }
            List<DepartYearIndexSet> list = super.findByCis(departYearIndexSetDTO, true);
            List<DepartYearIndexSetBO> listBO = BeanTransform.copyProperties(list, DepartYearIndexSetBO.class);
            return listBO;
        } else {
                String userId = userAPI.currentUser().getId();
                String department = userDetailAPI.findByUserId(userId).getDepartmentName();
                if(StringUtils.isNotBlank( department)){
                    departYearIndexSetDTO.setDepartment(department);
                    departYearIndexSetDTO.getConditions().add(Restrict.like("department",departYearIndexSetDTO.getDepartment()));
                    List<DepartYearIndexSet> list = super.findByCis(departYearIndexSetDTO, true);
                    List<DepartYearIndexSetBO> listBO = BeanTransform.copyProperties(list, DepartYearIndexSetBO.class);
                    return listBO;
                }else{
                    throw new SerException("您不存在任何部门中，没有权限查看");
                }

        }
    }

    @Override
    public DepartYearIndexSetBO addDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        DepartYearIndexSet temp = BeanTransform.copyProperties(departYearIndexSetTO, DepartYearIndexSet.class, true);
        //查询部门年度指标编号最大值
        String yearIndexNumber = super.findByMaxField("yearIndexNumber", DepartYearIndexSet.class);
        if (yearIndexNumber != null) {
            temp.setYearIndexNumber(Integer.parseInt(yearIndexNumber) + 1);
        } else {
            temp.setYearIndexNumber(1);
        }
        //为部门年度模块自己添加的数据设置年度指标为0
        temp.setIndexNumber(0);
        temp.setDepartWeightSum(100d);
        temp.setComplete(0d);
        temp.setWhetherStandar(departYearIndexSetTO.getComplete() > departYearIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(0d);
        temp.setExamScore(0d);
        temp.setWritePerson(userBO.getUsername());
        temp.setYearPersion(userBO.getUsername());
        temp.setYearIndexTime(LocalDate.now());
        temp.setSeparateStatus(SeparateStatus.NONE);
        temp.setSeperateComeStatus(SeperateComeStatus.FILL);
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save(temp);
        return BeanTransform.copyProperties(temp, DepartYearIndexSetBO.class);
    }

    @Override
    public DepartYearIndexSetBO editDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        if (StringUtils.isBlank(departYearIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        DepartYearIndexSet departYearIndexSet = super.findById(departYearIndexSetTO.getId());
        Double oldYearComplete = departYearIndexSet.getComplete();

        //没有分解过
        departYearIndexSet.setIndexName(departYearIndexSetTO.getIndexName());
        departYearIndexSet.setYear(departYearIndexSetTO.getYear());
        departYearIndexSet.setIndexType(departYearIndexSetTO.getIndexType());
        departYearIndexSet.setDimension(departYearIndexSetTO.getDimension());
        departYearIndexSet.setDescribtion(departYearIndexSetTO.getDescribtion());
        departYearIndexSet.setYearTarget(departYearIndexSetTO.getYearTarget());
        departYearIndexSet.setDepartment(departYearIndexSetTO.getDepartment());
        departYearIndexSet.setDepartYearWeight(departYearIndexSetTO.getDepartYearWeight());
        departYearIndexSet.setDepartWeightSum(departYearIndexSetTO.getDepartWeightSum());
        departYearIndexSet.setTarget(departYearIndexSet.getTarget());
        departYearIndexSet.setWager(departYearIndexSetTO.getWager());
        departYearIndexSet.setComplete(departYearIndexSetTO.getComplete());
        departYearIndexSet.setExamWay(departYearIndexSetTO.getExamWay());
        departYearIndexSet.setWhetherStandar(departYearIndexSetTO.getComplete() > departYearIndexSetTO.getTarget() ? "是" : "否");
        departYearIndexSet.setStandardRate(departYearIndexSetTO.getComplete() / departYearIndexSetTO.getWager());
        departYearIndexSet.setExamScore(departYearIndexSet.getStandardRate() * departYearIndexSet.getDepartYearWeight());
        departYearIndexSet.setExamDepart(departYearIndexSetTO.getExamDepart());
        departYearIndexSet.setDataOrigin(departYearIndexSetTO.getDataOrigin());
        departYearIndexSet.setExamDuring(departYearIndexSetTO.getExamDuring());
        departYearIndexSet.setModifyTime(LocalDateTime.now());
        //判断分解状态 已分解：重新分解下面的 且 判断是否由上面分解下来的，要修改上面的
        //若是 未分解的话，直接修改
        if (departYearIndexSet.getSeparateStatus().equals(SeparateStatus.SEPERATE)) {
            //修改重新分解部门月度
            List<DepartMonIndexSet> listDMon = seperateDepartMonIndex(departYearIndexSet);
            //修改重新分解岗位指标
            if (listDMon != null && listDMon.size() > 0) {
                List<PositionIndexSet> listPost = seperatePostIndex(listDMon);
            }
        }

        //若是由年指标分解下来的，那就重新修改年指标完成值
        if (departYearIndexSet.getSeperateComeStatus().equals(SeperateComeStatus.YEAR)) {
            //修改年指标完成值
            editYearComplete(oldYearComplete, departYearIndexSet);

        }

        super.update(departYearIndexSet);
        return BeanTransform.copyProperties(departYearIndexSet, DepartYearIndexSetBO.class);
    }

    /**
     * 修改年指标完成值
     *
     * @param oldYearComplete
     * @param dYear
     * @throws SerException
     */
    private void editYearComplete(Double oldYearComplete, DepartYearIndexSet dYear) throws SerException {
        //新的部门年度完成值
        Double newComplete = dYear.getComplete();
        //年度id
        String yearId = dYear.getYearIndexSetId();
        YearIndexSet year = yearIndexSetSer.findById(yearId);

        year.setComplete(year.getComplete() - oldYearComplete + newComplete);
        year.setModifyTime(LocalDateTime.now());

        yearIndexSetSer.update(year);

    }

    /**
     * 重新分解岗位指标
     *
     * @throws SerException
     */
    private List<PositionIndexSet> seperatePostIndex(List<DepartMonIndexSet> listDMon) throws SerException {

        List<PositionIndexSet> poList = new ArrayList<>();
        for (DepartMonIndexSet dm : listDMon) {

            PositionIndexSetDTO pDTO = new PositionIndexSetDTO();
            pDTO.getConditions().add(Restrict.eq("departMonIndexSetId", dm.getId()));
            List<PositionIndexSet> postList = positionIndexSetSer.findByCis(pDTO);

            postList.stream().forEach(str -> {
                str.setIndexName(dm.getIndexName());
                str.setYear(dm.getYear());
                str.setIndexType(dm.getIndexType());
                str.setDimension(dm.getDimension());
                str.setDescribtion(dm.getDescribtion());
                str.setDepartment(dm.getDepartment());
                str.setDepartYearWeight(dm.getDepartYearWeight());
                str.setDepartYearWager(dm.getWager());
                str.setTarget(dm.getTarget() * str.getWeight() / 100d);
                str.setComplete(0d);
                str.setStandardRate(0d);
                str.setExamScore(0d);
                str.setWhetherStandar(str.getComplete() > str.getTarget() ? "是" : "否");
                str.setModifyTime(LocalDateTime.now());
                poList.add(str);
            });
        }

        positionIndexSetSer.update(poList);
        return poList;
    }


    /**
     * 重新分解部门月度指标
     *
     * @throws SerException
     */
    private List<DepartMonIndexSet> seperateDepartMonIndex(DepartYearIndexSet departYearIndexSet) throws SerException {

        DepartMonIndexSetDTO dmDTO = new DepartMonIndexSetDTO();
        dmDTO.getConditions().add(Restrict.eq("departYearIndexSetId", departYearIndexSet.getId()));
        List<DepartMonIndexSet> listDMon = departMonIndexSetSer.findByCis(dmDTO);


        listDMon.stream().forEach(str -> {
            str.setIndexName(departYearIndexSet.getIndexName());
            str.setIndexNumber(departYearIndexSet.getIndexNumber());
            str.setYear(departYearIndexSet.getYear());
            str.setIndexType(departYearIndexSet.getIndexType());
            str.setDimension(departYearIndexSet.getDimension());
            str.setDescribtion(departYearIndexSet.getDescribtion());
            str.setDepartment(departYearIndexSet.getDepartment());
            str.setDepartYearWeight(departYearIndexSet.getDepartYearWeight());
            str.setDepartYearWager(departYearIndexSet.getWager());
            str.setTarget(departYearIndexSet.getYearTarget() / 12);
            str.setWeight(str.getTarget() / str.getWager() * 100d);
            str.setComplete(0d);
            str.setStandardRate(0d);
            str.setExamScore(0d);
            str.setWhetherStandar(str.getComplete() > str.getTarget() ? "是" : "否");
            str.setModifyTime(LocalDateTime.now());
        });


        departMonIndexSetSer.update(listDMon);
        return listDMon;
    }

    @Override
    public void deleteDepartYearIndexSet(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DepartYearIndexSet temp = super.findById(id);
        if (temp.getSeparateStatus().equals(SeparateStatus.SEPERATE)) {
            DepartMonIndexSetDTO dmonSet = new DepartMonIndexSetDTO();
            dmonSet.getConditions().add(Restrict.eq("departYearIndexSetId", id));
            List<DepartMonIndexSet> listDepartMon = departMonIndexSetSer.findByCis(dmonSet);

            //删除岗位分解的数据
            List<PositionIndexSet> postRemoveList = new ArrayList<>();
            for (DepartMonIndexSet departMonIndexSet : listDepartMon) {
                PositionIndexSetDTO pdto = new PositionIndexSetDTO();
                pdto.getConditions().add(Restrict.eq("departMonIndexSetId", departMonIndexSet.getId()));
                List<PositionIndexSet> postList = positionIndexSetSer.findByCis(pdto);
                postRemoveList.addAll(postList);
            }
            if (postRemoveList != null && postRemoveList.size() > 0) {
                positionIndexSetSer.remove(postRemoveList);
            }
            //删除部门月份分解的数据
            departMonIndexSetSer.remove(listDepartMon);
        }
        super.remove(id);
    }

    //分解年度部门指标
    @Override
    public DepartYearIndexSetBO seperateDepartYear(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        if (StringUtils.isBlank(departYearIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        DepartYearIndexSet temp = super.findById(departYearIndexSetTO.getId());
        //分解成部门数据
        List<DepartMonSerperateTO> list = departYearIndexSetTO.getDepartMonSerperateTOList();
        Double targerSum = 0d;
        if (list != null && list.size() > 0) {
            targerSum = list.stream().filter(str -> null != str.getSerparateTarget()).mapToDouble(DepartMonSerperateTO::getSerparateTarget).sum();
            if (!targerSum.equals(temp.getTarget())) {
                throw new SerException("分解失败,分解目标值必须加起来等于目标值");
            }
            //查询出部门月度指标编号最大值
            String monthIndexNumber = departMonIndexSetSer.findByMaxField("monthIndexNumber", DepartMonIndexSet.class);
            List<DepartMonIndexSet> saveList = new ArrayList<>();
            for (DepartMonSerperateTO str : list) {
                if (temp.getWager() > 0) {
                    DepartMonIndexSet departMonIndexSet = new DepartMonIndexSet();
                    departMonIndexSet.setIndexName(temp.getIndexName());
                    departMonIndexSet.setIndexNumber(temp.getIndexNumber());
                    departMonIndexSet.setYearIndexNumber(temp.getYearIndexNumber());
                    //判断部门月度指标编号最大值是否为空
                    if (monthIndexNumber != null) {
                        departMonIndexSet.setMonthIndexNumber(Integer.parseInt(monthIndexNumber) + 1);
                    } else if (saveList.size() >= 1) {
                        departMonIndexSet.setMonthIndexNumber(saveList.size() + 1);
                    } else {
                        departMonIndexSet.setMonthIndexNumber(1);
                    }
                    departMonIndexSet.setYear(temp.getYear());
                    departMonIndexSet.setMonth(str.getMonthValue());
                    departMonIndexSet.setIndexType(temp.getIndexType());
                    departMonIndexSet.setDimension(temp.getDimension());
                    departMonIndexSet.setDescribtion(temp.getDescribtion());
                    departMonIndexSet.setDepartment(temp.getDepartment());
                    departMonIndexSet.setDepartYearWeight(temp.getDepartYearWeight());
                    departMonIndexSet.setDepartYearWager(temp.getWager());
                    departMonIndexSet.setWeight(str.getSerparateTarget() / temp.getWager() * 100d);
                    departMonIndexSet.setTarget(str.getSerparateTarget());
                    departMonIndexSet.setWager(0d);
                    departMonIndexSet.setComplete(0d);
                    departMonIndexSet.setExamWay("");
                    departMonIndexSet.setWhetherStandar(departMonIndexSet.getComplete() > departMonIndexSet.getTarget() ? "是" : "否");
                    departMonIndexSet.setStandardRate(0d);
                    departMonIndexSet.setExamScore(0d);
                    departMonIndexSet.setWritePerson(userBO.getUsername());
                    departMonIndexSet.setExamDepart(temp.getExamDepart());
                    departMonIndexSet.setDataOrigin("");
                    departMonIndexSet.setExamDuring("");
                    departMonIndexSet.setYearPersion(userBO.getUsername());
                    departMonIndexSet.setYearIndexTime(LocalDate.now());
                    departMonIndexSet.setSeparateStatus(SeparateStatus.NONE);
                    departMonIndexSet.setSeperateComeStatus(SeperateComeStatus.DEPARTYEAR);
                    departMonIndexSet.setDepartYearIndexSetId(departYearIndexSetTO.getId());
                    departMonIndexSet.setCreateTime(LocalDateTime.now());
                    departMonIndexSet.setModifyTime(LocalDateTime.now());
                    saveList.add(departMonIndexSet);
                } else {
                    throw new SerException("分解失败，对赌值要大于0");
                }
            }
//            System.out.println( JSON.toJSONString( saveList));

            departMonIndexSetSer.save(saveList);
        }


        return BeanTransform.copyProperties(temp, DepartYearIndexSetBO.class);
    }

    @Override
    public byte[] departYearReport(ExportExcelDepartTO to) throws SerException {
        DepartYearIndexSetDTO dto = new DepartYearIndexSetDTO();
        if (StringUtils.isNotBlank(to.getDepart())) {
            dto.getConditions().add(Restrict.between("department", to.getDepart()));
        }
        if (StringUtils.isNotBlank(to.getIndexType())) {
            dto.getConditions().add(Restrict.between("indexType", to.getIndexType()));
        }
        if (StringUtils.isNotBlank(to.getDimension())) {
            dto.getConditions().add(Restrict.between("dimension", to.getDimension()));
        }
        if (StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
            LocalDate start = LocalDate.parse(to.getStartTime());
            LocalDate end = LocalDate.parse(to.getEndTime());
            String startYear = String.valueOf(start.getYear());
            String endYear = String.valueOf(end.getYear());
            String[] years = new String[]{startYear, endYear};
            dto.getConditions().add(Restrict.between("year", years));
        }

        List<DepartYearIndexSet> list = super.findByCis(dto);
        List<DepartYearIndexSetExcel> toList = new ArrayList<DepartYearIndexSetExcel>();
        for (DepartYearIndexSet model : list) {
            DepartYearIndexSetExcel excel = new DepartYearIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public List<String> listArea() throws SerException {
        List<String> areaList = new ArrayList<>();
        List<AreaBO> list = departmentDetailAPI.findArea();
        if (list != null && list.size() > 0) {
            areaList = list.stream().map(AreaBO::getArea).collect(Collectors.toList());
        }
        return areaList;
    }

    @Override
    public List<String> listDepart() throws SerException {
        List<String> departList = new ArrayList<>();
        List<OpinionBO> list = departmentDetailAPI.findAllOpinion();
        if (list != null && list.size() > 0) {
            departList = list.stream().map(OpinionBO::getValue).collect(Collectors.toList());
        }
        return departList;
    }

    @Override
    public List<String> listEmp() throws SerException {
        List<String> empList = new ArrayList<>();
        List<UserBO> list = positionDetailUserAPI.findUserList();
        if (list != null && list.size() > 0) {
            empList = list.stream().map(UserBO::getUsername).collect(Collectors.toList());
        }
        return empList;
    }

    // 导入 excel
    @Override
    public void leadExcel(List<DepartYearIndexSetTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser();
        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        String yearIndexNumber = super.findByMaxField("yearIndexNumber", DepartYearIndexSet.class);
        List<DepartYearIndexSet> list = BeanTransform.copyProperties(toList, DepartYearIndexSet.class, true);
        list.stream().forEach(str -> {
            str.setIndexNumber(0);
            str.setYearPersion(userBO.getUsername());
            str.setYearIndexTime(LocalDate.now());
            str.setSeparateStatus(SeparateStatus.NONE);
            str.setSeperateComeStatus(SeperateComeStatus.FILL);
            if (yearIndexNumber != null) {
                str.setYearIndexNumber(Integer.parseInt(yearIndexNumber) + 1);
            } else if (list.size() > 1) {
                str.setYearIndexNumber(list.size() + 1);
            } else {
                str.setYearIndexNumber(1);
            }
            str.setComplete(null == str.getComplete() ? 0d : str.getComplete());
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });

        super.save(list);
    }

    //校验字段是否存在
    private void isExist(DepartYearIndexSetTO to, Integer row) throws SerException {
        if (StringUtils.isBlank(to.getIndexName())) {
            throw new SerException("第" + row + "行的指标名称不能为空");
        }
        if (StringUtils.isBlank(to.getYear())) {
            throw new SerException("第" + row + "行的年份不能为空");
        }
        if (null == to.getDescribtion()) {
            throw new SerException("第" + row + "行的指标权重不能为空");
        }
        if (null == to.getYearTarget()) {
            throw new SerException("第" + row + "行的年度目标值不能为空");
        }
    }

    // 导出excel
    @Override
    public byte[] exportExcel(ExportExcelDepartTO to) throws SerException {
        DepartYearIndexSetDTO dto = new DepartYearIndexSetDTO();
        if (StringUtils.isNotBlank(to.getDepart())) {
            dto.getConditions().add(Restrict.eq("deDepart", to.getDepart()));
        }
        if (StringUtils.isNotBlank(to.getDimension())) {
            dto.getConditions().add(Restrict.gt("dimension", to.getDimension()));
        }

        List<DepartYearIndexSet> list = super.findByCis(dto);
        List<DepartYearIndexSetExcel> toList = new ArrayList<DepartYearIndexSetExcel>();
        for (DepartYearIndexSet model : list) {
            DepartYearIndexSetExcel excel = new DepartYearIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    /**
     * 树状图查询方法
     */
    @Override
    public List<DepartYearIndexSetBO> dendrogram(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        YearIndexSet temp = yearIndexSetSer.findById(id);
        DepartYearIndexSetDTO dto = new DepartYearIndexSetDTO();
        if (temp.getSeparateStatus() == SeparateStatus.SEPERATE) {
            dto.getConditions().add(Restrict.eq("indexNumber", temp.getIndexNumber()));
        }
        List<DepartYearIndexSet> list = super.findByCis(dto);

        List<DepartYearIndexSetBO> bolist = BeanTransform.copyProperties(list, DepartYearIndexSetBO.class);
        return bolist;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<DepartYearIndexSetExcel> yearIndexExports = new ArrayList<>();

        DepartYearIndexSetExcel excel = new DepartYearIndexSetExcel();
        excel.setIndexName("指标名称");
        excel.setYear( "年份" );
        excel.setIndexType("指标类型");
        excel.setDimension("维度");
        excel.setDepartment("责任部门");
        excel.setDescribtion(12d);
        excel.setYearTarget(12d);
        excel.setDepartYearWeight(13d);
        excel.setDepartWeightSum(12d);
        excel.setTarget(100d);
        excel.setWager(10d);
        excel.setComplete(10d);
        excel.setExamWay("考核方式");
        excel.setWhetherStandar("是否达标");
        excel.setStandardRate(0.1d);
        excel.setExamScore(19d);
        excel.setWritePerson("填报人员");
        excel.setExamDepart("考核部门");
        excel.setDataOrigin("数据来源");
        excel.setExamDuring("考核周期" );
        yearIndexExports.add( excel );

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(yearIndexExports, exce);
        return bytes;
    }
}