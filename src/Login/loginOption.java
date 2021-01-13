package Login;

//import org.jetbrains.annotations.Contract;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public enum loginOption {

    admin, employee;

    private loginOption() {
    }

    public String value() {

        return name();
    }

    public static loginOption fromvalue(String Val) {
        return valueOf(Val);
    }

}
