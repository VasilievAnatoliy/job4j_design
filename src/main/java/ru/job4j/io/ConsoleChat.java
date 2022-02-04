package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 'Консольный чат'. Описание:
 * пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * если пользователь вводит слово:
 * - «стоп» программа замолкает, при этом он может продолжать отправлять сообщения в чат.
 * - «продолжить» программа снова начинает отвечать.
 * - «закончить» программа прекращает работу.
 * запись диалога в текстовый лог включает слова-команды стоп/продолжить/закончить.
 */

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static List<String> answers;

    /**
     * класс принимает в конструктор 2 параметра
     * path - имя файла, в который будет записан весь диалог между ботом и пользователем,
     * botAnswers - имя файла в котором находятся строки с ответами,
     * которые будет использовать бот
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * содержит логику чата;
     */
    public void run() {
        readPhrases();
        List<String> dialog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет");
        dialog.add("Bot: Привет");
        String text = scanner.nextLine();
        dialog.add(String.format("User: %s", text));
        while (!OUT.equals(text)) {
            String bot = answer();
            System.out.println(bot);
            dialog.add(String.format("bot: %s", bot));
            text = scanner.nextLine();
            dialog.add(String.format("User: %s ", text));
            if (STOP.equals(text)) {
                while (!CONTINUE.equals(text)) {
                    text = scanner.nextLine();
                    dialog.add(String.format("User: %s", text));
                }
            }

        }
        System.out.println("Пока");
        dialog.add("Bot: Пока");
        saveLog(dialog);
    }

    /**
     * читает фразы из файла;
     *
     * @return список ответов
     */
    private List<String> readPhrases() {
        answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {  
            br.lines()
                    .filter(x -> !"".equals(x))
                    .forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    /**
     * @return возврщает случайную строчку из списка ответов
     */
    private String answer() {
        return answers.get(new Random().nextInt(answers.size() - 1));
    }

    /**
     * сохраняет лог чата в файл
     */
    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
