package model.services.impl;

import Utils.DateUtils;
import model.entities.Account;
import model.entities.Currency;
import model.entities.Person;
import model.services.archiveBankInterface;

import java.io.*;
import java.util.List;

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
            bufferedWriter.write(account.getPerson().getName() + ";" + DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) + ";" + String.valueOf(account.getActive()) + ";" + String.valueOf(account.getSalary()) + ";" + String.valueOf(account.getCurrency()));
            bufferedWriter.newLine();
        }catch (IOException e){
            e.getMessage();
        }
    }

    @Override
    public void updateAccount(String idName, Account account, String path) {
        File accountData = new File(path);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(accountData))){
            String heaaders = bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine()) != null){
                if(line.contains(idName)){
                    line = account.getPerson().getName() + ";" + DateUtils.formatAndParseLocalDateToString(account.getPerson().getBithDate()) + ";" + String.valueOf(account.getActive()) + ";" + String.valueOf(account.getSalary()) + ";" + String.valueOf(account.getCurrency());
                    break;
                }

            }
        }catch (IOException e){
            System.out.println("Capturamos um erro: " + e.getMessage());
        }


    }
}
