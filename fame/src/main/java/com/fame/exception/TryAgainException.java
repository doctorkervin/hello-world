package com.fame.exception;

import com.fame.common.ApiResultEnum;

/**
 * @program: hello-world
 * @desc: 自定义重试异常
 * @author: kervin
 * @time: 2019-12-26 16:33
 */
public class TryAgainException extends ApiException {

    public TryAgainException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum);
    }
}
