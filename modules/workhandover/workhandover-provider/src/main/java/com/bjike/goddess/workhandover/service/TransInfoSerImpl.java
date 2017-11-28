package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.workhandover.bo.TransInfoBO;
import com.bjike.goddess.workhandover.dto.TransInfoDTO;
import com.bjike.goddess.workhandover.entity.TransInfo;
import com.bjike.goddess.workhandover.enums.WorkHandoverReason;
import com.bjike.goddess.workhandover.excel.TransInfoExport;
import com.bjike.goddess.workhandover.to.TransInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作交接业务实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-10 05:08 ]
 * @Description: [ 工作交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workhandoverSerCache")
@Service
public class TransInfoSerImpl extends ServiceImpl<TransInfo, TransInfoDTO> implements TransInfoSer {

    @Override
    public List<TransInfoBO> findWorkHandOver(TransInfoDTO dto) throws SerException {
        dto.getSorts ().add ( "createTime=desc" );
        List<TransInfo> transinfo = super.findByPage ( dto );
        List<TransInfoBO> transinfobo = BeanTransform.copyProperties ( transinfo, TransInfoBO.class );
        return transinfobo;
    }

    @Override
    public TransInfoBO getOneWorkHandOver(String id) throws SerException {
        TransInfo transinfo = super.findById ( id );
        return BeanTransform.copyProperties ( transinfo, TransInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TransInfoBO insertWorkHandOver(TransInfoTO to) throws SerException {
        TransInfo transinfo = BeanTransform.copyProperties ( to, TransInfo.class, true );
        transinfo.setCreateTime ( LocalDateTime.now () );
        if (to.getIsHandoff () == true) {
            transinfo.setIsTaskOver ( to.getIsTaskOver () );
        }
        super.save ( transinfo );
        return BeanTransform.copyProperties ( transinfo, TransInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TransInfoBO editWorkHandOver(TransInfoTO to) throws SerException {
        TransInfo transinfo = super.findById ( to.getId () );
        LocalDateTime creatTime = transinfo.getCreateTime ();
        transinfo.setModifyTime ( LocalDateTime.now () );
        transinfo.setCreateTime ( creatTime );
        transinfo.setIsHandoff ( to.getIsHandoff () );
        if (to.getIsHandoff () == true) {
            transinfo.setIsTaskOver ( to.getIsTaskOver () );
        }
        super.update ( transinfo );
        return BeanTransform.copyProperties ( transinfo, TransInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWorkHandOver(String id) throws SerException {
        if(!"".equals(id) && id != null){
            TransInfo model = super.findById(id);
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Long countWorkHandOver(TransInfoDTO dto) throws SerException {
        Long count = super.count ( dto );
        return count;
    }

    private List<TransInfoBO> searchWorkHandOver(TransInfoTO to) throws SerException {
        TransInfoDTO transInfoDto = new TransInfoDTO ();

        if (StringUtils.isNotBlank ( to.getWorkStartTime () )) {
            transInfoDto.getConditions ().add ( Restrict.eq ( "workStartTime", to.getWorkStartTime () ) );
        }
        if (StringUtils.isNotBlank ( to.getWorkEndTime () )) {
            transInfoDto.getConditions ().add ( Restrict.eq ( "workEndTime", to.getWorkEndTime () ) );
        }
        if (StringUtils.isNotBlank ( to.getHandoffPeople () )) {
            transInfoDto.getConditions ().add ( Restrict.like ( "handoffPeople", to.getHandoffPeople () ) );
        }
        if (StringUtils.isNotBlank ( to.getReplacement () )) {
            transInfoDto.getConditions ().add ( Restrict.like ( "replacement", to.getReplacement () ) );
        }
        return BeanTransform.copyProperties ( super.findByCis ( transInfoDto ), TransInfoBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TransInfoBO auditWorkHandOver(TransInfoTO to) throws SerException {
        TransInfo transInfo = super.findById ( to.getId () );
        BeanTransform.copyProperties ( to, transInfo, true );
        if (to.getIsTaskOver () == true) {
            transInfo.setIsAudit ( to.getIsAudit () );
        }
        super.update ( transInfo );
        return BeanTransform.copyProperties ( transInfo, TransInfoBO.class );
    }

    @Override
    public TransInfoBO importExcel(List<TransInfoTO> to) throws SerException {
        List<TransInfo> transInfo = BeanTransform.copyProperties ( to, TransInfo.class, true );
        transInfo.stream ().forEach ( str -> {
            str.setCreateTime ( LocalDateTime.now () );
            str.setModifyTime ( LocalDateTime.now () );
        } );
        super.save ( transInfo );

        TransInfoBO bo = BeanTransform.copyProperties ( new TransInfo (), TransInfoBO.class );
        return bo;
    }

    @Override
    public byte[] exportExcel(TransInfoDTO dto) throws SerException {


        if (StringUtils.isNotBlank ( dto.getWorkStartTime () )) {
            dto.getConditions ().add ( Restrict.eq ( "workStartTime", dto.getWorkStartTime () ) );
        }
        if (StringUtils.isNotBlank ( dto.getWorkEndTime () )) {
            dto.getConditions ().add ( Restrict.eq ( "workEndTime", dto.getWorkEndTime () ) );
        }

        List<TransInfo> list = super.findByCis ( dto );

        List<TransInfoExport> exports = new ArrayList<> ();
        list.stream ().forEach ( str -> {

            TransInfoExport excel = BeanTransform.copyProperties ( str, TransInfoExport.class,"createTime","modifyTime","cause" );
            excel.setCause ( WorkHandoverReason.exportStrConvert (str.getCause ()) );
            exports.add ( excel );
        } );
        Excel excel = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( exports, excel );
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        return new byte[0];
    }
}