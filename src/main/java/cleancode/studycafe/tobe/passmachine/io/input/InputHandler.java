package cleancode.studycafe.tobe.passmachine.io.input;

import cleancode.studycafe.tobe.passmachine.model.StudyCafePass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    boolean getLockerSelection();
}
