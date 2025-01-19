package com.craft.controlller.algorithm;

import com.craft.model.CraftModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dixitabajracharya
 */
public class InsertionSort {
     List<CraftModel> craftList;

    
    public InsertionSort(){
        craftList = new ArrayList<>();
    }

    /**
     * Sorts a list of CraftModel objects by their name using the insertion sort algorithm.
     *
     * @param craftItems List of CraftModel objects to sort.
     * @param ascending  determines the sorting order
     */
    public List<CraftModel> sortByName(List<CraftModel> craftItems, boolean ascending) {
        craftList.clear();
        craftList.addAll(craftItems);
        if (craftItems == null || craftItems.isEmpty()) {
            throw new IllegalArgumentException("Craft list cannot be null or empty.");
        }
        
        //Iterate the list from the second element till the end
        for (int i = 1; i < craftList.size(); i++) {
            CraftModel currentItem = craftList.get(i);
            int j = i - 1;
            while (j >= 0 && compare(craftList.get(j), currentItem, ascending)) {
                craftList.set(j + 1, craftList.get(j)); // Shift element to the right
                j--;
            }
            craftList.set(j + 1, currentItem);
        }

        return craftList;
    }

    /**
     * Compares two CraftModel objects by their name for ordering.
     *
     * @param first     The first CraftModel object.
     * @param second    The second CraftModel object.
     * @param ascending determines the sorting order
     */
    private boolean compare(CraftModel first, CraftModel second, boolean ascending) {
        String firstName = first.getName() == null ? "" : first.getName();
        String secondName = second.getName() == null ? "" : second.getName();
        int comparisonResult = firstName.toLowerCase().compareTo(secondName.toLowerCase());
        return ascending ? (comparisonResult > 0) : (comparisonResult < 0);
    }
}
