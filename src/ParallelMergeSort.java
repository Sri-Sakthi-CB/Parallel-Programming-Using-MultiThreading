import java.util.Arrays;

public class ParallelMergeSort {

    public static void pairMergeSort(int[] arr, int numThreads) {
        if (numThreads <= 1) {
            mergeSort(arr);
        }
        else if (arr.length >= 2) {
            int middle = arr.length / 2;

            // Split the array into two subarrays
            int[] left = Arrays.copyOfRange(arr, 0, middle);
            int[] right = Arrays.copyOfRange(arr, middle, arr.length);

            // Create threads for sorting the subarrays
            Thread leftThread = new Thread(() -> parallelMergeSort(left, numThreads / 2));
            Thread rightThread = new Thread(() -> parallelMergeSort(right, numThreads / 2));

            // Start the threads
            leftThread.start();
            rightThread.start();

            try {
                // Wait for the threads to finish
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Merge the sorted subarrays
            merge(left, right, arr);
        }
    }

    private static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int middle = arr.length / 2;

            int[] left = Arrays.copyOfRange(arr, 0, middle);
            int[] right = Arrays.copyOfRange(arr, middle, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(left, right, arr);
        }
    }

    private static void merge(int[] left, int[] right, int[] arr) {
        int leftIndex = 0, rightIndex = 0, mergeIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                arr[mergeIndex++] = left[leftIndex++];
            } else {
                arr[mergeIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            arr[mergeIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            arr[mergeIndex++] = right[rightIndex++];
        }
    }

    public static int[] parallelMergeSort(int[] arr, int numThreads) {
//        int[] arr = {12, 11, 13, 5, 6, 7};
//        int numThreads = Runtime.getRuntime().availableProcessors(); // Use the number of available processors

        System.out.println("Original Array: " + Arrays.toString(arr));

        pairMergeSort(arr, numThreads);

        System.out.println("Sorted Array: " + Arrays.toString(arr));

        return arr;
    }
}

