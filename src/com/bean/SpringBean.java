package com.bean;

import java.util.List;

public class SpringBean
{
    List<Bean> beanList;
    
    List<String> idList;
    
    public List<Bean> getBeanList()
    {
        return beanList;
    }
    
    public void setBeanList(List<Bean> beanList)
    {
        this.beanList = beanList;
    }
    
    public List<String> getIdList()
    {
        return idList;
    }
    
    public void setIdList(List<String> idList)
    {
        this.idList = idList;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SpringBean [beanList=");
        sb.append(beanList);
        sb.append(",idList=");
        sb.append(idList);
        sb.append("]");
        return sb.toString();
    }
}
