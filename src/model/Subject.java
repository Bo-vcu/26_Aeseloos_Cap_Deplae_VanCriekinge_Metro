package model;

public interface Subject {
    void addObserver(MetroEventEnum event, Observer o);
    void removeObserver(Observer o);
    void notifyObservers(MetroEventEnum event);
}
