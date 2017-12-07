package com.bean;

import java.util.Map;

public class Bean
{
    /**
     * class name
     */
    private String id;
    
    /**
     * class path
     */
    private String classPath;
    
    /**
     * class property:key = name,value = ref
     */
    private Map<String, String> propertys;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getClassPath()
    {
        return classPath;
    }
    
    public void setClassPath(String classPath)
    {
        this.classPath = classPath;
    }
    
    public Map<String, String> getPropertys()
    {
        return propertys;
    }
    
    public void setPropertys(Map<String, String> propertys)
    {
        this.propertys = propertys;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Bean [id=");
        sb.append(id);
        sb.append(",classPath=");
        sb.append(classPath);
        sb.append(",propertys=");
        sb.append(propertys);
        sb.append("]");
        return sb.toString();
    }
}
