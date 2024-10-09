package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.passmachine.StudyCafePassMachine;
import cleancode.studycafe.tobe.passmachine.config.MachineConfig;
import cleancode.studycafe.tobe.passmachine.io.file.StudyCafeFileHandler;
import cleancode.studycafe.tobe.passmachine.io.input.ConsoleInputHandler;
import cleancode.studycafe.tobe.passmachine.io.output.ConsoleOutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        MachineConfig machineConfig = new MachineConfig(
            new ConsoleInputHandler(),
            new ConsoleOutputHandler(),
            new StudyCafeFileHandler()
            );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(machineConfig);
        studyCafePassMachine.run();
    }

}
