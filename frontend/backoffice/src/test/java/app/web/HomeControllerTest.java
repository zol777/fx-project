package app.web;

import core.framework.http.ContentType;
import core.framework.web.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * @author ericchung
 */
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {
    @Mock
    WebPages webpage;
    private HomeController controller;

    @BeforeEach
    void createController() {
        controller = new HomeController(webpage);
    }

    @Test
    void contentType() {
        Response response = controller.execute(null);
        assertThat(response.contentType()).get().isEqualTo(ContentType.TEXT_HTML);
    }

    @Test
    void indexPage() {
        verify(webpage).get(argThat("/index.html"::equals));
    }
}
