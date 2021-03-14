# j8draw

JFX 2D and 3D Drawing in JDK8.

Since it uses JFX you need to make sure you are using Oracle's JDK 1.8 or newer.

If running in Eclipse, since JFX is not accessible by default even when using Oracle JDK, 
you will need to follow these steps so eclipse won't freak out:

(Courtesy of this [Stackoverflow post](https://stackoverflow.com/questions/22812488/using-javafx-in-jre-8-access-restriction-error)...)

* Right-click on the project and bring up the project properties dialog. 
* Select "Build Path" in the left pane, and select the "Libraries" tab. You will see a "JRE System Library" entry. 
* Expand that entry, and you will see an "Access Rules" subentry.
* Select the "Access Rules" entry and click "Edit". Click "Add".
* Under "Resolution", choose "Accessible", and under "Rule Pattern", enter javafx/**.
* Click OK to exit all the dialogs.

This should suppress all JFX related warnings in Eclipse.

## How to Run

* Right click on src/rico/RenderTree.java and run as Java Application.

