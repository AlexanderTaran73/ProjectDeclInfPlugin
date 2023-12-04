import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DirInfTask : DefaultTask(){

    @TaskAction
    fun action() {
        val extension = project.extensions.run {
            findByName("dirinf") as DirInfExtention
        }

        val startDir = File(extension.dir)
        val declInfPlugin = DeclInf()
        val file = declInfPlugin.getClassAndMethodsDecInf(startDir)
        file.createNewFile()
    }
}