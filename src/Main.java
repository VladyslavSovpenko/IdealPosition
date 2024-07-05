import java.util.Arrays;

public class Main {

    private int[][] matrix;
    private int rows;
    private int cols;


    public Main(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    public static void main(String[] args) {
        Main main = new Main(5, 5);
        int[][] array = {
                {83, 12, 50, 93, 99},
                {79, 14, 15, 70, 1},
                {32, 68, 6, 59, 87},
                {54, 50, 86, 82, 62},
                {9, 19, 57, 88, 99}
        };
        int[] idealPosition = main.findIdealPosition(array);
        System.out.println("Ideal position " + idealPosition[0] + " " + idealPosition[1]);
    }

    private int[] findIdealPosition(int[][] arr) {
        int[] result = new int[2];
        int[] columnsSum = new int[arr.length];
        int[] rowsSum = new int[arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                rowsSum[i] += arr[i][j];
                columnsSum[j] += arr[i][j];
            }
        }

        result[0] = getIdealIndex(columnsSum);
        result[1] = getIdealIndex(rowsSum);
        return result;
    }

    //Get ideal index for array(column or rows). Calculate sum and return index
    private static int getIdealIndex(int[] array) {
        int arraySum = Arrays.stream(array)
                .reduce(0, Integer::sum);

        int steppedSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (steppedSum >= arraySum / 2) {
                return i;
            }
            steppedSum = array[i];
        }
        return array.length - 1;
    }

    //For edit value in matrix(array)
    public void addValue(int row, int col, int value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            matrix[row][col] = value;
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index. Rows: " + rows + ", Cols: " + cols);
        }
    }

    //For add new row in matrix(array)
    public void addRow(int[] newRow) {
        if (newRow.length != cols) {
            throw new IllegalArgumentException("New row must have the same number of columns. Number of column: " + cols);
        }
        matrix = Arrays.copyOf(matrix, rows + 1);
        matrix[rows] = newRow;
        rows++;
    }

    //For add new column in matrix(array)
    public void addColumn(int[] newCol) {
        if (newCol.length != rows) {
            throw new IllegalArgumentException("New column must have the same number of rows. Number of rows: " + rows);
        }
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.copyOf(matrix[i], cols + 1);
            matrix[i][cols] = newCol[i];
        }
        cols++;
    }
}