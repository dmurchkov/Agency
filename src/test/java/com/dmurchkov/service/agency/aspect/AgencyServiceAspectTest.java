package com.dmurchkov.service.agency.aspect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgencyServiceAspectTest {

    @Autowired
    MockMvc mockMvc;

    private OutputStream os = null;

    private final long mockedAuthorId = 15L;
    private final long mockedApartmentId = 25L;
    private final String mockedDescription = "description";

    @Before
    public void setUpStream() {
        os = new ByteArrayOutputStream();
        setOut(new PrintStream(os));
    }

    @After
    public void restoreStream() throws IOException {
        setOut(out);
        os.close();
    }

    @Test
    public void shouldLogStartParameters() throws Exception {
        String startParametersLog = format("Method submitAdd run with parameters: [%s, %s, %s]",
                mockedAuthorId, mockedApartmentId, mockedDescription);

        mockMvc.perform(post("/agency/ad")
                .param("authorId", valueOf(mockedAuthorId))
                .param("apartmentId", valueOf(mockedApartmentId))
                .param("description", mockedDescription));

        assertThat(os.toString()).containsIgnoringCase(startParametersLog);
    }

    @Test
    public void shouldLogExceptions() throws Exception {
        String exceptionLog =
                format("Method submitAdd threw: com.dmurchkov.service.agency.exception.NoSuchEntityException:" +
                        " Author id: %s", mockedAuthorId);

        mockMvc.perform(post("/agency/ad")
                .param("authorId", valueOf(mockedAuthorId))
                .param("apartmentId", valueOf(mockedApartmentId))
                .param("description", mockedDescription));

        assertThat(os.toString()).containsIgnoringCase(exceptionLog);
    }

    @Test
    public void shouldLogReturnedValue() throws Exception {
        String name = "name";
        String email = "email";
        String phone = "phone";
        String returnedValueLog = "Method createAuthor returned value: 0";

        mockMvc.perform(post("/agency/author")
                .param("name", name)
                .param("email", email)
                .param("phone", phone));

        assertThat(os.toString()).containsIgnoringCase(returnedValueLog);
    }
}