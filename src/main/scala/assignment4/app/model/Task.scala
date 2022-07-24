package assignment4.app.model
import scalafx.beans.property.{StringProperty, ObjectProperty}
import java.time.LocalDate;

/**
 *
 * @param taskTitleInput    - Takes in the Task Reminder's title input of type String
 * @param taskNotesInput    - Takes in the Task Reminder's notes input of type String
 * @param taskLocationInput - Takes in the Task Reminder's location input of type String
 * @param taskdd            - Takes in the Task Reminder's day input of type String
 * @param taskmm            - Takes in the Task Reminder's month input of type String
 * @param taskyy            - Takes in the Task Reminder's year input of type String
 *
 * @constructor Creates a Task object that inherits the Reminder class
 */

class Task (taskTitleInput:String, taskNotesInput:String, taskLocationInput:String, taskdd:Integer, taskmm:Integer, taskyy:Integer)
  extends Reminder (taskTitleInput, taskNotesInput, taskLocationInput, taskdd, taskmm, taskyy)
{
    var title = new StringProperty(taskTitleInput)
    var notes = new StringProperty(taskNotesInput)

    // Obtaining the current date and slicing it
    var currentDate = java.time.LocalDate.now.toString
    var dd = currentDate.slice(8, 10)
    var mm = currentDate.slice(5, 7)
    var yy = currentDate.slice(0, 4)
    var date = ObjectProperty[LocalDate](LocalDate.of(taskyy, taskmm, taskdd))

    val reminderType = new StringProperty("Task")
    var location = new StringProperty(taskLocationInput)
}