package br.com.julio.dao;
import br.com.julio.model.AccountModel;

public class AccountDAO {

    private final AccountModel accountModel;

    public AccountDAO(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public void createAccount(final String name, final double initialBalace){
        accountModel.setCreatedAccount(true);
        accountModel.setName(name);
        accountModel.setBalance(initialBalace);

        if(initialBalace < 500.0){
            accountModel.setSpecialCheckValue(50.0);
        }else {
            double specialCheckValue = initialBalace * (50D /100D);
        }
    }

    public double consultBalance(){
        return accountModel.getBalance();
    }

    public double consultSpecialCheck(){
        return accountModel.getSpecialCheckValue();
    }

    public boolean isUseSpecialCheck(){
        return accountModel.isUseSpecialCheck();
    }

    public void makeDeposit(final double value){
        double currentBalance = accountModel.getBalance();

        double balance =  currentBalance + value;

        if (currentBalance < 0 && balance > 0){
            accountModel.setUseSpecialCheck(false);
        }

        accountModel.setBalance(balance);
    }

    public void makeWithDraw(final double value){
        double currentBalance = accountModel.getBalance();

        double balance = currentBalance - value;
        if (balance < 0){
            accountModel.setUseSpecialCheck(true);
        }
        accountModel.setBalance(balance);
    }

    public void makePayment(final double value){
        double currentBalance = accountModel.getBalance();

        double balance = currentBalance - value;
        if (balance < 0){
            accountModel.setUseSpecialCheck(true);
        }

        accountModel.setBalance(balance);
    }

}
