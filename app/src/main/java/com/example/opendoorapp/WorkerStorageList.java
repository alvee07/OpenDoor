package com.example.opendoorapp;

import java.util.ArrayList;

public class WorkerStorageList {

  private ArrayList<Workers> workerStorageList;

  public WorkerStorageList(){

    workerStorageList = new ArrayList<>();

    ArrayList<String> brianneEmail = new ArrayList<String>();
    brianneEmail.add("Brianne@camroseopendoor.com");
    Workers brianne = new Workers("Brianne", brianneEmail, true);
    workerStorageList.add(brianne);

    ArrayList<String> mariahEmail = new ArrayList<String>();
    mariahEmail.add("mariah@camroseopendoor.com");
    Workers mariah = new Workers("Mariah", mariahEmail, true);
    workerStorageList.add(mariah);

    ArrayList<String> chelseaEmail = new ArrayList<String>();
    chelseaEmail.add("Chelsea@camroseopendoor.com");
    Workers chelsea = new Workers("Chelsea",chelseaEmail , true);
    workerStorageList.add(chelsea);

    ArrayList<String> javanEmail = new ArrayList<String>();
    javanEmail.add("Javan.stamp@camrosepcn.com");
    Workers javan = new Workers("Javan", javanEmail, true);
    workerStorageList.add(javan);

    ArrayList<String> silasEmail = new ArrayList<String>();
    silasEmail.add("silas@camroseopendoor.com");
    Workers silas = new Workers("Silas", silasEmail, false);
    workerStorageList.add(silas);

    ArrayList<String> jessicaEmail = new ArrayList<String>();
    jessicaEmail.add("Jessica@camroseopendoor.com");
    Workers jessica = new Workers("Jessica", jessicaEmail, false);
    workerStorageList.add(jessica);

    ArrayList<String> wayneEmail = new ArrayList<String>();
    wayneEmail.add("Wayne@camroseopendoor.com");
    Workers wayne = new Workers("Wayne", wayneEmail, false);
    workerStorageList.add(wayne);

  }//constructor

  public ArrayList<Workers> getWorkerStorageList() {
    return workerStorageList;
  }

  @Override
  public String toString() {
    return "WorkerStorageList{" +
            "workerStorageList=" + workerStorageList +
            '}';
  }
}
