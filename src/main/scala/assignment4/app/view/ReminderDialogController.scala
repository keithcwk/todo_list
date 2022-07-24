package assignment4.app.view

import assignment4.app.model.Reminder
import assignment4.app.Main
import scalafx.scene.control.{TextField, TableColumn, Label, Alert, TextArea}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import assignment4.app.util.DateUtility._
import scalafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import java.time.LocalDate;

/**
 * Controller class for the create new reminder dialog
 *
 * @param titleField        - The title of the note
 * @param notesField        - Additional remarks on the note
 * @param reminderTypeField - Type of reminder, either an Event or a Task
 * @param dateField         - The date of the reminder
 * @param locationField     - Where to carry out the reminder
 *
 * @constructor             - Creting a reminder dialog class
 */

@sfxml
class ReminderDialogController(
        private val titleField        : TextField,
        private val notesField        : TextArea,
        private val reminderTypeField : TextField,
        private val dateField         : TextField,
        private val locationField     : TextField
    )
{
    var dialogStage : Stage = null
    private var _reminder : Reminder = null
    var okClicked = false


    // Assigning the values to the fields
    def reminder = _reminder
    def reminder_= (x : Reminder)
    {
        _reminder = x

        titleField.text = _reminder.title.value
        notesField.text = _reminder.notes.value
        reminderTypeField.text = _reminder.reminderType.value
        dateField.text  = _reminder.date.value.asString
        dateField.setPromptText("dd.mm.yyyy");
        locationField.text = _reminder.location.value
    }


    /**
    * Function to confirm user's action when the 'OK' button
    * is clicked on
    *
    * @param action - Takes in an action as parameter
    *
    * @example handleOk takes in onClick as an ActionEvent
    *
    */

    def handleOk(action:ActionEvent)
    {
        if (isValidInput())
        {
            _reminder.title.value = titleField.text()
            _reminder.notes.value = notesField.text()
            _reminder.reminderType.value = reminderTypeField.text()
            _reminder.date.value = dateField.text.value.parseLocalDate;
            _reminder.location.value = locationField.text()

            okClicked = true;
            dialogStage.close()
        }
    }


    /**
    * Function to close the dialog when the user clicks 'Cancel'
    *
    * @param action - Takes in an action as parameter
    *
    * @example handleCancel takes in onClick as an ActionEvent
    */

    def handleCancel(action:ActionEvent)
    {
        dialogStage.close();
    }


    /**
    * Function to check for null inputs in any field
    *
    * @param x A string value for a field
    *
    * @example nullChecking takes in string "Hello" as an input
    *
    * @return Integer
    */

    def nullChecking(x: String) = x == null || x.length == 0


    /**
    * Function to handle valid inputs
    *
    * Assigns the nullChecking method to each field
    *
    * If the field does not have a value, it prompts the user that
    * they left out the particular field
    *
    * Else, return false
    *
    * @example handleNewReminder takes in onClick as an ActionEvent
    *
    * @return Boolean
    */

    def isValidInput():Boolean = {
        var errorMessage = ""

        if (nullChecking(titleField.text.value))
            errorMessage += "Invalid Title Inserted!\n"

        // Might not need as notes is optional
        if (nullChecking(notesField.text.value))
        errorMessage += "No Note Inserted!\n"

        if (nullChecking(reminderTypeField.text.value))
        errorMessage += "No Type Inserted!\n"

        if (nullChecking(dateField.text.value))
        errorMessage += "Invalid Date Inserted!\n"

        else
        {
            if(!dateField.text.value.isValid)
            {
                errorMessage += "Invalid Date. Format is dd.mm.yyyy!\n"
            }
        }

        // Location is also optional
        if (nullChecking(locationField.text.value))
        errorMessage += "No Location Inserted!\n"

        if (errorMessage.length() == 0)
        {
            return true;
        }

        else
        {
            // Shows an error message
            val alert = new Alert(Alert.AlertType.Error)
            {
                initOwner(dialogStage)
                title = "Invalid Field Entries"
                headerText = "Please Correct the invalid entries"
                contentText = errorMessage
            }.showAndWait()
        }
        return false;
    }
}