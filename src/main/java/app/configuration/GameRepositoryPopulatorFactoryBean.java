package app.configuration;

import app.entities.Game;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.AbstractRepositoryPopulatorFactoryBean;
import org.springframework.data.repository.init.ResourceReader;

import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class GameRepositoryPopulatorFactoryBean extends AbstractRepositoryPopulatorFactoryBean {
    @Override
    protected ResourceReader getResourceReader() {
        return new GameResourceReader();
    }
}

class GameResourceReader implements ResourceReader {

    @Override
    public Object readFrom(Resource resource, ClassLoader classLoader) throws Exception {
        InputStreamReader stream = new InputStreamReader(resource.getInputStream());
        CSVParser csvParser = new CSVParser(stream, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        return csvParser.getRecords().stream()
                .map(record ->
                {
                    List<String> mechanics = asList(record.get(CSVColumn.mechanic).split(", "));

                    return Game.builder()
                            .rank(Long.parseLong(record.get(CSVColumn.rank)))
                            .bggUrl(record.get(CSVColumn.bgg_url))
                            .name(record.get(CSVColumn.names))
                            .mechanics(mechanics)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
