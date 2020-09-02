package vip.hengnai.wine.eventbus;

/**
 * on 2019/12/25.
 *
 * @author hua.
 */

public class LoginEvent {
    private String login;

    public String getLogin() {
        return login == null ? "" : login;
    }

    public LoginEvent setLogin(String login) {
        this.login = login;
        return this;
    }
}
