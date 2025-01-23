package model.services.impl;

import Utils.DateUtils;
import model.entities.Account;
import model.entities.Currency;
import model.entities.Person;
import model.services.archiveBankInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class archiveBankImpl implements archiveBankInterface {

    @Override
    public void getAccounts(List<Account> accountList, String path) {
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

    @Override
    public void createAccount(Account account, String path) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            bufferedWriter.write(account.getPerson().getName() + ";" +
                    DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) +
                    ";" + String.valueOf(account.getActive()) + ";" + String.valueOf(account.getSalary()) +
                    ";" + String.valueOf(account.getCurrency()));
            bufferedWriter.newLine();
        }catch (IOException e){
            e.getMessage();
        }
    }

    @Override
    public void updateAccount(String idName, Account accountUpdated, String path) {
        File accountData = new File(path);
        List<Account> accountList = new ArrayList<>();

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

            for(Account account : accountList){
                if(account.getPerson().getName().equals(idName)){
                    account.setPerson(accountUpdated.getPerson());
                    account.setActive(accountUpdated.getActive());
                    account.setCurrency(accountUpdated.getCurrency());
                    account.setSalary(accountUpdated.getSalary());
                    break;
                }
            }

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
                bufferedWriter.write("Name;birthDate;Active;Salary;Currency;");
                bufferedWriter.newLine();
                for (Account account : accountList){
                    bufferedWriter.write(account.getPerson().getName() + ";" +
                            DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) +
                            ";" + account.getActive() + ";" +
                            account.getSalary() + ";" +
                            account.getCurrency()
                    );
                    bufferedWriter.newLine();
                }
            }
        }catch (IOException e){
            System.out.println("Capturamos um erro ao tentar atualizar: " + e.getMessage());
        }
    }

    @Override
    public void deleteAccount(String idName, String path) {
        File accountData = new File(path);
        List<Account> accountList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(accountData))) {
            String heaaders = bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] accountArray = line.split(";");
                String name = accountArray[0];
                String birthDate = accountArray[1];
                Person person = new Person(name, DateUtils.formatAndParseStringToLocalDate(birthDate));

                boolean active = Boolean.parseBoolean(accountArray[2]);
                Double salary = Double.parseDouble(accountArray[3]);

                Currency currency = Currency.valueOf(accountArray[4]);

                Account account = new Account(person, currency, active, salary);
                accountList.add(account);
            }

            List<Account> accountsFilterList = accountList
                    .stream()
                    .filter(x -> !x.getPerson().getName().equals(idName))
                    .collect(Collectors.toList());

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
                bufferedWriter.write("Name;birthDate;Active;Salary;Currency;");
                bufferedWriter.newLine();
                for (Account account : accountsFilterList) {
                    bufferedWriter.write(account.getPerson().getName() + ";" +
                            DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) +
                            ";" + account.getActive() + ";" +
                            account.getSalary() + ";" +
                            account.getCurrency()
                    );
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Capturamos um ao deletar: " + e.getMessage());
        }
    }
}