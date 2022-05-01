package org.Revature;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    App app = new App();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void topMenu() {
        InputStream inBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        app.topMenu();

        System.setIn(inBackup);
    }

    @org.junit.jupiter.api.Test
    void accountLogin() {
    }

    @org.junit.jupiter.api.Test
    void customerMenu() {
    }

    @org.junit.jupiter.api.Test
    void signUpMenu() {
    }

    @org.junit.jupiter.api.Test
    void employeeMenu() {
    }

    @org.junit.jupiter.api.Test
    void adminMenu() {
    }
}
