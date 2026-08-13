package api;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.extensions.TimingExtensions;

@ExtendWith(TimingExtensions.class)
public class BaseTest {
    protected SoftAssertions softly;

    @BeforeEach
    public void setup() {
        this.softly = new SoftAssertions();
    }

    @AfterEach
    public void afterTest() {
        softly.assertAll();
    }
}
