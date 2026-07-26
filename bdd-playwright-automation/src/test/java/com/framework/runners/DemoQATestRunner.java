package com.framework.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Runs only the DemoQA-tagged scenarios (src/test/resources/features/web/demoqa-login.feature)
   * against the public https://demoqa.com Book Store demo site.
 */
@Suite
  @SelectClasspathResource("features")
  @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.framework")
  @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@demoqa")
  @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
  public class DemoQATestRunner {
  }
