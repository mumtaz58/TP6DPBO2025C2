import java.awt.image.BufferedImage;

public class Player {
    private int x;
    private int y;
    private int velocityY;
    private BufferedImage image;

    public Player(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.velocityY = 0;
        this.image = image;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}