package junghyun;

import junghyun.db.Logger;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    private static boolean onRunning = false;

    public static void main(String[] args) {
        Logger.startLogger();
        GomokuBot.startGomokuBot();
        Main.onRunning = true;
        Logger.loggerInfo("----------------------------------");
        Logger.loggerInfo("Boot Done!");
        Logger.loggerInfo("----------------------------------");
        Main.startServerCommand();
    }

    private static void stopServer() {
        GomokuBot.endGomokuBot();
        Logger.saveLogs();
        Main.onRunning = false;
    }

    private static void startServerCommand() {
        Main.scanner = new Scanner(System.in);
        Main.scanCommand();
    }

    private static void scanCommand() {
        if (!Main.onRunning) return;

        String command = Main.scanner.nextLine();
        switch (command) {
            case "stop":
                Main.stopServer();
                break;
            case "count":
                Logger.loggerInfo("Count: " + GameManager.getGameListSize());
                break;
        }
        Main.scanCommand();
    }

}