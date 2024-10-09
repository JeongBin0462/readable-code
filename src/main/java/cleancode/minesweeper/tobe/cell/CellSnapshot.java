package cleancode.minesweeper.tobe.cell;

public class CellSnapshot {

    private final CellSnapshotStatus status;
    private final int nearbyLandMineCount;

    private CellSnapshot(CellSnapshotStatus status, int landMineCount) {
        this.status = status;
        this.nearbyLandMineCount = landMineCount;
    }

    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return this.status == cellSnapshotStatus;
    }

    public static CellSnapshot ofEmpty() {
        return new CellSnapshot(CellSnapshotStatus.EMPTY, 0);
    }

    public static CellSnapshot ofFlag() {
        return new CellSnapshot(CellSnapshotStatus.FLAG, 0);
    }

    public static CellSnapshot ofLandMine() {
        return new CellSnapshot(CellSnapshotStatus.LAND_MINE, 0);
    }

    public static CellSnapshot ofNumber(int nearbyLandMineCount) {
        return new CellSnapshot(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
    }

    public static CellSnapshot ofUnchecked() {
        return new CellSnapshot(CellSnapshotStatus.UNCHECKED, 0);
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearbyLandMineCount() {
        return nearbyLandMineCount;
    }
}
