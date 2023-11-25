import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MatrixMultiplicationPanel extends JPanel {
    private JTextField numThreadsField;
    private JTextArea resultArea;

    public MatrixMultiplicationPanel() {
        setLayout(new BorderLayout());

        numThreadsField = new JTextField(5);
        JButton multiplyButton = new JButton("Multiply");
        resultArea = new JTextArea(10, 30);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Number of Threads:"));
        controlPanel.add(numThreadsField);
        controlPanel.add(multiplyButton);

        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // --> Get the number of threads from the numThreadsField


                //int numThreads = Integer.parseInt(numThreadsField.getText());
                int numThreads = Runtime.getRuntime().availableProcessors();


                // Get Manual Input for matrixA and matrixB

                Scanner sc = new Scanner(System.in);
                int[][] matrixA = new int[3][3];
                int[][] matrixB = new int[3][3];
                int n = 3;

                System.out.println("Enter the input for 3*3 matrix A: ");
                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++){
                        matrixA[i][j] = sc.nextInt();
                    }
                }

                System.out.println("Enter the input for 3*3 matrix B: ");
                for(int i=0;i<n;i++)
                {
                    for(int j=0;j<n;j++){
                        matrixB[i][j] = sc.nextInt();
                    }
                }

                // Perform parallel matrix multiplication
                int[][] resultMatrix = ParallelMatrixMultiplication.multiply(matrixA, matrixB, numThreads);

                // Display the result in the resultArea
                resultArea.setText("Result Matrix:\n" + Arrays.deepToString(resultMatrix));
            }
        });
    }
}
