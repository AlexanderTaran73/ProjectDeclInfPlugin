import com.google.gson.Gson
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.File
import org.antlr.v4.runtime.tree.ParseTreeWalker
import gen.*
import genJava.*

class DeclInf {
    fun getClassAndMethodsDecInf(startDir: File): File{
        val classAndMethodsDec = ClassAndMethodsDec()
        startDir.walk().forEach {
            if (it.extension == "kt") {
                val text = CharStreams.fromString(it.readText())
                val lexer = KotlinLexer(text)
                val tokens = CommonTokenStream(lexer)
                val walker = ParseTreeWalker()
                val parser = KotlinParser(tokens)
                val listener = KotlinListener(classAndMethodsDec)
                walker.walk(listener, parser.kotlinFile())
                classAndMethodsDec.totalLines += it.readLines().size
            }
            if (it.extension == "java"){
                val text = CharStreams.fromString(it.readText())
                val lexer = JavaLexer(text)
                val tokens = CommonTokenStream(lexer)
                val walker = ParseTreeWalker()
                val parser = JavaParser(tokens)
                val listener = JavaListener(classAndMethodsDec)
                walker.walk(listener, parser.compilationUnit())
                classAndMethodsDec.totalLines += it.readLines().size
            }
        }
        val gson = Gson()
        val dataMap = mapOf<String, Int>(Pair("totalClasses", classAndMethodsDec.totalClasses), Pair("totalMethods", classAndMethodsDec.totalMethods), Pair("totalLines", classAndMethodsDec.totalLines))

        val file = File("ClassAndMethodsDecInf.json")
        file.writeText(gson.toJson(dataMap))
//        file.createNewFile()
//        println(gson.toJson(dataMap))
        return file
    }
}

class KotlinListener(val classAndMethodsDec: ClassAndMethodsDec): KotlinParserBaseListener() {
    override fun enterEveryRule(ctx: ParserRuleContext?) {
        if (ctx is KotlinParser.ClassDeclarationContext || ctx is KotlinParser.ObjectDeclarationContext) classAndMethodsDec.totalClasses++
        if (ctx is KotlinParser.FunctionDeclarationContext) classAndMethodsDec.totalMethods++
    }
}

class JavaListener(val classAndMethodsDec: ClassAndMethodsDec): JavaParserBaseListener() {
    override fun enterEveryRule(ctx: ParserRuleContext?) {
        if (ctx is JavaParser.ClassDeclarationContext) classAndMethodsDec.totalClasses++
        if (ctx is JavaParser.MethodDeclarationContext) classAndMethodsDec.totalMethods++
    }
}

class ClassAndMethodsDec {
    var totalClasses = 0
    var totalMethods = 0
    var totalLines = 0
}

