package assignment4.app
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import assignment4.app.model._
import assignment4.app.view._
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.{Stage, Modality}
import java.io.File

/**
 *  The main application class
 */

object Main extends JFXApp
{
   val reminderData = new ObservableBuffer[Reminder]()

   reminderData += new Task ("Meet Client", "Discuss about website", "Shop", 21, 7, 2020)
   reminderData += new Task ("Run Errands", "Pay Electricity Bill", "TNB Office", 21, 7, 2020)
   reminderData += new Event ("Attend Class", "Programming Lecture 11\nOS Fundamentals Lecture", "Zoom", 22, 7, 2020)
   reminderData += new Event ("Do Assignment", "Finish up report\nDo Codes for Forward Chaining", "Microsoft Teams", 23, 7, 2020)
   reminderData += new Event ("Lab Test", "AI Scikit Learn Lab Test", "Microsoft Teams", 24, 7, 2020)


   // Changing the path from RootLayout.fxml to URI for resource location
   val rootLayout = getClass.getResource("view/RootLayout.fxml")

   // Initializing the loader object
   val loader = new FXMLLoader(rootLayout, NoDependencyResolver)

   // Loading the root layout from fxml file
   loader.load();

   // Retrieve the root component BorderPane from the FXML
   val roots = loader.getRoot[jfxs.layout.BorderPane]

   // Initialization
    stage = new PrimaryStage{
        title = "ReminderApp"
        icons += new Image(getClass.getResourceAsStream("/images/icon.png"))
        scene = new Scene{
            val file = new File("app.css")
            stylesheets = Array(file.toURI().toURL().toString())
            root = roots
        }
    }

  /**
   * Function to trigger the Reminder Overview FXML
   *
   * @return jfxs loader
   *

   */

    def toggleReminderOverview() = {
        val resource = getClass.getResource("view/ReminderOverview.fxml")
        val loader = new FXMLLoader(resource, NoDependencyResolver)
        loader.load();

        val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
        this.roots.setCenter(roots2)
    }

  /**
   * Function to trigger the Edit Reminder FXML
   *
   * @param reminder
   * @param windowTitle
   *
   * @return okClicked Boolean
   */

    def toggleReminderEditDialog(reminder : Reminder, windowTitle:String):Boolean ={
        val resource = getClass.getResourceAsStream("view/ReminderDialog.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots3 = loader.getRoot[jfxs.Parent]
        val control = loader.getController[ReminderDialogController#Controller]

        val dialog = new Stage(){
            title = windowTitle
            icons += new Image(getClass.getResourceAsStream("/images/icon.png"))
            initModality(Modality.APPLICATION_MODAL)
            initOwner(stage)
            scene = new Scene{
                val file = new File("app.css")
                stylesheets = Array(file.toURI().toURL().toString())
                root = roots3
            }
        }

        control.dialogStage = dialog
        control.reminder = reminder
        dialog.showAndWait()
        control.okClicked
    }

  /**
   * Function to invoke the About FXML
   *
   * @return okClicked Boolean
   */

  def handleAbout(): Boolean ={
        val resource = getClass.getResourceAsStream("view/About.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource);
        val roots3 = loader.getRoot[jfxs.Parent]
        val control = loader.getController[AboutDialogController#Controller]

        val dialog = new Stage(){
            title = "About Reminder App"
            icons += new Image(getClass.getResourceAsStream("/images/icon.png"))
            initModality(Modality.APPLICATION_MODAL)
            initOwner(stage)
            scene = new Scene{
                val file = new File("app.css")
                stylesheets = Array(file.toURI().toURL().toString())
                root = roots3
            }
        }

        control.dialogStage = dialog
        dialog.showAndWait()
        control.okClicked
    }

    toggleReminderOverview()
}