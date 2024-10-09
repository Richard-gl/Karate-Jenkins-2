package bdd;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static bdd.util.ReportUtil.generateCucumberReport;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RunnerTest {

    @Test
    void TestUsers(){

        Results results =
                Runner.path("classpath:bdd/posts").tags("@Register").outputCucumberJson(true)
                        .parallel(2);

        generateCucumberReport(results.getReportDir());
        assertEquals(0, results.getFailCount(), results.getErrorMessages());


    }
}
