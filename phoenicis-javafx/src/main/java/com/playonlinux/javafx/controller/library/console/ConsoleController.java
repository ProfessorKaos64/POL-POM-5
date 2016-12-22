package com.playonlinux.javafx.controller.library.console;

import com.playonlinux.javafx.views.mainwindow.console.ConsoleTab;
import com.playonlinux.javafx.views.mainwindow.console.ConsoleTabFactory;
import com.playonlinux.scripts.interpreter.InteractiveScriptSession;
import com.playonlinux.scripts.interpreter.ScriptInterpreter;
import org.apache.commons.lang.exception.ExceptionUtils;

public class ConsoleController {
    private final ConsoleTabFactory consoleTabFactory;
    private final ScriptInterpreter scriptInterpreter;

    public ConsoleController(ConsoleTabFactory consoleTabFactory, ScriptInterpreter scriptInterpreter) {
        this.consoleTabFactory = consoleTabFactory;
        this.scriptInterpreter = scriptInterpreter;
    }

    public ConsoleTab createConsole() {
        final ConsoleTab consoleTab = consoleTabFactory.createInstance();
        final InteractiveScriptSession interactiveScriptSession = scriptInterpreter.createInteractiveSession();

        consoleTab.setOnSendCommand(command -> {
            consoleTab.appendTextToConsole("> " + command + "\n", "#000000");
            consoleTab.disableCommand();
            interactiveScriptSession.eval(command, result -> {
                consoleTab.appendTextToConsole(result + "\n");
                consoleTab.enableCommand();
            }, error -> {
                consoleTab.appendTextToConsole(ExceptionUtils.getFullStackTrace(error), "#CC0000");
                consoleTab.enableCommand();
            });
        });

        return consoleTab;
    }
}