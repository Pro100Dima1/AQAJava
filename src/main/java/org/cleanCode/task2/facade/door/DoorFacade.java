package org.cleanCode.task2.facade.door;

public class DoorFacade {

    private DoorClose doorClose;
    private DoorLock doorLock;
    private DoorOpen doorOpen;

    public DoorFacade(DoorClose doorClose, DoorLock doorLock, DoorOpen doorOpen) {
        this.doorClose = doorClose;
        this.doorLock = doorLock;
        this.doorOpen = doorOpen;
    }

    public void preapereDoor() {
        doorOpen.openDoor();
        doorLock.lockDoor();
        doorClose.closeDoor();
    }
}
