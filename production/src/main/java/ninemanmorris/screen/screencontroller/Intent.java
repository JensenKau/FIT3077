package ninemanmorris.screen.screencontroller;

import java.util.HashMap;

public class Intent {
    
    private HashMap<String, Object> items;

    public Intent() {
        items = new HashMap<>();
    }

    public void addItems(String key, Object value) {
        items.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T>T getItem(String key) {
        return (T)items.get(key);
    }
}
