import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import data.Language;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

public class SelenideParamTest {


  @EnumSource(Language.class)
  @ParameterizedTest
  void selenideSiteShouldDisplayCorrectMessage(Language language) {
    open("https://selenide.org/");
    $$("#languages a").find(text(language.name())).click();
    $("h3").shouldHave(text(language.description));
  }


  static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons() {
    return Stream.of(
        Arguments.of(Language.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
        Arguments.of(Language.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
    );
  }

  @MethodSource
  @ParameterizedTest
  void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
    open("https://selenide.org/");
    $$("#languages a").find(text(language.name())).click();
    $$(".main-menu-pages.a").filter(visible).shouldHave(texts(expectedButtons));
    $("h3").shouldHave(text(language.description));
  }

  public static void main(String[] args) {
    System.out.println(Language.RU.description);
    System.out.println(Language.EN.description);

  }

}
