package com.ahzaz.java.scrapper;

import com.ahzaz.java.scrapper.Impl.FirstPost;
import com.ahzaz.java.scrapper.Impl.HT;
import com.ahzaz.java.scrapper.Impl.InToday;
import com.ahzaz.java.scrapper.Impl.TOI;
import com.ahzaz.java.scrapper.Impl.DC;
import com.ahzaz.java.scrapper.Impl.NDTV;


import java.util.Map;
import java.util.TreeMap;

/**
 * @author Ahzaz
 */
public class Scrapper {
    static public Map<String, Class<? extends Website>> availableSites = new TreeMap<>();

    static {
        availableSites.put("TimesOfIndia", TOI.class);
        availableSites.put("DeccanChronicle", DC.class);
        availableSites.put("InToday", InToday.class);
        availableSites.put("FirstPost", FirstPost.class);
        availableSites.put("NDTV", NDTV.class);
        availableSites.put("Hindustan Times", HT.class);
    }
}
