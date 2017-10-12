package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.financeinit.dto.ProofWordsDTO;
import com.bjike.goddess.financeinit.entity.ProofWords;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 凭证字业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 03:09 ]
* @Description:	[ 凭证字业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="financeinitSerCache")
@Service
public class ProofWordsSerImpl extends ServiceImpl<ProofWords, ProofWordsDTO> implements ProofWordsSer { 

 }