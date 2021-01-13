package dashboard;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class npData {

    int npID;
    String npFname, npLname;
    int npFm;
    String npMobile;

    public npData(int npID, String npFname, String npLname, int npFm, String npMobile) {
        this.npID = npID;
        this.npFname = npFname;
        this.npLname = npLname;
        this.npFm = npFm;
        this.npMobile = npMobile;
    }

    public int getNpID() {
        return npID;
    }

    public void setNpID(int npID) {
        this.npID = npID;
    }

    public String getNpFname() {
        return npFname;
    }

    public void setNpFname(String npFname) {
        this.npFname = npFname;
    }

    public String getNpLname() {
        return npLname;
    }

    public void setNpLname(String npLname) {
        this.npLname = npLname;
    }

    public int getNpFm() {
        return npFm;
    }

    public void setNpFm(int npFm) {
        this.npFm = npFm;
    }

    public String getNpMobile() {
        return npMobile;
    }

    public void setNpMobile(String npMobile) {
        this.npMobile = npMobile;
    }
}
