package model;

import javafx.scene.paint.Color;
import view.Observer;

import java.util.List;

public class BackgroundColorTracker implements BackgroundColorObservable, BackgroundColorWritable{
    private static final Color INITIAL_COLOR = Color.WHITE;
    private Color backgroundColor;
    private List<Observer> observers;
    public BackgroundColorTracker(){
        backgroundColor = INITIAL_COLOR;
    }

    public void setObserver(Observer observer){
        this.observers.add(observer);
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        this.notifyObservers();
    }

    private void notifyObservers(){
        for(Observer observer : observers){
            observer.notifyOfChanges();
        }
    }
}
