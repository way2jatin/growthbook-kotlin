package com.comllc.growthbook.model

import com.comllc.growthbook.GrowthBookSDK

/*
Defines the GrowthBook context.
 */
class GBContext<T>(
    /// Switch to globally disable all experiments. Default true.
    val enabled : Boolean = true,
    /// Map of user attributes that are used to assign variations
    val attributes: HashMap<String, Any>,
    val url : String,
    /// Keys are unique identifiers for the features and the values are Feature objects.
    /// Feature definitions - To be pulled from API / Cache
    val features: HashMap<String, GBFeature<T>>,
    /// Keys are experiment trackingKeys and the value is an ExperimentOverride object
    /// Experiment overrides - To be pulled from API / Cache
    val overrides: HashMap<String, GBExperimentOverride>,
    ///  Force specific experiments to always assign a specific variation (used for QA)
    val forcedVariations: HashMap<String, Int>,
    ///  If true, random assignment is disabled and only explicitly forced variations are used.
    val qaMode : Boolean = false,
    /// A function that takes experiment and result as arguments.
    val trackingCallback : (GBExperiment<T>, GBExperimentResult<T>) -> Unit
)

interface GBSDKDelegate<T> {
    fun trackExperiment(experiment : GBExperiment<T>, result : GBExperimentResult<T>)
}

/*
    SDKBuilder - to pass APIKey, HostURL, UserAttributes, QAMode, Enabled
 */
class GBSDKBuilder<T>(
    val apiKey: String,
    val hostURL: String,
    val attributes: HashMap<String, Any>,
    val delegate: GBSDKDelegate<T>
){

    var qaMode: Boolean = false;
    var enabled: Boolean = true;

    fun setQAMode(isEnabled : Boolean) : GBSDKBuilder<T> {
        this.qaMode = isEnabled
        return this
    }

    fun setEnabled(isEnabled : Boolean) : GBSDKBuilder<T> {
        this.enabled = isEnabled
        return this
    }

    fun initialize() : GrowthBookSDK<T>{

        val sdkInstance = GrowthBookSDK<T>()

    }
}