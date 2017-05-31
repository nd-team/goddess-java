package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.entity.YearIndexSet;
import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.balancecard.to.DepartMonSerperateTO;
import com.bjike.goddess.balancecard.to.DepartYearIndexSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private DepartMonIndexSetSer departMonIndexSetSer;
    @Autowired
    private PositionIndexSetSer positionIndexSetSer;
    @Autowired
    private YearIndexSetSer yearIndexSetSer;

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

    @Override
    public List<DepartYearIndexSetBO> listDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getIndexName())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("indexName", departYearIndexSetDTO.getIndexName()));
        }
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getYear())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("year", departYearIndexSetDTO.getYear()));
        }
        if (StringUtils.isNotBlank(departYearIndexSetDTO.getDepartment())) {
            departYearIndexSetDTO.getConditions().add(Restrict.like("department", departYearIndexSetDTO.getDepartment()));
        }

        List<DepartYearIndexSet> list = super.findByCis(departYearIndexSetDTO, true);
        List<DepartYearIndexSetBO> listBO = BeanTransform.copyProperties(list, DepartYearIndexSetBO.class);
        return listBO;
    }

    @Override
    public DepartYearIndexSetBO addDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        DepartYearIndexSet temp = BeanTransform.copyProperties(departYearIndexSetTO, DepartYearIndexSet.class, true);
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
        departYearIndexSet.setTarget(departYearIndexSetTO.getTarget());
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
            editYearComplete( oldYearComplete,  departYearIndexSet );

        }

        super.update(departYearIndexSet);
        return BeanTransform.copyProperties(departYearIndexSet, DepartYearIndexSetBO.class);
    }

    /**
     * 修改年指标完成值
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
            List<DepartMonIndexSet> saveList = new ArrayList<>();
            for (DepartMonSerperateTO str : list) {
                DepartMonIndexSet departMonIndexSet = new DepartMonIndexSet();
                departMonIndexSet.setIndexName(temp.getIndexName());
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
            }

            departMonIndexSetSer.save(saveList);
        }

        return BeanTransform.copyProperties(temp, DepartYearIndexSetBO.class);
    }
}