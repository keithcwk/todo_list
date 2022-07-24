package assignment4.app.view
import assignment4.app.model._
import assignment4.app.Main
import assignment4.app.util.DateUtility._
import scalafx.scene.control.{TableView, TableColumn, Label, Alert}
import scalafxml.core.macros.sfxml
import scalafx.beans.property.{StringProperty}
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.ButtonType
import scalafx.scene.image.{Image, ImageView}

/**
 * @constructor Creates a Reminder Overview with a Table and several labels
 *
 * @param reminderTable     - The table view containing the Reminders
 * @param reminderColumn    - Column of the table view
 * @param titleLabel        - Label for the 'Title' field
 * @param notesLabel        - Label for the 'Notes' field
 * @param reminderTypeLabel - Label for the 'Reminde Type' field
 * @param dateLabel         - Label for the 'Date' field
 * @param locationLabel     - Label for the 'Location' field
 */

@sfxml
class ReminderOverviewController(
        private val reminderTable : TableView[Reminder],
        private val reminderColumn : TableColumn[Reminder, String],
        private val titleLabel:Label,
        private val notesLabel:Label,
        private val reminderTypeLabel: Label,
        private val dateLabel:Label,
        private val locationLabel:Label
    )
{
    // Initialize Table View display contents model
    reminderTable.items = Main.reminderData

    // Intialize Column's cell vlaues
    reminderColumn.cellValueFactory = {_.value.title}

   /**
    * Private Method to retrieve the variable's values and display them
    * Uses match case and bindings
    *
    * @param
    */

    /**
    * Function to delete reminder when the use clicks the 'Delete' button
    *
    * @param reminder - Takes an Option of class Reminder as a parameter
    *
    * @example When user clicks on a Reminder Entry, the entry is passed
    *          to the method
    */

    private def showReminderDetails(reminder: Option[Reminder]) = {
        reminder match
        {
            // Fill the labels with info from the reminder object
            case Some(reminder) =>
            titleLabel.text <== reminder.title
            notesLabel.text <== reminder.notes
            reminderTypeLabel.text <== reminder.reminderType
            // Equals is used as date cannot be bound to text
            dateLabel.text = reminder.date.value.asString
            locationLabel.text <== reminder.location

            case None =>
            // If Reminder is null, remove all the text
            titleLabel.text.unbind()
            notesLabel.text.unbind()
            reminderTypeLabel.text.unbind()
            locationLabel.text.unbind()

            titleLabel.text = ""
            notesLabel.text = ""
            reminderTypeLabel.text = ""
            dateLabel.text = ""
            locationLabel.text = ""
        }
    }

    showReminderDetails(None);

    reminderTable.selectionModel().selectedItem.onChange(
        (_, _, newValue) => showReminderDetails(Option(newValue))
    )

    /**
    * Function to delete reminder when the use clicks the 'Delete' button
    *
    * @param action - Takes in an action as parameter
    *
    * @example handleDeleteReminder takes in onClick as an ActionEvent
    */

    def handleDeleteReminder(action: ActionEvent) = {
        val selectedIndex = reminderTable.selectionModel().selectedIndex.value
        if (selectedIndex >= 0)
        {
            reminderTable.items().remove(selectedIndex);
        }

        else
        {
            new Alert(AlertType.Warning)
            {
                initOwner(Main.stage)
                    title = "No Selection"
                    headerText = "No Reminder Selected"
                    contentText = "Please select a reminder in the table."
            }.showAndWait()
        }
    }


    /**
    * Function to create new reminder when the use clicks the 'New' button
    *
    * @param action - Takes in an action as parameter
    *
    * @example handleNewReminder takes in onClick as an ActionEvent
    *
    * @return Unit
    */

    def handleNewReminder(action: ActionEvent) = {
        var item : Reminder = null
        val ButtonTypeOne = new ButtonType("Event")
        val ButtonTypeTwo = new ButtonType("Task")

        val alert = new Alert(AlertType.Confirmation) {
        initOwner(Main.stage)
        title = "Reminder Type Selection"
        headerText = "Please Select A Type of Reminder."
        buttonTypes = Seq(
            ButtonTypeOne, ButtonTypeTwo, ButtonType.Cancel)
        }

        val result = alert.showAndWait()

        result match {
        case Some(ButtonTypeOne)   =>
            item = new Event("", "", "", 1, 1, 2020)

            val okClicked = Main.toggleReminderEditDialog(item, "Add New Reminder");
            if (okClicked)
            {
                Main.reminderData += item
            }

        case Some(ButtonTypeTwo)   =>
            item = new Task("", "", "", 1, 1, 2020)

            val okClicked = Main.toggleReminderEditDialog(item, "Add New Reminder");
            if (okClicked)
            {
                Main.reminderData += item
            }

        // Prints an output if the user clicks 'Cancel'
        case _ => println("User Pressed Cancelled in Reminder Type Selection.")
        }
    }

    /**
    * Function to allow user to edit reminder when the use clicks the 'Edit'
    * button
    *
    * @param action - Takes in an action as parameter
    *
    * @example handleEditReminder takes in onClick as an ActionEvent
    */

    def handleEditReminder(action:ActionEvent) = {
        val selectedReminder = reminderTable.selectionModel().selectedItem.value
        if (selectedReminder != null)
        {
            val okClicked = Main.toggleReminderEditDialog(selectedReminder, "Edit Reminder")

            if (okClicked) showReminderDetails(Some(selectedReminder))
        }

        else
        {
            val alert = new Alert(Alert.AlertType.Warning)
            {
                initOwner(Main.stage)
                title = "No Selection"
                headerText = "No Reminder Selected"
                contentText = "Please select a reminder in the table."
            }.showAndWait()
        }
    }
}
