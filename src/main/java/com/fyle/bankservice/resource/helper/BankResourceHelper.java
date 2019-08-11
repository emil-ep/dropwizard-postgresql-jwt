package com.fyle.bankservice.resource.helper;

import com.fyle.bankservice.dao.BankDao;
import com.fyle.bankservice.datastore.filter.DBExpression;
import com.fyle.bankservice.models.BankModel;

import java.util.ArrayList;
import java.util.List;

public class BankResourceHelper {

    private static final String FIELD_IFSC = "IFSC";
    private static final String FIELD_BANK_NAME = "BANK_NAME";
    private static final String FIELD_CITY = "CITY";

    public static List<BankModel> fetchBankByIfsc(String entity, String ifsc, int limit, int offset) {

        List<DBExpression> args = new ArrayList<>();
        DBExpression expression = new DBExpression(FIELD_IFSC, ifsc);
        args.add(expression);
        return BankDao.fetchBanksByQuery(entity, args, limit, offset);
    }

    public static List<BankModel> fetchByBankNameAndCity(String entity, String bankName, String city, int limit, int offset) {

        List<DBExpression> args = new ArrayList<>();
        DBExpression bankExpression = new DBExpression(FIELD_BANK_NAME, bankName);
        DBExpression cityExpression = new DBExpression(FIELD_CITY, city);
        args.add(bankExpression);
        args.add(cityExpression);
        return BankDao.fetchBanksByQuery(entity, args, limit, offset);
    }
}
