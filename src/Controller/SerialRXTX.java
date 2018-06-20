/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import gnu.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;


public class SerialRXTX implements SerialPortEventListener{

    SerialPort serialPort = null;
    
    private String appName; //Nome da aplicação
    
    private BufferedReader input;
    
    private static int TIME_OUT = 1000;
    private static int DATA_RATE = 9600;
    
    private String serialPortName = "COM1";
    public String inputString;
    public int peso;
    
   // public String LEITURA[];
    String Leitura_serial;
    String soNumeros;
  public  boolean status = false;
    
       
    public boolean iniciaSerial(){
      
      try {
          //Obtem Portas Seriais do Sistema;
          CommPortIdentifier portId = null;
          
          Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
          
          while(portId ==null && portEnum.hasMoreElements())
          {
          CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
         
          if(currPortId.getName().equals(serialPortName)|| currPortId.getName().startsWith(serialPortName))
          {
              serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
              portId = currPortId;
              System.out.println("Conectado em "+currPortId.getName());
              break;
          }
          }
          if(portId ==null || serialPort ==null)
          {
          return false;
          
          }
          serialPort.setSerialPortParams(DATA_RATE,
                  SerialPort.DATABITS_8,
                  SerialPort.STOPBITS_1,
                  SerialPort.PARITY_ODD);
          
          serialPort.addEventListener(this);
          serialPort.notifyOnDataAvailable(true);
          status = true;
          
          
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              
              e.printStackTrace();              
          }
                  
          
        } catch (Exception e) {
            
            e.printStackTrace();
            status = false;
            
        }
    
      return status;
    }    
    public synchronized void close(){
        serialPort.removeEventListener();
        serialPort.close();    
    }
   
    @Override
    public void serialEvent(SerialPortEvent spe) {
     
        if (status ==true)
        {
        try {
            switch(spe.getEventType()){
                case SerialPortEvent.DATA_AVAILABLE:
                    if(input == null){
                        input = new BufferedReader(new InputStreamReader (serialPort.getInputStream()));
                                       
                    }
                    if (input.ready()){
                       Leitura_serial = input.readLine();
                       int tamanho = Leitura_serial.length();
                       soNumeros = Leitura_serial.substring(1, tamanho-1);
                       
                         
                    }else{ 
                  
                  input = new BufferedReader(new InputStreamReader (serialPort.getInputStream()));
                    }
                  break;
                  
                default:
                    break;
         
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
         
            try {
                
            } catch (Exception e) {
            }
        }
    }
    
    public String leituraSerial (){
    
    return soNumeros;
    
    
    }
   
}
