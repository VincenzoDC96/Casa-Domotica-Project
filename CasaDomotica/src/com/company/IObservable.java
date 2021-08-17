package com.company;

public interface IObservable {
    void add(IObserver o);
    void Notify();
}
