import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Create a new Menu to start the game
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public static void startGame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird(frame);
        frame.add(flappyBird);
        frame.setVisible(true);
    }
}