package expert.os.examples.mapping;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {


    private Mapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ReflectionMapper();
    }

    @Test
    public void shouldConvertToMap() {
        Pet ada = Pet.of("Ada", 8);
        Map<String, Object> map = mapper.toMap(ada);

        Assertions.assertThat(map)
                .isNotNull()
                .isNotEmpty()
                .containsKeys("_entity", "name", "age")
                .containsEntry("name", "Ada")
                .containsEntry("age", 8)
                .containsEntry("_entity", Pet.class.getName());

    }

    @Test
    public void shouldConvertEntity() {
        Map<String, Object> map = Map.of("_entity", Pet.class.getName()
        , "name", "Ada", "age", 8);

        Pet pet = mapper.toEntity(map);

        Assertions.assertThat(pet).isNotNull()
                .isInstanceOf(Pet.class)
                .matches(p -> p.name().equals("Ada"))
                .matches(p -> p.age() == 8);
    }
}