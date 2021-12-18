package br.com.introcdc.textstream.account;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 22:42
 */

import br.com.introcdc.textstream.account.settings.DatabaseInterface;
import br.com.introcdc.textstream.account.settings.LoginInterface;
import br.com.introcdc.textstream.components.database.FileConfig;

import java.util.ArrayList;
import java.util.List;

public class Account implements LoginInterface, DatabaseInterface {

    /**
     * Accounts Cache
     */
    private static final List<Account> accountList = new ArrayList<>();

    public static List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Account Getters
     */
    public static Account get(String username) {
        for (Account account : getAccountList()) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }

    public static Account getByName(String name) {
        for (Account account : getAccountList()) {
            if (account.getName().equalsIgnoreCase(name)) {
                return account;
            }
        }
        return null;
    }

    public static Account getByEmail(String email) {
        for (Account account : getAccountList()) {
            if (account.getEmail().equalsIgnoreCase(email)) {
                return account;
            }
        }
        return null;
    }

    private final String username;
    private String passwordHash;

    private String name;
    private String email;
    private long expires = 0;

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String passwordHash, String name, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
        save();
    }

    /**
     * Load Account Details
     */
    public boolean load() {
        FileConfig config = new FileConfig();
        config.load(saveFile());
        if (!config.isLoaded()) {
            return false;
        }

        this.passwordHash = config.get("password");
        this.name = config.get("name");
        this.email = config.get("email");
        this.expires = Long.parseLong(config.get("expires"));
        return true;
    }

    public void loadInCache() {
        getAccountList().add(this);
    }

    public void unloadInCache() {
        getAccountList().remove(this);
    }

    @Override
    public Account getAccount() {
        return this;
    }

}
