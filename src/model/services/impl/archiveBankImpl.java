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
            StringBuilder sb = new StringBuilder();

            sb.append("Nome: ");
            sb.append(account.getPerson().getName());
            sb.append(" - ");
            sb.append("Idade: ");
            sb.append(account.getPerson().getAge());
            sb.append(" - ");
            sb.append("Data de nascimento: ");
            sb.append(DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()));
            sb.append(" - ");
            sb.append("Conta Ativa: ");
            sb.append(active);
            sb.append(" - ");
            sb.append("Sálario: ");
            sb.append(account.getCurrency());
            sb.append(" ");
            sb.append(formatted);

            System.out.println("DADOS DA CONTA:");
            System.out.println( String.valueOf(sb));
            System.out.println();
        }
    }

    @Override
    public void createAccount(Account account, String path) {
        StringBuilder sb = new StringBuilder();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            sb.append(account.getPerson().getName());
            sb.append(";");
            sb.append(DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()));
            sb.append(";");
            sb.append(String.valueOf(account.getActive()));
            sb.append(";");
            sb.append(String.valueOf(account.getSalary()));
            sb.append(";");
            sb.append(String.valueOf(account.getCurrency()));
            bufferedWriter.write(String.valueOf(sb));
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
                StringBuilder sb = new StringBuilder();
                for (Account account : accountList){
                    sb.append(account.getPerson().getName());
                    sb.append(";");
                    sb.append(DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()));
                    sb.append(";");
                    sb.append(String.valueOf(account.getActive()));
                    sb.append(";");
                    sb.append(String.valueOf(account.getSalary()));
                    sb.append(";");
                    sb.append(String.valueOf(account.getCurrency()));

                    bufferedWriter.write(String.valueOf(sb));
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

                StringBuilder sb = new StringBuilder();
                for (Account account : accountsFilterList) {
                    sb.append(account.getPerson().getName());
                    sb.append(";");
                    sb.append(DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()));
                    sb.append(";");
                    sb.append(account.getActive());
                    sb.append(";");
                    sb.append(account.getSalary());
                    sb.append(";");
                    sb.append(account.getCurrency());

                    bufferedWriter.write(String.valueOf(sb));
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Capturamos um ao deletar: " + e.getMessage());
        }
    }
}