package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class NotFoundException extends BusinessException {

    public NotFoundException(ExceptionEnum e) {
        super(e);
    }
}
