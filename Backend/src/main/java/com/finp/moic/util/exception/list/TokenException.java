package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class TokenException extends BusinessException {

    public TokenException(ExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }
}
