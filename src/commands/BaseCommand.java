package commands;

import commands.contracts.Command;
import core.EngineImpl;
import core.contracts.ApplicationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class BaseCommand implements Command {
    protected final ApplicationRepository app;

    protected BaseCommand(ApplicationRepository app) {
        this.app = app;
    }


    protected List<String> extractParameters() {
        String str = EngineImpl.sc.nextLine();
        List<String> params = Arrays.stream(str.split(" / ")).toList();
        //// TODO: 19.5.2024 г. remove
        System.out.println(params);
        return params;
    }
}
