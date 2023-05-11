package ninemanmorris.screen.screencontroller;

public interface IObjectPoolHandler<T> {
    
    T createItem();

    T resetItem(T item);
}
