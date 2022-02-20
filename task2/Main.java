import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static int getPoint(float x, float y, Сircle circle) {
        if (Math.pow(x - circle.getX(), 2) + Math.pow(y - circle.getY(), 2) - Math.pow(circle.getRadius(), 2) < 0) {
            return 0;
        } else if (Math.pow(x - circle.getX(), 2) + Math.pow(y - circle.getY(), 2) - Math.pow(circle.getRadius(), 2) > 0) {
            return 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        try {
            Float x = null;
            Float y = null;
            Float radius = 0f;
            if (args.length < 2) {
                System.out.println("arguments not found!");
                return;
            }
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            Scanner scanner = new Scanner(file1);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (x == null && y == null) {
                    String[] array = str.split("\\s+");
                    x = Float.parseFloat(array[0]);
                    y = Float.parseFloat(array[1]);
                } else {
                    radius = Float.parseFloat(str);
                }
            }
            Сircle circle = new Сircle(x, y, radius);
            scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] array = str.split("\\s+");
                x = Float.parseFloat(array[0]);
                y = Float.parseFloat(array[1]);
                System.out.println(getPoint(x, y, circle));
            }
        } catch (IOException e) {
            System.out.println("error reading arguments");
        }
    }
}