package com.peter.matrix.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class IssueParser {
    public static void parse(Issue issue, HashMap<Integer, MappingMethod> mappingMethodHashMap) {
        String stack = (String) issue.getContent().get("stack");
        String stackKey = (String) issue.getContent().get("stackKey");
        System.out.println(stackKey);
        String[] stackKeys = stackKey.split("\\|");
        System.err.println("The Most Cost is |" +stackKeys[0]+"| "+mappingMethodHashMap.get(Integer.parseInt(stackKeys[0])));
        Scanner scanner = new Scanner(stack);
        LinkedList<MethodItem> methodItemLinkedList = new LinkedList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            int depth = Integer.parseInt(fields[0]);
            int methodId = Integer.parseInt(fields[1]);
            int durTime = Integer.parseInt(fields[3]);
            MethodItem methodItem = new MethodItem(mappingMethodHashMap.get(methodId), durTime, depth);
            methodItemLinkedList.add(methodItem);
        }

        scanner.close();
        for (MethodItem methodItem : methodItemLinkedList) {
            System.out.println(methodItem.print());
        }
    }
}
