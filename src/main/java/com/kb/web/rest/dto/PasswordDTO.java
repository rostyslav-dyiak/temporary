package com.kb.web.rest.dto;

/**
 * Created by rdyyak on 30.04.15.
 */
public class PasswordDTO {
    private String oldPassword;
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {

        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
            "oldPassword='" + oldPassword + '\'' +
            ", newPassword='" + newPassword + '\'' +
            '}';
    }
}
