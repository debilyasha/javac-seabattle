import java.util.ArrayList;

public class GameLogic {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.setUpGame();
        game.startPlayning();
    }

    private void setUpGame() {
        String[][] dotComName = {
                {"youtube.com", "github.com", "yoursite.com"},
                {"behance.com", "adobe.com", "upwork.com"},
                {"google.com", "jetbrains.com", "samsung.com"}};
        int randomIntForChoise = (int) (Math.random() * 3);
        DotCom com1 = new DotCom();
        com1.setName(dotComName[0][randomIntForChoise]);
        DotCom com2 = new DotCom();
        com2.setName(dotComName[1][randomIntForChoise]);
        DotCom com3 = new DotCom();
        com3.setName(dotComName[2][randomIntForChoise]);
        dotComList.add(com1);
        dotComList.add(com2);
        dotComList.add(com3);

        System.out.printf("У вас есть три сайта: %s, %s и %s\n", com1.getName(), com2.getName(), com3.getName());
        System.out.print("Ваша задача- их ддоснуть!\nУдачи!\n");

        for (DotCom site : dotComList) {
            ArrayList<String> newLoc = helper.placeDotCom(3);
            site.setLocationCells(newLoc);
        }
    }

    private void startPlayning() {
        while (!dotComList.isEmpty()) {
            String guess = helper.getUserInput("Введите свою догадку");
            checkUserGuess(guess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо";
        for (DotCom site : dotComList) {
            result = site.checkYourSelf(userGuess);
            if (result.equals("Попал")) {
                break;
            }

            if (result.equals("Потопил")) {
                dotComList.remove(site);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("Вы закончили игру!");
        if (numOfGuesses <= 18) {
            System.out.printf("Ваше колличесвто попыток- %d. Поздравляем вас!", numOfGuesses);
        }
        else {
            System.out.printf("Ваше колличесвто попыток- %d... Даже моя бабушка бы быстрее эту игру закончила.", numOfGuesses);
        }
    }
}
