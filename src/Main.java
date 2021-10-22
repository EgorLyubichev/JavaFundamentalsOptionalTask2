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

// 3) Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
    public static int sumOfNegativeNumbersAfterTheFirstPositiveToNextPositive(int[][] mainArray){
        int markerFirstPositiveNumber = -1;
        int sum = 0;
            for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < mainArray[i].length; j++) {

            if(mainArray[i][j] > 0 && markerFirstPositiveNumber == -1){
                markerFirstPositiveNumber = j;
            } else if(mainArray[i][j] < 0 && markerFirstPositiveNumber == -1){
                continue;
            } else if(mainArray[i][j] > 0 && markerFirstPositiveNumber != -1){
                break;
            }else if(mainArray[i][j] < 0 && markerFirstPositiveNumber != -1){
                sum = sum + mainArray[i][j];
            }
        }
        markerFirstPositiveNumber = -1;

    }
        System.out.println(sum);
            return sum;}
// 4) Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие.






}
