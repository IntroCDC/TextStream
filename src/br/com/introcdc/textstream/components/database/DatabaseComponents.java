package br.com.introcdc.textstream.components.database;
/*
 * Written by IntroCDC, Bruno Coêlho at 23/11/2021 - 22:44
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.text.Text;
import br.com.introcdc.textstream.text.comment.TextComment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DatabaseComponents {

    /**
     * Create folder if not exists and load accounts
     */
    static void loadAccounts() {
        new File("accounts").mkdirs();
        for (File saveFile : new File("accounts").listFiles()) {
            Account account = new Account(LoginComponents.aesToString(saveFile.getName(), LoaderComponents.password()));
            if (account.load()) {
                account.loadInCache();
            }
        }
    }

    /**
     * Convert List<String> to username string list
     */
    static String usernameListToString(List<String> usernameList) {
        StringBuilder string = new StringBuilder();
        for (String username : usernameList) {
            string.append(" ").append(username);
        }
        return string.toString();
    }

    /**
     * Convert username string list to List<String></String>
     */
    static List<String> stringToUsernameList(String string) {
        List<String> usernameList = new ArrayList<>();
        for (String username : string.split(" ")) {
            if (username.isEmpty()) {
                continue;
            }
            usernameList.add(username);
        }
        return usernameList;
    }

    /**
     * Create folder if not exists and load texts
     */
    static void loadTexts() {
        new File("texts").mkdirs();
        Map<Integer, File> files = new HashMap<>();
        for (File saveFile : new File("texts").listFiles()) {
            int idFile = Integer.parseInt(LoginComponents.aesToString(saveFile.getName(), LoaderComponents.password()));
            files.put(idFile, saveFile);
        }

        while (!files.isEmpty()) {
            int less = Integer.MAX_VALUE;
            for (int key : new ArrayList<>(files.keySet())) {
                if (key > less) {
                    continue;
                }
                less = key;
            }

            File saveFile = files.get(less);
            files.remove(less);

            FileConfig config = new FileConfig();
            config.load(saveFile);
            if (!config.isLoaded()) {
                continue;
            }

            int id = Integer.parseInt(config.get("id", "0"));
            String title = config.get("title", "Não definido");
            String creator = config.get("creator", "naoencontrado");
            String type = config.get("type", "Não encontrado");
            String created = config.get("created", "00/00/0000 - 00:00:00");
            int views = Integer.parseInt(config.get("views", "0"));
            int lines = Integer.parseInt(config.get("lines", "0"));
            List<String> text = new ArrayList<>();
            for (int i = 0; i < lines; i++) {
                text.add(config.get("line." + i, "Não encontrado"));
            }
            int comments = Integer.parseInt(config.get("comments", "0"));
            List<TextComment> commentList = new ArrayList<>();
            for (int i = 0; i < comments; i++) {
                String[] args = config.get("comment." + i,
                                "naoencontrado-/-/-/00/00/0000 - 00:00:00-/-/-/Não encontrado")
                        .split("-/-/-/", 3);
                commentList.add(new TextComment(args[0], args[1], args[2]));
            }
            new Text(id, title, creator, type, created, views, text, commentList,
                    stringToUsernameList(config.get("like", "naoencontrado")),
                    stringToUsernameList(config.get("dislike", "naoencontrado")),
                    stringToUsernameList(config.get("seem", "naoencontrado")));
        }
    }

}
