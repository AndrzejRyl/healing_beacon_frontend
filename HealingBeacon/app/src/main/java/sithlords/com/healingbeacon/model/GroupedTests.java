package sithlords.com.healingbeacon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupedTests implements Serializable {
    private final Map<String, List<Test>> grouped = new HashMap<>();

    public GroupedTests(List<Test> tests) {
        for (Test test : tests) {
            final String testType = test.getTestType();
            if (!grouped.containsKey(testType)) {
                grouped.put(testType, new ArrayList<Test>());
            }

            final List<Test> testsForType = grouped.get(testType);
            testsForType.add(test);
        }
    }

    public List<Test> getTestsForType(String type) {
        return grouped.get(type);
    }

    public List<String> getTestTypes() {
        return new ArrayList<>(grouped.keySet());
    }
}
