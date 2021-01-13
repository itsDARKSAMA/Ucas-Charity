
package dashboard;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */
public class whData {
    int whID;
    String whItem,whSize;
    int whQuan;

    public whData(int whID, String whItem, String whSize, int whQuan) {
        this.whID = whID;
        this.whItem = whItem;
        this.whSize = whSize;
        this.whQuan = whQuan;
    }

    public int getWhID() {
        return whID;
    }

    public void setWhID(int whID) {
        this.whID = whID;
    }

    public String getWhItem() {
        return whItem;
    }

    public void setWhItem(String whItem) {
        this.whItem = whItem;
    }

    public String getWhSize() {
        return whSize;
    }

    public void setWhSize(String whSize) {
        this.whSize = whSize;
    }

    public int getWhQuan() {
        return whQuan;
    }

    public void setWhQuan(int whQuan) {
        this.whQuan = whQuan;
    }
}
