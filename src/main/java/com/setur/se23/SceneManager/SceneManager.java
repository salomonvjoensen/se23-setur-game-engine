package com.setur.se23.SceneManager;

import java.util.Scanner;

import com.setur.se23.FlappyBird.FlappyBird;
import com.setur.se23.GameTemplate.GameTemplate;
import com.setur.se23.snake.SnakeGame;

public class SceneManager {

    private static String scene = "1";
    
    public static void manage() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1: Flappy Bird");
        System.out.println("2: Snake Game");
        System.out.println("3: GameTemplate");
        System.out.println("");
        System.out.println("Scene number: ");

        scene = scanner.nextLine();

        scanner.close();
    }

    public static void load() {

        if (scene.equals("1")) {
            FlappyBird flappyBird = new FlappyBird();
            flappyBird.gameLoop.start();
        }

        if (scene.equals("2")) {
            SnakeGame snakeGame = new SnakeGame();
            snakeGame.gameLoop.start();
            
        }

        if (scene.equals("3")) {
            GameTemplate gameTemplate = new GameTemplate();
            gameTemplate.gameLoop.start();
        }
    }
}
