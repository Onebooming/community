package com.onebooming.community.community.dto;

/**
 * 封装的一个access_token类，定义了相关属性
 * @author Onebooming
 */

public class AccessTokenDTO {
    /*
    Required. The client ID you received from GitHub for your GitHub App.
     */
    private String client_id;
    /*
    client_secret	string	Required. The client secret you received from GitHub for your GitHub App.
     */
    private String client_secret;
    /*
    code	string	Required. The code you received as a response to Step 1.
     */
    private String code;
    /*
    redirect_uri	string	The URL in your application where users are sent after authorization.
     */
    private String redirect_uri;
    /*
    state	string	The unguessable random string you provided in Step 1.
     */
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
