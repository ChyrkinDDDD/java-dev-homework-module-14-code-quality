import java.util.Scanner;

public class GameXO {
    private char[] boxes = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private byte winner = 0;

    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");
        displayBoxes();
        cleanBoxes();
        while (true) {
            if(checkResultGame()){
                break;
            }
            getUserSelect();
            checkUserWin();
            checkDraw();
            getCompSelection();
            checkCompWin();
            displayBoxes();
        }
    }

    private boolean checkDraw() {
        boolean boxAvailable = false;
        int amountBoxes = 9;
        for(int i = 0; i < amountBoxes; i++){
            if(boxes[i] != 'X' && boxes[i] != 'O'){
                boxAvailable = true;
                break;
            }
        }

        if(!boxAvailable){
            winner = 3;
            return true;
        }
        return false;
    }

    private void checkCompWin() {
        if(checkWinComb('O')){
            winner = 2;
        }
    }

    private boolean checkWinComb(char sign) {
        return (boxes[0] == sign && boxes[1] == sign && boxes[2] == sign) || (boxes[3] == sign && boxes[4] == sign && boxes[5] == sign) || (boxes[6] == sign && boxes[7] == sign && boxes[8] == sign) ||
                (boxes[0] == sign && boxes[3] == sign && boxes[6] == sign) || (boxes[1] == sign && boxes[4] == sign && boxes[7] == sign) || (boxes[2] == sign && boxes[5] == sign && boxes[8] == sign) ||
                (boxes[0] == sign && boxes[4] == sign && boxes[8] == sign) || (boxes[2] == sign && boxes[4] == sign && boxes[6] == sign);
    }

    private boolean checkUserWin() {
        if(checkWinComb('X')){
            winner = 1;
            return  true;
        }
        return false;
    }

    private boolean checkResultGame() {
        if(winner == 1){
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if(winner == 2){
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if(winner == 3){
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }
        return false;
    }

    private void cleanBoxes() {
            for(int i = 0; i < 9; i++) {
                boxes[i] = ' ';
            }
    }

    private void displayBoxes() {
        System.out.println("\n\n " + boxes[0] + " | " + boxes[1] + " | " + boxes[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + boxes[3] + " | " + boxes[4] + " | " + boxes[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + boxes[6] + " | " + boxes[7] + " | " + boxes[8] + " \n");
    }

    private void getCompSelection() {
        boolean availValue = false;
        while (!availValue) {
            byte rand = (byte) (Math.random() * (9 - 1 + 1));
            boolean emptyBox = boxes[rand] != 'X' && boxes[rand] != 'O';

            if (emptyBox) {
                boxes[rand] = 'O';
                availValue = true;
            }
        }
    }

    private void getUserSelect(){
        Scanner scan = new Scanner(System.in);
        boolean availValue = false;
        while (!availValue) {
            int inputBox = scan.nextByte();

            if (inputBox > 0 && inputBox < 10) {
                boolean emptyBox = boxes[inputBox - 1] == 'X' || boxes[inputBox - 1] == 'O';

                if (emptyBox)
                    System.out.println("That one is already in use. Enter another.");
                else {
                    boxes[inputBox - 1] = 'X';
                    availValue = true;
                }
            }
            else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }
}
