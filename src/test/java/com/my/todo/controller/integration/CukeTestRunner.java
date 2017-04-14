package com.my.todo.controller.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources" }, tags = {"~@ignore"}, junit = { "--filename-compatible-names" })
public class CukeTestRunner {


}