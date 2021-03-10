package com.merchant.merchant.service;

import com.merchant.merchant.bean.MerchantQuery;

import java.util.List;

public interface MerchantQueryService {
    public List<MerchantQuery> findAllQuery();
    public MerchantQuery findByQueryId(Integer queryId);
    public List<MerchantQuery> findQueryByMerchantId(Integer merhcantId);
    public MerchantQuery saveQuery(MerchantQuery merchantQuery);
    public long totalCountQuery();
    public List<MerchantQuery> findByStatus(String status);
    public void changeStatus(Integer merchantQueryId);

}
