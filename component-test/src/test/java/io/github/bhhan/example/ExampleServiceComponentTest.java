package io.github.bhhan.example;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by hbh5274@gmail.com on 2021-04-28
 * Github : http://github.com/bhhan5274
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class ExampleServiceComponentTest {
}
