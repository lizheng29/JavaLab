package myTestProject;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @description: 比较对象不同的工具类test
 * @author: lizheng29
 * @create: 2018-10-23 10:29
 **/
public class JavaObjectDiffTest {

    @Data
    @AllArgsConstructor
    static class People {
        private String name;
        private Integer age;
        private List<String> hobbies;
    }

    public static void main(String[] args) {
        People p1 = new People("lizheng", 24, Collections.singletonList("eat"));
        People p2 = new People("lizheng", 23, Collections.singletonList("sleep"));
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(p1, p2);

        System.out.println(diff.hasChanges());
        System.out.println(diff.childCount());


        /*diff.visit(new DiffNode.Visitor() {
            @Override
            public void node(DiffNode node, Visit visit) {
                final Object baseValue = node.canonicalGet(p1);
                final Object workingValue = node.canonicalGet(p2);
                final String message = node.getPath() + " changed from " +
                        baseValue + " to " + workingValue;
                System.out.println(message);

                System.out.println(node.getPath() + " => " + node.getState());
            }
        });*/

        diff.visit((node,visit) -> {
            final Object baseValue = node.canonicalGet(p1);
            final Object workingValue = node.canonicalGet(p2);
            final String message = node.getPath() + " changed from " +
                    baseValue + " to " + workingValue;
            System.out.println(message);

            System.out.println(node.getPath() + " => " + node.getState());
        });
    }
}
