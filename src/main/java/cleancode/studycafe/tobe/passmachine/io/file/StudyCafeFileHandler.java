package cleancode.studycafe.tobe.passmachine.io.file;

import cleancode.studycafe.tobe.passmachine.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.passmachine.model.StudyCafePass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StudyCafeFileHandler implements FileHandler {

    private static final String STUDY_CAFE_PASS_LIST_FILE_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private static final String STUDY_CAFE_LOCKER_FILE_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    private final List<String> passListLines;
    private final List<String> lockerListLines;

    public StudyCafeFileHandler() {
        try {
            this.passListLines = Files.readAllLines(Paths.get(STUDY_CAFE_PASS_LIST_FILE_PATH));
            this.lockerListLines = Files.readAllLines(Paths.get(STUDY_CAFE_LOCKER_FILE_PATH));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는데 실패했습니다.", e);
        }
    }

    @Override
    public List<StudyCafePass> readStudyCafePasses() {
        return passListLines.stream().map(StudyCafePass::byFileLine).toList();
    }

    @Override
    public List<StudyCafeLockerPass> readLockerPasses() {
        return lockerListLines.stream().map(StudyCafeLockerPass::byFileLine).toList();
    }
}
