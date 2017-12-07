package com.utils;

public class StrUtil
{
    /**
     * 转化:首字母大写
     * 
     * @param str
     * @return
     */
    public static String upper(String str)
    {
        if (Character.isLowerCase(str.toCharArray()[1]))
        {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }
    
    /**
     * 获取属性的set方法名
     * 
     * @return
     */
    public static String getMethodName(String fieldName)
    {
        return "set" + upper(fieldName);
    }
    
}
