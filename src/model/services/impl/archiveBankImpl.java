package model.services.impl;

import Utils.DateUtils;
import model.entities.Account;
import model.entities.Currency;
import model.entities.Person;
import model.services.getArchiveBank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class archiveBankImpl implements getArchiveBank {

    @Override
    public void upload(List<Account> accountList, String path) {
        File accountData = new File(path);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(accountData))){
            String heaaders = bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] accountArray = line.split(";");
                String name = accountArray[0];
                String birthDate = accountArray[1];
                Person person = new Person(name, DateUtils.formatAndParseStringToLocalDate(birthDate));

                boolean active = Boolean.parseBoolean(accountArray[2]);
                Double salary = Double.parseDouble(accountArray[3]);

                Currency currency = Currency.valueOf(accountArray[4]);

                Account account = new Account(person,currency,active,salary);
                accountList.add(account);
            }
        }catch (IOException e){
            System.out.println("Capturamos um erro: " + e.getMessage());
        }

        for(Account account : accountList){
            String active = account.getActive() ? "SIM" : "NÃO";
            String formatted = String.format("%.2f", account.getSalary()).replace(".", ",");

            System.out.println("DADOS DA CONTA:");
            System.out.println("Nome: " + account.getPerson().getName() + " - " +
                    "Idade: " + account.getPerson().getAge() + " - " +
                    "Data de nascimento: " + DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) + " - " +
                    "Conta Ativa: " + active + " - " +
                    "Sálario: " + account.getCurrency() + " " + formatted
            );
            System.out.println();
        }
    }
}
