  
package com.winform.event;

import com.winform.models.Messagetype;

 
public interface EventChat {

    public void sendMessage(String text,Messagetype messageType);
}