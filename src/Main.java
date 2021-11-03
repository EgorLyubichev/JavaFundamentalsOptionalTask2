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
        System.out.print((i+1) + ")  ");
        horizontalArray[i][0] = 0;
        for (int j = 1; j < mainArray.length; j++) {
            if (mainArray[i][j] > mainArray[i][j-1]){
                horizontalArray[i][j] = horizontalArray[i][j-1] + 1;
            }else {horizontalArray[i][j] = 0;}
            System.out.print(horizontalArray[i][j] + " | ");
        }   System.out.println();
    }       System.out.println();

    for (int i = 0; i < mainArray.length; i++) {
        System.out.print((i+1) + ")  ");
        verticalArray[0][i] = 0;
        for (int j = 1; j < mainArray.length; j++) {
            if (mainArray[j][i] > mainArray[j-1][i]){
                verticalArray[j][i] = verticalArray[j-1][i] + 1;
            }else{verticalArray[j][i] = 0;}
            System.out.print(verticalArray[j][i] + " | ");
        }
        System.out.println();
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
// Предлагаю здесь разбить на 2 метода. 1-й ищет максимальное число и его (их) координаты.
// 2-й вырезает из массива строки и столбцы по координатам.


    }

