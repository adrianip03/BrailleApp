package com.example.brailleapp.analyzer

import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer
import androidx.camera.core.ImageAnalysis
import com.example.brailleapp.utils.ImageAnalysisUtils

typealias LumaListener = (luma: Double) -> Unit

class LuminosityAnalyzer (private val listener: LumaListener) : ImageAnalysis.Analyzer {
    private var lastAnalysisTime = 0L
    private val analysisInterval = 500L

    override fun analyze(image: ImageProxy) {
        val currentTime = System.currentTimeMillis()

        // Only analyze if enough time has passed since last analysis
        if (currentTime - lastAnalysisTime < analysisInterval) {
            image.close()
            return
        }

        lastAnalysisTime = currentTime

        val bitmap = ImageAnalysisUtils.imageProxyToBitmap(image)
        val luminosity = ImageAnalysisUtils.calculateLuminosityFromBitmap(bitmap)
        listener(luminosity)

        image.close()
    }
}