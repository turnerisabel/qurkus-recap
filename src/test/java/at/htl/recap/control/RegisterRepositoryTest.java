package at.htl.recap.control;

import at.htl.recap.entity.Register;
import at.htl.recap.entity.Vehicle;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class RegisterRepositoryTest {

    @Inject
    RegisterRepository registerRepository;

    @Inject
    AgroalDataSource ds;


    @Test
    void t010_simpleInsert_Ok() {
        Register register = new Register();
        Vehicle opel = new Vehicle("Opel", "Kadett", 1974);
        Register bugsBunny = new Register("Bugs Bunny", "LL-123ABC", opel);

        registerRepository.persist(bugsBunny);

        Table registerTable = new Table(ds, "V_REGISTER");
        output(registerTable).toConsole();

        assertThat(registerTable)
                .row()
                .column("R_ID");
    }
}
