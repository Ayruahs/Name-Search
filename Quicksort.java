package com.shaurya;

/**
 * Created by ShauryaSinha on 06/06/17.
 */
public class Quicksort  {
    private String[][] numbers;
    private int number;

    public void sort(String[][] values) {
        // check for empty or null array
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = Integer.parseInt(numbers[low + (high-low)/2][1]);

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (Integer.parseInt(numbers[i][1]) < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (Integer.parseInt(numbers[j][1]) > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        int temp = Integer.parseInt(numbers[i][1]);
        numbers[i][1] = numbers[j][1];
        numbers[j][1] = Integer.toString(temp);

        String temp2 = numbers[i][0];
        numbers[i][0] = numbers[j][0];
        numbers[j][0] = temp2;
    }
}
