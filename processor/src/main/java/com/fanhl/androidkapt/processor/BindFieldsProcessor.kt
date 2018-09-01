package com.fanhl.androidkapt.processor

import com.fanhl.androidkapt.annotations.BindField
import com.google.auto.service.AutoService
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class) // For registering the service
//@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedSourceVersion(SourceVersion.RELEASE_7) // to support Java 8
@SupportedOptions(BindFieldsProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class BindFieldsProcessor : AbstractProcessor() {
    override fun process(set: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
//        roundEnv?.getElementsAnnotatedWith(BindField::class.java)?.forEach { methodElement ->
//            if (methodElement.kind != ElementKind.METHOD) {
//                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can only be applied to functions,  element: $methodElement ")
//                return false
//            }
//            val generatedSourcesRoot: String = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
//            if (generatedSourcesRoot.isEmpty()) {
//                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can't find the target directory for generated Kotlin files.")
//                return false
//            }
//        }

        createGeneratedClass(roundEnv)

        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(BindField::class.java.canonicalName)
    }

    private fun createGeneratedClass(roundEnv: RoundEnvironment?) {
        //last
        val builder = StringBuilder()
                .append("package com.fanhl.androidkapt.processor.generated;\n\n")
                .append("public class GeneratedClass {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"")


        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        roundEnv?.getElementsAnnotatedWith(BindField::class.java)?.forEach { element ->
            val objectType = element.getSimpleName().toString()


            // this is appending to the return statement
            builder.append(objectType).append(" says hello!\\n")
        }


        builder.append("\"; \n ") // end return
                .append("\t}\n") // close method
                .append("}\n") // close class


        try { // write the file
            val source = processingEnv.filer.createSourceFile("com.fanhl.androidkapt.processor.generated.GeneratedClass")


            val writer = source.openWriter()
            writer.write(builder.toString())
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
