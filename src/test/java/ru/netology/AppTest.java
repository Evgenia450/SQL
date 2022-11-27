package ru.netology;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

public class AppTest {

    @Test
    void shouldOpenDashboard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.checkHeading();
    }

    @Test
    void shouldBlockedSystem() {
        Faker faker = new Faker(new Locale("ru"));
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.invalidLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.invalidLogin(authInfo);
        loginPage.systemBlocked();
    }

    @AfterAll
     static void shouldCleanDB() {
        DataHelper.cleanTables();
    }
}