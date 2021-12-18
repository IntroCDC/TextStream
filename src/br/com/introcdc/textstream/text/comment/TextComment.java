package br.com.introcdc.textstream.text.comment;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 02:11
 */

import br.com.introcdc.textstream.account.Account;

public record TextComment(String creator, String when, String comment) {

    public String getCreator() {
        return creator;
    }

    public String getWhen() {
        return when;
    }

    public String getComment() {
        return comment;
    }

    public Account getCreatorAccount() {
        return Account.get(getCreator());
    }

    @Override
    public String toString() {
        return getCreator() + "-/-/-/" +
                getWhen() + "-/-/-/" +
                getComment();
    }

}
