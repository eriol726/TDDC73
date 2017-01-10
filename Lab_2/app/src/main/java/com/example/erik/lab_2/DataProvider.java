package com.example.erik.lab_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Erik on 2017-01-10.
 */

public class DataProvider {

    public static HashMap<String, List<String>> getInfo(){
        HashMap<String, List<String>> ColorsDetails = new HashMap<String, List<String>>();
        List<String> Light = new ArrayList<String>();
        Light.add("white");
        Light.add("gray");
        Light.add("begie");

        List<String> Medium = new ArrayList<String>();
        Medium.add("green");
        Medium.add("yellow");
        Medium.add("red");
        Medium.add("blue");

        List<String> Dark = new ArrayList<String>();
        Dark.add("black");
        Dark.add("brown");
        Dark.add("purple");

        ColorsDetails.put("light", Light);
        ColorsDetails.put("medium", Medium);
        ColorsDetails.put("dark", Dark);

        return ColorsDetails;


    }
}
