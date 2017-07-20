package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.excel.RentalExport;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 租房信息 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房信息 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalSerImpl extends ServiceImpl<Rental, RentalDTO> implements RentalSer {
    @Override
    public Long count(RentalDTO rentalDTO) throws SerException {
        Long count = super.count(rentalDTO);
        return count;
    }

    @Override
    public RentalBO getOne(String id) throws SerException {
        Rental rental = super.findById(id);
        return BeanTransform.copyProperties(rental, RentalBO.class);
    }

    @Override
    public List<RentalBO> findListRental(RentalDTO rentalDTO) throws SerException {

        List<Rental> rentals = super.findByCis(rentalDTO, true);
        List<RentalBO> rentalBOS = BeanTransform.copyProperties(rentals, RentalBO.class);
        return rentalBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalBO insertRental(RentalTO rentalTO) throws SerException {
        Rental rental = BeanTransform.copyProperties(rentalTO, Rental.class, true);
        rental.setCreateTime(LocalDateTime.now());
        super.save(rental);
        return BeanTransform.copyProperties(rental, RentalBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalBO editRental(RentalTO rentalTO) throws SerException {

        Rental rental = super.findById(rentalTO.getId());
        BeanTransform.copyProperties(rentalTO, rental, true);
        rental.setModifyTime(LocalDateTime.now());
        super.update(rental);
        return BeanTransform.copyProperties(rentalTO, RentalBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRental(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public byte[] exportExcel(RentalDTO dto) throws SerException {
        if (null != dto.getArea()) {
            dto.getConditions().add(Restrict.in("area", dto.getArea()));
        }
        List<Rental> list = super.findByCis(dto);
        List<RentalExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            RentalExport export = BeanTransform.copyProperties(str, RentalExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentalBO> rentalBOS = super.findBySql("select distinct area from accommodation_rental group by area order by area asc ", RentalBO.class, fields);

        List<String> areasList = rentalBOS.stream().map(RentalBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }

    @Override
    /**
     * chenjunhao
     * 获取所有租房地址
     */
    public Set<String> allAddress() throws SerException {
        List<Rental> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Rental rental : list) {
            set.add(rental.getAddress());
        }
        return set;
    }

}
