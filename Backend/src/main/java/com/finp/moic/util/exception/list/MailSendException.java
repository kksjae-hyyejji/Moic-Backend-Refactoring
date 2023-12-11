package com.finp.moic.util.exception.list;

import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;

public class MailSendException extends BusinessException {

    public MailSendException(ExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }
}
