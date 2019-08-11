package com.fyle.bankservice.service;

import com.fyle.bankservice.datastore.filter.DBExpression;
import com.fyle.bankservice.models.BankModel;

import java.util.List;

public interface DbServiceInterface {

    List<BankModel> fetch(String entity);

    List<BankModel> fetchWithPagination(String entity, List<DBExpression> args, int currentPage, int size);
}
