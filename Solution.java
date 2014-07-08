import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        System.out.print("Please enter a number ");
        Scanner in = new Scanner(System.in);
        int number = Integer.parseInt(in.nextLine());
        printStars(number);
    }

    public static void printStars(int n){
        char[] str = new char[n];

        int min = 0;
        int max = n-1;
        for (int i = 0; i < n; i++){
            char[] str2 = str.clone();
            str2[min] = '*';
            str2[max] = '*';

            min++;
            max--;

            System.out.println(str2);
            }

        }
    }


