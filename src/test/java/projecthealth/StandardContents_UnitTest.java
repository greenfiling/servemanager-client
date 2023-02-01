/**
 * Copyright 2023 Green Filing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package projecthealth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greenfiling.smclient.TestHelper;

public class StandardContents_UnitTest {
  @SuppressWarnings("unused")
  private static final Logger logger = LoggerFactory.getLogger(StandardContents_UnitTest.class);

  @BeforeClass
  public static void setUpClass() {
    TestHelper.loadTestResources();
  }

  // Run the find-copyright-issues.pl script and fail the test if it exits with a non-zero status.
  // Any output from the script will be written to stderr for evaluation on the console
  @Test
  public void testCheckForAccurateCopyright() throws Exception {
    URL script = TestHelper.class.getResource("/bin/find-copyright-issues.pl");
    assertNotNull("failed to find test script in resources", script);

    File scriptFile = new File(script.toURI());
    assertNotNull("failed to get Fil from URL", scriptFile);
    scriptFile.setExecutable(true, true);

    Process process = Runtime.getRuntime().exec(script.getPath());
    assertNotNull("failed to execute script", process);
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    assertNotNull("failed to get buffered reader for script stderr", reader);
    String s;
    while ((s = reader.readLine()) != null) {
      logger.error("copyright errors: {}", s);
    }

    process.waitFor();
    int exitStatus = process.exitValue();

    assertThat("copyright checker failed to run cleanly", exitStatus, equalTo(0));
  }
}
