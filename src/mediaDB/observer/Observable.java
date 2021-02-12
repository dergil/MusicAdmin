package mediaDB.observer;


public abstract class Observable {
        public abstract void register(Observer observer);

        public abstract void deregister(Observer observer);

        public abstract void advertise();
}
