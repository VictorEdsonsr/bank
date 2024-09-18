package model.services;

import model.entities.Account;

import java.util.List;

public interface getArchiveBank {
    void upload( List<Account> accountList, String path);
}
