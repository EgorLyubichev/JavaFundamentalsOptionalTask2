// Ввести с консоли n-размерность матрицы a[n][n]. Задать значения элементов матрицы в интервале значений от -M до M с
// помощью генератора случайных чисел (класс Random).

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите n-размерность матрицы для a[n][n].");
        Scanner scanner1 = new Scanner(System.in);
        int n = scanner1.nextInt();
        int[][] mainArray = getMatrix(n);
        headAriseArray(mainArray);
        maxQuantityIncreaseNumbers(mainArray);
        sumOfNegativeNumbersAfterTheFirstPositiveToNextPositive(mainArray);
        int maxNumber = maxNumber(mainArray);
        int amount = amountMaxNumber(mainArray,maxNumber);
        int[] verticalCoordinates = verticalCoordinatesMaxNum(mainArray,maxNumber, amount);
        int[][] cutArrayAlongVertical = createMatrixWithoutVerticalCoordinates(mainArray, verticalCoordinates);
        int[] horizontalCoordinates = horizontalCoordinatesMaxNum(mainArray,maxNumber, amount);
        createOurMatrix(cutArrayAlongVertical, horizontalCoordinates);
    }

    public static int[][]getMatrix(int n){
        System.out.println();
        int[][] mainArray = new int[n][n];
        int minValue = -1000;
        int range = 2000;
        for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < mainArray[i].length; j++) {
                mainArray[i][j] = minValue + (int)Math.round(Math.random()*range);
                System.out.print(mainArray[i][j] + " | ");
            }System.out.println();
        }
        return mainArray;}
// 1) Упорядочить строки матрицы в порядке возрастания значений элементов k-го столбца.
    static int[][] headAriseArray(int[][] array){
        int n  = array.length;
        int m = array[n-1].length;
        int[] headArray = new int[n];
        for (int k = 0; k < headArray.length; k++) {
            headArray[k] = array[k][0];
        }   System.out.println();

        Arrays.sort(headArray);
        System.out.println("Строки упорядочены согласно первому порядковому числу, " +
                "по мере возрастания чисел 1-го столбца:\n " );

        int indexHeadArray = 0;
        int[][] theFirstMatrix = new int[n][m];

        for (int i = 0; i < headArray.length; i++) {
            if(array[i][0] == headArray[indexHeadArray]){
                theFirstMatrix[indexHeadArray] = array[i];
                indexHeadArray = 0;
            }else{indexHeadArray++;
                i--;}
        }

        for (int i = 0; i < theFirstMatrix.length; i++) {
            for (int j = 0; j < theFirstMatrix[i].length; j++) {
                System.out.print(theFirstMatrix[i][j] + " | ");
            }System.out.println();
        }
        return theFirstMatrix;}
// 2) Найти и вывести наибольшее число возрастающих (убывающих) элементов матрицы, идущих подряд.
    public static int maxQuantityIncreaseNumbers(int[][] mainArray){
    int[][] horizontalArray = new int[mainArray.length][mainArray.length];
    int[][] verticalArray = new int[mainArray.length][mainArray.length];
    for (int i = 0; i < mainArray.length; i++) { //сначала делаем единички и нолики для строк
        horizontalArray[i][0] = 0;
        for (int j = 1; j < mainArray.length; j++) {
            if (mainArray[i][j] > mainArray[i][j-1]){
                horizontalArray[i][j] = horizontalArray[i][j-1] + 1;
            }else {horizontalArray[i][j] = 0;}
        }
    }
    for (int i = 0; i < mainArray.length; i++) {
        verticalArray[0][i] = 0;
        for (int j = 1; j < mainArray.length; j++) {
            if (mainArray[j][i] > mainArray[j-1][i]){
                verticalArray[j][i] = verticalArray[j-1][i] + 1;
            }else{verticalArray[j][i] = 0;}
        }
    }

    int quentity = 0;
    for (int i = 0; i < horizontalArray.length; i++) {
        for (int j = 0; j < horizontalArray.length; j++) {
            if(horizontalArray[i][j] > quentity){
                quentity = horizontalArray[i][j];
            }
        }
    }
    for (int i = 0; i < verticalArray.length; i++) {
        for (int j = 0; j < verticalArray.length; j++) {
            if(verticalArray[i][j] > quentity){
                quentity = verticalArray[i][j];
            }
        }
    }
    System.out.println("Наибольшее число возрастающих элементов матрицы, идущих подряд равно " + quentity);
    return quentity;}
// 3) Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
    public static int sumOfNegativeNumbersAfterTheFirstPositiveToNextPositive(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            int quantity = 0;
            String firstPositiveNumber = "No";
            String nextPositiveNumber = "No";
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] >= 0 && quantity == 0 || array[i][j] >= 0 && quantity == 1) {
                    quantity++;
                }
            }
            if (quantity == 2) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] >= 0 && firstPositiveNumber == "No") {
                        firstPositiveNumber = "Yes";
                        continue;
                    } else if (array[i][j] < 0 && firstPositiveNumber == "No") {
                        continue;
                    } else if (array[i][j] >= 0 && firstPositiveNumber == "Yes") {
                        nextPositiveNumber = "Yes";
                    } else if (array[i][j] < 0 && firstPositiveNumber == "Yes" && nextPositiveNumber == "No") {
                        sum = sum + array[i][j];
                    } else if (nextPositiveNumber == "Yes") {
                        continue;
                    }
                }
            }
        }
            System.out.println("Сумма отрицательных чисел, считая от первого положительного " +
                    "числа до второго положительного числа, = " + sum);
            return sum;}
// 4) Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие.
    public static int maxNumber(int[][] array) {
    int maxNumber = -1;
    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length; j++) {
            if (array[i][j] > maxNumber) {
                maxNumber = array[i][j];
            }
        }
    }
    System.out.println("Максимальное число матрицы равно " + maxNumber);
    return maxNumber;
} //находим максимальное число матрицы
    public static int amountMaxNumber(int[][] array, int maxNumber) {
        int quantity = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == maxNumber) {
                    quantity++;
                }
            }
        }
        System.out.println("Частота встречаемости максимального числа в матрице равно " + quantity);
        return quantity;
    }
    public static int[] verticalCoordinatesMaxNum(int[][] array, int maxNumber, int amount){
        int[] verticalCoordinates = new int[amount];
        int coordinatePlace = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] == maxNumber){
                    verticalCoordinates[coordinatePlace] = i;
                    coordinatePlace++;
                }
            }
        }
        Arrays.sort(verticalCoordinates);

        int coordinate = verticalCoordinates[0];
        int verticalCoordinatesWithoutRepeatLength = verticalCoordinates.length;
        for (int i = 1; i < verticalCoordinates.length; i++) {//вычисляем длину массива координат без повтора координат
            if (verticalCoordinates[i] == coordinate){
                coordinate = verticalCoordinates[i];
                verticalCoordinatesWithoutRepeatLength--;
            }else{coordinate = verticalCoordinates[i];}
        }
        int[] verticalCoordinatesWithoutRepeat = new int[verticalCoordinatesWithoutRepeatLength];
        verticalCoordinatesWithoutRepeat[0] = verticalCoordinates[0];
        int index = verticalCoordinates[0];
        int y = 1;
        for (int i = 1; i < verticalCoordinates.length; i++) {
            if(verticalCoordinates[i] != index){
                verticalCoordinatesWithoutRepeat[y] = verticalCoordinates[i];
                y++;
                index = verticalCoordinates[i];
            }else{continue;}
        }
        System.out.println("Координаты максимальных чисел по вертикали (без повтора): "
                + Arrays.toString(verticalCoordinatesWithoutRepeat));
        return verticalCoordinatesWithoutRepeat;
    }
    public static int[][] createMatrixWithoutVerticalCoordinates(int[][] array, int[] verticalCoordinates){
        int[][] cutArray = new int[array.length - verticalCoordinates.length][array.length];
        int[] verticalCoordinatesPlusOne = new int[verticalCoordinates.length+1];
        System.arraycopy(verticalCoordinates,0,verticalCoordinatesPlusOne,0,verticalCoordinates.length);
        verticalCoordinatesPlusOne[verticalCoordinates.length] = -1;
        int y = 0;
        int a = 0;
        for (int i = 0; i < array.length; i++) {
            int index = verticalCoordinatesPlusOne[a];
            if(i != index){
                cutArray[y] = array[i];
                y++;
            }else{a++;continue;}
        }
        return cutArray;}
    public static int[] horizontalCoordinatesMaxNum(int[][] array, int maxNumber, int amount){
        int[] horizontalCoordinates = new int[amount];
        int coordinatePlace = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] == maxNumber){
                    horizontalCoordinates[coordinatePlace] = j;
                    coordinatePlace++;
                }
            }
        }
        Arrays.sort(horizontalCoordinates);

        int coordinate = horizontalCoordinates[0];
        int horizontalCoordinatesWithoutRepeatLength = horizontalCoordinates.length;
        for (int i = 1; i < horizontalCoordinates.length; i++) {//вычисляем длину массива координат без повтора координат
            if (horizontalCoordinates[i] == coordinate){
                coordinate = horizontalCoordinates[i];
                horizontalCoordinatesWithoutRepeatLength--;
            }else{coordinate = horizontalCoordinates[i];}
        }
        int[] horizontalCoordinatesWithoutRepeat = new int[horizontalCoordinatesWithoutRepeatLength];
        horizontalCoordinatesWithoutRepeat[0] = horizontalCoordinates[0];
        int index = horizontalCoordinates[0];
        int y = 1;
        for (int i = 1; i < horizontalCoordinates.length; i++) {
            if(horizontalCoordinates[i] != index){
                horizontalCoordinatesWithoutRepeat[y] = horizontalCoordinates[i];
                y++;
                index = horizontalCoordinates[i];
            }else{continue;}
        }
        System.out.println("Координаты максимальных чисел по горизонтали (без повтора): "
                + Arrays.toString(horizontalCoordinatesWithoutRepeat));
        return horizontalCoordinatesWithoutRepeat;
    }
    public static int[][] createOurMatrix(int[][] cutArray, int[] horizontalCoordinates){
        int[] horizontalCoordinatesPlusOne = new int[horizontalCoordinates.length + 1];
        System.arraycopy(horizontalCoordinates, 0, horizontalCoordinatesPlusOne, 0, horizontalCoordinates.length);
        horizontalCoordinatesPlusOne[horizontalCoordinates.length] = -1;
        int iLength = 0;
        int jLength = 0;
        for (int i = 0; i < cutArray.length; i++) {
            for (int j = 0; j < cutArray[i].length; j++) {
                iLength = i+1;
                jLength = j+1;
            }
        }
        int[][] ourArray = new int[iLength][jLength - horizontalCoordinates.length];


        for (int i = 0; i < cutArray.length; i++) {int y = 0;int a = 0;
            for (int j = 0; j < cutArray[i].length; j++) {
                int index = horizontalCoordinatesPlusOne[a];
                if (j != index){
                    ourArray[i][y] = cutArray[i][j];
                    y++;
                }else{
                    a++; continue;}
            }
        }
        System.out.println("Конечная матрица, без строк и столбцов, изначально содержащих максимальное число матрицы: ");
        for (int i = 0; i < ourArray.length; i++) {
            for (int j = 0; j < ourArray[i].length; j++) {
                System.out.print(ourArray[i][j] + " | ");
            }   System.out.println();
        }

        return ourArray;}
}

