package com.book.mew.user.security;

public enum CommonErrorCode {
    UNAUTHORIZED(401, "Unauthorized"),

    FORBIDDEN(403, "Forbidden");
    // Add more error codes as needed

    private ErrorCode errorCode;

    CommonErrorCode(Integer resultCode, String resultMsg) {
        this.errorCode = new ErrorCode(resultCode, resultMsg);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
