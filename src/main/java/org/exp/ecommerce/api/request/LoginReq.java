package org.exp.ecommerce.api.request;

import lombok.Value;

@Value
public class LoginReq {
    String email;
    String password;
}
