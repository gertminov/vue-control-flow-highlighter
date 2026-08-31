// VueTemplateHighlighter.kt
package org.example.templatehighlighter

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlTag
import com.intellij.psi.xml.XmlTokenType

class VueTemplateHighlighter : Annotator {
    companion object {
        val TEMPLATE_TAG_KEY =
            TextAttributesKey.createTextAttributesKey(
                "VUE_TEMPLATE_TAG",
                DefaultLanguageHighlighterColors.KEYWORD,
            )
    }

    override fun annotate(
        element: PsiElement,
        holder: AnnotationHolder,
    ) {
        if (element is XmlTag && element.name == "template") {
            // dont highlight the top level template tag
            if (element.parentTag == null) {
                return
            }

            // only highlight tags with v- directives
            if (!element.attributes.any { it.descriptor?.name?.startsWith("v-") == true }) {
                return
            }

            // Highlight the opening tag
            val startTag = element.firstChild ?: return
            val startRange =
                TextRange(
                    startTag.textRange.startOffset,
                    startTag.textRange.startOffset + "<template".length,
                )
            holder
                .newAnnotation(HighlightSeverity.INFORMATION, "Control flow Tag")
                .range(startRange)
                .textAttributes(TEMPLATE_TAG_KEY)
                .create()

            // Highlight the closing tag if it exists
            val closingTagStart =
                element.node.getChildren(null)
                    .firstOrNull { it.elementType == XmlTokenType.XML_END_TAG_START }
                    ?: return
            val closeRange =
                TextRange(
                    closingTagStart.startOffset,
                    closingTagStart.startOffset + "</template>".length,
                )
            holder
                .newAnnotation(HighlightSeverity.INFORMATION, "Control flow Tag")
                .range(closeRange)
                .textAttributes(TEMPLATE_TAG_KEY)
                .create()
        }
    }
}
