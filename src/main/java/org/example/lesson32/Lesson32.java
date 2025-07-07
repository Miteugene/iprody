package org.example.lesson32;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.service.NetworksService;
import org.example.lesson32.ui.ConsoleController;

import java.util.Scanner;

public class Lesson32 {
    public static void main(String[] args) throws ClassNotFoundException {
        ConsoleController consoleController = new ConsoleController(new Scanner(System.in));
        NetworkDao networkDao = new NetworkDao();
        NetworksService networksService = new NetworksService(consoleController, networkDao);
        networksService.process();
    }
}