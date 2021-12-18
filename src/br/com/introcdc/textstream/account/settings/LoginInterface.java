package br.com.introcdc.textstream.account.settings;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 22:46
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.login.LoginComponents;

public interface LoginInterface {

    Account getAccount();

    /**
     * Create hash from string and compare if is equals
     */
    default boolean auth(String password) {
        return LoginComponents.hashSha512(password).equals(getAccount().getPasswordHash());
    }

}
