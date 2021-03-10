package com.merchant.merchant.service.impl;

import com.merchant.merchant.bean.MerchantQuery;
import com.merchant.merchant.dao.MerchantQueryRepository;
import com.merchant.merchant.service.MerchantQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantQueryServiceImpl implements MerchantQueryService {

    @Autowired
    MerchantQueryRepository merchantQueryRepository;

    @Override
    public List<MerchantQuery> findAllQuery() {
        return merchantQueryRepository.findAll();
    }

    @Override
    public MerchantQuery findByQueryId(Integer queryId) {
        return merchantQueryRepository.findByMerchantQueryId(queryId);
    }

    @Override
    public List<MerchantQuery> findQueryByMerchantId(Integer merhcantId) {
        return merchantQueryRepository.findByMechantID(merhcantId);
    }

    @Override
    public MerchantQuery saveQuery(MerchantQuery merchantQuery) {
        return merchantQueryRepository.save(merchantQuery);
    }

    @Override
    public long totalCountQuery() {
        return merchantQueryRepository.countTotalQuery();
    }

    @Override
    public List<MerchantQuery> findByStatus(String status) {
        return merchantQueryRepository.findByStatus(status);
    }

    @Override
    public void changeStatus(Integer merchantQueryId) {
        merchantQueryRepository.changeStatus(merchantQueryId);
    }
}
