import com.google.common.annotations.VisibleForTesting;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/07 12:45<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class MybatisSpringTest {

    @Test
    public void testQuery() {

    }

    @Test
    public void mybatisGeneratorTest() throws Exception {
//        List<String> warnings = new ArrayList<>();
//        boolean overwrite = true;
//        String config = "generatorConfig.xml";
//        File configFile = new File(getClass().getClassLoader().getResource(config).getFile());
//        ConfigurationParser parser = new ConfigurationParser(warnings);
//        Configuration configuration = null;
//        configuration = parser.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator generator = null;
//        generator = new MyBatisGenerator(configuration, callback, warnings);
        //generator.generate(null);
    }
}
