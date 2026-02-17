package org.cleanCode.task2.facade.door;

public class Main {
    public static void main(String[] args) {
        DoorOpen doorOpen = new DoorOpen();
        DoorLock doorLock = new DoorLock();
        DoorClose doorClose = new DoorClose();

        DoorFacade doorFacade = new DoorFacade(doorClose, doorLock, doorOpen);

        doorFacade.preapereDoor();
    }
}
