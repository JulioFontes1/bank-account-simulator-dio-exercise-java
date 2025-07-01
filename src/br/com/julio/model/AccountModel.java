package br.com.julio.model;

public class AccountModel {

    private boolean createdAccount = false;
    private String name;
    private double balance;
    private double specialCheckValue;
    private double specialCheckValueInUse = 0;
    private boolean useSpecialCheck = false;

    public AccountModel() {
    }

    public AccountModel(double balance) {
        this.balance = balance;
    }

    public AccountModel(String name, double balance) {
        this.name = name;
        this.balance = balance;
        createAccount(balance);
    }

    public boolean isCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(boolean createdAccount) {
        this.createdAccount = createdAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSpecialCheckValue() {
        return specialCheckValue;
    }

    public void setSpecialCheckValue(double specialCheckValue) {
        this.specialCheckValue = specialCheckValue;
    }

    public boolean isUseSpecialCheck() {
        return useSpecialCheck;
    }

    public void setUseSpecialCheck(boolean useSpecialCheck) {
        this.useSpecialCheck = useSpecialCheck;
    }

    private void createAccount(final double initialBalace){
        if(initialBalace < 500.0){
            this.specialCheckValue = 50.0;
        }else {
            this.specialCheckValue = initialBalace * (50D /100D);
        }
    }

    public double getSpecialCheckValueInUse() {
        return specialCheckValueInUse;
    }

    public void setSpecialCheckValueInUse() {
        if(this.balance < 0){
            this.specialCheckValueInUse = this.specialCheckValue - (this.specialCheckValue + this.balance);
        }
    }
}
