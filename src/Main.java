import Utils.DateUtils;
import model.entities.Account;


import model.entities.Currency;
import model.entities.Person;
import model.services.getArchiveBank;
import model.services.impl.archiveBankImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accountList = new ArrayList<>();
        String path = "/home/victorreis/study/bank/src/data/account.csv";

        getArchiveBank archiveBank = new archiveBankImpl();

        archiveBank.upload(accountList,path);

//        System.out.println("REGISTRO DE CONTA");
//        System.out.print("Nome: ");
//        String name = sc.nextLine();
//
//        System.out.print("Data de nascimento (dd/MM/yyyy): ");
//        String birthDate = sc.nextLine();
//        Person person = new Person(name, DateUtils.formatAndParseStringToLocalDate(birthDate));
//
//        System.out.print("Salário: ");
//        Double salary = sc.nextDouble();
//
//        System.out.print("Tipo de Moeda (RS,USD,EUR,JPY):");
//        String currency = sc.next();
//
//        Account account = new Account(person,Currency.valueOf(currency),true,salary);
//
//        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
//            bufferedWriter.write(name + ";" + birthDate + ";" + String.valueOf(account.getActive()) + ";" + String.valueOf(account.getSalary()) + ";" + String.valueOf(account.getCurrency()));
//            bufferedWriter.newLine();
//        }catch (IOException e){
//            e.getMessage();
//        }


        sc.close();
    }
}