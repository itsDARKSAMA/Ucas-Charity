package dashboard;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public enum dashboardOption {

    admin, employee;

    private dashboardOption() {
    }

    public String value() {

        return name();
    }

    public static dashboardOption fromvalue(String Val) {
        return valueOf(Val);
    }

}
