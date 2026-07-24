package ui.storage;

import models.CreateUserByAdminRequest;
import requests.skelethon.requesters.steps.UserSteps;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SessionStorage {
    // синглтон
	/* Thread Local - способ сделать SessionStorage потокобезопасным

  Каждый поток обращаясь к INSTANCE.get() получают свою КОПИЮ

  Map<Thread, SessionStorage>

  INSTANSE является как бы контейнером, внутри которого для каждого потока хранится объект SessionStorage.
  Поэтому надо сначала вызвать INSTANCE.get(), что б он вернул из ThreadLocal объект типа SessionStorage
  и вот у него уже есть доступ к полю userStepsMap.  Коментарий к строке 35

  Тест1 : создал юзеров, положил в SessionStorage (СВОЯ КОПИЯ1), работает с ними
  Тест2 : создал юзеров, положил в SessionStorage (СВОЯ КОПИЯ2), работает с ними
  Тест3 : создал юзеров, положил в SessionStorage (СВОЯ КОПИЯ3), работает с ними
   */

    private static final ThreadLocal<SessionStorage> INSTANCE = ThreadLocal.withInitial( SessionStorage::new);

    private final LinkedHashMap<CreateUserByAdminRequest, UserSteps> userStepsMap = new LinkedHashMap<>();

    private SessionStorage() {}

    public static void addUsers(List<CreateUserByAdminRequest> users) {
        for (CreateUserByAdminRequest user: users) {
            INSTANCE.get().userStepsMap.put(user, new UserSteps(user.getUsername(), user.getPassword()));
        }
    }

    /**
     * Возвращаем объект CreateUserRequest по его порядковому номеру в списке созданных пользователей.
     * @param number Порядковый номер, начиная с 1 (а не с 0).
     * @return Объект CreateUserRequest, соответствующий указанному порядковому номеру.
     */
    public static CreateUserByAdminRequest getUser(int number) {
        return new ArrayList<>(INSTANCE.get().userStepsMap.keySet()).get(number-1);
    }

    public static CreateUserByAdminRequest getUser() {
        return getUser(1);
    }

    public static UserSteps getSteps(int number) {
        return new ArrayList<>(INSTANCE.get().userStepsMap.values()).get(number-1);
    }

    public static UserSteps getSteps() {
        return getSteps(1);
    }

    public static void clear() {
        INSTANCE.get().userStepsMap.clear();
    }
}
