package br.com.introcdc.textstream.sounds;
/*
 * Written by IntroCDC, Bruno Coêlho at 30/11/2021 - 19:22
 */

public enum SoundEnum {
    TYPE_0(0, "br/com/introcdc/textstream/sounds/type0.wav"),
    TYPE_1(1, "br/com/introcdc/textstream/sounds/type1.wav"),
    TYPE_2(2, "br/com/introcdc/textstream/sounds/type2.wav"),
    TYPE_3(3, "br/com/introcdc/textstream/sounds/type3.wav"),
    TYPE_4(4, "br/com/introcdc/textstream/sounds/type4.wav"),
    TYPE_5(5, "br/com/introcdc/textstream/sounds/type5.wav"),
    TYPE_6(6, "br/com/introcdc/textstream/sounds/type6.wav"),
    TYPE_7(7, "br/com/introcdc/textstream/sounds/type7.wav"),
    TYPE_8(8, "br/com/introcdc/textstream/sounds/type8.wav"),
    TYPE_9(9, "br/com/introcdc/textstream/sounds/type9.wav"),
    TYPE_10(10, "br/com/introcdc/textstream/sounds/type10.wav"),
    TYPE_11(11, "br/com/introcdc/textstream/sounds/type11.wav");

    private final int id;
    private final String file;

    SoundEnum(int id, String file) {
        this.id = id;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public static SoundEnum getSound(int id) {
        for (SoundEnum sound : values()) {
            if (sound.getId() == id) {
                return sound;
            }
        }
        return null;
    }

}
