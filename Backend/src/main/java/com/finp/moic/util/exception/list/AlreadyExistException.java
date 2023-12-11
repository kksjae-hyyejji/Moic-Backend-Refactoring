package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class AlreadyExistException extends BusinessException {

    public AlreadyExistException(ExceptionEnum e) {
        super(e);
    }
}
