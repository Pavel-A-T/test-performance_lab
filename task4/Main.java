import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int getCount(int[] array) {
        int count = 0;
        while (!checkArray(array)) {
            int[] min = getMin(array);
            int[] max = getMax(array);
            if (min[0] != max[0]) {
                if (min.length > max.length) {
                    for (int j = 0; j < max.length; j++) {
                        array[max[j]]--;
                        count++;
                    }
                } else {
                    for (int j = 0; j < min.length; j++) {
                        array[min[j]]++;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    static boolean checkArray(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[0] != array[i]) {
                return false;
            }
        }
        return true;
    }

    static int[] getMax(int[] array) {
        int[] index = {0};
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                index = new int[]{i};
            } else if (max == array[i]) {
                index = Arrays.copyOf(index, index.length + 1);
                index[index.length - 1] = i;
            }
        }
        return index;
    }

    static int[] getMin(int[] array) {
        int[] index = {0};
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
                index = new int[]{i};
            } else if (min == array[i]) {
                index = Arrays.copyOf(index, index.length + 1);
                index[index.length - 1] = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("arguments not found!");
            return;
        }
        File file = new File(args[0]);
        List<Integer> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                try {
                    list.add(Integer.parseInt(str));
                } catch (Exception e) {
                    //ignore Exception empty String
                }
            }
            int[] array = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
            System.out.println(getCount(array));
        } catch (IOException e) {
            System.out.println("unreadable file error");
        }
    }
}

