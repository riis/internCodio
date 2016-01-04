package com.riis.cropcompare.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * author: solyss on 10/30/2015.
 */
public class ListComparer {
    public List compareCropLists(List response1, List response2) {
        List<String> listSimilarities = new ArrayList<>();
        for(int response1Index = 0; response1Index < response1.size(); response1Index++) {
            if(response2.contains(response1.get(response1Index).toString())) {
                listSimilarities.add(response1.get(response1Index).toString());
            }
        }
        return listSimilarities;
    }
}
