package com.craft.controlller.algorithm;

import com.craft.model.CraftModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dixitabajracharya
 */
public class SelectionSort {

    List<CraftModel> craftList;

    /**
     * Constructor to initialize the craftList
     */
    public SelectionSort() {
        craftList = new ArrayList<>();
    }

    /**
     * This method sorts the given list of CraftModel objects by their stock in either ascending or descending order.
     * @param craftItems The list of CraftModel objects to be sorted
     * @param isDesc determines the sorting order
     */
    public List<CraftModel> sortByStock(List<CraftModel> craftItems, boolean isDesc) {
        if (craftItems == null || craftItems.isEmpty()) {
            throw new IllegalArgumentException("Craft list cannot be null or empty.");
        }
        craftList.clear();
        craftList.addAll(craftItems);

        for (int i = 0; i < craftList.size() - 1; i++) {
            int extremumIndex = findExtremumIndex(craftList, i, isDesc);
            if (i != extremumIndex) {
                swap(craftList, i, extremumIndex);
            }
        }

        return craftList;
    }

    /**
     * This method finds the index of the extremum element (maximum or minimum)
     *
     * @param craftItemsSortList The list of CraftModel objects
     * @param startIndex The starting index for the search
     * @param isDesc determines the sorting order
     */
    private int findExtremumIndex(List<CraftModel> craftItemsSortList, int startIndex, boolean isDesc) {
        int extremumIndex = startIndex;

        for (int j = startIndex + 1; j < craftItemsSortList.size(); j++) {
            if (shouldSwap(craftItemsSortList.get(j).getStock(), craftItemsSortList.get(extremumIndex).getStock(), isDesc)) {
                extremumIndex = j;
            }
        }

        return extremumIndex;
    }

    /**
     * Determines if two stock values should be swapped based on sorting order.
     * @param current The current stock value
     * @param extremum The extremum (min or max) stock value 
     * @param isDesc If true, sorts in descending order; otherwise, sorts in ascending order
     * @return True if swapping is needed, otherwise false
     */
    private boolean shouldSwap(int current, int extremum, boolean isDesc) {
        return isDesc ? current > extremum : current < extremum;
    }

    /**
     * Swaps two elements in a list.
     * @param craftItems The list of CraftModel objects
     * @param i The index of the first element
     * @param j The index of the second element
     */
    private void swap(List<CraftModel> craftItemsSortList, int i, int j) {
        CraftModel temp = craftItemsSortList.get(i);
        craftItemsSortList.set(i, craftItemsSortList.get(j));
        craftItemsSortList.set(j, temp);
    }
}
