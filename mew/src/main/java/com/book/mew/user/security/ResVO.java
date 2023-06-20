package com.book.mew.user.security;

import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;

@ToString
public class ResVO {
    private final int status;
    private final String message;
    private final Integer size;
    private final ArrayList<?> items;

    @Builder
    public ResVO(int status, String message, Integer size, ArrayList<?> items) {
        this.status = status;
        this.message = message;
        this.size = size;
        this.items = items;
    }
}
