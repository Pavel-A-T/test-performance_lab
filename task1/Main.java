public class Main {
    static String getWay(int n, int m) {
        int[] array = new int[n];
        for (int i = 0, j = 0; i < array.length; i++) {
            array[i] = ++j;
        }
        String way = Integer.toString(array[0]);
        int index = m >= array.length ? m - array.length : m - 1;
        while (array[0] != array[index]) {
            way += array[index];
            int newIndex = (m - 2 - (array.length - 1 - index));
            index = newIndex >= 0 ? newIndex : array.length + newIndex;
        }
        return way;
    }

    public static void main(String[] args) {
        try {
           int n = Integer.parseInt(args[0]);
           int m = Integer.parseInt(args[1]);
            System.out.println(getWay(n, m));
        }
        catch (Exception e) {
            System.out.println("error reading arguments");
        }
    }
}