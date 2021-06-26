package com.peter.matrix.parser;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static HashMap<Integer, MappingMethod> sHashMap = new HashMap<>();

    public static void main(String[] args) {
        String path = ClassLoader.getSystemResource("log").getPath();
        File file = new File(path,".");
        System.out.println("jiangibn "+file.getAbsolutePath());
//        readMapping();
//        readLog();
    }

    private static void readLog() {
        long startTime = System.currentTimeMillis();
        Scanner scanner = null;
        try {
            String path = ClassLoader.getSystemResource("log").getPath();
            File file = new File(path,".");
            System.out.println("jiangibn "+file.getAbsolutePath());
            scanner = new Scanner(new File(path), "UTF-8");
            while (scanner.hasNext()) {
                String lineContent = scanner.nextLine().trim();
                String regex = "\\[(.*?)]";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(lineContent);
                Issue issue = new Issue();
                matcher.find();
                String tag = matcher.group(1);

                matcher.find();
                int type = Integer.parseInt(matcher.group(1));

                matcher.find();
                String key = matcher.group(1);

                matcher.find();
                String content = matcher.group(1);

                issue.setTag(tag);
                issue.setKey(key);
                issue.setType(type);
                issue.setContent(new JSONObject(content));
                IssueParser.parse(issue,sHashMap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
//                System.out.println("cost time " + (System.currentTimeMillis() - startTime));
            }
        }

    }

    private static void readMapping() {
        long startTime = System.currentTimeMillis();
        sHashMap.clear();
        Scanner scanner = null;
        try {
            String path = ClassLoader.getSystemResource("methodMapping.txt").getPath();
            scanner = new Scanner(new File(path), "UTF-8");

            while (scanner.hasNext()) {
                String lineContent = scanner.nextLine().trim();
                String[] fields = lineContent.split(",");
                MappingMethod method = new MappingMethod();
                method.id = Integer.parseInt(fields[0]);
                method.accessFlag = Integer.parseInt(fields[1]);
                String[] methodField = fields[2].split(" ");
                method.className = methodField[0].replace("/", ".");
                method.methodName = methodField[1];
                if (methodField.length > 2) {
                    method.desc = methodField[2].replace("/", ".");
                }
                sHashMap.put(method.id, method);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
//                System.out.println("cost time " + (System.currentTimeMillis() - startTime));
            }
        }
    }
}
