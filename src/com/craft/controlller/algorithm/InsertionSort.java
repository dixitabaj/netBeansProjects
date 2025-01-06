package com.craft.controlller.algorithm;

import com.craft.model.CraftModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dixitabajracharya
 */
public class InsertionSort {
     List<CraftModel> sortedList;

    
    public InsertionSort(){
        sortedList = new ArrayList<>();
    }

    /**
     * Sorts a list of CraftModel objects by their name using the insertion sort algorithm.
     *
     * @param craftItems List of CraftModel objects to sort.
     * @param ascending  determines the sorting order
     */
    public List<CraftModel> sortByName(List<CraftModel> craftItems, boolean ascending) {
        sortedList.clear();
        sortedList.addAll(craftItems);
        if (craftItems == null || craftItems.isEmpty()) {
            throw new IllegalArgumentException("Craft list cannot be null or empty.");
        }


        // Insertion Sort Algorithm
        for (int i = 1; i < sortedList.size(); i++) {
            CraftModel currentItem = sortedList.get(i);
            int j = i - 1;
            while (j >= 0 && compare(sortedList.get(j), currentItem, ascending)) {
                sortedList.set(j + 1, sortedList.get(j)); // Shift element to the right
                j--;
            }
            sortedList.set(j + 1, currentItem);
        }

        return sortedList;
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
        int comparisonResult = firstName.compareTo(secondName);
        return ascending ? (comparisonResult > 0) : (comparisonResult < 0);
    }
}
