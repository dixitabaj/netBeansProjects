package com.craft.controlller.algorithm;

import com.craft.model.CraftModel;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    List<CraftModel> craftList;

    public MergeSort() {
        craftList = new ArrayList<>();
    }
    /**
     * Sorts the craft items by price
     * @param craftItems - List of CraftModel objects 
     * @param ascending - Boolean indicating the sorting order
     * @return - A sorted list of CraftModel objects by price
     */
    public List<CraftModel> sortByPrice(List<CraftModel> craftItems, boolean ascending) {

        if (craftItems == null || craftItems.isEmpty()) {
            throw new IllegalArgumentException("Craft list cannot be null or empty.");
        }
        
        craftList.clear();
        craftList.addAll(craftItems);
        
        // If list has 1 or no elements, it's already sorted
        if (craftItems.size() <= 1) {
            return craftItems;
        }
        
        int middle = craftItems.size() / 2;
        
        List<CraftModel> leftList = new ArrayList<>(craftItems.subList(0, middle));
        List<CraftModel> rightList = new ArrayList<>(craftItems.subList(middle, craftItems.size()));
        
        // Recursively sort the left and right halves
        List<CraftModel> leftSort = sortByPrice(leftList, ascending);
        List<CraftModel> rightSort = sortByPrice(rightList, ascending);
        // Merge the sorted halves
        return merge(leftSort, rightSort, ascending);
    }

    /**
     * Merges two sorted lists into one sorted list
     * @param leftList - The left half of the sorted list
     * @param rightList - The right half of the sorted list
     * @param ascending - Boolean indicating the sorting order
     * @return - A merged and sorted list of CraftModel objects
     */
    private List<CraftModel> merge(List<CraftModel> leftList, List<CraftModel> rightList, boolean ascending) {
        List<CraftModel> mergedList = new ArrayList<>();

        int i = 0;
        int j = 0;

        // Compare elements from both lists and add the appropriate one
        while (i < leftList.size() && j < rightList.size()) {
            if (ascending) {
                // Ascending order
                if (leftList.get(i).getPrice() <= rightList.get(j).getPrice()) {
                    mergedList.add(leftList.get(i));
                    i++;
                } else {
                    mergedList.add(rightList.get(j));
                    j++;
                }
            } else {
                // Descending order
                if (leftList.get(i).getPrice() >= rightList.get(j).getPrice()) {
                    mergedList.add(leftList.get(i));
                    i++;
                } else {
                    mergedList.add(rightList.get(j));
                    j++;
                }
            }
        }

        // Add remaining elements from the left list if any
        while (i < leftList.size()) {
            mergedList.add(leftList.get(i));
            i++;
        }

        // Add remaining elements from the right list if any
        while (j < rightList.size()) {
            mergedList.add(rightList.get(j));
            j++;
        }

        return mergedList;
    }

}
