package com.peter.matrix.parser;

import org.json.JSONObject;

public class Issue {
    private int        type;
    private String     tag;
    private String     key;
    private JSONObject content;

    public static final String ISSUE_REPORT_TYPE    = "type";
    public static final String ISSUE_REPORT_TAG     = "tag";
    public static final String ISSUE_REPORT_PROCESS = "process";
    public static final String ISSUE_REPORT_TIME = "time";

    public Issue() {
    }

    public Issue(int type) {
        this.type = type;
    }

    public Issue(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }


    @Override
    public String toString() {
        String strContent = "";
        if (null != content) strContent = content.toString();
        return String.format("tag[%s]type[%d];key[%s];content[%s]", tag, type, key, strContent);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getTag() {
        return tag;
    }

    public void setType(int type) {
        this.type = type;
    }

}
