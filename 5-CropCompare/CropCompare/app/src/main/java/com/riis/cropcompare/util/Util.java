package com.riis.cropcompare.util;

import java.util.ArrayList;
import java.util.List;

public class Util
{
    public List<String> compareCropLists(List<String> response1, List<String> response2)
    {
        List<String> listSimilarities = new ArrayList<>();

        for(int response1Index = 0; response1Index < response1.size(); response1Index++)
        {
            if(response2.contains(response1.get(response1Index)))
            {
                listSimilarities.add(response1.get(response1Index));
            }
        }
        return listSimilarities;
    }
}
