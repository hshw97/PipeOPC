/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opc;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;

/**
 *
 * @author 黄尚文
 */
public class Opc_Operate {
    JOpc jopc;
    OpcGroup group = new OpcGroup("group1", true, 1000, 0.0f);
    String IP_Address ="localhost";
//String IP_Address ="192.168.0.5";
    public Opc_Operate(String Server_Name, OpcGroup read_group) throws ConnectivityException, UnableAddGroupException, UnableAddItemException{
        JOpc.coInitialize();
        this.group=read_group;
        jopc = new JOpc(IP_Address,Server_Name , "JOPC1");
        jopc.addGroup(group);
        jopc.connect();
        System.out.println("成功连接服务器...");
        jopc.registerGroups();
        //System.out.println("组件注册完成...");
    }
    public void Close(){
        JOpc.coUninitialize();
        new Constant_From().BEGIN=0;
    }
}
