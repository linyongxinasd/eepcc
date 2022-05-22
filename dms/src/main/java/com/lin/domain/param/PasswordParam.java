package com.lin.domain.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String currentPassword;
    private String newPassword;
    private String checkPassword;
}
