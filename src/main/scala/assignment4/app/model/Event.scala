package assignment4.app.model
import scalafx.beans.property.{StringProperty, ObjectProperty}
import java.time.LocalDate;

/**
 *
 * @param eventTitleInput       - Takes in the Event Reminder's title input of type String
 * @param eventNotesInput       - Takes in the Event Reminder's notes input of type String
 * @param eventLocationInput    - Takes in the Event Reminder's location input of type String
 * @param eventdd               - Takes in the Event Reminder's day input of type Integer
 * @param eventmm               - Takes in the Event Reminder's month input of type Integer
 * @param eventyy               - Takes in the Event Reminder's year input of type Integer
 *
 * @constructor Creates an Event object that inherits the Reminder Class
 */
class Event (eventTitleInput:String, eventNotesInput:String, eventLocationInput:String,
             eventdd:Integer, eventmm:Integer, eventyy:Integer)
    extends Reminder (eventTitleInput, eventNotesInput, eventLocationInput, eventdd, eventmm, eventyy)
{
    var title = new StringProperty(eventTitleInput)
    var notes = new StringProperty(eventNotesInput)

    var date = ObjectProperty[LocalDate](LocalDate.of(eventyy, eventmm, eventdd))

    val reminderType = new StringProperty("Event")
    var location = new StringProperty(eventLocationInput)
}
