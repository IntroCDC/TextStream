package br.com.introcdc.textstream.text;
/*
 * Written by IntroCDC, Bruno Coêlho at 24/11/2021 - 01:07
 */

import br.com.introcdc.textstream.account.Account;
import br.com.introcdc.textstream.components.database.DatabaseComponents;
import br.com.introcdc.textstream.components.database.FileConfig;
import br.com.introcdc.textstream.components.loader.LoaderComponents;
import br.com.introcdc.textstream.components.login.LoginComponents;
import br.com.introcdc.textstream.text.comment.TextComment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Text {

    private static final List<Text> textList = new ArrayList<>();

    public static List<Text> getTextList() {
        return textList;
    }

    public static List<Text> search(String search) {
        if (search.isEmpty() || search.equalsIgnoreCase("todos") || search.equalsIgnoreCase("all")) {
            return getTextList();
        }
        List<Text> result = new ArrayList<>();
        for (Text text : getTextList()) {
            if (String.valueOf(text.getId()).contains(search)) {
                result.add(text);
            } else if (text.getTitle().toLowerCase().contains(search.toLowerCase())) {
                result.add(text);
            } else if (text.getCreatorAccount().getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(text);
            } else if (text.getType().toLowerCase().contains(search.toLowerCase())) {
                result.add(text);
            } else if (text.getCreated().toLowerCase().contains(search.toLowerCase())) {
                result.add(text);
            } else {
                for (String textIndex : text.getText()) {
                    if (textIndex.toLowerCase().contains(search.toLowerCase())) {
                        result.add(text);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static Text byId(int id) {
        for (Text text : getTextList()) {
            if (text.getId() == id) {
                return text;
            }
        }
        return null;
    }

    private final int id;
    private final String title;
    private final String creator;
    private final String type;
    private final String created;
    private int views;
    private final List<TextComment> commentList;
    private final List<String> like;
    private final List<String> dislike;
    private final List<String> seem;
    private final List<String> text;

    public Text(int id, String title, String creator, String type, String created, int views, List<String> text, List<TextComment> commentList, List<String> like, List<String> dislike, List<String> seem) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.type = type;
        this.created = created;
        this.views = views;
        this.commentList = commentList;
        this.text = text;
        this.seem = seem;
        this.like = like;
        this.dislike = dislike;
        getTextList().add(this);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public Account getCreatorAccount() {
        return Account.get(getCreator());
    }

    public String getType() {
        return type;
    }

    public String getCreated() {
        return created;
    }

    public int getViews() {
        return views;
    }

    public void addView() {
        this.views++;
        save();
    }

    public List<String> getLike() {
        return like;
    }

    public boolean addLike(String username) {
        getDislike().remove(username);
        if (getLike().contains(username)) {
            getLike().remove(username);
            save();
            return false;
        }

        getLike().add(username);
        save();
        return true;
    }

    public List<String> getDislike() {
        return dislike;
    }

    public boolean addDislike(String username) {
        getLike().remove(username);
        if (getDislike().contains(username)) {
            getDislike().remove(username);
            save();
            return false;
        }

        getDislike().add(username);
        save();
        return true;
    }

    public List<String> getSeem() {
        return seem;
    }

    public void addSeem(String username) {
        if (!getSeem().contains(username)) {
            getSeem().add(username);
            save();
        }
    }

    public List<String> getText() {
        return text;
    }

    public List<TextComment> getCommentList() {
        return commentList;
    }

    public void addComment(TextComment textComment) {
        getCommentList().add(textComment);
        save();
    }

    public File saveFile() {
        return new File("texts", LoginComponents.stringToAes(String.valueOf(getId()), LoaderComponents.password()));
    }

    public void save() {
        FileConfig config = new FileConfig();
        config.put("id", String.valueOf(getId()));
        config.put("title", getTitle());
        config.put("creator", getCreator());
        config.put("type", getType());
        config.put("created", getCreated());
        config.put("views", String.valueOf(getViews()));
        config.put("lines", String.valueOf(getText().size()));
        for (int i = 0; i < getText().size(); i++) {
            config.put("line." + i, getText().get(i));
        }
        config.put("comments", String.valueOf(getCommentList().size()));
        for (int i = 0; i < getCommentList().size(); i++) {
            config.put("comment." + i, getCommentList().get(i).toString());
        }
        config.put("like", DatabaseComponents.usernameListToString(getLike()));
        config.put("dislike", DatabaseComponents.usernameListToString(getDislike()));
        config.put("seem", DatabaseComponents.usernameListToString(getSeem()));
        config.save(saveFile());
    }

}
