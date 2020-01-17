package com.example.opendoorapp;

import android.content.Context;

public class WorkerJSON {

  Workers[] currentWorkers;

  public WorkerJSON(Context context) {

    if(new String("" + JSON.loadJSON(context, Workers.class, "Workerlist.json")).equals(null)){

      currentWorkers = originalWorkerlist();
      JSON.saveJSON(context, currentWorkers, "Workerlist.json");
    }
    else{

      currentWorkers = JSON.loadJSON(context, Workers.class, "Workerlist.json").getWorkerList();

    }
  }

  private Workers[] originalWorkerlist(){

    Workers brianne = new Workers("Brianne", "Brianne@camroseopendoor.com", true);
    Workers mariah = new Workers("Mariah", "mariah@camroseopendoor.com", true);
    Workers chelsea = new Workers("Chelsea", "Chelsea@camroseopendoor.com", true);
    Workers javan = new Workers("Javan", "javan@camroseopendoor.com", true);
    Workers silas = new Workers("Silas", "silas@camproseopendoor.com", false);
    Workers jessica = new Workers("Jessica", "jessica@camroseopendoor.com", false);
    Workers wayne = new Workers("Wayne", "wayne@camroseopendoor.com", false);

    Workers[] workerList = {brianne, mariah, chelsea, javan, silas, jessica, wayne};

    return workerList;
  }
}
