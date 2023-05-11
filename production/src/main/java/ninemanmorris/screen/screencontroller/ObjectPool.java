package ninemanmorris.screen.screencontroller;

import java.util.ArrayList;
public class ObjectPool<T> {
    
    private ArrayList<T> stacks;
    private int limit;
    private IObjectPoolHandler<T> handler;

    public ObjectPool(int limit, IObjectPoolHandler<T> handler) {
        this.stacks = new ArrayList<>(limit);
        this.limit = limit;
        this.handler = handler;
    }

    public T retrieveItem() {
        if (stacks.size() == 0) {
            return handler.createItem();
        }
        return stacks.remove(stacks.size() - 1);
    }

    public void returnItem(T item) {
        if (stacks.size() < limit) {
            stacks.add(handler.resetItem(item));
        }
    }
}
