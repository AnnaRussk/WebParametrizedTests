import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class WebParametrizedTests {

    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com");
    }

    @ValueSource(strings = {
            "Selenide", "JUnit 5"
    })


    @ParameterizedTest
    @Tag("BLOCKER")
    @DisplayName("Поисковая выдача отдает информацию о Селениде")
    void successfulSearchTest(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }


}
