package com.example.opendoorapp;

import java.util.ArrayList;

public class WorkerStorageList {

  private ArrayList<Workers> workerStorageList;

  public WorkerStorageList(){

    workerStorageList = new ArrayList<>();

    Workers brianne = new Workers("Brianne", "Brianne@camroseopendoor.com", true);
    workerStorageList.add(brianne);

    Workers mariah = new Workers("Mariah", "mariah@camroseopendoor.com", true);
    workerStorageList.add(mariah);

    Workers chelsea = new Workers("Chelsea", "Chelsea@camroseopendoor.com", true);
    workerStorageList.add(chelsea);

    Workers javan = new Workers("Javan", "Javan.stamp@camrosepcn.com", true);
    workerStorageList.add(javan);

    Workers silas = new Workers("Silas", "silas@camroseopendoor.com", false);
    workerStorageList.add(silas);

    Workers jessica = new Workers("Jessica", "Jessica@camroseopendoor.com", false);
    workerStorageList.add(jessica);

    Workers wayne = new Workers("Wayne", "Wayne@camroseopendoor.com", false);
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
