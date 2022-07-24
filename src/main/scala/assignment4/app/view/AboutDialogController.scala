package assignment4.app.view

import assignment4.app.Main
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.event.ActionEvent

/**
 * Controller class for the About Dialog
 *
 * @return A closing action onto the About Dialog
 */

@sfxml
class AboutDialogController()
{
    var dialogStage: Stage = null
    var okClicked = false

    def handleOk(action : ActionEvent)
    {
        dialogStage.close()
    }
}
