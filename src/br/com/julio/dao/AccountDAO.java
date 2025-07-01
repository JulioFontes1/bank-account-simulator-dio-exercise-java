package br.com.julio.dao;
import br.com.julio.exception.SpecialCheckNotSufficient;
import br.com.julio.model.AccountModel;
import br.com.julio.validator.AccountValidator;

public class AccountDAO {

    private final AccountModel accountModel;


    public AccountDAO(AccountModel accountModel) {
        this.accountModel = accountModel;
    }


    public double consultBalance(){
        return accountModel.getBalance();
    }

    public double consultSpecialCheck(){
        return accountModel.getSpecialCheckValue();
    }

    public double consultSpecialCheckInUse(){
        return accountModel.getSpecialCheckValueInUse();
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


        try {
            double specialCheckValue = accountModel.getSpecialCheckValue();
            double currentBalance = accountModel.getBalance();
            double balance = currentBalance - value;

            AccountValidator.specialCheckValidator(specialCheckValue, balance, value);

            if (balance < 0){
                accountModel.setUseSpecialCheck(true);
            }
            accountModel.setBalance(balance);
        }catch (SpecialCheckNotSufficient ex){
            ex.printStackTrace();
        }


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
