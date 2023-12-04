import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

open class DirInfPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val extension = project.extensions.run {
            create("dirinf", DirInfExtention::class.java)
        }


        with(project.tasks) {
            create("dirinf", DirInfTask::class.java) {
                it.group = "DirInformation"
                it.description = "Get the amount of class, methods and lines"


            }
        }
    }
}