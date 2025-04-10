import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // Game assets
    private BufferedImage backgroundImage;
    private BufferedImage birdImage;
    private BufferedImage pipeImage;

    // Game objects
    private Player player;
    private ArrayList<Pipe> pipes;

    // Game mechanics
    private Timer gameTimer;
    private Timer pipeTimer;
    private int score = 0;
    private boolean gameOver = false;
    private JFrame parentFrame;
    private JLabel scoreLabel;

    // Constants
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -10;

    public FlappyBird(JFrame frame) {
        this.parentFrame = frame;

        // Load images
        try {
            backgroundImage = ImageIO.read(new File("src/assets/background.png"));
            birdImage = ImageIO.read(new File("src/assets/bird.png"));
            pipeImage = ImageIO.read(new File("src/assets/pipe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize game objects
        player = new Player(100, 250, birdImage);
        pipes = new ArrayList<>();

        // Create score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 150, 20);
        this.setLayout(null);
        this.add(scoreLabel);

        // Set game timers
        gameTimer = new Timer(20, this);
        gameTimer.start();

        pipeTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipe();
                }
            }
        });
        pipeTimer.start();

        // Add key listener
        this.setFocusable(true);
        this.addKeyListener(this);
        this.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Draw background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Draw pipes
        for (Pipe pipe : pipes) {
            g.drawImage(pipeImage, pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Draw player
        g.drawImage(player.getImage(), player.getX(), player.getY(), 50, 50, null);

        // Draw game over text
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", 150, 200);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press 'R' to restart", 150, 250);
        }
    }

    private void move() {
        // Move player
        if (!gameOver) {
            player.setVelocityY(player.getVelocityY() + GRAVITY);
            player.setY(player.getY() + player.getVelocityY());

            // Check if player hit the ground
            if (player.getY() + 50 > getHeight()) {
                gameOver = true;
            }

            // Move pipes
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setX(pipe.getX() - 5);

                // Remove pipes that are off-screen
                if (pipe.getX() + pipe.getWidth() < 0) {
                    pipes.remove(i);
                    i--;
                    continue;
                }

                // Check collision
                if (checkCollision(player, pipe)) {
                    gameOver = true;
                }

                // Update score when passing a pipe
                if (!pipe.isPassed() && pipe.getX() + pipe.getWidth() < player.getX()) {
                    if (pipe.getY() <= 0) { // Only count upper pipes to avoid double counting
                        score++;
                        scoreLabel.setText("Score: " + score);
                    }
                    pipe.setPassed(true);
                }
            }
        }
    }

    private boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), 50, 50);
        Rectangle pipeBounds = new Rectangle(pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight());

        return playerBounds.intersects(pipeBounds);
    }

    private void placePipe() {
        // Generate random pipe height
        Random random = new Random();
        int height = 100 + random.nextInt(150);
        int gap = 150;

        // Upper pipe (upside down)
        int upperHeight = height;
        Pipe upperPipe = new Pipe(getWidth(), -upperHeight + 50, 60, upperHeight, pipeImage);
        pipes.add(upperPipe);

        // Lower pipe
        int lowerY = upperHeight + gap;
        int lowerHeight = getHeight() - lowerY;
        Pipe lowerPipe = new Pipe(getWidth(), lowerY, 60, lowerHeight, pipeImage);
        pipes.add(lowerPipe);
    }

    private void restart() {
        // Reset game
        player.setY(250);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: " + score);
        gameOver = false;

        // Place first pipe
        placePipe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && !gameOver) {
            // Jump
            player.setVelocityY(JUMP_STRENGTH);
        } else if (key == KeyEvent.VK_R && gameOver) {
            // Restart game
            restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}