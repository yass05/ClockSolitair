/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clocksolitaire.excaptions;
/**
 *
 * @author yaya_
 */
public class InvalidValueOfTheCardException extends RuntimeException
{
    private static final double VERSIONUID = 4L;
  
    //Invalid Value has benn passed
    public InvalidValueOfTheCardException(String name)
    {
       super(name+" is invalid value of the card");
    }
    
}
