/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uw.tcss558.clientserver.server;

/**
 *
 * @author Sonia
 */
public class ServerFactory {
   //use getServer method to get object of type server 
   public Server getServer(String serverType, String port){
      if(serverType == null){
         return null;
      }		
      if(serverType.equalsIgnoreCase("US")){
         return new UdpServer(port);
         
      } else if(serverType.equalsIgnoreCase("TS")){
         return new TcpServer(port);
         
      }       
      return null;
   }
}
