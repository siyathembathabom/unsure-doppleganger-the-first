package co.za.share;

import java.security.SecureRandom;

public class UniqueIdentifierCreator {
    private String uniqueID;
    private SecureRandom secureRandom;

    public UniqueIdentifierCreator() {
        this.uniqueID = "#-195-";
        this.secureRandom = new SecureRandom();
    }

    public String createUserID() {
        for (int i = 0; i < 4; i++) {
            this.uniqueID += this.secureRandom.nextInt(9);
        }
        return this.uniqueID;
    }
}
