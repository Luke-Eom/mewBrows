package com.book.mew.userFeign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoogleRequestAccessToken {

    private String code;

    private String client_id;

    private String client_secret;

    private String redirect_uri;

    private String grant_type;

}
