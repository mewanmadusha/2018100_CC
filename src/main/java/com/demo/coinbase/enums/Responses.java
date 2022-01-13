package com.demo.coinbase.enums;

public enum Responses implements EnumType{

    REGISTER_SUCCESS("Registration Success!"),
    REGISTER_UNSUCCESS("Registration not Success!"),
    LOGIN_SUCCESS("Login Success!"),
    LOGIN_UNSUCCESS_1("No found User!"),
    LOGIN_UNSUCCESS_2("Inactive User!"),
    LOGIN_UNSUCCESS_3("Login not Success! Password mismatch"),
    ACTIVATE_SUCCESS("Activated Success!"),
    ACTIVATE_UNSUCCESS_1("No user found"),
    ACTIVATE_UNSUCCESS_2("Wrong Security code!"),
    LINKACC_UNSUCCESS_1("No found User!"),
    LINKACC_UNSUCCESS_2("not valid mpin"),
    LINKACC_UNSUCCESS_3("Already exist account"),
    LINKACC_SUCCESS("Account link  success!"),
    WALLET_CREDIT_SUCCESS("Wallet topup success!"),
    WALLET_CREDIT_UNSUCCESS("Wallet topup not success!"),
    WALLET_DEBIT_SUCCESS("Wallet payment success!"),
    WALLET_DEBIT_UNSUCCESS("Wallet payment not success!"),;

    private String s;

    Responses(String s) {
        this.s=s;
    }

    @Override
    public String getString() {
        return s;
    }
}
