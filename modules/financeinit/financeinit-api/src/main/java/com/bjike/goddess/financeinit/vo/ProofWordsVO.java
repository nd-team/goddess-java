package com.bjike.goddess.financeinit.vo;

import com.bjike.goddess.financeinit.enums.ProofCharacter;

/**
 * 凭证字表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 03:09 ]
 * @Description: [ 凭证字表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProofWordsVO {

    /**
     * id
     */
    private String id;
    /**
     * 凭证字
     */
    private ProofCharacter proofCharacter;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProofCharacter getProofCharacter() {
        return proofCharacter;
    }

    public void setProofCharacter(ProofCharacter proofCharacter) {
        this.proofCharacter = proofCharacter;
    }
}