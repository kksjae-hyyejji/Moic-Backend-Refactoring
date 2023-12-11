package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class ValidationException extends BusinessException {

    public ValidationException(ExceptionEnum e) {
        super(e);
    }
}
