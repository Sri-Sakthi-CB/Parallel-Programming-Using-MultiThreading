import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MergeSortPanel extends JPanel {
    private JTextField numThreadsField;
    private JTextArea resultArea;

    public MergeSortPanel() {
        setLayout(new BorderLayout());

        numThreadsField = new JTextField(5);
        JButton sortButton = new JButton("Sort");
        resultArea = new JTextArea(10, 30);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Number of Threads:"));
        controlPanel.add(numThreadsField);
        controlPanel.add(sortButton);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the number of threads from the numThreadsField
                int numThreads = Integer.parseInt(numThreadsField.getText());

                // Getting the input for array to be sorted
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the size of the Array: ");
                int n = sc.nextInt();
                int[] inputArray = new int[n];
                for(int i=0;i<n;i++)
                {
                    inputArray[i] = sc.nextInt();
                }

                // Perform parallel merge sort
                int[] sortedArray = ParallelMergeSort.parallelMergeSort(inputArray, numThreads);

                // Display the sorted result in the resultArea
                resultArea.setText("Sorted Array:\n" + Arrays.toString(sortedArray));
            }
        });
    }
}

