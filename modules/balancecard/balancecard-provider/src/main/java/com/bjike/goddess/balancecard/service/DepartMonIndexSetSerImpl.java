package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DepartMonIndexSetBO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.entity.YearIndexSet;
import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.excel.DepartMonIndexSetExcel;
import com.bjike.goddess.balancecard.to.DepartMonIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.PostSerperateTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
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

/**
 * 部门月度指标设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class DepartMonIndexSetSerImpl extends ServiceImpl<DepartMonIndexSet, DepartMonIndexSetDTO> implements DepartMonIndexSetSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionIndexSetSer positionIndexSetSer;
    @Autowired
    private DepartYearIndexSetSer departYearIndexSetSer;
    @Autowired
    private YearIndexSetSer yearIndexSetSer;

    @Override
    public Long countDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getIndexName())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("indexName", departMonIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getYear())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("year", departMonIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getMonth())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("month", departMonIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getDepartment())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("department", departMonIndexSetDTO.getDepartment()));
        }
        Long count = super.count(departMonIndexSetDTO);
        return count;
    }

    @Override
    public DepartMonIndexSetBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DepartMonIndexSet temp = super.findById(id);
        DepartMonIndexSetBO bo = BeanTransform.copyProperties(temp, DepartMonIndexSetBO.class);
        return bo;
    }

    @Override
    public List<DepartMonIndexSetBO> listDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getIndexName())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("indexName", departMonIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getYear())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("year", departMonIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getMonth())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("month", departMonIndexSetDTO.getMonth()));
        }
        if (StringUtils.isNotBlank(departMonIndexSetDTO.getDepartment())) {
            departMonIndexSetDTO.getConditions().add(Restrict.like("department", departMonIndexSetDTO.getDepartment()));
        }
        List<DepartMonIndexSet> list = super.findByCis(departMonIndexSetDTO, true);
        List<DepartMonIndexSetBO> listBO = BeanTransform.copyProperties(list, DepartMonIndexSetBO.class);
        return listBO;
    }

    @Override
    public DepartMonIndexSetBO addDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        DepartMonIndexSet temp = BeanTransform.copyProperties(departMonIndexSetTO, DepartMonIndexSet.class, true);
        temp.setWeight(temp.getTarget() / temp.getDepartYearWager() * 100d);
        temp.setComplete(departMonIndexSetTO.getComplete());
        temp.setWhetherStandar(temp.getComplete() > temp.getTarget() ? "是" : "否");
        temp.setStandardRate(temp.getComplete() / temp.getWager());
        temp.setExamScore(temp.getStandardRate() * temp.getWeight());
        temp.setWritePerson(userBO.getUsername());
        temp.setYearPersion(userBO.getUsername());
        temp.setYearIndexTime(LocalDate.now());
        temp.setSeparateStatus(SeparateStatus.NONE);
        temp.setSeperateComeStatus(SeperateComeStatus.FILL);
        temp.setDepartYearIndexSetId(null);
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save(temp);
        return BeanTransform.copyProperties(temp, DepartMonIndexSetBO.class);
    }

    @Override
    public DepartMonIndexSetBO editDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        if (StringUtils.isBlank(departMonIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        DepartMonIndexSet temp = super.findById(departMonIndexSetTO.getId());
        Double oldMonComplete = temp.getComplete();

        temp.setIndexName(departMonIndexSetTO.getIndexName());
        temp.setYear(departMonIndexSetTO.getYear());
        temp.setMonth(departMonIndexSetTO.getMonth());
        temp.setIndexType(departMonIndexSetTO.getIndexType());
        temp.setDimension(departMonIndexSetTO.getDimension());
        temp.setDescribtion(departMonIndexSetTO.getDescribtion());
        temp.setDepartment(departMonIndexSetTO.getDepartment());
        temp.setDepartYearWeight(departMonIndexSetTO.getDepartYearWeight());
        temp.setDepartYearWager(departMonIndexSetTO.getWager());
        temp.setWeight(departMonIndexSetTO.getTarget() / departMonIndexSetTO.getWager() * 100d);
        temp.setTarget(departMonIndexSetTO.getTarget());
        temp.setWager(departMonIndexSetTO.getWager());
        temp.setComplete(departMonIndexSetTO.getComplete());
        temp.setExamWay(departMonIndexSetTO.getExamWay());
        temp.setWhetherStandar(departMonIndexSetTO.getComplete() > departMonIndexSetTO.getTarget() ? "是" : "否");
        temp.setStandardRate(departMonIndexSetTO.getComplete() / departMonIndexSetTO.getWager());
        temp.setExamScore(temp.getStandardRate() * temp.getWeight());
        temp.setExamDepart(departMonIndexSetTO.getExamDepart());
        temp.setDataOrigin(departMonIndexSetTO.getDataOrigin());
        temp.setExamDuring(departMonIndexSetTO.getExamDuring());
        temp.setModifyTime(LocalDateTime.now());
        //若岗位分解状态为“已分解”，向上重新分解，向下重新分解
        //否则直接编辑
        if (temp.getSeparateStatus().equals(SeparateStatus.SEPERATE)) {
            //修改重新分解岗位指标
            List<PositionIndexSet> listPost = seperatePostIndex(temp);
        }
        //从上面分解来的，则修改完成值
        if (temp.getSeperateComeStatus().equals(SeperateComeStatus.DEPARTYEAR)) {
            editDepartYearComplete(oldMonComplete, temp);
        }

        super.update(temp);

        return BeanTransform.copyProperties(temp, DepartMonIndexSetBO.class);
    }

    private void editDepartYearComplete(Double oldMonComplete, DepartMonIndexSet dMon) throws SerException {
        //新的部门月度完成值
        Double newComplete = dMon.getComplete();
        //部门年度id
        String yearId = dMon.getDepartYearIndexSetId();
        DepartYearIndexSet dYear = departYearIndexSetSer.findById(yearId);
        //部门月度旧完成值
        Double oldYearComplete = dYear.getComplete();

        dYear.setComplete(dYear.getComplete() - oldMonComplete + newComplete);
        dYear.setWhetherStandar(dYear.getComplete() > dYear.getTarget() ? "是" : "否");
        dYear.setStandardRate(dYear.getComplete() / dYear.getWager());
        dYear.setExamScore(dYear.getStandardRate() * dYear.getDepartYearWeight());
        dYear.setModifyTime(LocalDateTime.now());

        //修改年指标完成值
        editYearComplete(oldYearComplete, dYear);

        departYearIndexSetSer.update(dYear);

    }

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
    private List<PositionIndexSet> seperatePostIndex(DepartMonIndexSet temp) throws SerException {
        PositionIndexSetDTO pDTO = new PositionIndexSetDTO();
        pDTO.getConditions().add(Restrict.eq("departMonIndexSetId", temp.getId()));
        List<PositionIndexSet> postList = positionIndexSetSer.findByCis(pDTO);

        postList.stream().forEach(str -> {
            str.setIndexName(temp.getIndexName());
            str.setYear(temp.getYear());
            str.setIndexType(temp.getIndexType());
            str.setDimension(temp.getDimension());
            str.setDescribtion(temp.getDescribtion());
            str.setDepartment(temp.getDepartment());
            str.setDepartYearWeight(temp.getDepartYearWeight());
            str.setDepartYearWager(temp.getWager());
            str.setTarget(temp.getTarget() * str.getWeight() / 100d);
            str.setComplete(0d);
            str.setStandardRate(0d);
            str.setExamScore(0d);
            str.setWhetherStandar(str.getComplete() > str.getTarget() ? "是" : "否");
            str.setModifyTime(LocalDateTime.now());
        });

        positionIndexSetSer.update(postList);
        return postList;
    }

    @Override
    public void deleteDepartMonIndexSet(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        DepartMonIndexSet temp = super.findById(id);
        //删除分解的岗位
        if (temp.getSeparateStatus().equals(SeparateStatus.SEPERATE)) {
            PositionIndexSetDTO pdto = new PositionIndexSetDTO();
            pdto.getConditions().add(Restrict.eq("departMonIndexSetId", id));
            List<PositionIndexSet> postList = positionIndexSetSer.findByCis(pdto);
            positionIndexSetSer.remove(postList);
        }
        super.remove(id);
    }

    @Override
    public DepartMonIndexSetBO seperateDepartYear(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        if (StringUtils.isBlank(departMonIndexSetTO.getId())) {
            throw new SerException("id不能为空");
        }
        UserBO userBO = userAPI.currentUser();
        DepartMonIndexSet temp = super.findById(departMonIndexSetTO.getId());
        //分解成部门数据
        List<PostSerperateTO> list = departMonIndexSetTO.getPostSerperateTOList();
        Double weightSum = 0d;
        if (list != null && list.size() > 0) {
            weightSum = list.stream().filter(str -> null != str.getWeight()).mapToDouble(PostSerperateTO::getWeight).sum();
            if (weightSum != 100d) {
                throw new SerException("分解失败，岗位指标权重加起来要等于100 ");
            }
            List<PositionIndexSet> saveList = new ArrayList();
            for (PostSerperateTO str : list) {
                PositionIndexSet positionIndexSet = new PositionIndexSet();
                positionIndexSet.setIndexName(temp.getIndexName());
                positionIndexSet.setYear(temp.getYear());
                positionIndexSet.setMonth(temp.getMonth());
                positionIndexSet.setIndexType(temp.getIndexType());
                positionIndexSet.setDimension(temp.getDimension());
                positionIndexSet.setDescribtion(temp.getDescribtion());
                positionIndexSet.setDepartment(temp.getDepartment());
                positionIndexSet.setDepartYearWeight(temp.getDepartYearWeight());
                positionIndexSet.setDepartYearWager(temp.getDepartYearWager());
                positionIndexSet.setPosition(str.getPostName());
                positionIndexSet.setPositioner(userBO.getUsername());
                positionIndexSet.setWeight(str.getWeight());
                positionIndexSet.setWeightSum(100d);
                positionIndexSet.setTarget(str.getSerparateTarget());
                positionIndexSet.setWager(0d);
                positionIndexSet.setComplete(0d);
                positionIndexSet.setExamWay("");
                positionIndexSet.setWhetherStandar(positionIndexSet.getComplete() > positionIndexSet.getTarget() ? "是" : "否");
                positionIndexSet.setStandardRate(positionIndexSet.getComplete() / positionIndexSet.getWager());
                positionIndexSet.setExamScore(positionIndexSet.getComplete() * positionIndexSet.getWeight());
                positionIndexSet.setWritePerson(userBO.getUsername());
                positionIndexSet.setExamDepart("");
                positionIndexSet.setDataOrigin("");
                positionIndexSet.setExamDuring("");
                positionIndexSet.setPosionIndexPersion(userBO.getUsername());
                positionIndexSet.setPosionIndexTime(LocalDate.now());
                positionIndexSet.setSeperateComeStatus(SeperateComeStatus.DEPARTMONTH);
                positionIndexSet.setDepartMonIndexSetId(temp.getId());
                positionIndexSet.setCreateTime(LocalDateTime.now());
                positionIndexSet.setModifyTime(LocalDateTime.now());
                saveList.add(positionIndexSet);
            }
            if (saveList != null && saveList.size() > 0) {
                positionIndexSetSer.save(saveList);
            }
        }


        return BeanTransform.copyProperties(temp, DepartMonIndexSetBO.class);
    }


    @Override
    public Long countNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        departMonIndexSetDTO.getConditions().add(Restrict.eq("year", year ));
        departMonIndexSetDTO.getConditions().add(Restrict.eq("month", month ));
        Long count = super.count(departMonIndexSetDTO);
        return count;
    }


    @Override
    public List<DepartMonIndexSetBO> listNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        LocalDate now = LocalDate.now();
        String year = now.getYear()+"";
        String month = now.getMonthValue()+"";
        departMonIndexSetDTO.getConditions().add(Restrict.eq("year", year ));
        departMonIndexSetDTO.getConditions().add(Restrict.eq("month", month ));
        List<DepartMonIndexSet> list = super.findByCis(departMonIndexSetDTO, true);
        List<DepartMonIndexSetBO> listBO = BeanTransform.copyProperties(list, DepartMonIndexSetBO.class);
        return listBO;
    }

    @Override
    public byte[] departMonReport(ExportExcelDepartTO to) throws SerException {
        DepartMonIndexSetDTO dto = new DepartMonIndexSetDTO();
        if(StringUtils.isNotBlank(dto.getDepartment())){
            dto.getConditions().add(Restrict.between("department", dto.getDepartment() ));
        }
        if ( StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime()) ) {
            LocalDate start  = LocalDate.parse(to.getStartTime());
            LocalDate end = LocalDate.parse(to.getEndTime());
            String startYear = String.valueOf(start.getYear());
            String endYear = String.valueOf(end.getYear());
            String startMon = String.valueOf(start.getMonthValue());
            String endMon = String.valueOf(end.getMonthValue());
            String [] years = new String[]{startYear,endYear};
            String [] months = new String[]{startMon,endMon};
            dto.getConditions().add(Restrict.between("year", years ));
            dto.getConditions().add(Restrict.between("month", months ));
        }

        if(StringUtils.isNotBlank(to.getIndexType())){
            dto.getConditions().add(Restrict.between("indexType", to.getIndexType() ));
        }
        if(StringUtils.isNotBlank(to.getDimension())){
            dto.getConditions().add(Restrict.between("dimension", to.getDimension() ));
        }

        List<DepartMonIndexSet> list = super.findByCis(dto);
        List<DepartMonIndexSetExcel> toList = new ArrayList<DepartMonIndexSetExcel>();
        for (DepartMonIndexSet model : list) {
            DepartMonIndexSetExcel excel = new DepartMonIndexSetExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }
}