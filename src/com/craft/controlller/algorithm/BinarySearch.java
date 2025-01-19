package com.craft.controlller.algorithm;


import com.craft.model.CraftModel;
import java.util.List;

/**
 *
 * @author dixitabajracharya
 */

public class BinarySearch {
  /* This method performs a binary search to find a CraftModel by its name.
     * 
     * @param searchName The name of the CraftModel to search for
     * @param carftItems The list of CraftModel objects 
     * @param left The left index of the range to search
     * @param right The right index of the range to search
     */
public CraftModel searchByName(String searchName, List<CraftModel> carftItems,int left, int right) {

        if (right < left) {
            return null;
        }

        int mid = (left + right) / 2;

        if (searchName.equalsIgnoreCase(carftItems.get(mid).getName().toLowerCase())) {
            return carftItems.get(mid);
        } else if (searchName.compareToIgnoreCase(carftItems.get(mid).getName()) < 0) {
            return searchByName(searchName, carftItems, left, mid - 1);
        } else {
            return searchByName(searchName, carftItems, mid + 1, right);
        }

    }
}
