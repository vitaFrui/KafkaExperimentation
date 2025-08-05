package com.example.kafkaexperimentation.service;

import com.example.kafkaexperimentation.model.UserEventV2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserEventService {
    /**
     * Validate and pre-process event per business rules. Client wants attribute map sorted alphabetically.
     *
     * @param userEventV2 the user event to be processed
     */
    public void preProcessUserEvent(final UserEventV2 userEventV2) {
        userEventV2.validate();

        if (null != userEventV2.getEventAttrMap()) {
            Map<String, String> originalMap = userEventV2.getEventAttrMap();
            List<String> keys = new ArrayList<>(originalMap.keySet());

            int n = keys.size();
            boolean swapped;
            for (int i = 0; i < n - 1; i++) {
                swapped = false;
                for (int j = 0; j < n - i - 1; j++) {
                    // Compare adjacent strings
                    if (keys.get(j).compareTo(keys.get(j + 1)) > 0) {
                        // Swap keys[j] and keys[j+1]
                        String temp = keys.get(j);
                        keys.set(j, keys.get(j + 1));
                        keys.set(j + 1, temp);
                        swapped = true;
                    }
                }
                // If no two elements were swapped by inner loop, then break
                if (!swapped) break;
            }

            Map<String, String> sortedMap = new LinkedHashMap<>();
            for (String key : keys) {
                // For each key, retrieve the value from the original map
                sortedMap.put(key, originalMap.get(key));
            }

            userEventV2.setEventAttrMap(sortedMap);
        }
    }
}
