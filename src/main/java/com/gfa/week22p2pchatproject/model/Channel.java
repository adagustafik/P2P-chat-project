package com.gfa.week22p2pchatproject.model;

import lombok.Data;

@Data
public class Channel {
    private Integer id;
    private String name;
    private String description;
    private String iconUrl;
    private String admin;
    private String secret;
}
