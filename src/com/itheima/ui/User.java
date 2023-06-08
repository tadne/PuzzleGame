package com.itheima.ui;

public class User
{
    private String name;
    private String password;
    public User()
    {
    }

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    /**
     * 获取
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String toString()
    {
        return "User{name = " + name + ", password = " + password + "}";
    }
}
