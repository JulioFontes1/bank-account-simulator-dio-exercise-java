package br.com.julio.validator;

import br.com.julio.exception.SpecialCheckNotSufficient;

public class AccountValidator {


    public static void specialCheckValidator(final double specialCheck, final double balance, final double value){
        final double totalAvailable = balance + specialCheck;

        if(value > totalAvailable){
            throw new SpecialCheckNotSufficient("Não há limite suficiente no seu cheque especial");
        }
    }

}
