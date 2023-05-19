import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Fishes {
    public static void main(String[] args) throws IOException {
        new Fishes();
    }
    public Fishes() throws IOException {
        BufferedImage goldImage = ImageIO.read(new File("src\\Photo\\gold.png"));
        Image goldFish = goldImage.getScaledInstance(164, 113, Image.SCALE_DEFAULT);

        BufferedImage guppyImage = ImageIO.read(new File("src\\Photo\\guppy.png"));
        Image guppyFish = guppyImage.getScaledInstance(156, 86, Image.SCALE_DEFAULT);

        ImageIcon goldIcon = new ImageIcon(goldFish);
        ImageIcon guppyIcon = new ImageIcon(guppyFish);

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(600, 600);

        JLabel goldLabel = new JLabel();
        goldLabel.setIcon(goldIcon);
        goldLabel.setBounds(0, 250, 164, 113);

        JLabel guppyLabel = new JLabel();
        guppyLabel.setIcon(guppyIcon);
        guppyLabel.setBounds(250, 0, 156, 86);

        frame.add(goldLabel);
        frame.add(guppyLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AtomicInteger speed = new AtomicInteger(5);

        Thread goldFishThread = new Thread(() -> {
            int x = goldLabel.getX();
            int width = frame.getContentPane().getWidth();

            while (true){
                if(x + goldLabel.getWidth() <= width && x >= 0){
                    x += speed.get();
                } else {
                    speed.set(-speed.get());
                    x += speed.get();
                }
                goldLabel.setLocation(x, goldLabel.getY());

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread guppyFishThread = new Thread(() -> {
           int y = guppyLabel.getY();
           int height = frame.getContentPane().getHeight();

           while (true) {
               if(y + guppyLabel.getHeight() <= height && y >= 0){
                   y += speed.get();
               } else {
                   speed.set(-speed.get());
                   y += speed.get();
               }
               guppyLabel.setLocation(guppyLabel.getX(), y);

               try {
                   Thread.sleep(50);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        goldFishThread.start();
        guppyFishThread.start();
    }
}