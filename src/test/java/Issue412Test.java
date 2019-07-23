import org.asciidoctor.demos.AsciidoctorRunnerMavenIssue412;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class Issue412Test {

    // ./gradlew clean test --no-daemon
    @Test
    public void should_not_fail() {
        // given
        System.setProperty("file.encoding", "UTF8");

        // when
        AsciidoctorRunnerMavenIssue412 runner = new AsciidoctorRunnerMavenIssue412();
        runner.main(null);

        // then
        assertThat(new File("build", "sample.html")).exists();
    }

}
