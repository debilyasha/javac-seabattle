import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public String getName() {
        return name;
    }

    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String checkYourSelf(String userInput) {
        String res = "Мимо";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                res = "Потопил";
                System.out.printf("Ой, вы потопили %s!\n", name);
            } else {
                res = "Попал";
            }
        }
        return res;
    }
}

class GameHelper {
    private static final String ALPHABET = "абвгдеё";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String text) {
        String input = null;
        System.out.print(text + ": ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            input = is.readLine();
            if (input.length() == 0) {
                return null;
            }
        } catch (IOException ex) {
            System.out.println("IOExpection: " + ex);
        }
        return input.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean succes = false;
        int location = 0;

        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1) {
            incr = gridLength;
        }
        while (!succes & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            int x = 0;
            succes = true;
            while (succes && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        succes = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        succes = false;
                    }
                } else {
                    succes = false;
                }
            }
        }

        int x = 0;
        int row = 0;
        int column = 0;

        while (x < comSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(ALPHABET.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        return alphaCells;
    }
}