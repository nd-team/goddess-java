package com.bjike.goddess.event.vo;

/**
 * 当月待办事件内容
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-11 15:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContentVO {
    /**
     * 时间
     */
    private String time;
    /**
     * 事件内容
     */
    private String content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
