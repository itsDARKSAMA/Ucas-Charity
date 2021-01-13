package dashboard;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class emData {

    int emID;
    String emFname,emLname,emPos,emMobile,emEmail,emUser,emPass,emPre;

    public emData(int emID, String emFname, String emLname, String emPos, String emMobile, String emEmail, String emUser, String emPass, String emPre) {
        this.emID = emID;
        this.emFname = emFname;
        this.emLname = emLname;
        this.emPos = emPos;
        this.emMobile = emMobile;
        this.emEmail = emEmail;
        this.emUser = emUser;
        this.emPass = emPass;
        this.emPre = emPre;
    }

    public int getEmID() {
        return emID;
    }

    public void setEmID(int emID) {
        this.emID = emID;
    }

    public String getEmFname() {
        return emFname;
    }

    public void setEmFname(String emFname) {
        this.emFname = emFname;
    }

    public String getEmLname() {
        return emLname;
    }

    public void setEmLname(String emLname) {
        this.emLname = emLname;
    }

    public String getEmPos() {
        return emPos;
    }

    public void setEmPos(String emPos) {
        this.emPos = emPos;
    }

    public String getEmMobile() {
        return emMobile;
    }

    public void setEmMobile(String emMobile) {
        this.emMobile = emMobile;
    }

    public String getEmEmail() {
        return emEmail;
    }

    public void setEmEmail(String emEmail) {
        this.emEmail = emEmail;
    }

    public String getEmUser() {
        return emUser;
    }

    public void setEmUser(String emUser) {
        this.emUser = emUser;
    }

    public String getEmPass() {
        return emPass;
    }

    public void setEmPass(String emPass) {
        this.emPass = emPass;
    }

    public String getEmPre() {
        return emPre;
    }

    public void setEmPre(String emPre) {
        this.emPre = emPre;
    }
}
