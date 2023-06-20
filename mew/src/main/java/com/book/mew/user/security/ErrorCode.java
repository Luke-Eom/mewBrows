package com.book.mew.user.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorCode {
    private int resultCode;
    private String resultMsg;

}
