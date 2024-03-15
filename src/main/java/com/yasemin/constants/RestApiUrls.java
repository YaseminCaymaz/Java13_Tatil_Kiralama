package com.yasemin.constants;

public class RestApiUrls {



    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String TEST = "/test";
    private static final String DEV = "/dev";
    private static final String PROD = "/prod";

    private static final String ROOT = DEV + VERSION;

    public static final String AUTH = ROOT + "/auth";

    public static final String USER_PROFILE = ROOT + "/user-profile";
    public static final String BOOKING = ROOT + "/booking";
    public static final String COMMENT = ROOT + "/comment";
    public static final String HOTEL = ROOT + "/hotel";
    public static final String ROOM = ROOT + "/room";
    public static final String TYPE = ROOT + "/type";
    public static final String MAIN_PROPERTIES = ROOT + "/main-properties";

    public static final String ADD = "/add";
    public static final String ACTIVATION = "/activation";
    public static final String GET_USER_PROFILE = "/get-user-profile";
    public static final String REZERVASYON_GECMISI= "/rezervasyon-gecmisi";
    public static final String GET_ALL_BY_NAME = "/get-all-by-name";
    public static final String FAVORI_GET_ALL= "/favori-get-all";
    public static final String YORUM_YAP = "/yorum-yap";
    public static final String FAVORI_ADD= "/favori-add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String GET_ALL = "/get-all";
    public static final String GET_BY_ID = "/get-by-id";
    public static final String DELETE_BY_ID = "/delete-by-id";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";



}
