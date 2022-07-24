package assignment4.app.view

import assignment4.app.Main
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.event.ActionEvent

/**
 * Cotroller class for the Root Layout
 *
 * @return Boolean  - This is because the handleAbout() method
 *                   returnsa boolean
 */

@sfxml
class RootLayoutController()
{
    var dialogStage: Stage = null
    val okClicked = false

    def handleAboutMenu(action : ActionEvent)
    {
        val okClicked = Main.handleAbout()
    }

}

