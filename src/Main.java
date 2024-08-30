import model.entities.Account;
import model.entities.Currency;
import model.entities.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            List<Account> accountList = new ArrayList<>();

            String path = "/home/victorreis/study/bank/src/data/account.csv";
            File accountData = new File(path);

            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(accountData))){
                String heaaders = bufferedReader.readLine();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    String[] accountArray = line.split(";");
                    String name = accountArray[0];
                    String birthDate = accountArray[1];
                    Person person = new Person(name,birthDate);

                    Boolean active = Boolean.parseBoolean(accountArray[2]);
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
                                "Data de nascimento: " + account.getPerson().getBithDate() + " - " +
                                "Conta Ativa: " + active + " - " +
                                "Sálario: " + account.getCurrency() + " " + formatted
                        );
                System.out.println();
            }
        sc.close();
    }
}