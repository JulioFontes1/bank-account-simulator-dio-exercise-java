import java.util.Scanner;

import br.com.julio.dao.AccountDAO;
import br.com.julio.exception.SpecialCheckNotSufficient;
import br.com.julio.model.AccountModel;
import br.com.julio.model.MenuOption;

import br.com.julio.model.MenuOption;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AccountModel accountModel = new AccountModel();
        AccountDAO dao = null;
        Scanner scanner = new Scanner(System.in);

        while (true){

            if(!accountModel.isCreatedAccount()){
                System.out.println("Digite seu nome");
                String name = scanner.next();
                System.out.println("Quanto deseja depositar ao abrir a conta");
                double initialBalance = scanner.nextDouble();

                accountModel = new AccountModel(name, initialBalance);
                dao = new AccountDAO(accountModel);
                accountModel.setCreatedAccount(true);
            }

            System.out.println("Selecione o número com a opção que deseja\n");

            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Consultar cheque especial");
            System.out.println("3 - Realizar depósito");
            System.out.println("4 - Sacar");
            System.out.println("5 - Realizar pagamento");
            System.out.println("6 - Verificar se a conta está usando cheque especial");
            System.out.println("7 - Sair");

            int userInput = scanner.nextInt();

            if(userInput < 1 || userInput > MenuOption.values().length){
                System.out.printf("Opção %s inválida", userInput);
            }

            MenuOption selectedOption = MenuOption.values()[userInput - 1];


            switch (selectedOption){
                case CONSULT_BALANCE -> {
                    if(dao != null) {
                        System.out.printf("Seu saldo é de R$ %s\n", dao.consultBalance());
                    }
                }
                case CONSULT_SPECIAL_CHECK -> {
                    if(dao != null) {
                        if(dao.consultSpecialCheckInUse() > dao.consultSpecialCheck()){
                            System.out.printf("Você usou todo o valor (R$ %s) disponível no seu cheque especial\n", dao.consultSpecialCheck());
                        }else {
                            System.out.printf("Você usou R$ %s de R$ %s disponível no seu cheque especial\n", dao.consultSpecialCheckInUse(), dao.consultSpecialCheck());
                        }
                    }

                }
                case MAKE_DEPOSIT -> {
                    System.out.println("Quanto deseja depositar?");
                    double value = scanner.nextDouble();
                    if(dao != null) {
                        dao.makeDeposit(value);
                        System.out.printf("Depósito realizado no valor de %s\n", value);
                    }
                }
                case WITHDRAW -> {
                    System.out.println("Quanto deseja sacar?");
                    double value = scanner.nextDouble();

                    if(dao != null) {
                        try {
                            dao.makeWithDraw(value);
                            System.out.printf("Saque realizado no valor de R$ %s", value);
                        }catch (SpecialCheckNotSufficient ex){
                            System.out.println(ex.getMessage());
                        }
                    }


                }
                case MAKE_PAYMENT -> {
                    System.out.println("Digite para quem será destinado o pagamento (Nº da conta ou chave pix)");
                    String paymentDestination = scanner.next();
                    System.out.println("Digite o valor do pagamento");
                    double value = scanner.nextDouble();

                    if(dao != null) {
                        try {
                            dao.makePayment(value);
                            System.out.printf("Pagamento no valor de R$ %s realizado para %s\n", value, paymentDestination);
                        }catch (SpecialCheckNotSufficient ex){
                            System.out.println(ex.getMessage());
                        }
                    }

                }
                case VERIFY_USE_SPECIAL_CHECK -> {

                    if(dao != null) {
                        boolean useSpecialCheck = dao.isUseSpecialCheck();
                        var result = useSpecialCheck ? "Você está utilizando o seu cheque especial" : "Você não está utilizando o seu cheque especial";
                        System.out.println(result);
                    }

                }
                case EXIT -> System.exit(0);
            }

        }
    }
}