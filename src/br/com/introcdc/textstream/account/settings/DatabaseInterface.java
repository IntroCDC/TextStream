package br.com.introcdc.textstream.account.settings;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 23:49
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.database.FileConfig;
import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;

import java.io.File;

public interface DatabaseInterface {

    Account getAccount();

    /**
     * Encrypted File with Account Settings
     */
    default File saveFile() {
        return new File("accounts", LoginComponents.stringToAes(getAccount().getUsername().toLowerCase(), LoaderComponents.password()));
    }

    /**
     * Save Account Details in Encrypted File
     */
    default void save() {
        FileConfig config = new FileConfig();
        config.put("name", getAccount().getName());
        config.put("email", getAccount().getEmail());
        config.put("password", getAccount().getPasswordHash());
        config.put("expires", String.valueOf(getAccount().getExpires()));
        config.save(saveFile());
    }

    /**
     * Check if next payment day is less then current time
     */
    default boolean isEnabled() {
        return System.currentTimeMillis() <= getAccount().getExpires();
    }

}
