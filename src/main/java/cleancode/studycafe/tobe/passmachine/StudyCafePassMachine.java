package cleancode.studycafe.tobe.passmachine;

import cleancode.studycafe.tobe.machine.MachineInitializable;
import cleancode.studycafe.tobe.machine.MachineRunnable;
import cleancode.studycafe.tobe.passmachine.config.MachineConfig;
import cleancode.studycafe.tobe.passmachine.exception.MachineException;
import cleancode.studycafe.tobe.passmachine.io.file.FileHandler;
import cleancode.studycafe.tobe.passmachine.io.input.InputHandler;
import cleancode.studycafe.tobe.passmachine.io.output.OutputHandler;
import cleancode.studycafe.tobe.passmachine.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine implements MachineInitializable, MachineRunnable {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    public StudyCafePassMachine(MachineConfig machineConfig) {
        this.inputHandler = machineConfig.getInputHandler();
        this.outputHandler = machineConfig.getOutputHandler();
        this.fileHandler = machineConfig.getFileHandler();

    }

    @Override
    public void initialize() {

    }

    @Override
    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType userSelectedPassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> studyCafePasses = fileHandler.readStudyCafePasses();

            if (StudyCafePassType.FIXED == userSelectedPassType) {
                StudyCafePass selectedPass = getStudyCafePass(studyCafePasses, userSelectedPassType);

                List<StudyCafeLockerPass> lockerPasses = fileHandler.readLockerPasses();

                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);

                    return;
                }
                outputHandler.showPassOrderSummary(selectedPass, null);

                return;
            }

            StudyCafePass selectedPass = getStudyCafePass(studyCafePasses, userSelectedPassType);
            outputHandler.showPassOrderSummary(selectedPass, null);
        } catch (MachineException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass getStudyCafePass(List<StudyCafePass> studyCafePasses, StudyCafePassType userSelectedPassType) {
        List<StudyCafePass> fixedPasses = selectedPasses(studyCafePasses, userSelectedPassType);
        outputHandler.showPassListForSelection(fixedPasses);
        return inputHandler.getSelectPass(fixedPasses);
    }

    private static List<StudyCafePass> selectedPasses(List<StudyCafePass> studyCafePasses, StudyCafePassType userSelectedPassType) {
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == userSelectedPassType)
            .toList();
    }
}
