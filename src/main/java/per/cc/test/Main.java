package per.cc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            Main.matchBenchmark(line);
        }
    }

    public static void matchBenchmark(String input) {
        // Access your code here. Feel free to create other classes as required
        try {
            List<Action> actions = BenchmarkMatcher.match(input);
            Printer.printAction(actions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

class Printer {
    public static void printAction(List<Action> actions) {
        for (Action a : actions) {
            System.out.println(a.toString());
        }
    }

}

enum Type {
    STOCK, BOND
}

enum ActionType {
    SELL, BUY
}

class BenchmarkMatcher {
    private static final String SP = "\\|";
    private static final String SP_BEN_SH = ":";
    private static final String COMMA = ",";
    private static final String ERROR = "ERROR";

    public static List<Action> match(String input) throws Exception {
        String[] benAndSh = input.split(SP_BEN_SH);
        if (benAndSh.length != 2) throw new Exception(ERROR);
        Map<String, Shares> origin = processToMap(benAndSh[0]);
        Map<String, Shares> benchmark = processToMap(benAndSh[1]);
        return calActions(origin, benchmark);
    }

    private static List<Action> calActions(Map<String, Shares> origin, Map<String, Shares> benchmark) {
        List<Action> res = new ArrayList<>();
        for (Map.Entry<String, Shares> benEntry : benchmark.entrySet()) {
            String name = benEntry.getKey();
            if (!origin.containsKey(name)) {
                res.addAll(Action.builtActionByShares(null, benEntry.getValue()));
            } else {
                res.addAll(Action.builtActionByShares(origin.get(name), benEntry.getValue()));
                origin.remove(name);
            }
        }
        if (!origin.isEmpty()) {
            for (Map.Entry<String, Shares> oriEntry : origin.entrySet()) {
                res.addAll(Action.builtActionByShares(oriEntry.getValue(), null));
            }
        }
        Collections.sort(res, new Comparator<Action>() {
            @Override
            public int compare(Action o1, Action o2) {
                if (o1.actionType.equals(o2.actionType)) {
                    if (o1.name.equals(o2.name)) {
                        if (o1.shareType == Type.BOND) return -1;
                        if (o2.shareType == Type.BOND) return 1;
                    } else {
                        return o1.name.compareTo(o2.name);
                    }
                } else {
                    if (o1.actionType == ActionType.SELL) return -1;
                    if (o2.actionType == ActionType.SELL) return 1;
                }
                return 0;
            }
        });
        return res;
    }

    private static Map<String, Shares> processToMap(String s) throws Exception {
        Map<String, Shares> map = new HashMap<>();
        String[] sh = s.split(SP);
        for (String tmp : sh) {
            String[] shEle = tmp.split(COMMA);
            if (shEle.length != 3) throw new Exception(ERROR);
            Shares share = map.get(shEle[0]);
            if (share == null) {
                share = new Shares(shEle[0]);
            }
            if (shEle[1].equals(Type.STOCK.toString())) {
                share.stockNum = Integer.parseInt(shEle[2]);
            } else {
                share.bondNum = Integer.parseInt(shEle[2]);
            }
            map.put(shEle[0], share);
        }
        return map;
    }
}

class Action {
    ActionType actionType;
    Type shareType;
    String name;
    int amount;

    public Action(ActionType t, Type sType, String n, int num) {
        this.actionType = t;
        this.name = n;
        this.shareType = sType;
        this.amount = num;
    }

    public static List<Action> builtActionByShares(Shares origin, Shares ben) {
        List<Action> res = new ArrayList<>();
        if (origin == null) {
            if (ben.stockNum > 0) {
                res.add(new Action(ActionType.BUY, Type.STOCK, ben.name, ben.stockNum));
            }
            if (ben.bondNum > 0) {
                res.add(new Action(ActionType.BUY, Type.BOND, ben.name, ben.bondNum));
            }
        } else if (ben == null) {
            if (origin.stockNum > 0) {
                res.add(new Action(ActionType.SELL, Type.STOCK, origin.name, origin.stockNum));
            }
            if (origin.bondNum > 0) {
                res.add(new Action(ActionType.SELL, Type.BOND, origin.name, origin.bondNum));
            }
        } else {
            if (origin.bondNum < ben.bondNum) {
                res.add(new Action(ActionType.BUY, Type.BOND, ben.name, ben.bondNum - origin.bondNum));
            } else if (origin.bondNum > ben.bondNum) {
                res.add(new Action(ActionType.SELL, Type.BOND, ben.name, origin.bondNum - ben.bondNum));
            }
            if (origin.stockNum < ben.stockNum) {
                res.add(new Action(ActionType.BUY, Type.STOCK, ben.name, ben.stockNum - origin.stockNum));
            } else if (origin.stockNum > ben.stockNum) {
                res.add(new Action(ActionType.SELL, Type.STOCK, ben.name, origin.stockNum - ben.stockNum));
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return actionType.toString() + "," + name + ","
                + shareType.toString() + "," + amount;
    }
}

class Shares {
    String name;
    int stockNum = 0;
    int bondNum = 0;

    public Shares(String n) {
        this.name = n;
    }
}