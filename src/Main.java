import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Parallel Processing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MatrixMultiplicationPanel matrixMultiplicationPanel = new MatrixMultiplicationPanel();
        MergeSortPanel mergeSortPanel = new MergeSortPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Matrix Multiplication", matrixMultiplicationPanel);
        tabbedPane.addTab("Merge Sort", mergeSortPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
