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


    public List<String> extractParameters(String input) {
        List<String> params = Arrays.stream(input.split(" / ")).toList();
        return params;
    }
}
