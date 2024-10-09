package cleancode.studycafe.tobe.passmachine.config;

import cleancode.studycafe.tobe.passmachine.io.file.FileHandler;
import cleancode.studycafe.tobe.passmachine.io.input.InputHandler;
import cleancode.studycafe.tobe.passmachine.io.output.OutputHandler;

public class MachineConfig {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    public MachineConfig(InputHandler inputHandler, OutputHandler outputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.fileHandler = fileHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
