package org.king2.blogs.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/*================================================================
说明：业务操作响应结果

作者          时间            注释
鹿七       2018.5.22	     创建
==================================================================*/
public class SystemResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    // 200 为操作成功
    // 100 为数据校验异常
    // 404 为找不到路径
    // 500 为程序内部错误
    private Integer status;

    // 响应消息
    // 默认为操作成功
    private String msg;

    // 响应中的数据
    private Object data;

    /**
     * =================================================================
     * 功能：构建响应结果
     * <p>
     * 参数：status			Integer		状态码
     * msg				String		响应消息
     * data			Object		响应数据
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult build(Integer status, String msg, Object data) {
        return new SystemResult(status, msg, data);
    }

    /**
     * =================================================================
     * 功能：返回成功信息，不带消息，带数据
     * <p>
     * 参数：data			Object		响应数据
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult ok(Object data) {
        return new SystemResult(data);
    }

    /**
     * =================================================================
     * 功能：返回成功信息，不带消息、数据
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult ok() {
        return new SystemResult(null);
    }

    public SystemResult() {

    }

    public SystemResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    /**
     * =================================================================
     * 功能：构建响应结果，不带数据
     * <p>
     * 参数：status			Integer		状态码
     * msg				String		响应消息
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult build(Integer status, String msg) {
        return new SystemResult(status, msg, null);
    }

    public SystemResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public SystemResult(Object data) {
        this.status = 200;
        this.msg = "操作成功";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * =================================================================
     * 功能：将json结果集转化为SystemResult对象(Object不是集合对象的转化)
     * <p>
     * 参数：jsonData			String		json数据
     * clazz			Class		SystemResult中的object类型
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, SystemResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * =================================================================
     * 功能：将json结果集转化为SystemResult对象(没有object对象的转化)
     * <p>
     * 参数：jsonData			String		json数据
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult format(String json) {
        try {
            return MAPPER.readValue(json, SystemResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * =================================================================
     * 功能：将json结果集转化为SystemResult对象(Object是集合对象的转化)
     * <p>
     * 参数：jsonData			String		json数据
     * clazz			Class		集合中的类型
     * <p>
     * 返回：SystemResult		业务操作响应结果
     * ===================================================================
     */
    public static SystemResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
