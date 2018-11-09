import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {


    // Put any tests you want here
    private static void runTests() {
    }

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        HashMap<String, Integer> myMap = new HashMap<>();
        if (args.length == 1 && "test".equals(args[0])) {
            runTests();
            System.exit(0);
        }

        int numScreens = args.length;
        for (int i = 0; i < numScreens; i++) {
            if(myMap.containsKey(args[i])) {
                myMap.put(args[i], myMap.get(args[i]) + 1);
            } else {
                myMap.put(args[i], 1);
            }
        }

        for(String key: myMap.keySet()) {
            Integer value = myMap.get(key);
            threads.add(new Thread(new StarModel(value, key)));
        }

//        for(Integer value: myMap.values()) {
//            threads.add(new Thread(new StarModel(value)));
//        }

        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }


    }



}
