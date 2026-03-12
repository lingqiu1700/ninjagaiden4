package ninjagaiden4.helpers;

public interface ModHelper {
    String MOD_ID = "ninjagaiden4";

    static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

}

