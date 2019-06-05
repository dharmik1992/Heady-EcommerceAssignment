package com.app.headyecommerceapp.interfaces;

public interface ApplicationConfig {
    int POST = 1;
    int GET = 2;
    int DELETE = 3;
    int RAWJSON = 100;
    int PUT = 4;
    int DATALIMIT = 25;

    int REPLACE = 1;
    int ADD = 2;
    int SUCCESS = 200;
    int ALTSUCCESS = 2;
    int FAILURE = 0;
    int SESSIONEXPIRE = 401;
    int NOINTERNETCONNECTION = 606;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 1.0f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    public final static int PAGES = 2;
    public final static int FIRST_PAGE = 0;


}
