package com.meddjamm.sn.utils;

public final class ConstantDeployment {

    //   private static final String DEST_ENV = "/opt/tomcat/apache-tomcat-9.0.78/webapps/ged_epme/";

    private static final String DEST_ENV = System.getProperty("user.home") + "/hdj/piecejointes/";

    //  private static final String DEST_ENV = "src/main/resources/static/webapp/document/";

    public static final String ged_dmi = DEST_ENV + "multi_ged/dmi";

    //  public static final String ged_dmi = DEST_ENV;

}