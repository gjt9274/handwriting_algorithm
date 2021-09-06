package dp;

import java.util.ArrayList;
import java.util.List;

public class Descartes {
    public static void main(String[] args) {
        List<List<Integer>> inputList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(4);

        List<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(6);
        list3.add(7);

        inputList.add(list1);
        inputList.add(list2);
        inputList.add(list3);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        descartes(inputList, result, 0, path);
        result.forEach(System.out::println);

    }
    private static void descartes(List<List<Integer>> inputList, List<List<Integer>> result, int layer, List<Integer> path) {
        if (layer < inputList.size() - 1) {
            for (int i = 0; i < inputList.get(layer).size(); i++) {
                List<Integer> list = new ArrayList<>(path);
                list.add(inputList.get(layer).get(i));
                descartes(inputList, result, layer + 1, list);
            }

        } else if (layer == inputList.size() - 1) {
            for (int i = 0; i < inputList.get(layer).size(); i++) {
                List<Integer> list = new ArrayList<>(path);
                list.add(inputList.get(layer).get(i));
                result.add(list);
            }

        }
    }
}
