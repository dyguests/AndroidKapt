package com.fanhl.androidkapt.processor

import com.fanhl.androidkapt.annotations.BindField
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class) // For registering the service
//@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedSourceVersion(SourceVersion.RELEASE_7) // to support Java 8
@SupportedOptions(BindFieldsProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class BindFieldsProcessor : AbstractProcessor() {
    override fun process(set: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        roundEnv?.getElementsAnnotatedWith(BindField::class.java)?.forEach { methodElement ->
            if (methodElement.kind != ElementKind.METHOD) {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can only be applied to functions,  element: $methodElement ")
                return false
            }
//            ...
        }

        return true
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
