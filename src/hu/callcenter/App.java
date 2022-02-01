package hu.callcenter;

import hu.callcenter.controller.CallService;
import hu.callcenter.domain.model.LogTime;
import hu.callcenter.domain.service.*;

import java.util.Scanner;

public class App {

    private final CallService callService;
    private final Console console;
    private final DataParser dataParser;
    private final FileWriter fileWriter;

    private App() {
        console = new Console(new Scanner(System.in));
        dataParser = new DataParser();
        fileWriter = new FileWriter("sikeres.txt");
        DataApi dataApi = new DataApi(new FileReader(), dataParser);
        callService = new CallService(dataApi.getData("hivas.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("3. feladat");
    }
}
