package com.orange.security.exception;

import com.orange.common.exception.BusinessException;

/**
 * Created by cgj on 2016/4/10.
 */
public class AuthBusinessException extends BusinessException {
    @Override
    protected String getPropertiesPath() {
        return "/config/properties/business_code.properties";
    }

    public AuthBusinessException(int errCode) {
        super(errCode);
    }

    public AuthBusinessException(String message) {
        super(message);
    }
}
