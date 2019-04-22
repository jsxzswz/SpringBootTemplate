package com.swz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.swz.pojo.domain.PersonDO;
import com.swz.pojo.dto.response.PersonResDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.swz.utils
 * @Description: Json转换工具类
 * @author: swz
 * @date: 2018/11/1 11:02
 */
public class JsonUtil {
    /**
     * json字符串转map集合
     *
     * @param jsonStr
     * @return
     */
    public static HashMap<String, String> json2Map(String jsonStr) {
        return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
    }

    /**
     * map转json字符串
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, String> map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T json2Object(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转换成List集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> List<T> json2List(String jsonString, Class cls) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转换成ArrayList集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> ArrayList<T> json2ArrayList(String jsonString, Class cls) {
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List集合转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String list2Json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * json转List
     * (不需要实体类)
     *
     * @param jsonStr
     * @return
     */
    public static JSONArray json2List(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

    public static void main(String[] args) throws Exception {
        PersonResDTO personDTO = new PersonResDTO();
        PersonDO personDO = new PersonDO();
        PersonDO personDO2 = new PersonDO();
        List list = new ArrayList<>();
        personDO.setName("swz");
        personDO.setAge(18);
        personDO2.setName("swz2");
        personDO2.setAge(20);
        personDO2.setAddress("徐州");
        list.add(personDO);
        list.add(personDO2);
        personDTO.setPersonList(list);
        System.out.println(JsonUtil.object2Json(personDTO));
        String str = "{\"personDO\":{\"age\":18,\"name\":\"swz\"}}";
        System.out.println(JsonUtil.json2Object(str, PersonResDTO.class));
        System.out.println(JsonUtil.list2Json(list));
        String jsonStr = "[{\"age\":18,\"name\":\"swz\"},{\"address\":\"徐州\",\"age\":20,\"name\":\"swz2\"}]";
//        System.out.println(JsonUtil.json2List(jsonStr,List.class));
//        System.out.println(JsonUtil.json2ArrayList(jsonStr,ArrayList.class));
        System.out.println(JsonUtil.json2List(jsonStr));
    }

}

