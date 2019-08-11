package com.fyle.bankservice.dao;

import com.fyle.bankservice.datastore.BaseDao;
import com.fyle.bankservice.datastore.filter.DBExpression;
import com.fyle.bankservice.models.BankModel;

import java.util.List;

public class BankDao extends BaseDao {

    public static List<BankModel> fetchBanksByQuery(String entity, List<DBExpression> args, int limit, int offset) {
        return dbService.fetchWithPagination(entity, args, offset, limit);
    }
}
