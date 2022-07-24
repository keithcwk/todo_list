package assignment4.app.model
import scalafx.beans.property.{StringProperty, ObjectProperty}
import java.time.LocalDate;

/**
 * Creates an abstract Reminder class
 *
 * @param titleInput    - Takes in the reminder's title input of type String
 * @param notesInput    - Takes in the reminder's notes input of type String
 * @param locationInput - Takes in the reminder's location input of type String
 * @param dd            - Takes in the reminder's day input of type Integer
 * @param mm            - Takes in the reminder's month input of type Integer
 * @param yy            - Takes in the reminder's year input of type Integer
 */

abstract class Reminder(titleInput:String, notesInput:String, locationInput:String,
                        dd:Integer, mm:Integer, yy:Integer)
{
    def title    : StringProperty
    def notes    : StringProperty
    def date     : ObjectProperty[LocalDate]
    def reminderType : StringProperty
    def location : StringProperty
}