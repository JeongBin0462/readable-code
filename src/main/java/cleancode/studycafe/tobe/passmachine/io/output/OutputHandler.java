package cleancode.studycafe.tobe.passmachine.io.output;

import cleancode.studycafe.tobe.passmachine.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();

    void showAnnouncement();

    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerPass(StudyCafeLockerPass lockerPass);

    void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass);

    void showSimpleMessage(String message);
}
