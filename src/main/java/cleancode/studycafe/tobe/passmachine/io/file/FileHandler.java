package cleancode.studycafe.tobe.passmachine.io.file;

import cleancode.studycafe.tobe.passmachine.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePass;

import java.util.List;

public interface FileHandler {

    List<StudyCafePass> readStudyCafePasses();

    List<StudyCafeLockerPass> readLockerPasses();
}
