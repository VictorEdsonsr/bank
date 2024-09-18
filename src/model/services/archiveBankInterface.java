package model.services;

import model.entities.Account;

import java.util.List;

public interface archiveBankInterface {
    void getAccounts( List<Account> accountList, String path);
    void createAccount(Account account, String path);
    void updateAccount(String idName, Account account, String path);
}
