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
    outreachEmail.add("outreach@camroseopendoor.com");

    Service outreach = new Service("Outreach", outreachEmail, true);
    serviceStorageList.add(outreach);

    /* ========================================================================================= */

    ArrayList<String> housingEmail = new ArrayList<>();
    housingEmail.add("mariah@camroseopendoor.com");
    housingEmail.add("wayne@camroseopendoor.com");


    Service housing = new Service("Housing", housingEmail, true);
    serviceStorageList.add(housing);


    /* ========================================================================================= */

    ArrayList<String> fasdSupportsEmail = new ArrayList<>();
    fasdSupportsEmail.add("Chelsea@camroseopendoor.com");


    Service fasdSupport = new Service("FASD Supports",
                                                fasdSupportsEmail, true);
    serviceStorageList.add(fasdSupport);

    /* ========================================================================================= */

    ArrayList<String> restorativeJusticeEmail = new ArrayList<>();
    restorativeJusticeEmail.add("silas@camroseopendoor.com");
    restorativeJusticeEmail.add("Brianne@camroseopendoor.com");


    Service restorativeJustive = new Service("Restorative Justice",
                                                  restorativeJusticeEmail, true);
    serviceStorageList.add(restorativeJustive);

    /* ========================================================================================= */

    ArrayList<String> communityHelpersEmail = new ArrayList<>();
    communityHelpersEmail.add("carson@camroseopendoor.com");


    Service communityHelpers = new Service("Community Helpers",
                                              communityHelpersEmail, false);
    serviceStorageList.add(communityHelpers);

    /* ========================================================================================= */


    ArrayList<String> idEmail = new ArrayList<>();
    idEmail.add("Brianne@camroseopendoor.com");
    idEmail.add("outreach@camroseopendoor.com");


    Service id = new Service("ID", idEmail, false);
    serviceStorageList.add(id);

    /* ========================================================================================= */

    ArrayList<String> tenantEducationEmail = new ArrayList<>();
    tenantEducationEmail.add("Brianne@camroseopendoor.com");
    tenantEducationEmail.add("outreach@camroseopendoor.com");


    Service tenantEducation = new Service("Tenant Education",
                                            tenantEducationEmail, false);
    serviceStorageList.add(tenantEducation);

    /* ========================================================================================= */

    ArrayList<String> counsellingEmail = new ArrayList<>();
    counsellingEmail.add("Brianne@camroseopendoor.com");


    Service counselling = new Service("Counselling",
                                            counsellingEmail, false);
    serviceStorageList.add(counselling);

    /* ========================================================================================= */

    ArrayList<String> foodEmail = new ArrayList<>();
    foodEmail.add("outreach@camroseopendoor.com");


    Service food = new Service("Food", foodEmail, true);
    serviceStorageList.add(food);

    /* ========================================================================================= */

    ArrayList<String> clothesEmail = new ArrayList<>();
    clothesEmail.add("outreach@camroseopendoor.com");


    Service clothes = new Service("Clothes", clothesEmail, true);
    serviceStorageList.add(clothes);

    /* ========================================================================================= */

    ArrayList<String> rideEmail = new ArrayList<>();
    rideEmail.add("outreach@camroseopendoor.com");


    Service ride = new Service("Ride", rideEmail, true);
    serviceStorageList.add(ride);

    /* ========================================================================================= */

    ArrayList<String> someoneToTalkToEmail = new ArrayList<>();
    someoneToTalkToEmail.add("outreach@camroseopendoor.com");


    Service someoneToTalkTo = new Service("Someone to talk to",
                                                    someoneToTalkToEmail, true);
    serviceStorageList.add(someoneToTalkTo);

    /* ========================================================================================= */

    ArrayList<String> helpWithHomeworkEmail = new ArrayList<>();
    helpWithHomeworkEmail.add("outreach@camroseopendoor.com");


    Service helpWithHomework = new Service("Help With Homework",
                                                helpWithHomeworkEmail, true);
    serviceStorageList.add(helpWithHomework);

    /* ========================================================================================= */

    ArrayList<String> informationEmail = new ArrayList<>();
    informationEmail.add("outreach@camroseopendoor.com");


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
