package projectweek.team22;

import projectweek.team22.model.entity.Wedstrijd;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WedstrijdTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;


    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void givenValidWedstrijd_shouldHaveNoViolations(){
        Wedstrijd leuven = WedstrijdBuilder.aWedstrijdleuven().build();

        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(leuven);

        assertTrue(violations.isEmpty());

    }
    @Test
    public void givenWedstrijdWithEmptyOmschrijving_shouldDetectInvalidOmschrijving() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("");
        slecht.setLocatie("leuven");
        slecht.setAantalToeschouwers(15);


        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("omschrijving.is.missing", violation.getMessage());
        assertEquals("omschrijving", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }
    @Test
    public void givenWedstrijdWithAToLongOmschrijvingLength60_shouldDetectInvalidOmschrijving() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("p".repeat(60));
        slecht.setLocatie("leuven");
        slecht.setAantalToeschouwers(15);


        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("omschrijving.is.to.long", violation.getMessage());
        assertEquals("omschrijving", violation.getPropertyPath().toString());
        assertEquals("p".repeat(60), violation.getInvalidValue());
    }
    @Test
    public void givenWedstrijdWithAToLongOmschrijvingLength51_shouldDetectInvalidOmschrijving() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("p".repeat(51));
        slecht.setLocatie("leuven");
        slecht.setAantalToeschouwers(15);


        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("omschrijving.is.to.long", violation.getMessage());
        assertEquals("omschrijving", violation.getPropertyPath().toString());
        assertEquals("p".repeat(51), violation.getInvalidValue());
    }


    @Test
    public void givenWedstrijdWithEmptyLocation_shouldDetectInvalidLocation() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("aaaaa");
        slecht.setLocatie("");
        slecht.setAantalToeschouwers(15);


        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("location.is.missing", violation.getMessage());
        assertEquals("locatie", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }

    @Test
    public void givenWedstrijdWithNegatiefAantalToeschouwers_shouldDetectInvalidAantalToeschouwers() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("aaaaa");
        slecht.setLocatie("leuven");
        slecht.setAantalToeschouwers(-20);



        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("aantal.moet.positief", violation.getMessage());
        assertEquals("aantalToeschouwers", violation.getPropertyPath().toString());
        assertEquals(-20, violation.getInvalidValue());
    }

    @Test
    public void givenWedstrijdWith0AantalToeschouwers_shouldDetectInvalidAantalToeschouwers() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("aaaaa");
        slecht.setLocatie("leuven");
        slecht.setAantalToeschouwers(0);



        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("aantal.moet.positief", violation.getMessage());
        assertEquals("aantalToeschouwers", violation.getPropertyPath().toString());
        assertEquals(0, violation.getInvalidValue());
    }
    @Test
    public void givenWedstrijdWithnNietGezetteAantalToeschouwers_shouldDetectInvalidAantalToeschouwers() {
        //given
        Wedstrijd slecht = new Wedstrijd();
        slecht.setOmschrijving("aaaaa");
        slecht.setLocatie("leuven");




        //when
        Set<ConstraintViolation<Wedstrijd>> violations = validator.validate(slecht);

        //then
        assertEquals(violations.size(), 1);

        ConstraintViolation<Wedstrijd> violation = violations.iterator().next();
        assertEquals("aantal.is.missing", violation.getMessage());
        assertEquals("aantalToeschouwers", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

}
