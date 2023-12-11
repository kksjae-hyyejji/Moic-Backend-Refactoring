package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class DeniedException extends BusinessException {

    public DeniedException(ExceptionEnum e) {
        super(e);
    }
}
