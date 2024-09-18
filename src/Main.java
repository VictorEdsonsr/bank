import Utils.DateUtils;
import model.entities.Account;


import model.entities.Currency;
import model.entities.Person;
import model.services.archiveBankInterface;
import model.services.impl.archiveBankImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean auth = true;
        int option;
        archiveBankInterface archiveBank = new archiveBankImpl();
        String path = "/home/victorreis/study/bank/src/data/account.csv";
        List<Account> accountList = new ArrayList<>();

        System.out.println("SISTEMA BANCÁRIO");

        while(auth){
            System.out.println("Digite o número para escolher a opção:");
            System.out.println("1.Cadastrar");
            System.out.println("2.Listar");
            System.out.println("3.Atualizar");
            System.out.println("4.Deletar");
            System.out.println("5.sair");
            option = sc.nextInt();

            switch (option){
                case 1:
                    sc.nextLine();
                    System.out.println("REGISTRO DE CONTA");
                    System.out.print("Nome: ");
                    String name = sc.nextLine();

                    System.out.print("Data de nascimento (dd/MM/yyyy): ");
                    String birthDate = sc.nextLine();
                    Person person = new Person(name, DateUtils.formatAndParseStringToLocalDate(birthDate));

                    System.out.print("Salário: ");
                    Double salary = sc.nextDouble();

                    System.out.print("Tipo de Moeda (RS,USD,EUR,JPY):");
                    String currency = sc.next();

                    Account account = new Account(person,Currency.valueOf(currency),true,salary);
                    archiveBank.createAccount(account,path);
                    break;
                case 2:
                    archiveBank.getAccounts(accountList,path);
                case 3:
                    sc.nextLine();
                    System.out.println("Digite o nome de quem está procurando: ");
                    String idName = sc.nextLine();

                    System.out.println("ATUALIZAÇÃO DE CONTA");
                    System.out.print("Nome: ");
                    String nameUpdated = sc.nextLine();

                    System.out.print("Data de nascimento (dd/MM/yyyy): ");
                    String birthDateUpdated = sc.nextLine();
                    Person personUpdated = new Person(nameUpdated, DateUtils.formatAndParseStringToLocalDate(birthDateUpdated));

                    System.out.print("Salário: ");
                    Double salaryUpdated = sc.nextDouble();

                    System.out.print("Tipo de Moeda (RS,USD,EUR,JPY):");
                    String currencyUpdated = sc.next();

                    Account accountUpdated = new Account(personUpdated,Currency.valueOf(currencyUpdated),true,salaryUpdated);

                    archiveBank.updateAccount(idName, accountUpdated, path);
                    break;
                case 5:
                    System.out.println("Até a próxima!!");
                    auth = false;
                    break;
                default:
                    System.out.println("Digite uma das opções apresentadas!");
                    break;
            }
        }

        sc.close();
    }
}