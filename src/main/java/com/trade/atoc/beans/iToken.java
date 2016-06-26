package com.trade.atoc.beans;

/**
 * Created by vu.nt on 2/25/2016.
 */
public class iToken {
    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private String id_token;
    private int not_before_policy;
    private String session_state;

    public String getAccess_token() {
        return access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public int getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getId_token() {
        return id_token;
    }

    public int getNot_before_policy() {
        return not_before_policy;
    }

    public String getSession_state() {
        return session_state;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_expires_in(int refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public void setNot_before_policy(int not_before_policy) {
        this.not_before_policy = not_before_policy;
    }

    public void setSession_state(String session_state) {
        this.session_state = session_state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        iToken token = (iToken) o;

        if (expires_in != token.expires_in) return false;
        if (refresh_expires_in != token.refresh_expires_in) return false;
        if (not_before_policy != token.not_before_policy) return false;
        if (!access_token.equals(token.access_token)) return false;
        if (!refresh_token.equals(token.refresh_token)) return false;
        if (!token_type.equals(token.token_type)) return false;
        if (!id_token.equals(token.id_token)) return false;
        return session_state.equals(token.session_state);

    }

    @Override
    public int hashCode() {
        int result = access_token.hashCode();
        result = 31 * result + expires_in;
        result = 31 * result + refresh_expires_in;
        result = 31 * result + refresh_token.hashCode();
        result = 31 * result + token_type.hashCode();
        result = 31 * result + id_token.hashCode();
        result = 31 * result + not_before_policy;
        result = 31 * result + session_state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_expires_in=" + refresh_expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", id_token='" + id_token + '\'' +
                ", not_before_policy=" + not_before_policy +
                ", session_state='" + session_state + '\'' +
                '}';
    }
}
