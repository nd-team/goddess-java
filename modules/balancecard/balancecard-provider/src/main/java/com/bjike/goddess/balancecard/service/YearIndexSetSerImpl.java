package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.YearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.dto.YearIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.entity.YearIndexSet;
import com.bjike.goddess.balancecard.enums.GuideAddrStatus;
import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.excel.YearIndexSetExcel;
import com.bjike.goddess.balancecard.to.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
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
 * 年度指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class YearIndexSetSerImpl extends ServiceImpl<YearIndexSet, YearIndexSetDTO> implements YearIndexSetSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartYearIndexSetSer departYearIndexSetSer;
    @Autowired
    private DepartMonIndexSetSer departMonIndexSetSer;
    @Autowired
    private PositionIndexSetSer positionIndexSetSer;
    @Autowired
    private BalancecardPermissionSer cusPermissionSer;

    //下拉导航权限
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("yearIndex");
        obj.setDescribesion("年度指标");
        obj.setFlag(true);
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = departYearIndexSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("departYearIndex");
        obj.setDescribesion("部门年度指标");
        obj.setFlag(true);
        list.add(obj);

        Boolean flagSeeCate = departMonIndexSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contractcategory");
        obj.setDescribesion("部门月度指标");
        obj.setFlag(true);
        list.add(obj);

        Boolean flagSeeEmail = positionIndexSetSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("岗位指标");
        obj.setFlag(true);
        list.add(obj);

        return list;
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
    public Long countYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(yearIndexSetDTO.getIndexName())) {
            yearIndexSetDTO.getConditions().add(Restrict.like("indexName", yearIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(yearIndexSetDTO.getYear())) {
            yearIndexSetDTO.getConditions().add(Restrict.like("year", yearIndexSetDTO.getYear()));
        }
        Long count = super.count(yearIndexSetDTO);
        return count;
    }

    @Override
    public YearIndexSetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        YearIndexSet temp = super.findById(id);
        YearIndexSetBO bo = BeanTransform.copyProperties(temp, YearIndexSetBO.class);
        return bo;
    }

    @Override
    public List<YearIndexSetBO> listYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(yearIndexSetDTO.getIndexName())) {
            yearIndexSetDTO.getConditions().add(Restrict.like("indexName", yearIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(yearIndexSetDTO.getYear())) {
            yearIndexSetDTO.getConditions().add(Restrict.like("year", yearIndexSetDTO.getYear()));
        }
        List<YearIndexSet> list = super.findByCis(yearIndexSetDTO, true);
        List<YearIndexSetBO> listBO = BeanTransform.copyProperties(list, YearIndexSetBO.class);
        return listBO;
    }

    @Override
    public YearIndexSetBO addYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        YearIndexSet yearIndexSets = BeanTransform.copyProperties(yearIndexSetTO, YearIndexSet.class, true);
        yearIndexSets.setYearPersion(userBO.getUsername());
        //查询所有的指标编号之中最大的指标编号
        String indexNumber = super.findByMaxField("indexNumber", YearIndexSet.class);
        //判断indexNumber是否为空
        if (indexNumber != null) {
            //给新增的数据的指标编号加1
            yearIndexSets.setIndexNumber(Integer.parseInt(indexNumber) + 1);
        } else {
            yearIndexSets.setIndexNumber(1);
        }
        yearIndexSets.setYearIndexTime(LocalDate.now());
        yearIndexSets.setSeparateStatus(SeparateStatus.NONE);

        yearIndexSets.setComplete(null == yearIndexSetTO.getComplete() ? 0d : yearIndexSetTO.getComplete());

        yearIndexSets.setCreateTime(LocalDateTime.now());
        yearIndexSets.setModifyTime(LocalDateTime.now());

        super.save(yearIndexSets);
        return BeanTransform.copyProperties(yearIndexSets, YearIndexSetBO.class);
    }

    @Override
    public YearIndexSetBO editYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        if (StringUtils.isBlank(yearIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        YearIndexSet yearIndexSet = super.findById(yearIndexSetTO.getId());

        //判断分解状态 已分解：重新分解下面的
        //若是 未分解的话，直接修改
        yearIndexSet.setIndexName(yearIndexSetTO.getIndexName());
        yearIndexSet.setYear(yearIndexSetTO.getYear());
        yearIndexSet.setIndexType(yearIndexSetTO.getIndexType());
        yearIndexSet.setDimension(yearIndexSetTO.getDimension());
        yearIndexSet.setDescribtion(yearIndexSetTO.getDescribtion());
        yearIndexSet.setYearTarget(yearIndexSetTO.getYearTarget());
        yearIndexSet.setComplete(yearIndexSetTO.getComplete());
        yearIndexSet.setDataOrigin(yearIndexSetTO.getDataOrigin());
        yearIndexSet.setExamDuring(yearIndexSetTO.getExamDuring());
        yearIndexSet.setModifyTime(LocalDateTime.now());
        if (SeparateStatus.SEPERATE.equals(yearIndexSet.getSeparateStatus())) {
            //判断分解状态 已分解：重新分解下面的
            //修改部门年度
            List<DepartYearIndexSet> listDYear = seperateDepartYearIndex(yearIndexSet);
            //修改部门月度
            if (listDYear != null && listDYear.size() > 0) {

                List<DepartMonIndexSet> listDMon = seperateDepartMonIndex(listDYear);
                //修改岗位
                if (listDMon != null && listDMon.size() > 0) {
                    List<PositionIndexSet> listPost = seperatePostIndex(listDMon);
                }
            }

        }
        super.update(yearIndexSet);
        return BeanTransform.copyProperties(yearIndexSet, YearIndexSetBO.class);
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
    private List<DepartMonIndexSet> seperateDepartMonIndex(List<DepartYearIndexSet> listDYear) throws SerException {

        List<DepartMonIndexSet> monthList = new ArrayList<>();
        for (DepartYearIndexSet dy : listDYear) {

            DepartMonIndexSetDTO dmDTO = new DepartMonIndexSetDTO();
            dmDTO.getConditions().add(Restrict.eq("departYearIndexSetId", dy.getId()));
            List<DepartMonIndexSet> listDMon = departMonIndexSetSer.findByCis(dmDTO);


            listDMon.stream().forEach(str -> {
                str.setIndexName(dy.getIndexName());
                str.setYear(dy.getYear());
                str.setIndexType(dy.getIndexType());
                str.setDimension(dy.getDimension());
                str.setDescribtion(dy.getDescribtion());
                str.setDepartment(dy.getDepartment());
                str.setDepartYearWeight(dy.getDepartYearWeight());
                str.setDepartYearWager(dy.getWager());
                str.setTarget(dy.getYearTarget() / 12);
                str.setWeight(str.getTarget() / str.getWager() * 100d);
                str.setComplete(0d);
                str.setStandardRate(0d);
                str.setExamScore(0d);
                str.setWhetherStandar(str.getComplete() > str.getTarget() ? "是" : "否");
                str.setModifyTime(LocalDateTime.now());
                monthList.add(str);
            });
        }

        departMonIndexSetSer.update(monthList);
        return monthList;
    }


    /**
     * 重新分解部门年度指标
     *
     * @throws SerException
     */
    private List<DepartYearIndexSet> seperateDepartYearIndex(YearIndexSet yearIndexSet) throws SerException {

        DepartYearIndexSetDTO dyDTO = new DepartYearIndexSetDTO();
        dyDTO.getConditions().add(Restrict.eq("yearIndexSetId", yearIndexSet.getId()));
        List<DepartYearIndexSet> listDYear = departYearIndexSetSer.findByCis(dyDTO);

        listDYear.stream().forEach(str -> {
            str.setIndexName(yearIndexSet.getIndexName());
            str.setIndexNumber(yearIndexSet.getIndexNumber());
            str.setYear(yearIndexSet.getYear());
            str.setIndexType(yearIndexSet.getIndexType());
            str.setDimension(yearIndexSet.getDimension());
            str.setDescribtion(yearIndexSet.getDescribtion());
            str.setYearTarget(yearIndexSet.getYearTarget());
            str.setTarget(yearIndexSet.getYearTarget() / str.getDepartYearWeight() * 100d);
            str.setWhetherStandar(str.getComplete() > str.getTarget() ? "是" : "否");
            str.setModifyTime(LocalDateTime.now());

        });
        departYearIndexSetSer.update(listDYear);
        return listDYear;
    }


    @Override
    public void deleteYearIndexSet(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        YearIndexSet temp = super.findById(id);
        DepartYearIndexSetDTO dyDTO = new DepartYearIndexSetDTO();
        dyDTO.getConditions().add(Restrict.eq("yearIndexSetId", id));
        List<DepartYearIndexSet> listDYear = departYearIndexSetSer.findByCis(dyDTO);

        //删除部门年度指标
        if (listDYear != null && listDYear.size() > 0) {
            departYearIndexSetSer.remove(listDYear);
            //所有部门年id
            List<String> dyIdList = listDYear.stream().map(DepartYearIndexSet::getId).collect(Collectors.toList());
            DepartMonIndexSetDTO dmDTO = new DepartMonIndexSetDTO();
            dmDTO.getConditions().add(Restrict.in("departYearIndexSetId", (String[]) dyIdList.toArray()));
            List<DepartMonIndexSet> listDMon = departMonIndexSetSer.findByCis(dmDTO);
            //删除部门月指标
            if (listDMon != null && listDMon.size() > 0) {
                departMonIndexSetSer.remove(listDMon);
                //所有部门月id
                List<String> dmIdList = listDMon.stream().map(DepartMonIndexSet::getId).collect(Collectors.toList());
                PositionIndexSetDTO pDTO = new PositionIndexSetDTO();
                pDTO.getConditions().add(Restrict.in("departMonIndexSetId", (String[]) dmIdList.toArray()));
                List<PositionIndexSet> postList = positionIndexSetSer.findByCis(pDTO);
                if (postList != null && postList.size() > 0) {
                    positionIndexSetSer.remove(postList);
                } else {
                    //删除年指标
                    super.remove(id);
                }
            } else {
                //删除年指标
                super.remove(id);
            }
        } else {
            //删除年指标
            super.remove(id);
        }
    }

    //分解年度指标
    @Override
    public YearIndexSetBO seperateDepartYear(YearIndexSetTO yearIndexSetTO) throws SerException {
        if (StringUtils.isBlank(yearIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        YearIndexSet temp = super.findById(yearIndexSetTO.getId());
        Double weigthSum = 0d;
        List<DepartSerperateTO> departSerperateTOS = yearIndexSetTO.getDepartSerperateTOS();
        if (departSerperateTOS != null && departSerperateTOS.size() > 0) {
            weigthSum = departSerperateTOS.stream().filter(str -> null != str.getIndexWeight()).mapToDouble(DepartSerperateTO::getIndexWeight).sum();
            if (weigthSum != 100d) {
                throw new SerException("分解失败,权重之和必须加起来等于100");
            }
            //查询部门年度指标编号最大值
            String yearIndexNumber = departYearIndexSetSer.findByMaxField("yearIndexNumber", DepartYearIndexSet.class);
            List<DepartYearIndexSet> departYearIndexSetList = new ArrayList<>();
            departSerperateTOS.stream().forEach(str -> {
                DepartYearIndexSet departYearIndexSet = new DepartYearIndexSet();
                departYearIndexSet.setIndexNumber(temp.getIndexNumber());
                //判断部门年度指标编号最大值是否为空
                if (yearIndexNumber != null) {
                    departYearIndexSet.setYearIndexNumber(Integer.parseInt(yearIndexNumber) + 1);
                } else if (departYearIndexSetList.size() >= 1) {
                    departYearIndexSet.setYearIndexNumber(departYearIndexSetList.size() + 1);

                } else {
                    departYearIndexSet.setYearIndexNumber(1);
                }
                departYearIndexSet.setIndexName(temp.getIndexName());
                departYearIndexSet.setYear(temp.getYear());
                departYearIndexSet.setIndexType(temp.getIndexType());
                departYearIndexSet.setDimension(temp.getDimension());
                departYearIndexSet.setDescribtion(temp.getDescribtion());
                departYearIndexSet.setYearTarget(temp.getYearTarget());
                departYearIndexSet.setDepartment(str.getDepartment());
                departYearIndexSet.setDepartYearWeight(str.getIndexWeight());
                departYearIndexSet.setDepartWeightSum(100d);
                departYearIndexSet.setTarget(str.getTarget());
                departYearIndexSet.setWager(0d);
                departYearIndexSet.setComplete(0d);
                departYearIndexSet.setExamWay("");
                departYearIndexSet.setWhetherStandar("否");
                departYearIndexSet.setStandardRate(0d);
                departYearIndexSet.setExamScore(0d);
                departYearIndexSet.setWritePerson(userBO.getUsername());
                departYearIndexSet.setExamDepart("");
                departYearIndexSet.setDataOrigin("");
                departYearIndexSet.setExamDuring("");
                departYearIndexSet.setYearPersion(userBO.getUsername());
                departYearIndexSet.setYearIndexTime(LocalDate.now());
                departYearIndexSet.setSeparateStatus(SeparateStatus.NONE);
                departYearIndexSet.setSeperateComeStatus(SeperateComeStatus.YEAR);
                departYearIndexSet.setYearIndexSetId(yearIndexSetTO.getId());
                departYearIndexSet.setCreateTime(LocalDateTime.now());
                departYearIndexSet.setModifyTime(LocalDateTime.now());
                departYearIndexSetList.add(departYearIndexSet);
            });
            departYearIndexSetSer.save(departYearIndexSetList);
        }
        //分解成功后更新年度指标分解状态为1  2017-07-11 23:02 jiangzaixuan 改
        temp.setSeparateStatus(SeparateStatus.SEPERATE);
        super.update(temp);

        return BeanTransform.copyProperties(temp, YearIndexSetBO.class);
    }


    @Override
    public List<String> yearList() throws SerException {
        //获取所有年
        List<String> yearList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int start = 1900;
        int end = now.getYear();
        String year = "";
        while (start <= end) {
            year = start + "";
            start = start + 1;
            yearList.add(year);
        }
        return yearList;
    }

    @Override
    public void leadExcel(List<YearIndexSetTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser();
        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        String indexNumber = super.findByMaxField("indexNumber", YearIndexSet.class);
        List<YearIndexSet> list = BeanTransform.copyProperties(toList, YearIndexSet.class, true);
        list.stream().forEach(str -> {

            str.setYearPersion(userBO.getUsername());
            str.setYearIndexTime(LocalDate.now());
            str.setSeparateStatus(SeparateStatus.NONE);
            if (indexNumber != null) {
                str.setIndexNumber(Integer.parseInt(indexNumber) + 1);
            } else if (list.size() > 1) {
                str.setIndexNumber(list.size() + 1);
            } else {
                str.setIndexNumber(1);
            }
            str.setComplete(null == str.getComplete() ? 0d : str.getComplete());
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });

        super.save(list);
    }

    //校验字段是否存在
    private void isExist(YearIndexSetTO to, Integer row) throws SerException {
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

    @Override
    public byte[] exportExcel(ExportExcelYearTO to) throws SerException {
        YearIndexSetDTO dto = new YearIndexSetDTO();
        if (StringUtils.isNotBlank(to.getIndexName())) {
            dto.getConditions().add(Restrict.eq("indexName", to.getIndexName()));
        }
        if (StringUtils.isNotBlank(to.getYear())) {
            dto.getConditions().add(Restrict.gt("year", to.getYear()));
        }

        List<YearIndexSet> list = super.findByCis(dto);
        List<YearIndexSetExcel> toList = new ArrayList<YearIndexSetExcel>();
        for (YearIndexSet model : list) {
            YearIndexSetExcel excel = new YearIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public byte[] exportYearExcel(ExportExcelYearTO to) throws SerException {
        YearIndexSetDTO dto = new YearIndexSetDTO();
        if (StringUtils.isBlank(to.getStartTime()) && !StringUtils.isBlank(to.getEndTime())) {
            throw new SerException("请输入时间");
        }
        LocalDate start = LocalDate.parse(to.getStartTime());
        LocalDate end = LocalDate.parse(to.getEndTime());
        String startYear = String.valueOf(start.getYear());
        String endYear = String.valueOf(end.getYear());

        String[] years = new String[]{startYear, endYear};
        dto.getConditions().add(Restrict.between("year", years));

        List<YearIndexSet> list = super.findByCis(dto);
        List<YearIndexSetExcel> toList = new ArrayList<YearIndexSetExcel>();
        for (YearIndexSet model : list) {
            YearIndexSetExcel excel = new YearIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public byte[] exportYearDeExcel(ExportExcelYearTO to) throws SerException {
        YearIndexSetDTO dto = new YearIndexSetDTO();
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

        List<YearIndexSet> list = super.findByCis(dto);
        List<YearIndexSetExcel> toList = new ArrayList<YearIndexSetExcel>();
        for (YearIndexSet model : list) {
            YearIndexSetExcel excel = new YearIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    /**
     * 树状图
     * @param yearIndexSetDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<YearIndexSetBO> dendrogram(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        List<YearIndexSet> list = super.findByCis(yearIndexSetDTO, true);
        List<YearIndexSetBO> listBO = BeanTransform.copyProperties(list, YearIndexSetBO.class);
        return listBO;
    }
}