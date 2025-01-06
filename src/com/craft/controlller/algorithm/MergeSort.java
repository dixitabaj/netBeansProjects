package com.craft.controlller.algorithm;

import com.craft.model.CraftModel;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
List<CraftModel> craftList;

    public MergeSort() {
craftList = new ArrayList<>();
    }
    
public List<CraftModel> sortByPrice(List<CraftModel> craftItems, boolean ascending) {
craftList.clear();
    craftList.addAll(craftItems);
    if (craftItems == null || craftItems.isEmpty()) {
        throw new IllegalArgumentException("Craft list cannot be null or empty.");
    }

    // Base case: If list has 1 or no elements, it's already sorted
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


private List<CraftModel> merge(List<CraftModel> leftList, List<CraftModel> rightList, boolean ascending) {
    List<CraftModel> mergedList = new ArrayList<>();

    int i = 0;
    int j = 0;

    // Compare elements from both lists and add the appropriate one
    while (i < leftList.size() && j < rightList.size()) {
        if (ascending) {
            // Ascending order: smaller prices go first
            if (leftList.get(i).getPrice() <= rightList.get(j).getPrice()) {
                mergedList.add(leftList.get(i));
                i++;
            } else {
                mergedList.add(rightList.get(j));
                j++;
            }
        } else {
            // Descending order: larger prices go first
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
