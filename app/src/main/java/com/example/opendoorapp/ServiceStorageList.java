package com.example.opendoorapp;

import java.util.ArrayList;

public class ServiceStorageList {

  private ArrayList<Service> serviceStorageList;

  public ServiceStorageList() {

    serviceStorageList = new ArrayList<>();

    /* ========================================================================================= */
    ArrayList<String> iAmInCrisisEmail = new ArrayList<>();
    iAmInCrisisEmail.add("Brianne@camroseopendoor.com");
    iAmInCrisisEmail.add("wayne@camroseopendoor.com");
    iAmInCrisisEmail.add("mariah@camroseopendoor.com");
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");

    Service iAmInCrisis = new Service("I AM IN CRISIS",
                                            iAmInCrisisEmail, true);
    serviceStorageList.add(iAmInCrisis);

    /* ========================================================================================= */
    ArrayList<String> outreachEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");

    Service outreach = new Service("Outreach", outreachEmail, true);
    serviceStorageList.add(outreach);

    /* ========================================================================================= */

    ArrayList<String> housingEmail = new ArrayList<>();
    iAmInCrisisEmail.add("mariah@camroseopendoor.com");
    iAmInCrisisEmail.add("wayne@camroseopendoor.com");


    Service housing = new Service("Housing", housingEmail, true);
    serviceStorageList.add(housing);


    /* ========================================================================================= */

    ArrayList<String> fasdSupportsEmail = new ArrayList<>();
    iAmInCrisisEmail.add("Chelsea@camroseopendoor.com");


    Service fasdSupport = new Service("FASD Supports",
                                                fasdSupportsEmail, true);
    serviceStorageList.add(fasdSupport);

    /* ========================================================================================= */

    ArrayList<String> restorativeJusticeEmail = new ArrayList<>();
    iAmInCrisisEmail.add("silas@camroseopendoor.com");
    iAmInCrisisEmail.add("Brianne@camroseopendoor.com");


    Service restorativeJustive = new Service("Restorative Justice",
                                                  restorativeJusticeEmail, true);
    serviceStorageList.add(restorativeJustive);

    /* ========================================================================================= */

    ArrayList<String> communityHelpersEmail = new ArrayList<>();
    iAmInCrisisEmail.add("carson@camroseopendoor.com");


    Service communityHelpers = new Service("Community Helpers",
                                              communityHelpersEmail, false);
    serviceStorageList.add(communityHelpers);

    /* ========================================================================================= */


    ArrayList<String> idEmail = new ArrayList<>();
    iAmInCrisisEmail.add("Brianne@camroseopendoor.com");
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service id = new Service("ID", idEmail, false);
    serviceStorageList.add(id);

    /* ========================================================================================= */

    ArrayList<String> tenantEducationEmail = new ArrayList<>();
    iAmInCrisisEmail.add("Brianne@camroseopendoor.com");
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service tenantEducation = new Service("Tenant Education",
                                            tenantEducationEmail, false);
    serviceStorageList.add(tenantEducation);

    /* ========================================================================================= */

    ArrayList<String> counsellingEmail = new ArrayList<>();
    iAmInCrisisEmail.add("Brianne@camroseopendoor.com");


    Service counselling = new Service("Counselling",
                                            counsellingEmail, false);
    serviceStorageList.add(counselling);

    /* ========================================================================================= */

    ArrayList<String> foodEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service food = new Service("Food", foodEmail, true);
    serviceStorageList.add(food);

    /* ========================================================================================= */

    ArrayList<String> clothesEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service clothes = new Service("Clothes", clothesEmail, true);
    serviceStorageList.add(clothes);

    /* ========================================================================================= */

    ArrayList<String> rideEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service ride = new Service("Ride", rideEmail, true);
    serviceStorageList.add(ride);

    /* ========================================================================================= */

    ArrayList<String> someoneToTalkToEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service someoneToTalkTo = new Service("Someone To Talk To",
                                                    someoneToTalkToEmail, true);
    serviceStorageList.add(someoneToTalkTo);

    /* ========================================================================================= */

    ArrayList<String> helpWithHomeworkEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service helpWithHomework = new Service("Help With Homework",
                                                helpWithHomeworkEmail, true);
    serviceStorageList.add(helpWithHomework);

    /* ========================================================================================= */

    ArrayList<String> informationEmail = new ArrayList<>();
    iAmInCrisisEmail.add("outreach@camroseopendoor.com");


    Service information = new Service("Information",
                                          informationEmail, true);
    serviceStorageList.add(information);

    /* ========================================================================================= */

  }//constructor

  public ArrayList<Service> getServiceStorageList() {
    return serviceStorageList;
  }

  @Override
  public String toString() {
    return "ServiceStorageList{" +
            "serviceStorageList=" + serviceStorageList +
            '}';
  }
}
